/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.good;

import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.Good;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao {
  @Override
  public List<Good> getGoodList( Connection connection, String goodName, String ownerName, int goodType,
                                 int currentPageNo, int pageSize ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Good good = null;
    List<Good> goods = new ArrayList<>();
    List<Object> list = new ArrayList<>();
    StringBuffer sql = new StringBuffer();
    
    if( connection != null ){
      sql.append( "select g.*,t.typeName as goodTypeName,u.userName as ownerName " +
                      "from good g,good_type t,user u " +
                      "where g.goodType=t.id and g.owner=u.id" );
      
      if( !StringUtils.isNullOrEmpty( goodName ) ){
        sql.append( " and g.goodName like ?" );
        list.add( "%" + goodName + "%" );
      }
      if( !StringUtils.isNullOrEmpty( ownerName ) ){
        sql.append( " and u.userName like ?" );
        list.add( "%" + ownerName + "%" );
      }
      if( goodType != 0 ){
        sql.append( " and g.goodType = ?" );
        list.add( goodType );
      }
      sql.append( " order by g.id ASC limit ?,?" );
      currentPageNo = ( currentPageNo - 1 ) * pageSize;
      list.add( currentPageNo );
      list.add( pageSize );
      
      Object[] param = list.toArray();
      String s = sql.toString();
      rs = DaoUtils.execute( connection, pstm, rs, s, param );
      while( rs.next() ){
        good = new Good();
        good.setId( rs.getInt( "id" ) );
        good.setGoodName( rs.getString( "goodName" ) );
        good.setGoodCode( rs.getString( "goodCode" ) );
        good.setGoodType( rs.getInt( "goodType" ) );
        good.setInventory( rs.getInt( "inventory" ) );
        good.setOwner( rs.getInt( "owner" ) );
        good.setCreatedBy( rs.getInt( "createdBy" ) );
        good.setCreationDate( rs.getDate( "creationDate" ) );
        good.setModifyBy( rs.getInt( "modifyBy" ) );
        good.setModifyDate( rs.getDate( "modifyDate" ) );
        good.setGoodTypeName( rs.getString( "goodTypeName" ) );
        good.setOwnerName( rs.getString( "ownerName" ) );
        goods.add( good );
      }
      DaoUtils.close( null, pstm, rs );
    }
    return goods;
  }
  
  @Override
  public int getGoodCount( Connection connection, String goodName, String ownerName, int goodType ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    StringBuffer sql = new StringBuffer();
    List<Object> list = new ArrayList<>();
    int count = 0;
    
    if( connection != null ){
      sql.append( "select count(*) as count " +
                      "from good g,good_type t,user u " +
                      "where g.goodType=t.id and g.owner=u.id" );
      
      if( !StringUtils.isNullOrEmpty( goodName ) ){
        sql.append( " and g.goodName like ?" );
        list.add( "%" + goodName + "%" );
      }
      if( !StringUtils.isNullOrEmpty( ownerName ) ){
        sql.append( " and u.userName like ?" );
        list.add( "%" + ownerName + "%" );
      }
      if( goodType != 0 ){
        sql.append( " and g.goodType = ?" );
        list.add( goodType );
      }
      Object[] param = list.toArray();
      String s = sql.toString();
      rs = DaoUtils.execute( connection, pstm, rs, s, param );
      while( rs.next() ){
        count = rs.getInt( "count" );
      }
      DaoUtils.close( null, pstm, rs );
      
    }
    return count;
    
  }
  
  @Override
  public int updateGood( Connection connection, Good good ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "update good g set g.goodName = ?,g.goodCode = ?,g.goodType = ?,g.inventory = ?,g.owner = ?," +
          "g.createdBy = ?,g.creationDate = ?,g.modifyBy = ?,g.modifyDate  = ? where g.id = ?";
      Object[] param = {
          good.getGoodName(), good.getGoodCode(), good.getGoodType(), good.getInventory(), good.getOwner(),
          good.getCreatedBy(),
          good.getCreationDate(), good.getModifyBy(), good.getModifyDate(), good.getId()
      };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    return result;
  }
  
  @Override
  public Good getGoodById( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Good good = null;
    
    if( connection != null ){
      String sql = "select g.*,t.typeName as goodTypeName,u.userName as ownerName " +
          "from good g,good_type t,user u " +
          "where g.goodType=t.id and g.owner=u.id and g.id = ?";
      Object[] param = { id };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        good = new Good();
        good.setId( rs.getInt( "id" ) );
        good.setGoodName( rs.getString( "goodName" ) );
        good.setGoodCode( rs.getString( "goodCode" ) );
        good.setGoodType( rs.getInt( "goodType" ) );
        good.setInventory( rs.getInt( "inventory" ) );
        good.setOwner( rs.getInt( "owner" ) );
        good.setCreatedBy( rs.getInt( "createdBy" ) );
        good.setCreationDate( rs.getDate( "creationDate" ) );
        good.setModifyBy( rs.getInt( "modifyBy" ) );
        good.setModifyDate( rs.getDate( "modifyDate" ) );
        good.setGoodTypeName( rs.getString( "goodTypeName" ) );
        good.setOwnerName( rs.getString( "ownerName" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return good;
  }
  
  @Override
  public Good getGoodByName( Connection connection, String goodName ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Good good = null;
    
    if( connection != null ){
      String sql = "select g.*,t.typeName as goodTypeName,u.userName as ownerName " +
          "from good g,good_type t,user u " +
          "where g.goodType=t.id and g.owner=u.id and g.goodName = ?";
      Object[] param = { goodName };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        good = new Good();
        good.setId( rs.getInt( "id" ) );
        good.setGoodName( rs.getString( "goodName" ) );
        good.setGoodCode( rs.getString( "goodCode" ) );
        good.setGoodType( rs.getInt( "goodType" ) );
        good.setInventory( rs.getInt( "inventory" ) );
        good.setOwner( rs.getInt( "owner" ) );
        good.setCreatedBy( rs.getInt( "createdBy" ) );
        good.setCreationDate( rs.getDate( "creationDate" ) );
        good.setModifyBy( rs.getInt( "modifyBy" ) );
        good.setModifyDate( rs.getDate( "modifyDate" ) );
        good.setGoodTypeName( rs.getString( "goodTypeName" ) );
        good.setOwnerName( rs.getString( "ownerName" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return good;
    
  }
  
  @Override
  public int addGood( Connection connection, Good good ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "insert into good (goodName, goodCode, goodType, inventory, " +
          "owner, createdBy, creationDate, modifyBy, modifyDate) values (?,?,?,?,?,?,?,?,?);";
      Object[] param = {
          good.getGoodName(), good.getGoodCode(), good.getGoodType(), good.getInventory(),
          good.getOwner(), good.getCreatedBy(), good.getCreationDate(), good.getModifyBy(), good.getModifyDate()
      };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public int deleteGood( Connection connection, int id ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "delete from good where id = ?";
      Object[] param = { id };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Override
  public Good getGoodByCode( Connection connection, String code ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Good good = null;
    
    if( connection != null ){
      String sql = "select g.*,t.typeName as goodTypeName,u.userName as ownerName " +
          "from good g,good_type t,user u " +
          "where g.goodType=t.id and g.owner=u.id and g.goodCode = ?";
      Object[] param = { code };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        good = new Good();
        good.setId( rs.getInt( "id" ) );
        good.setGoodName( rs.getString( "goodName" ) );
        good.setGoodCode( rs.getString( "goodCode" ) );
        good.setGoodType( rs.getInt( "goodType" ) );
        good.setInventory( rs.getInt( "inventory" ) );
        good.setOwner( rs.getInt( "owner" ) );
        good.setCreatedBy( rs.getInt( "createdBy" ) );
        good.setCreationDate( rs.getDate( "creationDate" ) );
        good.setModifyBy( rs.getInt( "modifyBy" ) );
        good.setModifyDate( rs.getDate( "modifyDate" ) );
        good.setGoodTypeName( rs.getString( "goodTypeName" ) );
        good.setOwnerName( rs.getString( "ownerName" ) );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return good;
    
    
  }
  
  @Test
  public void test() throws SQLException {
    System.out.println( this.getGoodList( DaoUtils.getConnection(), "", "", 0, 1, 5 ) );
  }
}
