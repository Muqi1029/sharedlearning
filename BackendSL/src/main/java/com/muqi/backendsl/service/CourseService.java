package com.muqi.backendsl.service;

import cn.hutool.db.Page;
import com.muqi.backendsl.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.CourseCardDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;

/**
* @author mq
* @description 针对表【t_course(课程类)】的数据库操作Service
* @createDate 2023-02-01 15:06:21
*/
public interface CourseService extends IService<Course> {

    PageResultDTO<CourseCardDTO> listCourses();


    String getCourseIntroByID(int courseID);

    PageResultDTO<CourseCardDTO> listCoursesByTag();

//    boolean userSaveCourse(int userID, int courseID);
}
