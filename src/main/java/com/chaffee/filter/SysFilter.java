package com.chaffee.filter;

import com.chaffee.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SysFilter implements Filter {
  private Logger logger;
  
  @Override
  public void init( FilterConfig filterConfig ) throws ServletException {
    Filter.super.init( filterConfig );
    logger = Logger.getRootLogger();
  }
  
  @Override
  public void destroy() {
    Filter.super.destroy();
  }
  
  @Override
  public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse,
                        FilterChain filterChain ) throws IOException, ServletException {
    HttpServletRequest request = ( HttpServletRequest ) servletRequest;
    HttpServletResponse response = ( HttpServletResponse ) servletResponse;
    
    Object o = request.getSession().getAttribute( Constants.USER_SESSION );
    
    if( o == null ){
      logger.info( "------------拦截成功-------------" );
      response.sendRedirect( request.getContextPath() + "/error.jsp" );
    }
    else{
      filterChain.doFilter( servletRequest, servletResponse );
    }
    
  }
}

