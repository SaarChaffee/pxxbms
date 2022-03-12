/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.servlet;

import com.chaffee.entity.Good;
import com.chaffee.entity.GoodType;
import com.chaffee.service.good.GoodService;
import com.chaffee.service.good.GoodServiceImpl;
import com.chaffee.service.good.GoodTypeService;
import com.chaffee.service.good.GoodTypeServiceImpl;
import com.chaffee.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodServlet extends HttpServlet {
  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    
    switch( method ){
      case "query" -> {
        this.query( req, resp );
      }
    }
    
  }
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    doGet( req, resp );
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
