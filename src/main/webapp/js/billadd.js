var billCode = null;
var goodCode = null;
var goodName = null;
var goodPrice = null;
var customerCode = null;
var customerId = null;
var customerName = null;
var quantity = null;
var totalPrice = null;
var paymentMethod = null;
var address = null;
var addBtn = null;
var backBtn = null;
var inventory = 0;

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

//解决精度丢失问题
function accMul(arg1, arg2) {
  var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
  try {
    m += s1.split(".")[1].length
  } catch (e) {
  }
  try {
    m += s2.split(".")[1].length
  } catch (e) {
  }
  return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
}

$(function () {
  billCode = $("#billCode");
  goodCode = $("#goodCode");
  goodName = $("#goodName");
  goodPrice = $("#goodPrice");
  customerCode = $("#customerCode");
  customerId = $("#customerId");
  customerName = $("#customerName");
  quantity = $("#quantity");
  totalPrice = $("#totalPrice");
  paymentMethod = $("#paymentMethod");
  address = $("#address");
  addBtn = $("#add");
  backBtn = $("#back");
  
  //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
  billCode.next().html("*");
  goodCode.next().html("*");
  customerCode.next().html("*");
  quantity.next().html("*");
  goodPrice.next().html("*");
  paymentMethod.next().html("*");
  // address.next().html("*")
  
  $.ajax({
    type: "GET",//请求类型
    url: path + "/jsp/bill",//请求的url
    data: {method: "getPaymentMethodList"},//请求参数
    dataType: "json",//ajax接口（请求url）返回的数据类型
    success: function (data) {//data：返回数据（json对象）
      if (data != null) {
        $("select").html("");//通过标签选择器，得到select标签，适用于页面里只有一个select
        var options = "<option value=\"0\">请选择</option>";
        for (var i = 0; i < data.length; i++) {
          //alert(data[i].id);
          //alert(data[i].typeName);
          options += "<option value=\"" + data[i].id + "\">" + data[i].typeName + "</option>";
        }
        $("select").html(options);
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
  billCode.on("blur", function () {
    if (billCode.val() !== null && billCode.val() !== "") {
      $.ajax({
        type: "GET",
        url: path + "/jsp/bill",
        data: {
          method: "getBillByCode",
          billCode: billCode.val(),
        },
        dataType: "json",
        success: function (data) {
          if (data != null) {
            if (data.flag === false) {
              validateTip(billCode.next(), {"color": "green"}, imgYes, true);
            } else {
              validateTip(billCode.next(), {"color": "red"}, imgNo + " 订单号已被使用，请重新输入", false);
            }
          }
        }
      })
    } else {
      validateTip(billCode.next(), {"color": "red"}, imgNo + " 订单号不能为空，请重新输入", false);
    }
  }).on("focus", function () {
    //显示友情提示
    validateTip(billCode.next(), {"color": "#666666"}, "* 请输入订单号", false);
  }).focus();
  
  goodCode.on("focus", function () {
    validateTip(goodCode.next(), {"color": "#666666"}, "* 请输入商品编号", false);
  }).on("blur", function () {
    if (goodCode.val() !== "" && goodCode.val() !== null) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/good",
        data: {method: "getGoodByCode", goodCode: goodCode.val()},
        dataType: "json",
        success: function (data) {
          if (data.flag) {
            goodCode.val(data.gid);
            inventory = data.inventory;
            goodName.val(data.gName);
            validateTip(goodCode.next(), {"color": "green"}, imgYes + "商品剩余库存为：" + inventory, true);
          } else {
            validateTip(goodCode.next(), {"color": "red"}, imgNo + " 商品不存在，请重新输入", false);
          }
        },
        error: function (data) {
          validateTip(goodCode.next(), {"color": "red"}, imgNo + " 获取商品信息error", false);
        }
      })
    } else {
      validateTip(goodCode.next(), {"color": "red"}, imgNo + " 商品编号称不能为空，请重新输入", false);
    }
    
  });
  
  customerCode.on("focus", function () {
    validateTip(customerCode.next(), {"color": "#666666"}, "* 请输入顾客账户", false);
  }).on("blur", function () {
    if (customerCode.val() !== "" && customerCode.val() !== null) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/user",
        data: {method: "getUserByCode", userCode: customerCode.val()},
        dataType: "json",
        success: function (data) {
          if (data.flag) {
            customerId.val(data.uid);
            customerName.val(data.uname);
            validateTip(customerCode.next(), {"color": "green"}, imgYes, true);
          } else {
            validateTip(customerCode.next(), {"color": "red"}, imgNo + " 顾客姓名错误或不存在，请重新输入", false);
          }
        },
        error: function (data) {
          validateTip(customerCode.next(), {"color": "red"}, imgNo + " 获取顾客姓名error", false);
        }
      })
    } else {
      validateTip(customerCode.next(), {"color": "red"}, imgNo + " 顾客账户不能为空，请重新输入", false);
    }
    
  });
  
  paymentMethod.on("focus", function () {
    validateTip(paymentMethod.next(), {"color": "#666666"}, "* 请选择支付方式", false);
  }).on("blur", function () {
    if (paymentMethod.val() !== null && paymentMethod.val() !== "" && paymentMethod.val() > 0) {
      validateTip(paymentMethod.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(paymentMethod.next(), {"color": "red"}, imgNo + " 支付方式不能为空，请选择", false);
    }
    
  });
  
  quantity.on("focus", function () {
    validateTip(quantity.next(), {"color": "#666666"}, "* 请输入正整数", false);
  }).on("blur", function () {
    if (this.value !== "" && this.value !== null) {
      if (this.value > inventory) {
        validateTip(quantity.next(), {"color": "red"}, "* 库存不足", false);
      } else {
        if (goodPrice.val() !== null && goodPrice.val() !== "") {
          totalPrice.val(accMul(this.value, goodPrice.val()));
        }
        validateTip(quantity.next(), {"color": "green"}, imgYes, true);
      }
    } else {
      validateTip(quantity.next(), {"color": "red"}, "* 格式错误", false);
    }
  });
  
  goodPrice.on("focus", function () {
    validateTip(goodPrice.next(), {"color": "#666666"}, "* 请输入大于0的正自然数，小数点后保留2位", false);
  }).on("keyup", function () {
    this.value = priceReg(this.value);
  }).on("blur", function () {
    this.value = priceReg(this.value);
    if (this.value !== "" && this.value !== null) {
      //解决精度丢失问题
      validateTip(goodPrice.next(), {"color": "green"}, imgYes, true);
      if (quantity.val() !== "" && quantity.val() !== null) {
        totalPrice.val(accMul(this.value, quantity.val()));
      }
    } else {
      validateTip(goodPrice.next(), {"color": "red"}, "* 格式错误请重新输入", false);
    }
  });
  
  addBtn.on("click", function () {
    if (billCode.attr("validateStatus") !== "true") {
      billCode.blur();
    } else if (goodCode.attr("validateStatus") !== "true") {
      goodCode.blur();
    } else if (goodPrice.attr("validateStatus") !== "true") {
      goodPrice.blur();
    } else if (quantity.attr("validateStatus") !== "true") {
      quantity.blur();
    } else if (customerCode.attr("validateStatus") !== "true") {
      customerCode.blur();
    } else if (paymentMethod.attr("validateStatus") !== "true") {
      paymentMethod.blur();
    } else {
      if (confirm("是否确认提交数据")) {
        $("#billForm").submit();
      }
    }
  });
  
  backBtn.on("click", function () {
    if (referer !== undefined
        && null !== referer
        && "" !== referer
        && "null" !== referer
        && referer.length > 4) {
      window.location.href = referer;
    } else {
      history.back(-1);
    }
  });
});
