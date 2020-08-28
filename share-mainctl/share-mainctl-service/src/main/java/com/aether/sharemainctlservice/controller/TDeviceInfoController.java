package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.entity.TWifiInfo;
import com.aether.sharemainctlservice.service.TDeviceInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 终端设备信息(TDeviceInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 13:22:21
 */
@Api(value = "device", tags = "终端设备管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
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
    @ApiOperation(value = "登陆或注册设备", notes = "终端设备箱sdi注册")
    @PostMapping("reglogin/{longitude}/{latitude}")
    public ResultVO<TDeviceInfo> regOrLogin(@RequestBody @Valid TDeviceInfo deviceInfo,
                                          @PathVariable("longitude")double longitude,
                                          @PathVariable("latitude")double latitude) {
        TGpsHis tGpsHis = new TGpsHis();
        tGpsHis.setDeviceId(deviceInfo.getDeviceId());
        tGpsHis.setLatitude(latitude);
        tGpsHis.setLongitude(longitude);
        tGpsHis.setCreateTime(new Date());
        TDeviceInfo tDeviceInfo = tDeviceInfoService.regLogin(deviceInfo,tGpsHis);
        return new ResultVO<TDeviceInfo>(tDeviceInfo);
    }

    /**
     * 查询终端是否在线
     *
     * @return 终端数据
     */
    @ApiOperation(value = "查询终端是否在线", notes = "查询终端是否在线")
    @GetMapping("query/status")
    public ResultVO<TDeviceInfo> queryDevice() {
        List<TDeviceInfo> tDeviceInfos = tDeviceInfoService.queryDevicesOnlinesOr();
        return new ResultVO(tDeviceInfos);
    }





}