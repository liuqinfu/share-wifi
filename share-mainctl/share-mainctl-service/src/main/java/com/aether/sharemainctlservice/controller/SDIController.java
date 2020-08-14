package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharecommon.utils.RedisUtil;
import com.aether.sharemainctlservice.service.SDIService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 我走路带风
 * @since 2020/8/14 13:17
 */
@Api(value = "sdi", tags = "SDI业务")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功", response = ResultVO.class),
        @ApiResponse(code = 201, message = "操作失败", response = ResultVO.class),
        @ApiResponse(code = 402, message = "输入数据检查不通过", response = ResultVO.class),
        @ApiResponse(code = 500, message = "后台程序异常", response = ResultVO.class)
})
@RestController
@RequestMapping("sdi")
public class SDIController {

    @Autowired
    private SDIService sdiService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "为SDI提供注册服务", notes = "sdi向主控注册服务")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "ip", value = "sdi的外网ip地址或域名", required = true),
            @ApiImplicitParam(paramType = "query", name = "port", value = "sdi的外网端口", required = true)
    })
    @GetMapping("registry")
    public ResultVO registrySDI(@RequestParam("ip") @Valid String sdiIP, @RequestParam("port") @Valid String sdiPort) {
        sdiService.registrySDI(sdiIP, sdiPort);
        return new ResultVO(ResultCode.SUCCESS);
    }

}
