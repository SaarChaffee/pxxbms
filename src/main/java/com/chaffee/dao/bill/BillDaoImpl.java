/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.bill;

import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.Bill;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillDaoImpl implements BillDao {
  
  
  @Override
  public List<Bill> getBillList( Connection connection, String goodName, String customerName, int paymentMethod,
                                 int currentPageNo,
                                 int pageSize ) throws SQLException {
    PreparedStatement pstm = null;
    Bill bill = null;
    ResultSet rs = null;
    List<Bill> bills = null;
    List<Object> list = null;
    StringBuffer sql = new StringBuffer();
    
    if( connection != null ){
      list = new ArrayList<>();
      bills = new ArrayList<>();
      sql.append( "select b.*,p.typeName as paymentMethodName,u.userName as customerName,g.goodName as goodName " +
                      "from bill b,payment_method p,user u,good g " +
                      "where b.paymentMethod = p.id and b.customerCode=u.id and b.goodCode = g.id" );
      if( !StringUtils.isNullOrEmpty( goodName ) ){
        sql.append( " and g.goodName like ?" );
        list.add( "%" + goodName + "%" );
      }
      if( !StringUtils.isNullOrEmpty( customerName ) ){
        sql.append( " and u.userName like ?" );
        list.add( "%" + customerName + "%" );
      }
      if( paymentMethod > 0 ){
        sql.append( " and b.paymentMethod = ?" );
        list.add( paymentMethod );
      }
      sql.append( " order by p.id ASC limit ?,?" );
      currentPageNo = ( currentPageNo - 1 ) * pageSize;
      list.add( currentPageNo );
      list.add( pageSize );
      
      Object[] param = list.toArray();
      String s = sql.toString();
      rs = DaoUtils.execute( connection, pstm, rs, s, param );
      while( rs.next() ){
        bill = new Bill();
        bill.setId( rs.getInt( "id" ) );
        bill.setBillCode( rs.getString( "billCode" ) );
        bill.setGoodCode( rs.getInt( "goodCode" ) );
        bill.setQuantity( rs.getInt( "quantity" ) );
        bill.setGoodPrice( rs.getDouble( "goodPrice" ) );
        bill.setTotalPrice( rs.getDouble( "totalPrice" ) );
        bill.setCustomerCode( rs.getInt( "customerCode" ) );
        bill.setAddress( rs.getString( "address" ) );
        bill.setBillTime( rs.getDate( "billTime" ) );
        bill.setPaymentMethod( rs.getInt( "paymentMethod" ) );
        bill.setDeliveryTime( rs.getDate( "deliveryTime" ) );
        bill.setCreatedBy( rs.getInt( "createdBy" ) );
        bill.setCreationDate( rs.getDate( "creationDate" ) );
        bill.setModifyBy( rs.getInt( "modifyBy" ) );
        bill.setModifyDate( rs.getDate( "modifyDate" ) );
        bill.setPaymentMethodName( rs.getString( "paymentMethodName" ) );
        bill.setCustomerName( rs.getString( "customerName" ) );
        bill.setGoodName( rs.getString( "goodName" ) );
        bills.add( bill );
      }
      DaoUtils.close( null, pstm, rs );
    }
    
    
    return bills;
  }
  
  @Override
  public int getBillCount( Connection connection, String goodName, String customerName, int paymentMethod ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    StringBuffer sql = new StringBuffer();
    List<Object> list = new ArrayList<>();
    int count = 0;
    
    if( connection != null ){
      sql.append( "select count(*) as count " +
                      "from bill b,user u,good g,payment_method p " +
                      "where b.paymentMethod = p.id and b.customerCode = u.id and b.goodCode = g.id" );
      
      if( !StringUtils.isNullOrEmpty( goodName ) ){
        sql.append( " and g.goodName like ?" );
        list.add( "%" + goodName + "%" );
      }
      if( !StringUtils.isNullOrEmpty( customerName ) ){
        sql.append( " and u.userName like ?" );
        list.add( "%" + customerName + "%" );
      }
      if( paymentMethod > 0 ){
        sql.append( " and b.paymentMethod = ?" );
        list.add( paymentMethod );
      }
      String s = sql.toString();
      Object[] param = list.toArray();
      rs = DaoUtils.execute( connection, pstm, rs, s, param );
      if( rs.next() ){
        count = rs.getInt( "count" );
      }
      DaoUtils.close( null, pstm, rs );
    }
    return count;
    
  }
  
  @Override
  public int updateBill( Connection connection, int id, Bill bill ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "update bill b set b.billCode = ?,b.goodCode = ?,b.quantity = ?,b.goodPrice = ?,b.totalPrice = ?," +
          "b.customerCode = ?,b.address = ?,b.billTime = ?,b.paymentMethod = ?,b.deliveryTime = ?,b.createdBy = ?,b" +
          ".creationDate = ?,b.modifyBy = ?,b.modifyDate = ? where b.id = ?";
      Object[] param = {
          bill.getBillCode(), bill.getGoodCode(), bill.getQuantity(), bill.getGoodPrice(), bill.getTotalPrice(),
          bill.getCustomerCode(), bill.getAddress(), bill.getBillTime(), bill.getPaymentMethod(),
          bill.getDeliveryTime(), bill.getCreatedBy(), bill.getCreationDate(), id,
          new Date( System.currentTimeMillis() ), bill.getId()
      };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public Bill getBillById( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Bill bill = null;
    
    if( connection != null ){
      String sql = "select b.*,p.typeName as paymentMethodName,u.userName as customerName,g.goodName as goodName " +
          "from bill b,payment_method p,user u,good g " +
          "where b.paymentMethod = p.id and b.customerCode=u.id and b.goodCode = g.id and b.id = ?";
      Object[] param = { id };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        bill = new Bill();
        bill.setId( rs.getInt( "id" ) );
        bill.setBillCode( rs.getString( "billCode" ) );
        bill.setGoodCode( rs.getInt( "goodCode" ) );
        bill.setQuantity( rs.getInt( "quantity" ) );
        bill.setGoodPrice( rs.getDouble( "goodPrice" ) );
        bill.setTotalPrice( rs.getDouble( "totalPrice" ) );
        bill.setCustomerCode( rs.getInt( "customerCode" ) );
        bill.setAddress( rs.getString( "address" ) );
        bill.setBillTime( rs.getDate( "billTime" ) );
        bill.setPaymentMethod( rs.getInt( "paymentMethod" ) );
        bill.setDeliveryTime( rs.getDate( "deliveryTime" ) );
        bill.setCreatedBy( rs.getInt( "createdBy" ) );
        bill.setCreationDate( rs.getDate( "creationDate" ) );
        bill.setModifyBy( rs.getInt( "modifyBy" ) );
        bill.setModifyDate( rs.getDate( "modifyDate" ) );
        bill.setPaymentMethodName( rs.getString( "paymentMethodName" ) );
        bill.setCustomerName( rs.getString( "customerName" ) );
        bill.setGoodName( rs.getString( "goodName" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return bill;
  }
  
  @Override
  public int addBill( Connection connection, Bill bill ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "insert into bill (billCode, goodCode, quantity, goodPrice, totalPrice, " +
          "customerCode, address, billTime, paymentMethod,deliveryTime, createdBy, " +
          "creationDate, modifyBy, modifyDate)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      
      Object[] param = {
          bill.getBillCode(), bill.getGoodCode(), bill.getQuantity(), bill.getGoodPrice(), bill.getTotalPrice(),
          bill.getCustomerCode(), bill.getAddress(), bill.getBillTime(), bill.getPaymentMethod(),
          bill.getDeliveryTime(),
          bill.getCreatedBy(), bill.getCreationDate(), bill.getModifyBy(), bill.getModifyDate()
      };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public int deleteBill( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "delete from bill where id = ?";
      Object[] param = { id };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Test
  public void test() throws Exception {
    System.out.println( this.getBillById( DaoUtils.getConnection(), 1 ) );
  }
}

