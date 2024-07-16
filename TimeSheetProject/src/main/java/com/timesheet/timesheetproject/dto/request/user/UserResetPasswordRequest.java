package com.timesheet.timesheetproject.dto.request.user;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResetPasswordRequest {
    @NotNull(message = "ID_REQUIRED")
    Long id;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
}
