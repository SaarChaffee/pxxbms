/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.service.good;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.good.GoodTypeDao;
import com.chaffee.dao.good.GoodTypeDaoImpl;
import com.chaffee.entity.GoodType;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodTypeServiceImpl implements GoodTypeService {
  private final GoodTypeDao goodTypeDao;
  
  public GoodTypeServiceImpl() {
    goodTypeDao = new GoodTypeDaoImpl();
  }
  
  @Override
  public List<GoodType> getGoodTypeList() {
    Connection connection = null;
    List<GoodType> goodTypeList = null;
    
    try{
      connection = DaoUtils.getConnection();
      goodTypeList = goodTypeDao.getGoodTypeList( connection );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    return goodTypeList;
    
  }
  
  @Test
  public void test(){
    System.out.println(this.getGoodTypeList());
  }
}
