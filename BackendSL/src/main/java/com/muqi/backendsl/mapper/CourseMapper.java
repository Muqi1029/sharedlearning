package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.backendsl.model.dto.ArticleCardDTO;
import com.muqi.backendsl.model.dto.CourseCardDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author mq
* @description 针对表【t_course(课程类)】的数据库操作Mapper
* @createDate 2023-02-01 15:06:21
* @Entity com.muqi.backendsl.entity.Course
*/
@Repository // 代表DTO层
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseCardDTO> listCourses(@Param("current") Long current, @Param("size") Long size);


    String getCourseIntroByID(@Param("courseID") int courseID);
}




