package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.entity.TWifiInfo;
import com.aether.sharemainctlservice.service.TDeviceInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 终端设备信息(TDeviceInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:21
 */
@Api(value = "device", tags = "终端设备管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功", response = ResultVO.class),
        @ApiResponse(code = 201, message = "操作失败", response = ResultVO.class),
        @ApiResponse(code = 402, message = "输入数据检查不通过", response = ResultVO.class),
        @ApiResponse(code = 500, message = "后台程序异常", response = ResultVO.class)
})
@RestController
@RequestMapping("device")
public class TDeviceInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TDeviceInfoService tDeviceInfoService;

    /**
     * 登陆或注册设备
     *
     * @return 单条数据
     */
    @ApiOperation(value = "为SDI提供注册服务", notes = "sdi向主控注册服务",response = ResultVO.class)
    @GetMapping("reg||login")
    public ResultVO<TWifiInfo> regOrLogin(@RequestBody @Valid TDeviceInfo deviceInfo,
                                          @RequestBody @Valid TGpsHis tGpsHis) {
        TDeviceInfo tDeviceInfo = tDeviceInfoService.regLogin(deviceInfo,tGpsHis);
        return new ResultVO(tDeviceInfo);
    }



}