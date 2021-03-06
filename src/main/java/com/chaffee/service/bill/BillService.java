/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.service.bill;

import com.chaffee.entity.Bill;

import java.util.List;

public interface BillService {
  public List<Bill> getBillList( String goodName, String customerName, int paymentMethod,
                                 int currentPageNo,
                                 int pageSize );
  
  public int getBillCount( String goodName, String customerName, int paymentMethod );
  
  public boolean updateBill( int id, Bill bill );
  
  public Bill getBillById( int id );
  
  public boolean addBill( int id, Bill bill );
  
  public boolean deleteBill( int id );
  
  public Bill getBillByCode(String billCode);
}
