package com.aether.sharesdiservice.controller;

import com.aether.sharesdiservice.entity.TRemotecmdInfo;
import com.aether.sharesdiservice.service.TRemotecmdInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * (TRemotecmdInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-18 17:47:23
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