package com.firebugsoft.common.mvc.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.firebugsoft.common.mvc.json.Body;

/**
 * json异常处理解析器
 *
 * @author felix
 */
public class JsonHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MappingJackson2JsonView view = new MappingJackson2JsonView();
    
    public JsonHandlerExceptionResolver() {
    	view.setExtractValueFromSingleKeyModel(true);
    }
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    	logger.error("", e);
        ModelAndView mav = new ModelAndView(view);
        if (e instanceof BindException) {
            return mav.addObject(Body._400);
        }
        if (e instanceof MaxUploadSizeExceededException) {
            return mav.addObject(Body._400);
        }
        return mav.addObject(Body._500);
    }
}
