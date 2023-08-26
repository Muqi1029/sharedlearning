package com.muqi.backendsl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.muqi.backendsl.entity.User;
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
     * @return userID
     */
    @PostMapping("/register")
    public Long userRegister(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(loginAccount, loginPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(loginAccount, loginPassword, checkPassword);
    }


    @PostMapping("/login")
    public ResultVO<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String loginAccount = userLoginRequest.getLoginAccount();
        String loginPassword = userLoginRequest.getLoginPassword();
        if (StringUtils.isAnyBlank(loginAccount, loginPassword)) {
            return ResultVO.fail();
        }
        User user = userService.userLogin(loginAccount, loginPassword, request);
        int userAuthority;
        if (user == null) {
            return ResultVO.fail();
        } else {
            userAuthority = user.getUserAuthority();
        }

        if (userAuthority == 0) {
            return ResultVO.ok(user, "user");
        }
        return ResultVO.ok(user, "admin");
    }

    @GetMapping("/search")
    public List<User> searchUsers(String loginName, HttpServletRequest request) {
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
