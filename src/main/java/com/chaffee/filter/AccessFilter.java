/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/13
 */
package com.chaffee.filter;

import com.chaffee.entity.User;
import com.chaffee.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
  @Override
  public void init( FilterConfig filterConfig ) throws ServletException {
    Filter.super.init( filterConfig );
  }
  
  @Override
  public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
    HttpServletRequest request = ( HttpServletRequest ) servletRequest;
    HttpServletResponse response = ( HttpServletResponse ) servletResponse;
    
    User user = ( User ) request.getSession().getAttribute( Constants.USER_SESSION );
    
    if( user != null && user.getUserRole() < 3 ){
      filterChain.doFilter( servletRequest, servletResponse );
    }
    else{
      System.out.println( "------------拦截成功-------------" );
      response.sendRedirect( request.getContextPath() + "/error.jsp" );
    }
    
  }
  
  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
