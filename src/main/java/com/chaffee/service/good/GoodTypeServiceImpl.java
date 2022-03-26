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
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GoodTypeServiceImpl implements GoodTypeService {
  private final GoodTypeDao goodTypeDao;
  private final Logger logger = Logger.getRootLogger();
  
  
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
  
  @Override
  public GoodType getGoodTypeById( int id ) {
    Connection connection = null;
    GoodType goodType = null;
    
    try{
      connection = DaoUtils.getConnection();
      goodType = goodTypeDao.getGoodTypeById( connection, id );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return goodType;
  }
  
  @Override
  public GoodType getGoodTypeByCode( String typeCode ) {
    Connection connection = null;
    GoodType goodType = null;
    
    try{
      connection = DaoUtils.getConnection();
      goodType = goodTypeDao.getGoodTypeByCode( connection, typeCode );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return goodType;
  }
  
  @Override
  public boolean addGoodType( int id, GoodType goodType ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      logger.info( "'''''''''addGoodType''''Open transaction''''''''''" );
      goodType.setCreatedBy( id );
      goodType.setCreationDate( new Date( System.currentTimeMillis() ) );
      int i = goodTypeDao.addGoodType( connection, goodType );
      if( i > 0 ){
        flag = true;
        logger.info( "'''''''''addGoodType''''success''''''''''" );
      }
      else{
        logger.info( "'''''''''addGoodType''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        logger.info( "'''''''''addGoodType''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        logger.info( "'''''''''addGoodType''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    return flag;
  }
  
  @Override
  public boolean updGoodType( int id, GoodType goodType ) {
    return false;
  }
  
  @Override
  public boolean delGoodType( int id ) {
    return false;
  }
  
  @Test
  public void test() {
    System.out.println( this.getGoodTypeList() );
  }
}
