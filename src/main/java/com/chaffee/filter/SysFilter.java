package com.chaffee.filter;

import com.chaffee.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SysFilter implements Filter {
  @Override
  public void init( FilterConfig filterConfig ) throws ServletException {
    Filter.super.init( filterConfig );
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
    
    //if( o == null ){
    //  System.out.println( "------------拦截成功-------------" );
    //  response.sendRedirect( request.getContextPath() + "/error.jsp" );
    //}
    //else{
      filterChain.doFilter( servletRequest, servletResponse );
    //}
    
  }
}

/* @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        Filter.super.init( filterConfig );
    }
    
    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse,
    FilterChain filterChain ) throws IOException, ServletException {
        HttpServletRequest request = ( HttpServletRequest ) servletRequest;
        HttpServletResponse response = ( HttpServletResponse ) servletResponse;
        Object session = request.getSession().getAttribute( Constant.USER_SESSION );
        if( session == null ){
            System.out.println("过滤成功");
            response.sendRedirect( request.getContextPath()+"/login.jsp");
        }
    
        
        filterChain.doFilter( servletRequest, servletResponse );
    }
    
    @Override
    public void destroy() {
        Filter.super.destroy();
    }*/
