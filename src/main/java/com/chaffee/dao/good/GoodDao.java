/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.good;

import com.chaffee.entity.Good;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GoodDao {
  /**
   * 获得商品列表
   *
   * @param connection
   * @param goodName
   * @param ownerName
   * @param goodType
   * @param currentPageNo
   * @param pageSize
   * @return List<Good>
   * @throws SQLException
   */
  public List<Good> getGoodList( Connection connection, String goodName, String ownerName, int goodType,
                                 int currentPageNo,
                                 int pageSize ) throws SQLException;
  
  /**
   * 获得商品数量
   *
   * @param connection
   * @param goodName
   * @param ownerName
   * @param goodType
   * @return
   * @throws SQLException
   */
  public int getGoodCount( Connection connection, String goodName, String ownerName, int goodType ) throws SQLException;
  
  /**
   * 更新商品
   *
   * @param connection
   * @param id
   * @param good
   * @return
   * @throws SQLException
   */
  public int updateGood( Connection connection, Good good ) throws SQLException;
  
  /**
   * 通过id获得商品
   *
   * @param connection
   * @param id
   * @return
   * @throws SQLException
   */
  public Good getGoodById( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过商品名获得商品
   *
   * @param connection
   * @param goodName
   * @return
   * @throws SQLException
   */
  public Good getGoodByName( Connection connection, String goodName ) throws SQLException;
  
  /**
   * 增加商品
   *
   * @param connection
   * @param good
   * @return
   * @throws SQLException
   */
  public int addGood( Connection connection, Good good ) throws SQLException;
  
  /**
   * 删除商品
   *
   * @param connection
   * @param id
   * @return
   * @throws SQLException
   */
  public int deleteGood( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过商品编号获得商品
   *
   * @param connection
   * @param code
   * @return
   * @throws SQLException
   */
  public Good getGoodByCode( Connection connection, String code ) throws SQLException;
}
