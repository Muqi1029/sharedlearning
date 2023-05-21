package com.muqi.backendsl.service;

import com.muqi.backendsl.entity.Tag;
import com.muqi.backendsl.mapper.TagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagServiceTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void insertTagTest() {
        int courseID = 1;
        String[] tags = {
                "矩阵分解",
                "python求解数学",
                "凸优化",
                "信息论"
        };
//        for (int i = 0; i < tags.length; i++) {
//            Tag tag = new Tag();
//            tag.setTag_name(tags[i]);
//            tag.setCourseID(courseID);
//            tagMapper.insert(tag);
//        }

        courseID = 2;
        String[] tags2 = {
          "系统编程",
          "计算机系统",
          "汇编",
          "链接",
          "计算机体系结构",
        };

        for (int i = 0; i < tags2.length; i++) {
            Tag tag = new Tag();
            tag.setTag_name(tags2[i]);
            tag.setCourseID(courseID);
            tagMapper.insert(tag);
        }
    }

    @Test
    public void insertProbTest() {
        String[] tags = {
                "概率论",
                "数理统计",
                "贝叶斯估计",
                "参数估计"
        };
        int courseID = 3;

        for (int i = 0; i < tags.length; i++) {
            Tag tag = new Tag();
            tag.setTag_name(tags[i]);
            tag.setCourseID(3);
            tagMapper.insert(tag);
        }
    }
}