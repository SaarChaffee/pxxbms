/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.good;

import com.chaffee.entity.GoodType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GoodTypeDao {
  public List<GoodType> getGoodTypeList( Connection connection ) throws SQLException;
}
