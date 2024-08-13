package com.timesheet.timesheetproject.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private String token;

    @BeforeEach
    public void initDate() {

        request = UserCreationRequest.builder()
                .username("join")
                .password("12345678")
                .surname("wick")
                .name("join wick")
                .email("join.wick@example.com") // Cần email hợp lệ
                .active(true) // Hoặc false tùy theo yêu cầu của bạn
                .sex("Male") // Ví dụ "Male", "Female", hoặc giá trị hợp lệ khác
                .basicTrainer("Basic Trainer Name") // Có thể bỏ qua nếu không cần thiết
                .startDate(LocalDate.of(2024, 1, 1)) // Ngày bắt đầu
                .salaryAt(LocalDate.of(2024, 12, 31)) // Ngày lương
                .salary(5000.0) // Ví dụ mức lương
                .address("123 Main Street, Hometown") // Địa chỉ
                .phone("123-456-7890") // Số điện thoại
                .levelId(1L) // ID cấp bậc
                .typeUserId(2L) // ID loại người dùng
                .positionId(3L) // ID vị trí
                .branchId(4L) // ID chi nhánh
                .build();
        userResponse = UserResponse.builder()
                .username("join")
                .surname("wick")
                .name("join wick")
                .password("12345678")
                .build();
        token = "";
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN" , "create-user"})
    void createUser_validRequest_success() throws Exception {
        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);
        //When, Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1000)
                );
    }
}
