package com.gfs.api.controller;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.utils.RedirectStrategyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApiRestController(value = "/redirect")
public class RedirectController {

    @GetMapping(value = "")
    public void redirect(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                         @RequestParam(name = "url") String url) throws IOException {
        RedirectStrategyUtil.sendRedirect(servletRequest, servletResponse, url);
    }
}
