/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.servlet;

import com.chaffee.entity.Good;
import com.chaffee.entity.GoodType;
import com.chaffee.entity.User;
import com.chaffee.service.good.GoodService;
import com.chaffee.service.good.GoodServiceImpl;
import com.chaffee.service.good.GoodTypeService;
import com.chaffee.service.good.GoodTypeServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodServlet extends HttpServlet {
  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    switch( method ){
      case "query" -> {
        this.query( req, resp );
      }
      case "modify" -> {
        this.modify( req, resp );
      }
      case "gettypelist" -> {
        this.getTypeList( req, resp );
      }
      case "getGoodByName" -> {
        this.getGoodByName( req, resp );
      }
      case "delGood" -> {
        this.delGood( req, resp );
      }
      case "getGoodByCode" -> {
        this.getGoodByCode( req, resp );
      }
      case "view" -> {
        this.view( req, resp );
      }
      case "querygoodtype" -> {
        this.queryGoodType( req, resp );
      }
      case "delGoodType" -> {
        this.delGoodType( req, resp );
      }
      case "modifygoodtype" -> {
        this.modifyGoodType( req, resp );
      }
      case "getGoodTypeByCode" -> {
        this.getGoodTypeByCode( req, resp );
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
      case "goodtypemodifyexe" -> {
        this.goodTypeModifyExe( req, resp );
      }
      case "addtype" -> {
        this.addType( req, resp );
      }
    }
    
  }
  
  protected void modify( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String gid = req.getParameter( "gid" );
    int getId = 0;
    GoodService goodService = new GoodServiceImpl();
    Good good = null;
    
    try{
      getId = Integer.parseInt( gid );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    good = goodService.getGoodById( getId );
    req.setAttribute( "good", good );
    req.getRequestDispatcher( "/jsp/goodmodify.jsp" ).forward( req, resp );
    
  }
  
  protected void modifyExe( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String gid = req.getParameter( "gid" );
    String goodName = req.getParameter( "goodName" );
    String goodCode = req.getParameter( "goodCode" );
    String goodType = req.getParameter( "goodType" );
    String inventory = req.getParameter( "inventory" );
    String owner = req.getParameter( "owner" );
    User o = ( User ) req.getSession().getAttribute( Constants.USER_SESSION );
    int currentUser = o.getId();
    int id = 0;
    int type = 0;
    int inv = 0;
    int ownerId = 0;
    GoodServiceImpl goodService = new GoodServiceImpl();
    
    try{
      id = Integer.parseInt( gid );
      type = Integer.parseInt( goodType );
      inv = Integer.parseInt( inventory );
      ownerId = Integer.parseInt( owner );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    
    Good good = goodService.getGoodById( id );
    good.setGoodName( goodName );
    good.setGoodCode( goodCode );
    good.setGoodType( type );
    good.setInventory( inv );
    good.setOwner( ownerId );
    
    goodService.updateGood( currentUser, good );
    
    resp.sendRedirect( req.getContextPath() + "/jsp/good?method=query" );
  }
  
  protected void getTypeList( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    List<GoodType> goodTypeList = goodTypeService.getGoodTypeList();
    
    PrintWriter out = resp.getWriter();
    try{
      resp.setContentType( "application/json" );
      Gson gson = new Gson();
      String json = gson.toJson( goodTypeList );
      out.write( json );
    }finally{
      out.flush();
      out.close();
    }
  }
  
  protected void getGoodByName( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String goodName = req.getParameter( "goodName" );
    GoodService goodService = new GoodServiceImpl();
    Map<String, Object> resultMap = new HashMap<>();
    
    Good good = goodService.getGoodByName( goodName );
    if( good != null ){
      resultMap.put( "flag", true );
      resultMap.put( "gid", good.getId() );
      resultMap.put( "inventory", good.getInventory() );
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
  
  protected void add( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String goodCode = req.getParameter( "goodCode" );
    String goodName = req.getParameter( "goodName" );
    String tempInv = req.getParameter( "inventory" );
    String ownerCode = req.getParameter( "ownerId" );
    String type = req.getParameter( "goodType" );
    int inventory = 0;
    int goodType = 0;
    int ownerId = 0;
    User o = ( User ) req.getSession().getAttribute( Constants.USER_SESSION );
    int currentUser = o.getId();
    
    try{
      inventory = Integer.parseInt( tempInv );
      goodType = Integer.parseInt( type );
      ownerId = Integer.parseInt( ownerCode );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    Good good = new Good();
    good.setGoodCode( goodCode );
    good.setGoodName( goodName );
    good.setInventory( inventory );
    good.setOwner( ownerId );
    good.setGoodType( goodType );
    System.out.println( good );
    GoodService goodService = new GoodServiceImpl();
    if( goodService.addGood( currentUser, good ) ){
      resp.sendRedirect( req.getContextPath() + "/jsp/good?method=query" );
    }
    else{
      req.getRequestDispatcher( "/goodadd.jsp" ).forward( req, resp );
    }
    
  }
  
  protected void delGood( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String goodId = req.getParameter( "gid" );
    Map<String, Object> resultMap = new HashMap<>();
    int delId = 0;
    GoodService goodService = new GoodServiceImpl();
    
    try{
      delId = Integer.parseInt( goodId );
    }catch( NumberFormatException e ){
      e.printStackTrace();
      delId = 0;
    }
    if( delId <= 0 ){
      resultMap.put( "delResult", "notexist" );
    }
    else{
      boolean flag = goodService.deleteGood( delId );
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
  
  protected void getGoodByCode( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String goodCode = req.getParameter( "goodCode" );
    GoodService goodService = new GoodServiceImpl();
    Map<String, Object> resultMap = new HashMap<>();
    
    Good good = goodService.getGoodByCode( goodCode );
    if( good != null ){
      resultMap.put( "flag", true );
      resultMap.put( "gid", good.getId() );
      resultMap.put( "inventory", good.getInventory() );
      resultMap.put( "gName", good.getGoodName() );
      resultMap.put( "gCode", good.getGoodCode() );
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
  
  protected void view( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String gid = req.getParameter( "gid" );
    int getId = 0;
    GoodService goodService = new GoodServiceImpl();
    Good good;
    
    try{
      getId = Integer.parseInt( gid );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    good = goodService.getGoodById( getId );
    req.setAttribute( "good", good );
    req.getRequestDispatcher( "/jsp/goodview.jsp" ).forward( req, resp );
  }
  
  protected void query( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String queryGoodName = req.getParameter( "queryGoodName" );
    String queryOwnerName = req.getParameter( "queryOwnerName" );
    String tempGoodType = req.getParameter( "queryGoodType" );
    String tempPageIndex = req.getParameter( "pageIndex" );
    int queryGoodType = 0;
    GoodService goodService = new GoodServiceImpl();
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    
    int pageSize = 5;
    int currentPageNo = 1;
    if( StringUtils.isNullOrEmpty( queryGoodName ) ){
      queryGoodName = "";
    }
    if( StringUtils.isNullOrEmpty( queryOwnerName ) ){
      queryOwnerName = "";
    }
    if( !StringUtils.isNullOrEmpty( tempGoodType ) ){
      queryGoodType = Integer.parseInt( tempGoodType );
    }
    if( !StringUtils.isNullOrEmpty( tempPageIndex ) ){
      currentPageNo = Integer.parseInt( tempPageIndex );
    }
    
    int totalCount = goodService.getGoodCount( queryGoodName, queryOwnerName, queryGoodType );
    
    PageSupport pageSupport = new PageSupport();
    pageSupport.setPageSize( pageSize );
    pageSupport.setCurrentPageNo( currentPageNo );
    pageSupport.setTotalCount( totalCount );
    
    int totalPageCount = pageSupport.getTotalPageCount();
    if( currentPageNo < 1 ){
      currentPageNo = 0;
    }
    else if( currentPageNo > totalPageCount ){
      currentPageNo = totalPageCount;
    }
    
    List<Good> goodList = goodService.getGoodList( queryGoodName, queryOwnerName, queryGoodType, currentPageNo,
                                                   pageSize );
    List<GoodType> goodTypeList = goodTypeService.getGoodTypeList();
    req.setAttribute( "queryGoodName", queryGoodName );
    req.setAttribute( "queryOwnerName", queryOwnerName );
    req.setAttribute( "queryGoodType", queryGoodType );
    req.setAttribute( "goodTypeList", goodTypeList );
    req.setAttribute( "goodList", goodList );
    req.setAttribute( "totalCount", totalCount );
    req.setAttribute( "currentPageNo", currentPageNo );
    req.setAttribute( "totalPageCount", totalPageCount );
    
    req.getRequestDispatcher( "/jsp/goodlist.jsp" ).forward( req, resp );
  }
  
  protected void queryGoodType( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    List<GoodType> goodTypeList = goodTypeService.getGoodTypeList();
    req.setAttribute( "goodTypeList", goodTypeList );
    req.getRequestDispatcher( "/jsp/goodtypelist.jsp" ).forward( req, resp );
  }
  
  protected void delGoodType( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String tempGoodTypeId = req.getParameter( "gtid" );
    Map<String, Object> resultMap = new HashMap<>();
    int delId = 0;
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    
    try{
      delId = Integer.parseInt( tempGoodTypeId );
    }catch( NumberFormatException e ){
      e.printStackTrace();
      delId = 0;
    }
    if( delId <= 0 ){
      resultMap.put( "delResult", "notexist" );
    }
    else{
      boolean flag = goodTypeService.delGoodType( delId );
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
  
  protected void modifyGoodType( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String tempGTId = req.getParameter( "gtid" );
    int getId = 0;
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    GoodType goodType = null;
    
    try{
      getId = Integer.parseInt( tempGTId );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    goodType = goodTypeService.getGoodTypeById( getId );
    req.setAttribute( "goodType", goodType );
    req.getRequestDispatcher( "/jsp/goodtypemodify.jsp" ).forward( req, resp );
    
  }
  
  protected void goodTypeModifyExe( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String tempGTId = req.getParameter( "gtid" );
    String typeName = req.getParameter( "typeName" );
    String tempTypeCode = req.getParameter( "typeCode" );
    User o = ( User ) req.getSession().getAttribute( Constants.USER_SESSION );
    int currentUser = o.getId();
    int gTId = 0;
    int typeCode = 0;
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    
    try{
      gTId = Integer.parseInt( tempGTId );
      typeCode = Integer.parseInt( tempTypeCode );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    
    GoodType goodType = goodTypeService.getGoodTypeById( gTId );
    goodType.setTypeName( typeName );
    goodType.setTypeCode( typeCode );
    
    goodTypeService.updGoodType( currentUser, goodType );
    resp.sendRedirect( req.getContextPath() + "/jsp/good?method=querygoodtype" );
  }
  
  protected void getGoodTypeByCode( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String tempCode = req.getParameter( "typeCode" );
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    Map<String, Object> resultMap = new HashMap<>();
    GoodType goodType = null;
    int typeCode = 0;
    try{
      typeCode = Integer.parseInt( tempCode );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    
    goodType = goodTypeService.getGoodTypeByCode( typeCode );
    if( goodType != null ){
      resultMap.put( "flag", true );
      resultMap.put( "gTId", goodType.getId() );
      resultMap.put( "typeName", goodType.getTypeName() );
      resultMap.put( "typeCode", goodType.getTypeCode().toString() );
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
  
  protected void addType( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    String typeName = req.getParameter( "typeName" );
    String tempTypeCode = req.getParameter( "typeCode" );
    User o = ( User ) req.getSession().getAttribute( Constants.USER_SESSION );
    int currentUser = o.getId();
    int gTId = 0;
    int typeCode = 0;
    GoodTypeService goodTypeService = new GoodTypeServiceImpl();
    
    try{
      typeCode = Integer.parseInt( tempTypeCode );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    
    GoodType goodType = new GoodType();
    goodType.setTypeName( typeName );
    goodType.setTypeCode( typeCode );
    if( goodTypeService.addGoodType( currentUser, goodType ) ){
      resp.sendRedirect( req.getContextPath() + "/jsp/good?method=querygoodtype" );
    }
    else{
      req.getRequestDispatcher( "/goodtypeadd.jsp" ).forward( req, resp );
    }
  }
}
