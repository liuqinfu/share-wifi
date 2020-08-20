package com.aether.sharemainctlservice.controller;

import com.aether.sharemainctlservice.entity.TOrderInfo;
import com.aether.sharemainctlservice.service.TOrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备的套餐订单管理
 * (TOrderInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:53
 */
@RestController
@RequestMapping("tOrderInfo")
public class TOrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderInfoService tOrderInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TOrderInfo selectOne(String id) {
        return this.tOrderInfoService.queryById(id);
    }

}