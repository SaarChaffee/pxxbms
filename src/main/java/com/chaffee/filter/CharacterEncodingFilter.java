package com.chaffee.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
  private Logger logger;
  
  @Override
  public void init( FilterConfig filterConfig ) throws ServletException {
    Filter.super.init( filterConfig );
    logger = Logger.getRootLogger();
  }
  
  @Override
  public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse,
                        FilterChain filterChain ) throws IOException, ServletException {
    
    servletRequest.setCharacterEncoding( "utf-8" );
    servletResponse.setCharacterEncoding( "utf-8" );
    logger.info( "------------转码成功-------------" );
    filterChain.doFilter( servletRequest, servletResponse );
    
  }
  
  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
