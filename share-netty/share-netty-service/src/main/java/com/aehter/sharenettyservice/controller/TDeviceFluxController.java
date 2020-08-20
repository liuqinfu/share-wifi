package com.aehter.sharenettyservice.controller;

import com.aehter.sharenettyservice.entity.TDeviceFlux;
import com.aehter.sharenettyservice.service.TDeviceFluxService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:56:34
 */
@ApiIgnore
@RestController
@RequestMapping("tDeviceFlux")
public class TDeviceFluxController {
    /**
     * 服务对象
     */
    @Resource
    private TDeviceFluxService tDeviceFluxService;


}