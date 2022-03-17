/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/9
 */
package com.chaffee.servlet;

import com.chaffee.entity.User;
import com.chaffee.service.user.UserService;
import com.chaffee.service.user.UserServiceImpl;
import com.chaffee.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
  private UserService userService = new UserServiceImpl();
  
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    
    String userCode = req.getParameter( "userCode" );
    String userPassword = req.getParameter( "userPassword" );
    User user = userService.Login( userCode, userPassword );
    if( user != null ){
      req.getSession().setAttribute( Constants.USER_SESSION, user );
      resp.sendRedirect( req.getContextPath() + "/jsp/frame.jsp" );
    }
    else{
      req.setAttribute( "error", "用户名或密码错误" );
      req.getRequestDispatcher( "login.jsp" ).forward( req, resp );
    }
  }
  
}
