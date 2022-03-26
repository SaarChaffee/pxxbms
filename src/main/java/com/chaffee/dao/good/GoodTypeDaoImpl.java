/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.good;

import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.GoodType;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodTypeDaoImpl implements GoodTypeDao {
  @Override
  public List<GoodType> getGoodTypeList( Connection connection ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    GoodType goodType = null;
    List<GoodType> goodTypeList = new ArrayList<>();
    
    if( connection != null ){
      String sql = "select * from good_type";
      Object[] param = {};
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        goodType = new GoodType();
        goodType.setId( rs.getInt( "id" ) );
        goodType.setTypeCode( rs.getInt( "typeCode" ) );
        goodType.setTypeName( rs.getString( "typeName" ) );
        goodType.setCreatedBy( rs.getInt( "createdBy" ) );
        goodType.setCreationDate( rs.getDate( "creationDate" ) );
        goodType.setModifyBy( rs.getInt( "modifyBy" ) );
        goodType.setModifyDate( rs.getDate( "modifyDate" ) );
        goodTypeList.add( goodType );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return goodTypeList;
  }
  
  @Override
  public GoodType getGoodTypeById( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    GoodType goodType = null;
    
    if( connection != null ){
      String sql = "select * from good_type where id = ?";
      Object[] param = { id };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        goodType = new GoodType();
        goodType.setId( rs.getInt( "id" ) );
        goodType.setTypeCode( rs.getInt( "typeCode" ) );
        goodType.setTypeName( rs.getString( "typeName" ) );
        goodType.setCreatedBy( rs.getInt( "createdBy" ) );
        goodType.setCreationDate( rs.getDate( "creationDate" ) );
        goodType.setModifyBy( rs.getInt( "modifyBy" ) );
        goodType.setModifyDate( rs.getDate( "modifyDate" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return goodType;
  }
  
  @Override
  public GoodType getGoodTypeByCode( Connection connection, String typeCode ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    GoodType goodType = null;
    
    if( connection != null ){
      String sql = "select * from good_type where typeCode = ?";
      Object[] param = { typeCode };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        goodType = new GoodType();
        goodType.setId( rs.getInt( "id" ) );
        goodType.setTypeCode( rs.getInt( "typeCode" ) );
        goodType.setTypeName( rs.getString( "typeName" ) );
        goodType.setCreatedBy( rs.getInt( "createdBy" ) );
        goodType.setCreationDate( rs.getDate( "creationDate" ) );
        goodType.setModifyBy( rs.getInt( "modifyBy" ) );
        goodType.setModifyDate( rs.getDate( "modifyDate" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return goodType;
  }
  
  @Override
  public int addGoodType( Connection connection, GoodType goodType ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "insert into good_type (typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate) " +
          "values (?,?,?,?,?,?)";
      Object[] param = {
          goodType.getTypeCode(), goodType.getTypeName(), goodType.getCreatedBy(), goodType.getCreationDate(),
          goodType.getModifyBy(), goodType.getModifyDate()
      };
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public int delGoodType( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "delete from good_type where id = ?";
      Object[] param = { id };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public int updGoodType( Connection connection, int id, GoodType goodType ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "update good_type set typeCode = ?,typeName = ?,createdBy = ?,creationDate = ?,modifyBy = ?," +
          "modifyDate = ? where id = ?";
      Object[] param = {
          goodType.getTypeCode(), goodType.getTypeName(), goodType.getCreatedBy(), goodType.getCreationDate(),
          goodType.getModifyBy(), goodType.getModifyDate(), goodType.getId()
      };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Test
  public void test() throws Exception {
    System.out.println( this.getGoodTypeList( DaoUtils.getConnection() ) );
  }
}
