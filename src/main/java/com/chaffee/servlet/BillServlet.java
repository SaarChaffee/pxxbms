/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.servlet;

import com.chaffee.entity.Bill;
import com.chaffee.entity.PaymentMethod;
import com.chaffee.service.bill.BillService;
import com.chaffee.service.bill.BillServiceImpl;
import com.chaffee.service.bill.PaymentMethodService;
import com.chaffee.service.bill.PaymentMethodServiceImpl;
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

public class BillServlet extends HttpServlet {
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
      case "getPaymentMethodList" -> {
        this.getPaymentMethodList( req, resp );
      }
    }
    
    
  }
  
  @Override
  protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    doGet( req, resp );
  }
  
  protected void query( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String queryGoodName = req.getParameter( "queryGoodName" );
    String queryCustomerName = req.getParameter( "queryname" );
    String tempPaymentMethod = req.getParameter( "queryPaymentMethod" );
    String pageIndex = req.getParameter( "pageIndex" );
    int queryPaymentMethod = 0;
    BillService billService = new BillServiceImpl();
    PaymentMethodService paymentMethodService = new PaymentMethodServiceImpl();
    
    int pageSize = 5;
    int currentPageNo = 1;
    if( StringUtils.isNullOrEmpty( queryGoodName ) ){
      queryGoodName = "";
    }
    if( StringUtils.isNullOrEmpty( queryCustomerName ) ){
      queryCustomerName = "";
    }
    if( !StringUtils.isNullOrEmpty( tempPaymentMethod ) ){
      queryPaymentMethod = Integer.parseInt( tempPaymentMethod );
    }
    if( !StringUtils.isNullOrEmpty( pageIndex ) ){
      currentPageNo = Integer.parseInt( pageIndex );
    }
    
    int totalCount = billService.getBillCount( queryGoodName, queryCustomerName, queryPaymentMethod );
    
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
    
    List<Bill> billList = billService.getBillList( queryGoodName, queryCustomerName, queryPaymentMethod,
                                                   currentPageNo, pageSize );
    List<PaymentMethod> paymentMethodList = paymentMethodService.getPaynentMethodList();
    req.setAttribute( "billList", billList );
    req.setAttribute( "paymentMethodList", paymentMethodList );
    req.setAttribute( "totalCount", totalCount );
    req.setAttribute( "currentPageNo", currentPageNo );
    req.setAttribute( "totalPageCount", totalPageCount );
    req.setAttribute( "queryGoodName", queryGoodName );
    req.setAttribute( "queryCustomerName", queryCustomerName );
    req.setAttribute( "queryPaymentMethod", queryPaymentMethod );
    
    req.getRequestDispatcher( "/jsp/billlist.jsp" ).forward( req, resp );
  }
  
  protected void getPaymentMethodList( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
      IOException {
    PaymentMethodService paymentMethodService = new PaymentMethodServiceImpl();
    List<PaymentMethod> paymentMethodList = paymentMethodService.getPaynentMethodList();
    
    PrintWriter out = resp.getWriter();
    try{
      resp.setContentType( "application/json" );
      Gson gson = new Gson();
      String json = gson.toJson( paymentMethodList );
      out.write( json );
    }finally{
      out.flush();
      out.close();
    }
    
  }
  
  /**
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   * @TODO 修改顾客账号
   */
  protected void modify( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String billId = req.getParameter( "billid" );
    int id = 0;
    BillService billService = new BillServiceImpl();
    Bill bill;
    
    try{
      id = Integer.parseInt( billId );
    }catch( NumberFormatException e ){
      e.printStackTrace();
    }
    bill = billService.getBillById( id );
    req.setAttribute( "bill", bill );
    req.getRequestDispatcher( "/jsp/billmodify.jsp" ).forward( req, resp );
  }
  
}
