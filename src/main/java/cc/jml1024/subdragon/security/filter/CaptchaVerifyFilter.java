package cc.jml1024.subdragon.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Evil
 */
@Component
public class CaptchaVerifyFilter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String FILTER_URI = "/login";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod()) &&
                FILTER_URI.equals(request.getServletPath())) {
            String verifyCode = request.getParameter("verifyCode");
            if (verifyCode == null || "".equals(verifyCode)) {
                logger.error("验证码为空");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
