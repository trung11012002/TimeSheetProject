package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserResetPasswordRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.*;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.mapper.UserMapper;
import com.timesheet.timesheetproject.repository.*;
import com.timesheet.timesheetproject.service.IRoleService;
import com.timesheet.timesheetproject.service.IUserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LevelRepository levelRepostitory;

    @Autowired
    TypeUserRepository typeUserRepostitory;

    @Autowired
    RoleRepository roleRepostitory;

    @Autowired
    PositionRepository positionRepostitory;

    @Autowired
    BranchRepository branchRepostitory;

    @Autowired
    UserRoleRepository userRoleRepostitory;
    @Autowired
    IRoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        if(request.getLevelId() != null){
            Level level = levelRepostitory.findById(request.getLevelId()).orElse(null);
            user.setLevel(level);
        }
        if(request.getTypeUserId() != null){
            TypeUser typeUser = typeUserRepostitory.findById(request.getTypeUserId()).orElse(null);
            user.setTypeUser(typeUser);
        }
        if(request.getPositionId() != null){
            Position position = positionRepostitory.findById(request.getPositionId()).orElse(null);
            user.setPosition(position);
        }
        if(request.getBranchId() != null){
            Branch branch = branchRepostitory.findById(request.getBranchId()).orElse(null);
            user.setBranch(branch);
        }
        user.setAllowLeaveDay(0);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //roleuser
        Role role = roleRepostitory.findByCode("BASICUSER").orElse(null);
        user = userRepository.save(user);
        UserRole userRole = new UserRole(user,role);
        userRoleRepostitory.save(userRole);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserResponse userResponse = userMapper.toUserResponse(user);
            userResponse.setRoles(roleService.getRoleByUserId(user.getId()));
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse getUserById(Long id) {

        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

    }

    @Override
    public UserResponse updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);

        if(request.getLevelId() != null){
            Level level = levelRepostitory.findById(request.getLevelId()).orElse(null);
            user.setLevel(level);
        }
        if(request.getTypeUserId() != null){
            TypeUser typeUser = typeUserRepostitory.findById(request.getTypeUserId()).orElse(null);
            user.setTypeUser(typeUser);
        }
        if(request.getPositionId() != null){
            Position position = positionRepostitory.findById(request.getPositionId()).orElse(null);
            user.setPosition(position);
        }
        if(request.getBranchId() != null){
            Branch branch = branchRepostitory.findById(request.getBranchId()).orElse(null);
            user.setBranch(branch);
        }
        if(request.getBeginLevelId() != null){
            Level beginLevel = levelRepostitory.findById(request.getLevelId()).orElse(null);
            user.setBeginLevel(beginLevel);
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse resetPassword(UserResetPasswordRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getUserByRoleId(Long roleId) {
        return userMapper.toUserResponses(userRepository.findUserByRoleId(roleId));
    }

    @Override
    public Page<UserResponse> searchUsers(String username,Long positionId, Boolean active, Long userTypeId, Long branchId, Long levelId,Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,4);
        Page<User> usersPage = userRepository.searchUsers(username,positionId,active,userTypeId,branchId,levelId,pageable);
        return usersPage.map(userMapper::toUserResponse);
    }

    @Override
    public Page<UserResponse> getPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,2);
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(userMapper::toUserResponse);
    }
}
