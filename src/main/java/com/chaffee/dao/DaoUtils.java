/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DaoUtils {
  //声明dataSource
  private static DataSource dataSource;
  
  //通过静态代码块
  static {
    try{
      //1. 创建Properties对象
      Properties properties = new Properties();
      //2. 将配置文件转换成字节输入流
      InputStream is = DaoUtils.class.getClassLoader().getResourceAsStream( "config/druid.properties" );
      //3. 使用properties对象加载is
      properties.load( is );
      //druid底层是使用的工厂设计模式，去加载配置文件，创建DruidDataSource对象
      dataSource = DruidDataSourceFactory.createDataSource( properties );
    }catch( Exception e ){
      e.printStackTrace();
    }
  }
  
  public static Connection getConnection() {
    Connection connection = null;
    try{
      connection = dataSource.getConnection();
    }catch( Exception e ){
      e.printStackTrace();
    }
    return connection;
  }
  
  public static ResultSet execute( Connection connection, PreparedStatement pstm, ResultSet rs,
                                   String sql, Object[] param ) throws SQLException {
    
    pstm = connection.prepareStatement( sql );
    
    for( int i = 0; i < param.length; i++ ){
      pstm.setObject( i + 1, param[i] );
    }
    rs = pstm.executeQuery();
    return rs;
  }
  
  public static int execute( Connection connection, PreparedStatement pstm, String sql, Object[] param ) throws SQLException {
    
    pstm = connection.prepareStatement( sql );
    
    for( int i = 0; i < param.length; i++ ){
      pstm.setObject( i + 1, param[i] );
    }
    return pstm.executeUpdate();
  }
  
  public static boolean close( Connection connection, PreparedStatement pstm, ResultSet rs ) {
    boolean flag = true;
    try{
      if( rs != null ){
        rs.close();
        rs = null;
      }
      if( pstm != null ){
        pstm.close();
        pstm = null;
      }
      if( connection != null ){
        connection.close();
        connection = null;
      }
    }catch( SQLException e ){
      flag = false;
      e.printStackTrace();
    }
    return flag;
  }
  
  @Test
  public void Test() throws Exception {
    System.out.println( dataSource );
    System.out.println( dataSource.getConnection() );
    System.out.println( dataSource );
  }
}
