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
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserServlet extends HttpServlet {
  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    if( method != null ){
      switch( method ){
        case "savepwd" -> {
          this.savePwd( req, resp );
        }
        case "query" -> {
          this.query( req, resp );
        }
        case "getrolelist" -> {
          this.getRoleList( req, resp );
        }
        case "deluser" -> {
          this.delUser( req, resp );
        }
        case "modify" -> {
          this.modify( req, resp );
        }
        case "isTraderExist" -> {
          this.isTraderExist( req, resp );
        }
        case "getUserByCode" -> {
          this.getUserByCode( req, resp );
        }
        case "view" -> {
          this.view( req, resp );
        }
      }
    }
  }
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    switch( method ){
      case "add" -> {
        this.add( req, resp );
      }
      case "modifyexe" -> {
        this.modifyExe( req, resp );
      }
      case "pwdmodify" -> {
        this.pwdModify( req, resp );
      }
    }
  }
  
  //修改密码
  protected void savePwd( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    Object o = req.getSession().getAttribute( Constants.USER_SESSION );
    String newpassword = req.getParameter( "newpassword" );
    boolean flag = false;
    Logger logger = Logger.getRootLogger();
    if( o != null && !StringUtils.isNullOrEmpty( newpassword ) ){
      UserService service = new UserServiceImpl();
      flag = service.updatePwd( ( ( User ) o ).getId(), newpassword );
      logger.info( "--------UserServlet---------" + flag + "-----------" );
      if( flag ){
        req.setAttribute( Constants.MSG, "密码修改成功，请用新密码登录" );
        logger.info( "--------UserServlet---------清除旧Session-----------" );
        req.getSession().removeAttribute( Constants.USER_SESSION );
        logger.info( "--------UserServlet---------清除完成-----------" );
      }
      else{
        req.setAttribute( Constants.MSG, "修改密码失败" );
        logger.info( "--------UserServlet---------修改密码失败-----------" );
      }
    }
    else{
      req.setAttribute( Constants.MSG, "新密码有问题" );
      logger.info( "--------UserServlet---------新密码有问题-----------" );
    }
    req.getRequestDispatcher( "/jsp/pwdmodify.jsp" ).forward( req, resp );
  }
  
  //查询旧密码
  protected void pwdModify( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
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
      resp.sendRedirect( req.getContextPath() + "/jsp/user?method=query" );
    }
    else{
      req.getRequestDispatcher( "/useradd.jsp" ).forward( req, resp );
    }
  }
  
  //查询角色列表
  protected void getRoleList( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
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
  protected void delUser( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
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
    UserService userService = new UserServiceImpl();
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
  protected void modifyExe( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    User o = ( User ) req.getSession().getAttribute( Constants.USER_SESSION );
    int currentUser = o.getId();
    String userId = req.getParameter( "uid" );
    String userName = req.getParameter( "userName" );
    String gender = req.getParameter( "gender" );
    String birthday = req.getParameter( "birthday" );
    String phone = req.getParameter( "phone" );
    String address = req.getParameter( "address" );
    String userRole = req.getParameter( "userRole" );
    UserServiceImpl userService = new UserServiceImpl();
    int getId = 0;
    int uGender = 0;
    int roleCode = 0;
    Date uBir = null;
    try{
      getId = Integer.parseInt( userId );
      uGender = Integer.parseInt( gender );
      roleCode = Integer.parseInt( userRole );
      uBir = new SimpleDateFormat( "yyyy-MM-dd" ).parse( birthday );
    }catch( Exception e ){
      e.printStackTrace();
    }
    
    User user = userService.getUserById( getId );
    user.setUserName( userName );
    user.setGender( uGender );
    user.setBirthday( uBir );
    user.setPhone( phone );
    user.setAddress( address );
    user.setUserRole( roleCode );
    
    userService.updateUser( currentUser, user );
    
    resp.sendRedirect( req.getContextPath() + "/jsp/user?method=query" );
  }
  
  protected void isTraderExist( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String traderName = req.getParameter( "traderName" );
    UserService userService = new UserServiceImpl();
    Map<String, Object> resultMap = new HashMap<>();
    
    User user = userService.getUserByName( traderName );
    if( user != null ){
      resultMap.put( "flag", true );
      resultMap.put( "oid", user.getId() );
    }
    else{
      resultMap.put( "flag", false );
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
  
  protected void view( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String uid = req.getParameter( "uid" );
    int getId = 0;
    UserService userService = new UserServiceImpl();
    User user;
    
    try{
      getId = Integer.parseInt( uid );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    user = userService.getUserById( getId );
    req.setAttribute( "user", user );
    req.getRequestDispatcher( "/jsp/userview.jsp" ).forward( req, resp );
  }
  
  protected void getUserByCode( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String userCode = req.getParameter( "userCode" );
    UserService userService = new UserServiceImpl();
    Map<String, Object> resultMap = new HashMap<>();
    
    User user = userService.getUserByCode( userCode );
    if( user != null ){
      resultMap.put( "flag", true );
      resultMap.put( "uid", user.getId() );
      resultMap.put( "uname", user.getUserName() );
    }
    else{
      resultMap.put( "flag", false );
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
}
