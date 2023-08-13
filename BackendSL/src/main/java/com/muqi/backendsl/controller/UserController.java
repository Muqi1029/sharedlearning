package com.muqi.backendsl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.muqi.backendsl.entity.User;
import com.muqi.backendsl.entity.UserCourse;
import com.muqi.backendsl.model.request.UserCourseRequest;
import com.muqi.backendsl.model.request.UserLoginRequest;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.UserCourseService;
import com.muqi.backendsl.service.UserService;
import com.muqi.backendsl.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.muqi.backendsl.constant.UserConstant.ADMIN_ROLE;
import static com.muqi.backendsl.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private UserCourseService userCourseService;



    /**
     * 用户注册接口
     *
     * @param
     * @return userID
     */
    @PostMapping("/register")

    public Long userRegister(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword, String checkPassword) {
        // 检查用户请求体的内容（预处理）
        if (StringUtils.isAnyBlank(loginAccount, loginPassword, checkPassword)) {
            return null;
        }
        // handle the user register
        return userService.userRegister(loginAccount, loginPassword, checkPassword);
    }

    @PostMapping("/login")
    public ResultVO<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // DONE
        String loginAccount = userLoginRequest.getLoginAccount();
        String loginPassword = userLoginRequest.getLoginPassword();
        Integer userAuthority=userLoginRequest.getUserAuthority();


        User user = userService.userLogin(loginAccount, loginPassword, request);



        if (userAuthority == 0) {
            // This is a normal user login
            return ResultVO.ok(user, "user");
        } else if (userAuthority == 1) {
            // This is an administrator login
            return ResultVO.ok(user, "admin");
        }

        if (StringUtils.isAnyBlank(loginAccount, loginPassword)) {
            return null;
        }
//
//        /** handle user login */
        return ResultVO.ok(userService.userLogin(loginAccount, loginPassword, request));
    }

    @GetMapping("/search")
    public List<User> searchUsers(String loginName, HttpServletRequest request) {
        // 仅管理员可查询
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(loginName)) {
            queryWrapper.like("loginName", loginName);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> UserUtil.getSafeUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        // 仅管理员可删除
        if (!isAdmin(request)) return false;
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 判断是否是管理员
     *
     * @param request 请求体
     * @return true: 是  | false: 否
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserAuthority() == ADMIN_ROLE;
    }


    /**
     * record the user click course
     *
     * @param userCourseRequest containing userID and courseID
     * @return succeed or fail
     */
    @PostMapping("/recordsClick")
    public ResultVO<?> recordUserClickCourse(@RequestBody UserCourseRequest userCourseRequest) {
        Integer userID = userCourseRequest.getUserID();
        Integer courseID = userCourseRequest.getCourseID();
        boolean result = userCourseService.updateClick(userID, courseID);
        if (result) {
            return ResultVO.ok();
        }
        return ResultVO.fail();
    }

    @GetMapping("/info/{userID}")
    public ResultVO<?> getUserInfoByID(@PathVariable("userID") Integer userID) {
        return ResultVO.ok(userService.getUserInfoByID(userID));
    }
}
