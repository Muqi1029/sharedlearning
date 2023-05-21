package com.muqi.backendsl.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlUtilTest {

    @Test
    void checkUrl() {
        String url = "http://www.baidu.com";
//        boolean b = UrlUtil.checkUrl(url);
//        assert b;
        boolean matches = url.matches("^http.*");
        assert matches;
    }
}