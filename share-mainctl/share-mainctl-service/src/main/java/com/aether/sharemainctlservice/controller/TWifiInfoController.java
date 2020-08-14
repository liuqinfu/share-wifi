package com.aether.sharemainctlservice.controller;

import com.aether.sharemainctlservice.entity.TWifiInfo;
import com.aether.sharemainctlservice.service.TWifiInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 热点信息(TWifiInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
@RestController
@RequestMapping("tWifiInfo")
public class TWifiInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TWifiInfoService tWifiInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TWifiInfo selectOne(String id) {
        return this.tWifiInfoService.queryById(id);
    }

}