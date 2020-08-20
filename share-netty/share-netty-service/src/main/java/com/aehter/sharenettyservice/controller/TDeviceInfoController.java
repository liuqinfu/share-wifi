package com.aehter.sharenettyservice.controller;

import com.aehter.sharenettyservice.entity.TDeviceInfo;
import com.aehter.sharenettyservice.service.TDeviceInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 终端设备信息(TDeviceInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:50:14
 */
@ApiIgnore
@RestController
@RequestMapping("tDeviceInfo")
public class TDeviceInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TDeviceInfoService tDeviceInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TDeviceInfo selectOne(String id) {
        return this.tDeviceInfoService.queryById(id);
    }

}