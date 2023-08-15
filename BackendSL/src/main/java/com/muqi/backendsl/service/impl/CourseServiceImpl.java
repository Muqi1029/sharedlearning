package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Course;
import com.muqi.backendsl.entity.UserCourse;
import com.muqi.backendsl.mapper.ArticleMapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.dto.CourseCardDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.service.CourseService;
import com.muqi.backendsl.mapper.CourseMapper;
import com.muqi.backendsl.utils.PageUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
* @author mq
* @description 针对表【t_course(课程类)】的数据库操作Service实现
* @createDate 2023-02-01 15:06:21
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @SneakyThrows
    @Override
    public PageResultDTO<CourseCardDTO> listCourses() {

        /**
         * CompletableFuture.supplyAsync:
         *
         * run the task asynchronously, return the result (type is Long)
         */
        CompletableFuture<Long> asyncCount = CompletableFuture.supplyAsync(() -> courseMapper.selectCount(null));

        List<CourseCardDTO> courses = courseMapper.listCourses(PageUtil.getLimitCurrent(), PageUtil.getSize());

        return new PageResultDTO<>(courses, asyncCount.get().intValue());


    }

    @Override
    public String getCourseIntroByID(int courseID) {
        return courseMapper.getCourseIntroByID(courseID);
    }

    /**  */
    @Override
    public PageResultDTO<CourseCardDTO> listCoursesByTag() {
        return null;
    }



}




