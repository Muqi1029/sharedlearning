package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author mq
* @description 针对表【t_tag(标签表)】的数据库操作Mapper
* @createDate 2023-02-16 22:57:27
* @Entity com.muqi.backendsl.entity.Tag
*/
@Repository
public interface TagMapper extends BaseMapper<Tag> {

}




