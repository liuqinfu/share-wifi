package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TDeviceFlux;
import com.aether.sharemainctlservice.service.TDeviceFluxService;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 终端设备流量使用情况，只记录STA设备(TDeviceFlux)表控制层
 *
 * @author makejava
 * @since 2020-08-16 12:37:29
 */
@Api(value = "flux", tags = "流量管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("flux")
public class TDeviceFluxController {
    /**
     * 服务对象
     */
    @Resource
    private TDeviceFluxService tDeviceFluxService;

    /**
     * 查询流量使用
     *
     * @param unit     单位 年 year 月 month
     * @param deviceId 设备唯一标识
     * @return
     */
    @ApiOperation(value = "统计流量", notes = "按设备+时间单位统计流量")
    @GetMapping("group/sta/{unit}/{deviceId}")
    public ResultVO<List<Map>> staGroupByUnit(@PathVariable(value = "unit",required = true) @ApiParam(value = "时间单位",allowableValues = "year,month",required = true) String unit,
                                           @PathVariable(value = "deviceId", required = true) @ApiParam(value = "设备唯一标识",required = true) String deviceId) {
        Map params = new HashMap();
        params.put("unit", unit);
        if (StringUtils.isNotEmpty(deviceId)) params.put("deviceId", deviceId);
        List<Map> mapLists = this.tDeviceFluxService.countSTAByParam(params);
        return new ResultVO<>(mapLists);
    }

    /**
     * 查询流量使用
     *
     * @param unit 单位 年 year 月 month
     * @return
     */
    @ApiOperation(value = "统计流量", notes = "按时间单位统计流量")
    @GetMapping("group/sta/{unit}")
    public ResultVO<List<Map>> staGroupByUnit(@PathVariable("unit") @ApiParam(value = "时间单位",allowableValues = "year,month",required = true)  String unit) {
        return staGroupByUnit(unit, null);
    }

    /**
     * 查询流量使用明细
     *
     * @param deviceId 设备唯一标识
     * @return
     */
    @ApiOperation(value = "流量使用查询", notes = "查询设备的流量使用明细")
    @GetMapping("flow/{device}")
    public ResultVO<List<Map>> queryFlowDetail(@PathVariable("device") @ApiParam(value = "设备唯一标识",required = true) String deviceId) {
        Map params = new HashMap();
        params.put("deviceId", deviceId);
        List<Map> mapLists = this.tDeviceFluxService.queryDetailByParam(params);
        return new ResultVO<>(mapLists);
    }


    /**
     * 上报流量使用
     *
     * @param tDeviceFlux
     * @return
     */
    @ApiOperation(value = "统计流量", notes = "主动发送消息到终端", hidden = true)
    @PostMapping("sta/report")
    public ResultVO reportFlux(@RequestBody @Valid TDeviceFlux tDeviceFlux) {
        tDeviceFluxService.insert(tDeviceFlux);
        return new ResultVO(ResultCode.SUCCESS);
    }


    /**
     * 查询已共享流量
     *
     * @param unit     单位 年 year 月 month
     * @param deviceId 设备唯一标识
     * @return
     */
    @ApiOperation(value = "统计流量", notes = "按设备+时间单位统计流量")
    @GetMapping("group/ap/{device}/{unit}")
    public ResultVO<List<Map>> apGroupByUnit(@PathVariable(value = "unit",required = true)  @ApiParam(value = "时间单位",allowableValues = "year,month",required = true,defaultValue = "month")  String unit,
                                           @PathVariable(value = "device", required = true)  @ApiParam(value = "设备唯一标识",required = true)  String deviceId) {
        Map params = new HashMap();
        params.put("unit", unit);
        if (StringUtils.isNotEmpty(deviceId)) params.put("deviceId", deviceId);
        List<Map> maps = tDeviceFluxService.countAPByParam(params);
        return new ResultVO<>(maps);
    }

}