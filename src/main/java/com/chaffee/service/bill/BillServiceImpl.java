/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.service.bill;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.bill.BillDao;
import com.chaffee.dao.bill.BillDaoImpl;
import com.chaffee.entity.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {
  private final BillDao billDao;
  
  public BillServiceImpl() {
    billDao = new BillDaoImpl();
  }
  
  
  @Override
  public List<Bill> getBillList( String goodName, String customerName, int paymentMethod,
                                 int currentPageNo,
                                 int pageSize ) {
    Connection connection = null;
    List<Bill> bills = null;
    
    try{
      connection = DaoUtils.getConnection();
      bills = billDao.getBillList( connection, goodName, customerName, paymentMethod, currentPageNo, pageSize );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return bills;
  }
  
  @Override
  public int getBillCount( String goodName, String customerName, int paymentMethod ) {
    Connection connection = null;
    int count = 0;
    
    try{
      connection = DaoUtils.getConnection();
      count = billDao.getBillCount( connection, goodName, customerName, paymentMethod );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    return count;
  }
}
