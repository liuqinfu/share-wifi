package com.aehter.sharenettyservice.controller;

import com.aehter.sharenettyservice.entity.OrderDTO;
import com.aehter.sharenettyservice.enums.MessageType;
import com.aehter.sharenettyservice.enums.UsageMessageType;
import com.aehter.sharenettyservice.websocket.module.Message;
import com.aehter.sharenettyservice.websocket.WSConstants;
import com.aether.sharecommon.finals.ResultCode;
import com.aether.sharecommon.finals.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 我走路带风
 * @since 2020/8/19 15:56
 */
@Api(value = "msg", tags = "通知事件")
@ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 201, message = "操作失败"),
        @ApiResponse(code = 402, message = "输入数据检查不通过"),
        @ApiResponse(code = 500, message = "后台程序异常")
})
@RestController
@RequestMapping("msg")
public class MessageController {

    /**
     * 发送通知到终端
     *
     * @return
     */
    @ApiOperation(value = "主动发送消息到终端", notes = "主动发送消息到终端")
    @PostMapping("order/send")
    public ResultVO<OrderDTO> sendOrder(@RequestBody @Valid OrderDTO orderDTO){
        Channel channel = WSConstants.getChannel(orderDTO.getDeviceId());
        if (channel == null){
            return new ResultVO(ResultCode.FAILED,"用户离线");
        }
        Message message = new Message(orderDTO, MessageType.REQUEST, UsageMessageType.OPERATION);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect)));
        return new ResultVO(ResultCode.SUCCESS,orderDTO);
    }

}
