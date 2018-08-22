package com.xct.media.queuing.controller.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Chris on 2018/5/19.
 */
@ControllerAdvice
public class ExceptionAdviceController {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdviceController.class);

    /**
     * 500
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView intercept500(Exception exception) {
        logger.info("intercept 500 error. {}", exception);
        return new ModelAndView("error/500");
    }


    /**
     * 404
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView intercept400(Exception exception) {
        logger.info("intercept 404 error. {}", exception);
        return new ModelAndView("error/500");
    }
}
