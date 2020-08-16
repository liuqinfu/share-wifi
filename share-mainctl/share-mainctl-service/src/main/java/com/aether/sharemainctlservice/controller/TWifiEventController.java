package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TWifiEvent;
import com.aether.sharemainctlservice.service.TWifiEventService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)表控制层
 *
 * @author makejava
 * @since 2020-08-16 13:26:20
 */
@RestController
@RequestMapping("event/wifi")
public class TWifiEventController {
    /**
     * 服务对象
     */
    @Resource
    private TWifiEventService tWifiEventService;

    @PostMapping("report")
    public ResultVO reportWifiEvent(@RequestBody @Valid TWifiEvent tWifiEvent){
        tWifiEventService.insert(tWifiEvent);
        return new ResultVO(ResultCode.SUCCESS);
    }


}