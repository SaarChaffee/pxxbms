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
  
  @Test
  public void test() throws Exception {
    System.out.println( this.getGoodTypeList( DaoUtils.getConnection() ) );
  }
}
