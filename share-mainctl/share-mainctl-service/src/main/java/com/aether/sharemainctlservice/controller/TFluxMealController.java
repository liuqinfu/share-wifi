package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TFluxMeal;
import com.aether.sharemainctlservice.service.TFluxMealService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已购买的套餐流量使用情况管理
 * (TFluxMeal)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:32:30
 */
@Api(value = "meal/flux", tags = "流量套餐用量管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("meal/flux")
public class TFluxMealController {
    /**
     * 服务对象
     */
    @Resource
    private TFluxMealService tFluxMealService;

    /**
     * 查询所有套餐用量
     * @param deviceId
     * @return
     */
    @ApiOperation(value = "查询套餐用量", notes = "查询所有套餐用量")
    @GetMapping("get/{device}")
    public ResultVO<TFluxMeal> selectOne(@PathVariable("device") @ApiParam(value = "设备唯一标识",required = true) String deviceId) {
        Map param = new HashMap();
        param.put("deviceId",deviceId);
        List<TFluxMeal> tFluxMeals = this.tFluxMealService.queryAll(param);
        return new ResultVO(ResultCode.SUCCESS,tFluxMeals);
    }

}