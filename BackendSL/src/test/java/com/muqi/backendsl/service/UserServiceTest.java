package com.muqi.backendsl.service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muqi.backendsl.entity.User;
import com.muqi.backendsl.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


import static com.muqi.backendsl.constant.UserConstant.SALT;

/**
 * 用户服务测试
 *
 * @author mq
 */

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    // 单元测试
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("test");
        user.setLoginAccount("12345678");
        String loginPassword = "12345678";

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + loginPassword).getBytes(StandardCharsets.UTF_8));
        user.setLoginPassword(encryptPassword);

        user.setUserPhone("1875685");
        user.setAvatarURL("");
        user.setGender(0);
        user.setUserAuthority(1);
        user.setUserStatus(1);
        user.setIsDelete(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String loginAccount = "dase";
        String loginPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        // 检查loginPassword.equals(checkPassword)
        Assertions.assertEquals(-1, result);

        // 账户长度小于4位
        loginAccount = "mu";
        result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        // 检查账户重复
        loginAccount = "muqi";
        result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        Assertions.assertEquals(-1, result);

        // 检查密码小于8位
        loginAccount = "dase";
        loginPassword = "123456";
        checkPassword = "123456";
        result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        Assertions.assertEquals(-1, result);

        // 检查特殊字符
        loginAccount = " dase";
        result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        Assertions.assertEquals(-1, result);

        loginAccount = "dasetest";
        loginPassword = "123456789";
        checkPassword = "123456789";
        result = userService.userRegister(loginAccount,loginPassword,checkPassword);
        System.out.println(result);
        Assertions.assertTrue(result > 0);

    }

    @Test
    public void testAutowired() {
        System.out.println(userService);
    }


    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
    }

//    @Test
//    public void testReserveCourseInsert() {
//        List<Integer> courseIds = new ArrayList<>();
//        courseIds.add(1);
//        courseIds.add(2);
//
//        Long userID = 12L;
//
//        boolean b = userService.reserveCourse(courseIds, userID);
//        assert b;
//    }
}