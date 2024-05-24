package t1.home.starter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Логирование должно включать в себя метод запроса, URL, заголовки запроса и ответа, код ответа, время обработки запроса и т.д.
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * перед обработкой запроса
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        log.info("[" + request.getMethod() + "]" + "[" + request.getRequestURL() + "]" + "[" + getHeadersRequest(request) + "]"
        + "[" + getParameters(request) + "]");
        return true;
    }

    /**
     * после обработки запроса
     */
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("[" + response.getStatus() + "]" + "[" + getHeadersResponse(response) + "]");
    }

    private Map<String, String> getHeadersRequest(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }

    private Map<String, String> getHeadersResponse(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            String headerValue = response.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }

    private String getParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr!=null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

}
