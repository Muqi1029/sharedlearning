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


//    /**
//     *
//     * @param map favList uploaded by users
//     * @param userID userID
//     * @return operating result
//     */
//    @PostMapping("/reserveCourse/{userID}")
//    public ResultVO<?> reserveCourse(@RequestBody HashMap<String, Object> map, @PathVariable("userID") Long userID) {
//        List<Integer> favList = (List<Integer>) map.get("favList");
//        userService.reserveCourse(favList, userID);
//        return ResultVO.fail();
//    }

    /**
     * 用户注册接口
     *
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
        String loginAccount = userLoginRequest.getLoginAccount();
        String loginPassword = userLoginRequest.getLoginPassword();


        /**
         * 1. 用户登录：输入账号和密码
         * 2. 根据账号和密码去查询数据库 同时将userAuthority拿出来，判断这是个普通用户登录还是管理员登录
         * 3. 想一个类似于flag的属性，用ResultVO包装，返回给前端
         * 4. 前端收到flag，根据其用户的权限导航到不同的页面
         * 5. 管理员页面要显示对应待审核的数据 type == 0 # 设计对应的URL供前端使用
         * 6. 返回type==0的文章数据给前端渲染
         */


        if (StringUtils.isAnyBlank(loginAccount, loginPassword)) {
            return null;
        }

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
