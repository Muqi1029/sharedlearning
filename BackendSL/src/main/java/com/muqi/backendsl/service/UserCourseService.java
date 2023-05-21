package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.UserCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mq
* @description 针对表【t_user_course(记录用户点击课程次数)】的数据库操作Service
* @createDate 2023-03-22 14:28:34
*/
public interface UserCourseService extends IService<UserCourse> {

    /**
     * 更新用户点击课程的次数
     * @param userID 用户ID
     * @param courseID 课程ID
     * @return boolean recording this behavior is successful
     */
    boolean updateClick(Integer userID, Integer courseID);
}
