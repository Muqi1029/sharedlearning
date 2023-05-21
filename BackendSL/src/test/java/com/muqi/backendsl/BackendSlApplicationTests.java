package com.muqi.backendsl;

import com.muqi.backendsl.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BackendSlApplicationTests {

    @Resource
    private UserMapper userMapper;


}
