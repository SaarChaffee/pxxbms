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
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
  
  @Override
  public boolean updateBill( int id, Bill bill ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''updateBill''''Open transaction''''''''''" );
      int i = billDao.updateBill( connection, id, bill );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''updateBill''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''updateBill''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''updateBill''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        System.out.println( "'''''''''updateBill''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public Bill getBillById( int id ) {
    Connection connection = null;
    Bill bill = null;
    
    try{
      connection = DaoUtils.getConnection();
      bill = billDao.getBillById( connection, id );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    return bill;
  }
  
  @Override
  public boolean addBill( int id, Bill bill ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''addBill''''Open transaction''''''''''" );
      bill.setCreatedBy( id );
      bill.setCreationDate( new Date( System.currentTimeMillis() ) );
      int i = billDao.addBill( connection, bill );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''addBill''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''addBill''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''addBill''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        System.out.println( "'''''''''addBill''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public boolean deleteBill( int id ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''delBill''''Open transaction''''''''''" );
      int i = billDao.deleteBill( connection, id );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''delBill''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''delBill''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''delBill''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    return flag;
    
  }
  
  @Test
  public void test() {
    System.out.println( this.getBillById( 1 ) );
  }
}
