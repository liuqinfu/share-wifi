package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharecommon.utils.DateUtils;
import com.aether.sharemainctlservice.exception.ParameterException;
import com.aether.sharemainctlservice.service.TGpsHisService;
import com.aether.sharemainctlservice.vo.GpsRequestVo;
import com.aether.sharemainctlservice.vo.GpsResponseVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 终端设备位置表(TGpsHis)表控制层
 *
 * @author 我走路带风
 * @since 2020-08-14 17:00:28
 */
@RestController
@RequestMapping("tGpsHis")
public class TGpsHisController {
    /**
     * 服务对象
     */
    @Resource
    private TGpsHisService tGpsHisService;


    /**
     * 根据条件 查询 终端移动轨迹
     * @param vo
     * @return
     */
    @PostMapping("/findGps")
    public ResultVO<List<GpsResponseVo>> findGpsByCondition(@RequestBody GpsRequestVo vo){
        String startDate = vo.getStartDate();
        if(StringUtils.hasText(startDate)){
            if(startDate.length() != 10){
                throw new ParameterException("startDate日期格式错误【格式：yyyy-MM-dd】");
            }
            Date sDate = DateUtils.convertDateFormat(startDate, DateUtils.yyyy_MM_dd);
            if(Objects.isNull(sDate)){
                throw new ParameterException("startDate日期格式错误【格式：yyyy-MM-dd】");
            }
        }
        String endDate = vo.getEndDate();
        if(StringUtils.hasText(endDate)){
            if(endDate.length() != 10){
                throw new ParameterException("endDate日期格式错误【格式：yyyy-MM-dd】");
            }
            Date eDate = DateUtils.convertDateFormat(endDate, DateUtils.yyyy_MM_dd);
            if(Objects.isNull(eDate)){
                throw new ParameterException("endDate日期格式错误【格式：yyyy-MM-dd】");
            }
        }
        List<GpsResponseVo> list = tGpsHisService.findGpsByCondition(vo);
        return new ResultVO<>(ResultCode.SUCCESS, list);
    }



}