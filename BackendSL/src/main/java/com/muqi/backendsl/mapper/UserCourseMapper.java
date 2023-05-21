package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.UserCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author mq
* @description 针对表【t_user_course(记录用户点击课程次数)】的数据库操作Mapper
* @createDate 2023-03-22 14:28:34
* @Entity com.muqi.backendsl.entity.UserCourse
*/
@Repository
public interface UserCourseMapper extends BaseMapper<UserCourse> {

    Integer getMaximumCourseIDByClick(@Param("userID") Integer userID);
}




