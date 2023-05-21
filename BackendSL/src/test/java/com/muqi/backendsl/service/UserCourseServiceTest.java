package com.muqi.backendsl.service;

import com.muqi.backendsl.mapper.UserCourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCourseServiceTest {

    @Autowired
    UserCourseService userCourseService;

    @Autowired
    UserCourseMapper userCourseMapper;


    @Test
    public void userCourseServiceTest() {
        int userID = 12;
        int courseID = 1;

        boolean b = userCourseService.updateClick(userID, courseID);

        assert b;
    }

    @Test
    public void getMaximumCourseIDByClickTest() {
        int userID = 12;
        Integer maximumCourseIDByClick = userCourseMapper.getMaximumCourseIDByClick(userID);
        assert maximumCourseIDByClick == 3;

    }
}