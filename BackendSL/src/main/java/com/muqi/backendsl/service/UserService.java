package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author muqi
* @description 针对表【user】的数据库操作Service
* @createDate 2022-11-27 11:08:11
 *
 * 继承了IService
*/
public interface UserService extends IService<User> {


    /**
     * 处理用户注册
     *
     * @param loginAccount  用户账号
     * @param loginPassword 用户密码
     * @param checkPassword 验证密码
     * @return UserID
     * @author muqi
     */
    long userRegister(String loginAccount, String loginPassword, String checkPassword);

    /**
     * 处理用户登录
     *
     * @param loginAccount  用户账号
     * @param loginPassword 用户密码
     * @param request       带有cookie的Http请求
     * @return 用户
     */
    User userLogin(String loginAccount, String loginPassword, HttpServletRequest request);

    User getUserInfoByID(Integer userID);

}

