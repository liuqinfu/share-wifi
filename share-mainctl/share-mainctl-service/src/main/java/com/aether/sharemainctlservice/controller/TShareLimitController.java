package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TShareLimit;
import com.aether.sharemainctlservice.service.TShareLimitService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (TShareLimit)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-28 16:30:53
 */
@Api(value = "flux/limit", tags = "流量限制管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("flux/limit")
public class TShareLimitController {
    /**
     * 服务对象
     */
    @Resource
    private TShareLimitService tShareLimitService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TShareLimit selectOne(String id) {
        return this.tShareLimitService.queryById(id);
    }

    @ApiOperation(value = "设置用户分享限制", notes = "用户设置每日流量分享限制")
    @PostMapping("save/{device}/{limit}")
    public ResultVO saveLimits(@PathVariable(value = "device",required = true) @ApiParam(value = "设备唯一标识",required = true) String device,
                               @PathVariable(value = "limit",required = true) @ApiParam(value = "流量限制 单位Kb",required = true) double limit){
        tShareLimitService.save(new TShareLimit().setDeviceId(device).setLimits(limit).setCreateTime(new Date()).setUpdateTime(new Date()));
        return new ResultVO(ResultCode.SUCCESS);
    }

}