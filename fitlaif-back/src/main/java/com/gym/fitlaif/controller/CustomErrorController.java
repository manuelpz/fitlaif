package com.gym.fitlaif.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    
    private static final String ERROR_MAPPING = "/error";
    
    @RequestMapping(ERROR_MAPPING)
    public ResponseEntity<Map<String, Object>> handleErrors(HttpServletRequest request, Exception ex) {
        HttpStatus status = getStatus(request);
        Map<String, Object> error = new HashMap<>();
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", ex.getMessage());
        error.put("path", request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
    
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    
    public String getErrorPath() {
        return ERROR_MAPPING;
    }
}

