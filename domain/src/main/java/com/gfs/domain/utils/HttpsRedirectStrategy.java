package com.gfs.domain.utils;

import com.gfs.domain.config.ApplicationProperties;
import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpsRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (url.startsWith("http"))
            super.sendRedirect(request, response, url);
        else {
            String prefix = request.getContextPath();
            StringBuffer httpsRedirectUrl = new StringBuffer(ApplicationProperties.getOfficialApiDomain());
            if (!url.startsWith(prefix))
                httpsRedirectUrl.append(prefix);
            httpsRedirectUrl.append(url);
            super.sendRedirect(request, response, httpsRedirectUrl.toString());
        }
    }
}
