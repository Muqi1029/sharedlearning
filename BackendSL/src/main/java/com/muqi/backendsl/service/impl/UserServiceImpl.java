package com.muqi.backendsl.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.User;
import com.muqi.backendsl.mapper.UserMapper;
import com.muqi.backendsl.service.UserService;
import com.muqi.backendsl.utils.TokenUtil;
import com.muqi.backendsl.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.muqi.backendsl.constant.UserConstant.SALT;
import static com.muqi.backendsl.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author muqi
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-11-27 11:08:11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * @param loginAccount  用户账号
     * @param loginPassword 用户密码
     * @param checkPassword 验证密码
     * @return 注册成功返回 用户id
     * 注册失败则返回 -1
     */
    @Override
    public long userRegister(String loginAccount, String loginPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(loginAccount, loginPassword, checkPassword)) {
            return -1;
        }
        if (loginAccount.length() < 4) {
            return -1;
        }

        if (loginPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        String validPattern = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(loginAccount);
        if (matcher.find()) {
            return -1;
        }

        if (!checkPassword.equals(loginPassword)) {
            return -1;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginAccount", loginAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + loginPassword).getBytes(StandardCharsets.UTF_8));

        User user = new User();
        user.setLoginAccount(loginAccount);
        user.setLoginPassword(encryptPassword);

        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }


    /**
     * @param loginAccount  用户账号
     * @param loginPassword 用户密码
     * @param request       带有cookie的Http请求
     * @return 用户对象
     */
    @Override
    public User userLogin(String loginAccount, String loginPassword, HttpServletRequest request) {

        if (StringUtils.isAnyBlank(loginAccount, loginPassword)) {
            return null;
        }

        if (loginAccount.length() < 4) {
            return null;
        }

        if (loginPassword.length() < 4) {
            return null;
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(loginAccount);
        if (matcher.find()) {
            return null;
        }

        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + loginPassword).getBytes(StandardCharsets.UTF_8));

        // 查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginAccount", loginAccount);
        queryWrapper.eq("loginPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user not found");
            return null;
        }

        String token = TokenUtil.generateToken(user.getUserName());
        user.setToken(token);
        // 用户脱敏
        User safeUser = UserUtil.getSafeUser(user);

        // 记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, token);

        return safeUser;
    }

    @Override
    public User getUserInfoByID(Integer userID) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userID);
        User user = userMapper.selectOne(userQueryWrapper);
        return UserUtil.getSafeUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public boolean changeUser(int uId, int sId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", uId);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            return false;
        }
        user.setIsDelete(sId);
        return userMapper.updateById(user) == 1;
    }

}




