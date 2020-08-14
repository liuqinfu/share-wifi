package com.aether.sharemainctlservice.controller;

import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.service.TGpsHisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 终端设备位置表(TGpsHis)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
@RestController
@RequestMapping("tGpsHis")
public class TGpsHisController {
    /**
     * 服务对象
     */
    @Resource
    private TGpsHisService tGpsHisService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TGpsHis selectOne( id) {
        return this.tGpsHisService.queryById(id);
    }

}