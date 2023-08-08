package com.muqi.backendsl.controller;

import com.muqi.backendsl.entity.Link;
import com.muqi.backendsl.model.request.LinkRequest;
import com.muqi.backendsl.model.vo.RecommendLinkVO;
import com.muqi.backendsl.model.vo.ResultVO;
import com.muqi.backendsl.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags ="链接模块")
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @ApiOperation("用户上传链接")
    @PostMapping("/import/official")
    public ResultVO<?> importLink(@RequestBody LinkRequest linkRequest) {
        boolean b = linkService.saveLink(linkRequest);
        if (b) {
            return ResultVO.ok();
        }
        return ResultVO.fail();
    }

    @GetMapping("/recommend/{userID}")
    public ResultVO<RecommendLinkVO> getCommendURL(@PathVariable("userID") Integer userID) {
//        return ResultVO.ok(linkService.getRecommendURL(userID));
        return null;
    }

    @ApiOperation("根据课程ID获取链接")
    @RequestMapping("/{courseID}")
    public ResultVO<List<Link>> listLinkByCourseID(@PathVariable("courseID") Integer courseID) {
        return ResultVO.ok(linkService.listLinkByCourseID(courseID));
    }
}
