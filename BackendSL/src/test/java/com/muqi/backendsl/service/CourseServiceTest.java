package com.muqi.backendsl.service;

import com.muqi.backendsl.mapper.CourseMapper;
import com.muqi.backendsl.mapper.UserCourseMapper;
import com.muqi.backendsl.model.dto.CourseCardDTO;
import com.muqi.backendsl.utils.PageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    @Test
    public void testQuery() {
        List<CourseCardDTO> courses = courseMapper.listCourses(PageUtil.getLimitCurrent(), PageUtil.getSize());

        System.out.println(courses);
    }


//    @Test
//    public void testInsertUserCourse() {
//        courseService.userSaveCourse(2,1);
//    }
}