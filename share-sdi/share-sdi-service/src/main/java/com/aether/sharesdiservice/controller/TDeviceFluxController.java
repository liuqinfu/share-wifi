package com.aether.sharesdiservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharesdiservice.entity.TDeviceFlux;
import com.aether.sharesdiservice.service.TDeviceFluxService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表控制层
 *
 * @author makejava
 * @since 2020-08-16 12:37:29
 */
@RestController
@RequestMapping("sdi/flux")
public class TDeviceFluxController {
    /**
     * 服务对象
     */
    @Resource
    private TDeviceFluxService tDeviceFluxService;

    /**
     * 查询流量使用
     * @param unit  单位 年 year 月 month
     * @param deviceId  设备唯一标识
     * @return
     */
    @GetMapping("group/{unit}/{deviceId}")
    public ResultVO<List<Map>> groupByUnit(@PathVariable("unit") String unit,@PathVariable("deviceId")String deviceId) {
        Map params = new HashMap();
        params.put("unit",unit);
        if (StringUtils.isNotEmpty(deviceId))params.put("deviceId",deviceId);
        List<Map> mapLists = this.tDeviceFluxService.countByParam(params);
        return new ResultVO<>(mapLists);
    }

    @PostMapping("report")
    public ResultVO reportFlux(@RequestBody @Valid TDeviceFlux tDeviceFlux){
        tDeviceFluxService.insert(tDeviceFlux);
        return new ResultVO(ResultCode.SUCCESS);
    }

}