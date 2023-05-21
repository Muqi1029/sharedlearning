package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author mq
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-11-27 11:08:11
* @Entity com.muqi.backendsl.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




