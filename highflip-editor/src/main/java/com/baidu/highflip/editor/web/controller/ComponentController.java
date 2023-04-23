package com.baidu.highflip.editor.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.highflip.editor.model.Result;
import com.baidu.highflip.editor.services.HighFlipEditorService;
import com.baidu.highflip.editor.vo.LoginRequest;
import com.baidu.highflip.editor.web.annotation.IgnoreRequestBody;

@RestController
public class ComponentController {

    @IgnoreRequestBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginRequest request) {
        HighFlipEditorService highFlipEditorService =
                new HighFlipEditorService();
        highFlipEditorService.login(request.getUsername(),
                                    request.getPassword(),
                                    request.getServerIp(),
                                    request.getServerPort());
        return Result.success("login successfully");
    }
}
