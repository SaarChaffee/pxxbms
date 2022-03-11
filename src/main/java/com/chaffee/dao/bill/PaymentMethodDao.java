/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.bill;

import com.chaffee.entity.PaymentMethod;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PaymentMethodDao {
  
  public List<PaymentMethod> getPaymentMethodList( Connection connection ) throws SQLException;
}
