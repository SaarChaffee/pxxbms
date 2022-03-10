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
  
  //修改密码
  protected void savepwd( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    Object o = req.getSession().getAttribute( Constants.USER_SESSION );
    String newpassword = req.getParameter( "newpassword" );
    boolean flag = false;
    
    if( o != null && !StringUtils.isNullOrEmpty( newpassword ) ){
      UserService service = new UserServiceImpl();
      flag = service.updatePwd( ( ( User ) o ).getId(), newpassword );
      System.out.println( "--------UserServlet---------" + flag + "-----------" );
      if( flag ){
        req.setAttribute( Constants.MSG, "密码修改成功，请用新密码登录" );
        System.out.println( "--------UserServlet---------清除旧Session-----------" );
        req.getSession().removeAttribute( Constants.USER_SESSION );
        System.out.println( "--------UserServlet---------清除完成-----------" );
      }
      else{
        req.setAttribute( Constants.MSG, "修改密码失败" );
        System.out.println( "--------UserServlet---------修改密码失败-----------" );
      }
    }
    else{
      req.setAttribute( Constants.MSG, "新密码有问题" );
      System.out.println( "--------UserServlet---------新密码有问题-----------" );
    }
    req.getRequestDispatcher( "/jsp/pwdmodify.jsp" ).forward( req, resp );
  }
  
  //查询旧密码
  protected void pwdmodify( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    Object o = req.getSession().getAttribute( Constants.USER_SESSION );
    String oldpassword = req.getParameter( "oldpassword" );
    Map<String, String> resultMap = new HashMap<>();
    
    if( o == null ){
      resultMap.put( "result", "sessionerror" );
    }
    else if( StringUtils.isNullOrEmpty( oldpassword ) ){
      resultMap.put( "result", "error" );
    }
    else{
      String userPassword = ( ( User ) o ).getUserPassword();
      if( oldpassword.equals( userPassword ) ){
        resultMap.put( "result", "true" );
      }
      else{
        resultMap.put( "result", "false" );
      }
    }
    
    PrintWriter out = resp.getWriter();
    try{
      resp.setContentType( "application/json" );
      Gson gson = new Gson();
      String json = gson.toJson( resultMap );
      out.write( json );
    }finally{
      out.flush();
      out.close();
    }
  }
  
  //查询用户列表
  protected void query( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String queryUserName = req.getParameter( "queryname" );
    String tempUserRole = req.getParameter( "queryUserRole" );
    String pageIndex = req.getParameter( "pageIndex" );
    int queryUserRole = 0;
    UserServiceImpl userService = new UserServiceImpl();
    UserRoleService roleService = new UserRoleServiceImpl();
    
    int pageSize = 5;
    int currentPageNo = 1;
    if( queryUserName == null ){
      queryUserName = "";
    }
    if( !StringUtils.isNullOrEmpty( tempUserRole ) ){
      queryUserRole = Integer.parseInt( tempUserRole );
    }
    if( pageIndex != null ){
      currentPageNo = Integer.parseInt( pageIndex );
    }
    int totalCount = userService.getUserCount( queryUserName, queryUserRole );
    
    PageSupport pageSupport = new PageSupport();
    pageSupport.setPageSize( pageSize );
    pageSupport.setCurrentPageNo( currentPageNo );
    pageSupport.setTotalCount( totalCount );
    
    int totalPageCount = pageSupport.getTotalPageCount();
    if( currentPageNo < 1 ){
      currentPageNo = 1;
    }
    else if( currentPageNo > totalPageCount ){
      currentPageNo = totalPageCount;
    }
    
    List<User> userList = userService.getUserList( queryUserName, queryUserRole, currentPageNo, pageSize );
    List<UserRole> roleList = roleService.getRoleList();
    req.setAttribute( "roleList", roleList );
    req.setAttribute( "userList", userList );
    req.setAttribute( "totalCount", totalCount );
    req.setAttribute( "currentPageNo", currentPageNo );
    req.setAttribute( "totalPageCount", totalPageCount );
    req.setAttribute( "queryUserName", queryUserName );
    req.setAttribute( "queryUserRole", queryUserRole );
    
    req.getRequestDispatcher( "/jsp/userlist.jsp" ).forward( req, resp );
  }
  
  //添加用户
  protected void add( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String userCode = req.getParameter( "userCode" );
    String userName = req.getParameter( "userName" );
    String userPassword = req.getParameter( "userPassword" );
    String gender = req.getParameter( "gender" );
    String birthday = req.getParameter( "birthday" );
    String phone = req.getParameter( "phone" );
    String address = req.getParameter( "address" );
    String userRole = req.getParameter( "userRole" );
    
    User user = new User();
    user.setUserCode( userCode );
    user.setUserName( userName );
    user.setUserPassword( userPassword );
    user.setGender( Integer.parseInt( gender ) );
    try{
      user.setBirthday( new SimpleDateFormat( "yyyy-MM-dd" ).parse( birthday ) );
    }catch( ParseException e ){
      e.printStackTrace();
    }
    user.setPhone( phone );
    user.setAddress( address );
    user.setUserRole( Integer.parseInt( userRole ) );
    
    UserServiceImpl userService = new UserServiceImpl();
    if( userService.addUser( user ) ){
      resp.sendRedirect( req.getContextPath() + "/jsp/user.do?method=query" );
    }
    else{
      req.getRequestDispatcher( "/useradd.jsp" ).forward( req, resp );
    }
  }
  
  //查询角色列表
  protected void getrolelist( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    UserRoleService roleService = new UserRoleServiceImpl();
    List<UserRole> roleList = roleService.getRoleList();
    
    PrintWriter out = resp.getWriter();
    try{
      resp.setContentType( "application/json" );
      Gson gson = new Gson();
      String json = gson.toJson( roleList );
      out.write( json );
    }finally{
      out.flush();
      out.close();
    }
  }
  
  //删除用户
  protected void deluser( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String uid = req.getParameter( "uid" );
    Map<String, String> resultMap = new HashMap<>();
    int delId = 0;
    UserServiceImpl userService = new UserServiceImpl();
    
    try{
      delId = Integer.parseInt( uid );
    }catch( NumberFormatException e ){
      e.printStackTrace();
      delId = 0;
    }
    if( delId < 0 ){
      resultMap.put( "delResult", "notexist" );
    }
    else{
      boolean flag = userService.delUser( delId );
      if( flag ){
        resultMap.put( "delResult", "true" );
      }
      else{
        resultMap.put( "delResult", "false" );
      }
    }
    
    PrintWriter out = resp.getWriter();
    try{
      resp.setContentType( "application/json" );
      Gson gson = new Gson();
      String json = gson.toJson( resultMap );
      out.write( json );
    }finally{
      out.flush();
      out.close();
    }
  }
  
  //获取要修改的用户
  protected void modify( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String uid = req.getParameter( "uid" );
    int getId = 0;
    UserServiceImpl userService = new UserServiceImpl();
    User user;
    
    try{
      getId = Integer.parseInt( uid );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    user = userService.getUserById( getId );
    req.setAttribute( "user", user );
    req.getRequestDispatcher( "/jsp/usermodify.jsp" ).forward( req, resp );
  }
  
  //应用修改
  protected void modifyexe( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    
    
    UserServiceImpl userService = new UserServiceImpl();
//    if( userService.updateUser(  )){
//      resp.sendRedirect( req.getContextPath() + "/jsp/user.do?method=query" );
//    }
//    else{
//      req.getRequestDispatcher( "/useradd.jsp" ).forward( req, resp );
//    }
  }
}
