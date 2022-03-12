/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.service.good;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.good.GoodDao;
import com.chaffee.dao.good.GoodDaoImpl;
import com.chaffee.entity.Good;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodServiceImpl implements GoodService {
  private final GoodDao goodDao;
  
  public GoodServiceImpl() {
    goodDao = new GoodDaoImpl();
  }
  
  @Override
  public List<Good> getGoodList( String goodName, String ownerName, int goodType, int currentPageNo, int pageSize ) {
    Connection connection = null;
    List<Good> goods = null;
    
    try{
      connection = DaoUtils.getConnection();
      goods = goodDao.getGoodList( connection, goodName, ownerName, goodType, currentPageNo, pageSize );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return goods;
  }
  
  @Override
  public int getGoodCount( String goodName, String ownerName, int goodType ) {
    int count = 0;
    Connection connection = null;
    
    try{
      connection = DaoUtils.getConnection();
      count = goodDao.getGoodCount( connection, goodName, ownerName, goodType );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    return count;
  }
}
