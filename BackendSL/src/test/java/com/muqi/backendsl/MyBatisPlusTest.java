package com.muqi.backendsl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectList;
import com.muqi.backendsl.mapper.UserMapper;
import com.muqi.backendsl.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试选择列表
     */
    @Test
    public void testSelectList() {

        /**
         * 查出list
         */
        List<User> userList = userMapper.selectList(null);

        for (User user: userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试新增功能
     */
    @Test
    public void testInsert() {
        // INSERT INTO user ( loginAccount, loginPassword ) VALUES ( ?, ? )
        User user = new User();
        user.setLoginAccount("987654321");
        user.setLoginPassword("12345678");

        // return 1 if successful, else 0
        int insertResult = userMapper.insert(user);
        System.out.println("insertResult: " + insertResult);
    }

    /**
     * 测试删除功能
     */
    @Test
    public void testDelete() {
        // UPDATE user SET isDelete=1 WHERE userID=? AND isDelete=0

        // 1. 根据用户id删除信息
        int i = userMapper.deleteById(7);
        System.out.println("result: " + i);

        // 2. 根据map集合删除
        Map<String, Object> map = new HashMap<>();
        map.put("UserId", 6);
        map.put("LoginAccount", 987654321);
        // UPDATE user SET isDelete=1 WHERE UserId = ? AND LoginAccount = ? AND isDelete=0
        int i1 = userMapper.deleteByMap(map);
        System.out.println("i1: " + i1);

        // 3. 根据id批量删除语句
        List<Integer> list = Arrays.asList(6, 7);
        // UPDATE user SET isDelete=1 WHERE userID IN ( ? , ? ) AND isDelete=0
        int i2 = userMapper.deleteBatchIds(list);
        System.out.println("i2: " + i2);
    }

    /**
     * 测试修改功能
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setLoginAccount("1111");
        int i = userMapper.updateById(user);
        System.out.println("result: " + i);
    }

    /**
     * 测试查询方法
     */
    @Test
    public void testSelect() {
        // 1. 通过id查询
        // SELECT userID,userName,loginAccount,loginPassword,userPhone,avatarURL,gender,userAuthority,userStatus,isDelete,userEmail,createTime,updateTime FROM user WHERE userID=1 AND isDelete=0
        User user = userMapper.selectById(1L);
        System.out.println(user);

        // 2. 根据多个id查询数据

        // 3. 根据map查询数据
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("UserId", 1L);
        List<User> userList = userMapper.selectByMap(hashMap);
        for (User user1: userList) System.out.println(user1);

        //
    }

    /**
     * 查询条件构造器查找排序
     */
    @Test
    public void testQuery() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userName", "m")
                .eq("userStatus", 1)
                .isNotNull("loginAccount")
                .orderByDesc("userID");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user: list) System.out.println(user);


    }

    /**
     * 条件构造器删除
     */

    @Test
    public void testQueryDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("userPhone");
        userMapper.delete(queryWrapper);
    }

    /**
     * 修改
     */
    @Test
    public void testQueryUpdate() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("userID", 3)
                .like("userName", 'm')
                .or();
        User user = new User();
        user.setUserName("muqi");
        userMapper.update(user, queryWrapper);
    }


    @Test
    public void testQueryBoolean() {
        String userName = "muqi";
        Integer ageBegin = null;
        Integer ageEnd = null;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(userName),"userName", userName)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
    }

    /**
     * LambdaQueryWrapper
     *
     * 防止column写错，可以使用函数式接口来指定对应的column
     *
     */
    @Test
    public void testLambdaQueryWrapper() {
        String userName = "muqi";
        Integer ageBegin = null;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userLambdaQueryWrapper.like(StringUtils.isNotBlank(userName), User::getUserName, userName)
                .ge(ageBegin != null, User::getAvatarURL, ageBegin)
                .le(ageEnd != null, User::getAvatarURL, ageEnd);
    }


    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("userName")
                .ge("loginAccount",10000);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void testQueryOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", "muqi");
        System.out.println(userMapper.selectOne(wrapper));
    }



}
