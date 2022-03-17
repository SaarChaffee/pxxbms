var goodName = null;
var goodCode = null;
var inventory = null;
var ownerName = null;
var owner = null;
var goodType = null;
var saveBtn = null;
var backBtn = null;

$(function () {
  goodName = $("#goodName");
  goodCode = $("#goodCode");
  inventory = $("#inventory");
  ownerName = $("#ownerName");
  owner = $("#owner");
  goodType = $("#goodType");
  saveBtn = $("#save");
  backBtn = $("#back");
  
  goodName.next().html("*");
  goodCode.next().html("*");
  inventory.next().html("*");
  ownerName.next().html("*");
  goodType.next().html("*");
  
  
  $.ajax({
    type: "GET",//请求类型
    url: path + "/jsp/good",//请求的url
    data: {method: "gettypelist"},//请求参数
    dataType: "json",//ajax接口（请求url）返回的数据类型
    success: function (data) {//data：返回数据（json对象）
      if (data != null) {
        var tid = $("#tid").val();
        goodType.html("");
        var options = "<option value=\"0\">请选择</option>";
        for (var i = 0; i < data.length; i++) {
          //alert(data[i].id);
          //alert(data[i].typeName);
          if (tid !== null && tid !== undefined && data[i].id === tid) {
            options += "<option selected=\"selected\" value=\"" + data[i].id + "\" >" + data[i].typeName + "</option>";
          } else {
            options += "<option value=\"" + data[i].id + "\" >" + data[i].typeName + "</option>";
          }
          
        }
        goodType.html(options);
      }
    },
    error: function (data) {//当访问时候，404，500 等非200的错误状态码
      validateTip(goodType.next(), {"color": "red"}, imgNo + " 获取商品类型列表error", false);
    }
  });
  
  ownerName.on("focus", function () {
    validateTip(ownerName.next(), {"color": "#666666"}, "* 商家姓名必须是大于1小于10的字符", false);
  }).on("blur", function () {
    $.ajax({
      type: "GET",
      url: path + "/jsp/user",
      data: {method: "isTraderExist", traderName: ownerName.val()},
      dataType: "json",
      success: function (data) {
        if (data.flag) {
          validateTip(ownerName.next(), {"color": "green"}, imgYes, true);
          owner.val(data.oid);
        } else {
          validateTip(ownerName.next(), {"color": "red"}, imgNo + " 商家姓名错误或不存在，请重新输入", false);
        }
      },
      error: function (data) {
        validateTip(ownerName.next(), {"color": "red"}, imgNo + " 获取商家姓名error", false);
      }
    })
  })
  
  goodName.on("focus", function () {
    validateTip(goodName.next(), {"color": "#666666"}, "* 商品名长度必须是大于1小于10的字符", false);
  }).on("blur", function () {
    if (goodName.val() != null && goodName.val() !== "" && goodName.val().length > 1
        && goodName.val().length < 10) {
      validateTip(goodName.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(goodName.next(), {"color": "red"}, imgNo + " 商品名输入的不符合规范，请重新输入", false);
    }
    
  });
  
  goodCode.on("blur", function () {
    if (goodCode.val() !== null && goodCode.val() !== "" && /^\w/.test(goodCode.val())) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/good",
        data: {method: "getGoodByCode", goodCode: goodCode.val()},
        dataType: "json",
        success: function (data) {
          if (data != null) {
            if (data.flag === false || goodCode.val() === data.gCode) {
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
  
  inventory.on("focus", function () {
    validateTip(inventory.next(), {"color": "#666666"}, "* 请输入库存，必须为0或正整数", false);
  }).on("blur", function () {
    var patrn = /^0|\d+$/;
    if (inventory.val() != null && inventory.val() !== "" && inventory.val().match(patrn)) {
      validateTip(inventory.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(inventory.next(), {"color": "red"}, imgNo + " 您输入的库存不正确", false);
    }
  });
  
  goodType.on("focus", function () {
    validateTip(goodType.next(), {"color": "#666666"}, "* 请选择商品类型", false);
  }).on("blur", function () {
    if (goodType.val() != null && goodType !== "" && goodType.val() > 0) {
      validateTip(goodType.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(goodType.next(), {"color": "red"}, imgNo + " 请重新选择商品类型", false);
    }
    
  });
  
  saveBtn.on("click", function () {
    goodName.blur();
    inventory.blur();
    goodCode.blur();
    goodType.blur();
    console.log(goodType.attr("validateStatus"));
    if (goodName.attr("validateStatus") === "true"
        && inventory.attr("validateStatus") === "true"
        && goodCode.attr("validateStatus") === "true"
        && goodType.attr("validateStatus") === "true") {
      if (confirm("是否确认要提交数据？")) {
        $("#goodForm").submit();
      }
    }
  });
  
  backBtn.on("click", function () {
    //alert("modify: "+referer);
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
