package com.example.kusoul.controller;

import com.example.kusoul.tools.WebsocketUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {""})
@RestController
@RequestMapping("/websocketSend")
public class WebSocketController {

    @Resource
    WebsocketUtil websocketUtil;

    @RequestMapping(value = "sendOne",method = RequestMethod.GET)
    public void senndOne(@RequestParam(required=true) String id,@RequestParam(required=true) String message) {
        websocketUtil.sendMessage(id,message);
    }

    @RequestMapping(value = "sendAll",method = RequestMethod.GET)
    public void senndAll(@RequestParam(required=true) String id,@RequestParam(required=true) String message) {
        websocketUtil.sendAllMessage(id,message);
    }

}
