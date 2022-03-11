/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/11
 */
package com.chaffee.service.bill;

import com.chaffee.entity.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
  
  public List<PaymentMethod> getPaynentMethodList();
}
