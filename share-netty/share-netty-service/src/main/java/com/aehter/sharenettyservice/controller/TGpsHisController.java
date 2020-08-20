package com.aehter.sharenettyservice.controller;

import com.aehter.sharenettyservice.entity.TGpsHis;
import com.aehter.sharenettyservice.service.TGpsHisService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 终端设备位置表(TGpsHis)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-19 09:55:05
 */
@ApiIgnore
@RestController
@RequestMapping("tGpsHis")
public class TGpsHisController {
    /**
     * 服务对象
     */
    @Resource
    private TGpsHisService tGpsHisService;


}