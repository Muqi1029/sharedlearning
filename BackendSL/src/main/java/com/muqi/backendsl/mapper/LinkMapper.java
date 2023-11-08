package com.muqi.backendsl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muqi.backendsl.entity.Link;
import com.muqi.backendsl.model.dto.LinkDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author mq
* @description 针对表【t_link】的数据库操作Mapper
* @createDate 2023-11-03 15:27:30
* @Entity com.muqi.backendsl.generator.Link
*/
@Repository
public interface LinkMapper extends BaseMapper<Link> {

    List<LinkDTO> getPendingLink();
}




