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
import java.util.List;

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
      case "modifyexe" -> {
        this.modifyExe( req, resp );
      }
      case "gettypelist" -> {
        this.getTypeList( req, resp );
      }
    }
    
  }
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    doGet( req, resp );
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
    
    resp.sendRedirect( req.getContextPath() + "/jsp/good.do?method=query" );
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
}
