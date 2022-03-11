/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.bill;

import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDaoImpl implements PaymentMethodDao {
  @Override
  public List<PaymentMethod> getPaymentMethodList( Connection connection ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Object[] param = {};
    List<PaymentMethod> methods = new ArrayList<>();
    PaymentMethod _paymentMethod = null;
    
    if( connection != null ){
      String sql = "select * from payment_method";
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        _paymentMethod = new PaymentMethod();
        _paymentMethod.setId( rs.getInt( "id" ) );
        _paymentMethod.setTypeCode( rs.getInt( "typeCode" ) );
        _paymentMethod.setCreatedBy( rs.getInt( "createBy" ) );
        _paymentMethod.setCreationDate( rs.getDate( "creationDate" ) );
        _paymentMethod.setModifyBy( rs.getInt( "modifyBy" ) );
        _paymentMethod.setModifyDate( rs.getDate( "modifyDate" ) );
        methods.add( _paymentMethod );
      }
    }
    DaoUtils.close( null, pstm, rs );
    return methods;
  }
}
