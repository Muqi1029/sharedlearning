package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.UserCourse;
import com.muqi.backendsl.service.UserCourseService;
import com.muqi.backendsl.mapper.UserCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【t_user_course(记录用户点击课程次数)】的数据库操作Service实现
* @createDate 2023-03-22 14:28:34
*/
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse>
    implements UserCourseService{

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Override
    public boolean updateClick(Integer userID, Integer courseID) {
        QueryWrapper<UserCourse> userCourseQueryWrapper = new QueryWrapper<>();
        userCourseQueryWrapper.eq("userID", userID).eq("courseID", courseID);

        /** judge whether there exists this userID */
        if (userCourseMapper.selectCount(userCourseQueryWrapper) == 0) {
            UserCourse userCourse = new UserCourse();
            userCourse.setUserID(userID);
            userCourse.setCourseID(courseID);
            userCourse.setClicks(1L);
            int insert = userCourseMapper.insert(userCourse);
            if (insert > 0) {
                return true;
            }
            return false;
        } else {
            UserCourse userCourse = userCourseMapper.selectOne(userCourseQueryWrapper);
            Long clicks = userCourse.getClicks();
            clicks++;
            userCourse.setClicks(clicks);
            int update = userCourseMapper.update(userCourse, userCourseQueryWrapper);
            if (update > 0) {
                return true;
            }
            return false;
        }
    }
}




