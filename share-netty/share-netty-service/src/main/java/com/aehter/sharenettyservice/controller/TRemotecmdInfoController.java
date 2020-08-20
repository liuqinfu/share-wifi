package com.aehter.sharenettyservice.controller;

import com.aehter.sharenettyservice.entity.TRemotecmdInfo;
import com.aehter.sharenettyservice.service.TRemotecmdInfoService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * (TRemotecmdInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:57:46
 */
@ApiIgnore
@RestController
@RequestMapping("tRemotecmdInfo")
public class TRemotecmdInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TRemotecmdInfoService tRemotecmdInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRemotecmdInfo selectOne(Integer id) {
        return this.tRemotecmdInfoService.queryById(id);
    }

}