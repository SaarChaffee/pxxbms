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
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GoodServiceImpl implements GoodService {
  private final GoodDao goodDao;
  private final Logger logger = Logger.getRootLogger();
  
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
  
  @Override
  public Good getGoodById( int id ) {
    Connection connection = null;
    Good good = null;
    
    try{
      connection = DaoUtils.getConnection();
      good = goodDao.getGoodById( connection, id );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return good;
  }
  
  @Override
  public boolean updateGood( int id, Good good ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      good.setModifyBy( id );
      good.setModifyDate( new Date( System.currentTimeMillis() ) );
      logger.info( "'''''''''updateGood''''Open transaction''''''''''" );
      int i = goodDao.updateGood( connection, good );
      if( i > 0 ){
        flag = true;
        logger.info( "'''''''''updateGood''''success''''''''''" );
        
      }
      else{
        logger.info( "'''''''''updateGood''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        logger.info( "'''''''''updateGood''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public Good getGoodByName( String name ) {
    Connection connection = null;
    Good good = null;
    
    try{
      connection = DaoUtils.getConnection();
      good = goodDao.getGoodByName( connection, name );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return good;
  }
  
  @Override
  public boolean addGood( int id, Good good ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      logger.info( "'''''''''addGood''''Open transaction''''''''''" );
      good.setCreatedBy( id );
      good.setCreationDate( new Date( System.currentTimeMillis() ) );
      int i = goodDao.addGood( connection, good );
      if( i > 0 ){
        flag = true;
        logger.info( "'''''''''addGood''''success''''''''''" );
      }
      else{
        logger.info( "'''''''''addGood''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        logger.info( "'''''''''addGood''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        logger.info( "'''''''''addGood''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public boolean deleteGood( int id ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      logger.info( "'''''''''delGood''''Open transaction''''''''''" );
      int i = goodDao.deleteGood( connection, id );
      if( i > 0 ){
        flag = true;
        logger.info( "'''''''''delGood''''success''''''''''" );
      }
      else{
        logger.info( "'''''''''delGood''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        logger.info( "'''''''''delGood''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    return flag;
  }
  
  @Override
  public Good getGoodByCode( String code ) {
    Connection connection = null;
    Good good = null;
    
    try{
      connection = DaoUtils.getConnection();
      good = goodDao.getGoodByCode( connection, code );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return good;
    
    
  }
}
