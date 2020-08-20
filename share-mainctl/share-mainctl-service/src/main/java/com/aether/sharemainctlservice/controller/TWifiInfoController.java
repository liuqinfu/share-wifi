package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TDeviceInfo;
import com.aether.sharemainctlservice.entity.TGpsHis;
import com.aether.sharemainctlservice.entity.TWifiInfo;
import com.aether.sharemainctlservice.service.TDeviceInfoService;
import com.aether.sharemainctlservice.service.TWifiInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 热点信息(TWifiInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 17:05:57
 */
@Api(value = "ap", tags = "热点管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("tWifiInfo")
public class TWifiInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TWifiInfoService tWifiInfoService;

    /**
     * 登陆或注册设备
     *
     * @return 单条数据
     */
    @ApiOperation(value = "热点上报", notes = "终端设备箱共享热点")
    @GetMapping("reg||login")
    public ResultVO<TWifiInfo> regOrLogin(@RequestBody @Valid  TWifiInfo tWifiInfo,
                                          @RequestBody @Valid  TGpsHis tGpsHis) {
        TWifiInfo tWifiInfo1 = tWifiInfoService.save(tWifiInfo,tGpsHis);
        return new ResultVO(tWifiInfo1);
    }

}