/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.bill;

import com.chaffee.entity.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl implements BillDao {
  
  @Override
  public List<Bill> getBillList( Connection connection, String goodCode, String customerCode, String paymentMethod ) throws SQLException {
    
    return null;
  }
}
