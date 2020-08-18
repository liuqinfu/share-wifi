package com.aether.sharesdiservice.controller;

import com.aether.sharecommon.finals.ResultVO;
import com.aether.sharecommon.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author 我走路带风
 * @since 2020/8/18 16:55
 */
@Api(value = "balance", tags = "netty负载均衡器")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功", response = ResultVO.class),
        @ApiResponse(code = 201, message = "操作失败", response = ResultVO.class),
        @ApiResponse(code = 402, message = "输入数据检查不通过", response = ResultVO.class),
        @ApiResponse(code = 500, message = "后台程序异常", response = ResultVO.class)
})
@RestController
@RequestMapping("balance")
public class LoadBalanceController {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${server.host}")
    private String ip;

    @Value("${server.port}")
    private int port;

    @GetMapping("getnetty")
    public ResultVO<String> getNetty(){
        String serverAddr = ip+":"+port;
        Map<Object, Object> nettyLoads = redisUtil.hmget(serverAddr);
        List<Map.Entry<String, Integer>> nettys = sortASC(nettyLoads);
        Map.Entry<String, Integer> netty_loads_map = nettys.stream().findFirst().get();
        String netty_Addr = netty_loads_map.getKey(); //ip:port
        Integer sdi_loads = netty_loads_map.getValue();
        return new ResultVO<>(netty_Addr);
    }

    /**
     * 对SDIBALANCER进行排序，升序
     * @param source
     * @return
     */
    public List<Map.Entry<String,Integer>> sortASC(Map source){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(source.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        return list;
    }
}
