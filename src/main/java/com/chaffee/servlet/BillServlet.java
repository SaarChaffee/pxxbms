/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.servlet;

import com.chaffee.service.bill.BillService;
import com.chaffee.service.bill.BillServiceImpl;
import com.chaffee.service.bill.PaymentMethodService;
import com.chaffee.service.bill.PaymentMethodServiceImpl;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillServlet extends HttpServlet {
  @Override
  protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
    String method = req.getParameter( "method" );
    
    switch( method ){
      case "query" -> {
        query( req, resp );
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
    if( !StringUtils.isNullOrEmpty( pageIndex ) ){
      currentPageNo = Integer.parseInt( pageIndex );
    }
    //int totalCount =
  }
  
  
}
