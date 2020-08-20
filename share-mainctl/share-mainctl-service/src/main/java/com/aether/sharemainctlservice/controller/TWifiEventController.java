package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TWifiEvent;
import com.aether.sharemainctlservice.service.TWifiEventService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)表控制层
 *
 * @author makejava
 * @since 2020-08-16 13:26:20
 */
@Api(value = "event", tags = "热点事件管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("event/wifi")
public class TWifiEventController {
    /**
     * 服务对象
     */
    @Resource
    private TWifiEventService tWifiEventService;

    /**
     * 终端上报热点操作事件
     *
     * @return 单条数据
     */
    @ApiOperation(value = "设备连接或断开热点时上报", notes = "设备连接或断开热点时上报")
    @PostMapping("report")
    public ResultVO reportWifiEvent(@RequestBody @Valid TWifiEvent tWifiEvent){
        tWifiEventService.insert(tWifiEvent);
        return new ResultVO(ResultCode.SUCCESS);
    }


}