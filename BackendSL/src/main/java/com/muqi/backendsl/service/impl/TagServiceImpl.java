package com.muqi.backendsl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqi.backendsl.entity.Tag;
import com.muqi.backendsl.service.TagService;
import com.muqi.backendsl.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author mq
* @description 针对表【t_tag(标签表)】的数据库操作Service实现
* @createDate 2023-02-16 22:57:27
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




