package com.muqi.backendsl.controller;

import com.muqi.backendsl.model.dto.ArticleCardDTO;

import com.muqi.backendsl.model.dto.CourseCardDTO;
import com.muqi.backendsl.model.dto.PageResultDTO;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.ArticleService;
import com.muqi.backendsl.service.CourseService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

import java.util.HashMap;


@Api(tags = "课程模块")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ArticleService articleService;

    /**
     *  get all courses
     */
    @ApiOperation("获取所有课程")
    @GetMapping("/all")
    public ResultVO<PageResultDTO<CourseCardDTO>> listCourse() {
        return ResultVO.ok(courseService.listCourses());
    }

    @ApiOperation("list Articles by courseID")
    @GetMapping("/{courseId}")
    public ResultVO<PageResultDTO<ArticleCardDTO>> listArticlesByCourseId(@PathVariable("courseId") Long courseID) {
        //DONE
        return ResultVO.ok(articleService.listArticlesByCourseId(courseID));
    }

    @GetMapping("/getIntroById/{courseID}")
    public ResultVO<String> getCourseIntroByID(@PathVariable("courseID") int courseID) {
        //DONE
        return ResultVO.ok(courseService.getCourseIntroByID(courseID));
    }

    @GetMapping("/tag/{tagID}")
    public ResultVO<PageResultDTO<CourseCardDTO>> listCourseByTag(@PathVariable("tagID") int tagID) {
        // TODO non-finished
        return ResultVO.ok(courseService.listCoursesByTag());
    }


//    @ApiOperation("用户收藏课程")
//    @PostMapping("/saveCourse")
//    public ResultVO<?> userSaveCourse(@RequestBody HashMap<String, Object> hashMap) {
//        //TODO need to be tested
//        int courseID = (int) hashMap.get("courseID");
//        int userID = (int) hashMap.get("userID");
//        if (courseService.userSaveCourse(userID, courseID)) {
//            return ResultVO.ok();
//        }
//        return ResultVO.fail();
//    }



}
