var billCode = null;
var goodName = null;
var customerCode = null;
var quantity = null;
var goodPrice = null;
var totalPrice = null;
var paymentMethod = null;
var saveBtn = null;
var backBtn = null;

function priceReg(value) {
  value = value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
  value = value.replace(/^\./g, "");  //验证第一个字符是数字而不是.
  value = value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
  value = value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");//去掉特殊符号￥
  if (value.indexOf(".") > 0) {
    value = value.substring(0, value.indexOf(".") + 3);
  }
  return value;
}


$(function () {
  billCode = $("#billCode");
  goodName = $("#goodName");
  customerCode = $("#customerCode");
  quantity = $("#quantity");
  goodPrice = $("#goodPrice");
  totalPrice = $("#totalPrice")
  paymentMethod = $("#paymentMethod");
  addBtn = $("#save");
  backBtn = $("#back");
  
  //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
  billCode.next().html("*");
  goodName.next().html("*");
  customerCode.next().html("*");
  quantity.next().html("*");
  goodPrice.next().html("*");
  totalPrice.next().html("*")
  paymentMethod.next().html("*");
  
  $.ajax({
    type: "GET",//请求类型
    url: path + "/jsp/bill.do",//请求的url
    data: {method: "getPaymentMethodList"},//请求参数
    dataType: "json",//ajax接口（请求url）返回的数据类型
    success: function (data) {//data：返回数据（json对象）
      if (data != null) {
        var rid = $("#rid").val();
        paymentMethod.html("");
        var options = "<option value=\"0\">请选择</option>";
        for (var i = 0; i < data.length; i++) {
          //alert(data[i].id);
          //alert(data[i].roleName);
          if (rid != null && rid != undefined && data[i].id == rid) {
            options += "<option selected=\"selected\" value=\"" + data[i].id + "\" >" + data[i].typeName + "</option>";
          } else {
            options += "<option value=\"" + data[i].id + "\" >" + data[i].typeName + "</option>";
          }
          
        }
        paymentMethod.html(options);
      }
    },
    error: function (data) {//当访问时候，404，500 等非200的错误状态码
      validateTip(paymentMethod.next(), {"color": "red"}, imgNo + " 获取支付方式列表error", false);
    }
  });
  /*
   * 验证
   * 失焦\获焦
   * jquery的方法传递
   */
  
  goodName.on("focus", function () {
    validateTip(goodName.next(), {"color": "#666666"}, "* 请输入商品名称", false);
  }).on("blur", function () {
    if (goodName.val() != null && goodName.val() != "") {
      validateTip(goodName.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(goodName.next(), {"color": "red"}, imgNo + " 商品名称不能为空，请重新输入", false);
    }
    
  });
  
  customerCode.on("focus", function () {
    validateTip(customerCode.next(), {"color": "#666666"}, "* 请输入顾客账户", false);
  }).on("blur", function () {
    if (customerCode.val() != null && customerCode.val() != "") {
      validateTip(customerCode.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(customerCode.next(), {"color": "red"}, imgNo + " 顾客账户不能为空，请重新输入", false);
    }
    
  });
  
  paymentMethod.on("focus", function () {
    validateTip(paymentMethod.next(), {"color": "#666666"}, "* 请选择付款方式", false);
  }).on("blur", function () {
    if (paymentMethod.val() != null && paymentMethod.val() != "" && paymentMethod.val() != 0) {
      validateTip(paymentMethod.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(paymentMethod.next(), {"color": "red"}, imgNo + " 付款方式不能为空，请选择", false);
    }
    
  });
  
  quantity.on("focus", function () {
    validateTip(quantity.next(), {"color": "#666666"}, "* 请输入正整数", false);
  }).on("keyup", function () {
    this.value = priceReg(this.value);
  }).on("blur", function () {
    this.value = priceReg(this.value);
    if (this.value != null && goodPrice.value != null) {
      totalPrice.value = this.value * goodPrice.value;
    }
  });
  
  goodPrice.on("focus", function () {
    validateTip(goodPrice.next(), {"color": "#666666"}, "* 请输入大于0的正自然数，小数点后保留2位", false);
  }).on("keyup", function () {
    this.value = priceReg(this.value);
  }).on("blur", function () {
    this.value = priceReg(this.value);
    this.value = priceReg(this.value);
    if (this.value != null && quantity.value != null) {
      totalPrice.value = this.value * quantity.value;
    }
  });
  
  
  addBtn.on("click", function () {
    goodName.blur();
    customerCode.blur();
    paymentMethod.blur();
    if (goodName.attr("validateStatus") == "true"
        && customerCode.attr("validateStatus") == "true"
        && paymentMethod.attr("validateStatus") == "true") {
      if (confirm("是否确认提交数据")) {
        $("#billForm").submit();
      }
    }
  });
  
  backBtn.on("click", function () {
    //alert("modify: "+referer);
    if (referer != undefined
        && null != referer
        && "" != referer
        && "null" != referer
        && referer.length > 4) {
      window.location.href = referer;
    } else {
      history.back(-1);
    }
  });
});
