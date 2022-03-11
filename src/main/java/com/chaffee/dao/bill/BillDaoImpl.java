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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
      sql.append( "select b.*,p.typeName as paymentMethodName,u.userName as customerName,g.goodName as goodName " +
                      "from bill b,payment_method p,user u,good g where b.paymentMethod=p.id and b.customerCode=u.id " +
                      "and b" +
                      ".goodCode = g.id" );
      if( !StringUtils.isNullOrEmpty( goodName ) ){
        sql.append( " and g.goodName like ?" );
        list.add( "%" + goodName + "%" );
      }
      if( !StringUtils.isNullOrEmpty( customerName ) ){
        sql.append( "and u.userName like ?" );
        list.add( "%" + customerName + "%" );
      }
      if( paymentMethod > 0 ){
        sql.append( "and b.paymentMethod = ?" );
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
        bill.setQuantity( rs.getInt( "quality" ) );
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
        bill.setGoodName( rs.getString( "goodNane" ) );
        bills.add( bill );
      }
      DaoUtils.close( null, pstm, rs );
    }
    
    
    return bills;
  }
}
