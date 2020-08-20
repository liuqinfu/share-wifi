package com.aether.sharemainctlservice.controller;

import com.aether.sharemainctlservice.entity.TFluxMeal;
import com.aether.sharemainctlservice.service.TFluxMealService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 已购买的套餐流量使用情况管理
 * (TFluxMeal)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:30
 */
@RestController
@RequestMapping("tFluxMeal")
public class TFluxMealController {
    /**
     * 服务对象
     */
    @Resource
    private TFluxMealService tFluxMealService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TFluxMeal selectOne(String id) {
        return this.tFluxMealService.queryById(id);
    }

}