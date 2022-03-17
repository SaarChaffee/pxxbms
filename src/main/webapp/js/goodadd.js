var goodCode = null;
var goodName = null;
var ownerCode = null;
var ownerId = null;
var ownerName = null;
var inventory = null;
var goodType = null;
var addBtn = null;
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
  goodCode = $("#goodCode");
  goodName = $("#goodName");
  ownerCode = $("#ownerCode");
  ownerId = $("#ownerId");
  ownerName = $("#ownerName");
  inventory = $("#inventory");
  goodType = $("#goodType");
  addBtn = $("#add");
  backBtn = $("#back");
  
  //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
  goodCode.next().html("*");
  goodName.next().html("*");
  ownerCode.next().html("*");
  inventory.next().html("*");
  goodType.next().html("*");
  
  $.ajax({
    type: "GET",//请求类型
    url: path + "/jsp/good",//请求的url
    data: {method: "gettypelist"},//请求参数
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
      validateTip(goodType.next(), {"color": "red"}, imgNo + " 获取商品类型列表error", false);
    }
  });
  /*
   * 验证
   * 失焦\获焦
   * jquery的方法传递
   */
  goodCode.on("blur", function () {
    if (goodCode.val() !== null && goodCode.val() !== "" && /^\w/.test(goodCode.val())) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/good",
        data: {method: "getGoodByCode", goodCode: goodCode.val()},
        dataType: "json",
        success: function (data) {
          if (data != null) {
            if (data.flag === false) {
              console.log(data.gid)
              validateTip(goodCode.next(), {"color": "green"}, imgYes, true);
            } else {
              validateTip(goodCode.next(), {"color": "red"}, imgNo + " 商品编号已存在，请重新输入", false);
            }
          }
        }
      })
    } else {
      validateTip(goodCode.next(), {"color": "red"}, imgNo + " 商品编号不能为空或格式错误，请重新输入", false);
    }
  }).on("focus", function () {
    //显示友情提示
    validateTip(goodCode.next(), {"color": "#666666"}, "* 请输入商品编号，由字母和数字组成", false);
  }).focus();
  
  goodName.on("focus", function () {
    validateTip(goodName.next(), {"color": "#666666"}, "* 请输入商品名称", false);
  }).on("blur", function () {
    if (goodName.val() != null && goodName.val() !== "") {
      validateTip(goodName.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(goodName.next(), {"color": "red"}, imgNo + " 商品名称不能为空，请重新输入", false);
    }
    
  });
  
  ownerCode.on("focus", function () {
    validateTip(ownerCode.next(), {"color": "#666666"}, "* 请输入商家账户", false);
  }).on("blur", function () {
    if (ownerCode.val() != null && ownerCode.val() !== "") {
      $.ajax({
        type: "GET",
        url: path + "/jsp/user",
        data: {method: "getUserByCode", userCode: ownerCode.val()},
        dataType: "json",
        success: function (data) {
          if (data.flag) {
            ownerId.val(data.uid);
            ownerName.val(data.uname);
            validateTip(ownerCode.next(), {"color": "green"}, imgYes, true);
          } else {
            validateTip(ownerCode.next(), {"color": "red"}, imgNo + " 商家姓名错误或不存在，请重新输入", false);
          }
        },
        error: function (data) {
          validateTip(ownerCode.next(), {"color": "red"}, imgNo + " 获取商家姓名error", false);
        }
      })
    } else {
      validateTip(ownerCode.next(), {"color": "red"}, imgNo + " 商家账户不能为空，请重新输入", false);
    }
  });
  
  goodType.on("focus", function () {
    validateTip(goodType.next(), {"color": "#666666"}, "* 请选择商品类型", false);
  }).on("blur", function () {
    if (goodType.val() != null && goodType.val() !== "" && goodType.val() !== 0) {
      validateTip(goodType.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(goodType.next(), {"color": "red"}, imgNo + " 商品类型不能为空，请选择", false);
    }
    
  });
  
  inventory.on("focus", function () {
    validateTip(inventory.next(), {"color": "#666666"}, "* 请输入正整数", false);
  }).on("blur", function () {
    if (this.value != null && inventory.val() !== null && /^\d+/.test(this.value)) {
      validateTip(inventory.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(inventory.next(), {"color": "red"}, imgNo + " 库存不能为空或格式错误，请重新输入", false);
      
    }
  });
  
  
  addBtn.on("click", function () {
    if (goodCode.attr("validateStatus") !== "true") {
      goodCode.blur();
    } else if (goodName.attr("validateStatus") !== "true") {
      goodName.blur();
    } else if (ownerCode.attr("validateStatus") !== "true") {
      ownerCode.blur();
    } else if (goodType.attr("validateStatus") !== "true") {
      goodType.blur();
    } else {
      if (confirm("是否确认提交数据")) {
        $("#goodForm").submit();
      }
    }
  });
  
  backBtn.on("click", function () {
    if (referer !== undefined
        && null != referer
        && "" !== referer
        && "null" !== referer
        && referer.length > 4) {
      window.location.href = referer;
    } else {
      history.back(-1);
    }
  });
});
