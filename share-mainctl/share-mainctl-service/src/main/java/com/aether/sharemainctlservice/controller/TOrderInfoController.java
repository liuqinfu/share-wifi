package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TOrderInfo;
import com.aether.sharemainctlservice.service.TOrderInfoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备的套餐订单管理
 * (TOrderInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:33:53
 */
@Api(value = "meal/order", tags = "流量套餐订单管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("meal/order")
public class TOrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderInfoService tOrderInfoService;

    /**
     * 用户下单
     * @param deviceId
     * @param mealId
     * @return
     */
    @ApiOperation(value = "套餐下单", notes = "用户下单流量套餐，还需要支付")
    @GetMapping("add/{meal}/{device}")
    public ResultVO<TOrderInfo> initOrder(@PathVariable("device") @ApiParam(value = "设备唯一标识",required = true) String deviceId,
                              @PathVariable("meal") @ApiParam(value = "套餐id",required = true) String mealId) {
        TOrderInfo tOrderInfo = tOrderInfoService.initOrder(deviceId, mealId);
        return new ResultVO<>(tOrderInfo == null?ResultCode.FAILED:ResultCode.SUCCESS,tOrderInfo);
    }

    /**
     * 用户支付订单
     * @param deviceId
     * @param orderId
     * @return
     */
    @ApiOperation(value = "套餐支付", notes = "用户支付成功，购买成功")
    @GetMapping("pay/{order}/{device}")
    public ResultVO payOrder(@PathVariable("device") @ApiParam(value = "设备唯一标识",required = true) String deviceId,
                                          @PathVariable("order") @ApiParam(value = "订单id",required = true) String orderId) {
        tOrderInfoService.payOrder(deviceId, orderId);
        return new ResultVO<>(ResultCode.SUCCESS);
    }

    /**
     * 查询订单
     * @param deviceId
     * @return
     */
    @ApiOperation(value = "查询订单", notes = "用户查询所有订单")
    @GetMapping("get/{device}/{status}")
    public ResultVO<TOrderInfo> queryOrder(@PathVariable("device") @ApiParam(value = "设备唯一标识",required = true) String deviceId,
                               @PathVariable("status") @ApiParam(value = "订单状态",allowableValues = "1,2,3",required = true) String status) {
        Map param = new HashMap<>();
        param.put("deviceId",deviceId);
        param.put("status",status);
        List<TOrderInfo> tOrderInfos = tOrderInfoService.queryAll(param);
        return new ResultVO(ResultCode.SUCCESS,tOrderInfos);
    }

}