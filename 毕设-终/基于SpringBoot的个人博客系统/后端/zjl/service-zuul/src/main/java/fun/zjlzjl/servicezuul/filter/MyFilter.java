package fun.zjlzjl.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import fun.zjlzjl.servicezuul.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Keymap;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/smsApi/*"}, filterName = "MyFilter")
@Component
public class MyFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        if(!ctx.getRequest().getHeader("visitType").equals("guest"))
        {
            ctx.addZuulRequestHeader("username", Util.getCurrentUser().getUsername());
            ctx.addZuulRequestHeader("userId",Util.getCurrentUser().getId().toString());

        }
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

//        Object accessToken = request.getParameter("token");
//        if(accessToken == null) {
//            log.warn("token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){}
//
//            return null;
//        }
        log.info("ok");
        return null;

    }
}
