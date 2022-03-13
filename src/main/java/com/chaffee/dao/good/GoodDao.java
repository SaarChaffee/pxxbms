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
  public List<Good> getGoodList( Connection connection, String goodName, String ownerName, int goodType,
                                 int currentPageNo,
                                 int pageSize ) throws SQLException;
  
  public int getGoodCount( Connection connection, String goodName, String ownerName, int goodType ) throws SQLException;
  
  public int updateGood( Connection connection, int id, Good good ) throws SQLException;
  
  public Good getGoodById( Connection connection, int id ) throws SQLException;
  
  public Good getGoodByName( Connection connection, String goodName ) throws SQLException;
  
  public int addGood( Connection connection, Good good ) throws SQLException;
  
  public int deleteGood( Connection connection, int id ) throws SQLException;
  
  public Good getGoodByCode( Connection connection, String code ) throws SQLException;
}
