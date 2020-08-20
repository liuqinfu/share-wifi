package com.aether.sharemainctlservice.controller;

import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharemainctlservice.service.TRemoteCmdInfoService;
import com.aether.sharemainctlservice.vo.TRemoteCmdRequestVo;
import com.aether.sharemainctlservice.vo.TRemoteCmdResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luojianye
 * @description 远程指令集Controller
 * @date 2020/8/20 16:01
 */
@Slf4j
@RestController
@RequestMapping("/remoteCmd")
public class TRemoteCmdInfoController {

    @Autowired
    private TRemoteCmdInfoService tRemoteCmdInfoService;

    /**
     * 根据条件 查询 远程指令集
     * @param vo
     * @return
     */
    @PostMapping("/find")
    public ResultVO<List<TRemoteCmdResponseVo>> findRemoteCmdsByCondition(@RequestBody TRemoteCmdRequestVo vo){
        List<TRemoteCmdResponseVo> list = tRemoteCmdInfoService.findRemoteCmdsByCondition(vo);
        return new ResultVO<>(ResultCode.SUCCESS, list);
    }

}
