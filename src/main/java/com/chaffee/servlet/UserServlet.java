/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/10
 */
package com.chaffee.servlet;

import com.chaffee.entity.User;
import com.chaffee.entity.UserRole;
import com.chaffee.service.user.UserRoleService;
import com.chaffee.service.user.UserRoleServiceImpl;
import com.chaffee.service.user.UserService;
import com.chaffee.service.user.UserServiceImpl;
import com.chaffee.util.Constants;
import com.chaffee.util.PageSupport;
import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {
  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    if( method != null ){
      switch( method ){
        case "savepwd" -> {
          this.savepwd( req, resp );
        }
        case "pwdmodify" -> {
          this.pwdmodify( req, resp );
        }
        case "query" -> {
          this.query( req, resp );
        }
        case "add" -> {
          this.add( req, resp );
        }
        case "getrolelist" -> {
          this.getrolelist( req, resp );
        }
        case "deluser" -> {
          this.deluser( req, resp );
        }
        case "modify" -> {
          this.modify( req, resp );
        }
      }
    }
  }
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    doGet( req, resp );
  }
  

}
