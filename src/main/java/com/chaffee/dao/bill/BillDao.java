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

public interface BillDao {
  /**
   * 获取订单列表
   *
   * @param connection
   * @param goodName
   * @param customerName
   * @param paymentMethod
   * @param currentPageNo
   * @param pageSize
   * @return List<Bill>
   * @throws SQLException
   */
  public List<Bill> getBillList( Connection connection, String goodName, String customerName, int paymentMethod,
                                 int currentPageNo,
                                 int pageSize ) throws SQLException;
  
  /**
   * 获取订单数
   *
   * @param connection
   * @param goodName
   * @param customerName
   * @param paymentMethod
   * @return
   * @throws SQLException
   */
  public int getBillCount( Connection connection, String goodName, String customerName, int paymentMethod ) throws SQLException;
  
  /**
   * 更新订单
   *
   * @param connection
   * @param id
   * @param bill
   * @return
   * @throws SQLException
   */
  public int updateBill( Connection connection, int id, Bill bill ) throws SQLException;
  
  /**
   * 通过id获得订单
   *
   * @param connection
   * @param id
   * @return
   * @throws SQLException
   */
  public Bill getBillById( Connection connection, int id ) throws SQLException;
  
  /**
   * 增加订单
   *
   * @param connection
   * @param bill
   * @return
   * @throws SQLException
   */
  public int addBill( Connection connection, Bill bill ) throws SQLException;
  
  /**
   * 删除订单
   *
   * @param connection
   * @param id
   * @return
   * @throws SQLException
   */
  public int deleteBill( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过billCode获得订单
   *
   * @param connection
   * @param billCode
   * @return Bill
   * @throws SQLException
   */
  public Bill getBillByCode( Connection connection, String billCode ) throws SQLException;
  
}
