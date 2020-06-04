package com.gfs.domain.utils;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectStrategyUtil {
    private static RedirectStrategy redirectStrategy = new HttpsRedirectStrategy();

    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        redirectStrategy.sendRedirect(request, response, url);
    }
}
