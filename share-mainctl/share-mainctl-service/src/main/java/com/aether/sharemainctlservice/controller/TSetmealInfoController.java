package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.entity.TSetmealInfo;
import com.aether.sharemainctlservice.service.TSetmealInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流量套餐管理
 * (TSetmealInfo)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-20 17:26:43
 */
@Api(value = "meal", tags = "流量套餐管理")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("meal")
public class TSetmealInfoController {
    /**
     * 服务对象
     */
    @Resource
    private TSetmealInfoService tSetmealInfoService;

    /**
     * 查询套餐
     * @return
     */
    @ApiOperation(value = "查询所有套餐", notes = "查询所有套餐")
    @GetMapping("all")
    public ResultVO<List<TSetmealInfo>> queryAll() {

        List<TSetmealInfo> tSetmealInfos = tSetmealInfoService.queryAll(new TSetmealInfo());
        return new ResultVO<>(ResultCode.SUCCESS, tSetmealInfos);
    }

}