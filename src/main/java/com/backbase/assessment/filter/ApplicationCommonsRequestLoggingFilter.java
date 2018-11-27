package com.backbase.assessment.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Created by Peter_Szanto on 8/7/2016.
 */
public class ApplicationCommonsRequestLoggingFilter extends CommonsRequestLoggingFilter {
	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		super.afterRequest(request, message);
		logger.info(message);
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		super.beforeRequest(request, message);
		logger.info(message);
	}

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		return true;
	}
}
