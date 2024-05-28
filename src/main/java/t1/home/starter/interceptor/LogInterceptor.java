package t1.home.starter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final String level;

    private final Boolean requestMethod;
    private final Boolean requestUrl;
    private final Boolean requestHeaders;
    private final Boolean requestParam;

    private final Boolean responseStatus;
    private final Boolean responseHeaders;
    private final Boolean responseProcessingTime;

    /**
     * перед обработкой запроса
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        StringBuffer logStr = new StringBuffer();
        if(requestMethod) {
            logStr.append("[").append(request.getMethod()).append("]");
        }
        if(requestUrl) {
            logStr.append("[").append(request.getRequestURL()).append("]");
        }
        if(requestHeaders) {
            logStr.append("[").append(getHeadersRequest(request)).append("]");
        }
        if(requestParam) {
            logStr.append("[").append(getParameters(request)).append("]");
        }
        logString(logStr.toString());

        request.setAttribute("startTime", System.currentTimeMillis());
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

        StringBuffer logStr = new StringBuffer();
        if(responseStatus) {
            logStr.append("[").append(response.getStatus()).append("]");
        }
        if(responseHeaders) {
            logStr.append("[").append(getHeadersResponse(response)).append("]");
        }
        if(responseProcessingTime) {
            long startTime = (Long) request.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            logStr.append("[Processing Time: ").append(processingTime).append("ms]");
        }
        logString(logStr.toString());
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
        return posted.toString();
    }

    private void logString(String logStr) {
        if (level.equalsIgnoreCase("error")) {
            log.error(logStr);
        } else if(level.equalsIgnoreCase("warn")) {
            log.warn(logStr);
        } else {
            log.info(logStr);
        }
    }
}
