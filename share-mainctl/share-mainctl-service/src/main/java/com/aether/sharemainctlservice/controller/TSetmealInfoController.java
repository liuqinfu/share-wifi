package com.aether.sharemainctlservice.controller;

import com.aether.sharemainctlservice.entity.TSetmealInfo;
import com.aether.sharemainctlservice.service.TSetmealInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 流量套餐管理
 * (TSetmealInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:26:43
 */
@RestController
@RequestMapping("tSetmealInfo")
public class TSetmealInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TSetmealInfoService tSetmealInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSetmealInfo selectOne(String id) {
        return this.tSetmealInfoService.queryById(id);
    }

}