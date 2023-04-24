package com.baidu.highflip.editor.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.highflip.editor.model.Result;
import com.baidu.highflip.editor.vo.ListOperatorsRequest;
import com.baidu.highflip.editor.web.services.HighFlipEditorService;
import com.baidu.highflip.editor.vo.LoginRequest;
import com.baidu.highflip.editor.web.annotation.IgnoreRequestBody;

import highflip.v1.Highflip;

@RestController
public class OperatorController {

    @Autowired
    HighFlipEditorService highFlipEditorService;

    @IgnoreRequestBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginRequest request) {
        highFlipEditorService.login(request.getUsername(),
                                    request.getPassword(),
                                    request.getServerIp(),
                                    request.getServerPort());
        return Result.success("login successfully");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result listOperators(ListOperatorsRequest request) {

        // TODO: how to transfer username and password
        String username = "username";
        String password = "password";
        List<Highflip.OperatorGetResponse> operators =
                highFlipEditorService.getOperators(
                        request.getHighFlipServerIp(),
                        request.getHighFlipServerPort(),
                        username, password);

        return Result.success(operators);
    }
}
