package com.baidu.highflip.editor.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.baidu.highflip.editor.model.HighFlipEditorException;
import com.baidu.highflip.editor.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GlobalExceptionFilter extends OncePerRequestFilter {

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            // handle TicketException
            if (e instanceof HighFlipEditorException) {
                HighFlipEditorException ticketException = (HighFlipEditorException) e;
                objectMapper.writeValue(response.getWriter(),
                                        Result.failure(
                                                ticketException.getCode(),
                                                ticketException.getMessage()));
            } else {
                objectMapper.writeValue(response.getWriter(),
                                        Result.failure(400, e.getMessage()));
            }
        }
    }
}

