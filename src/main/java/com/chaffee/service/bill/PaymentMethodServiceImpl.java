/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.service.bill;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.bill.PaymentMethodDao;
import com.chaffee.dao.bill.PaymentMethodDaoImpl;
import com.chaffee.entity.PaymentMethod;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentMethodServiceImpl implements PaymentMethodService {
  private final PaymentMethodDao paymentMethodDao;
  
  public PaymentMethodServiceImpl() {
    paymentMethodDao = new PaymentMethodDaoImpl();
  }
  
  @Override
  public List<PaymentMethod> getPaynentMethodList() {
    Connection connection = null;
    List<PaymentMethod> methods = null;
    
    try{
      connection = DaoUtils.getConnection();
      methods = paymentMethodDao.getPaymentMethodList( connection );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    return methods;
  }
}
