var typeName = null;
var typeCode = null;
var saveBtn = null;
var backBtn = null;

$(function () {
  typeName = $("#typeName");
  typeCode = $("#typeCode");
  saveBtn = $("#save");
  backBtn = $("#back");
  
  typeName.next().html("*");
  typeCode.next().html("*");
  
  typeName.on("focus", function () {
    validateTip(typeName.next(), {"color": "#666666"}, "* 类型名称长度必须是大于1小于10的字符", false);
  }).on("blur", function () {
    if (typeName.val() != null && typeName.val() !== "" && typeName.val().length > 1
        && typeName.val().length < 10) {
      validateTip(typeName.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(typeName.next(), {"color": "red"}, imgNo + " 类型名称输入的不符合规范，请重新输入", false);
    }
    
  });
  
  typeCode.on("blur", function () {
    if (typeCode.val() !== null && typeCode.val() !== "" && /^\w/.test(typeCode.val())) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/good",
        data: {method: "getGoodTypeByCode", typeCode: typeCode.val()},
        dataType: "json",
        success: function (data) {
          if (data != null) {
            if (data.flag === false || typeCode.val() === data.typeCode) {
              validateTip(typeCode.next(), {"color": "green"}, imgYes, true);
            } else {
              validateTip(typeCode.next(), {"color": "red"}, imgNo + " 商品类型编号已存在，请重新输入", false);
            }
          }
        }
      })
    } else {
      validateTip(typeCode.next(), {"color": "red"}, imgNo + " 商品类型编号不能为空或格式错误，请重新输入", false);
    }
  }).on("focus", function () {
    //显示友情提示
    validateTip(typeCode.next(), {"color": "#666666"}, "* 请输入商品类型编号，由字母和数字组成", false);
  }).focus();
  
  
  saveBtn.on("click", function () {
    typeName.blur();
    typeCode.blur();
    if (typeName.attr("validateStatus") === "true"
        && typeCode.attr("validateStatus") === "true") {
      if (confirm("是否确认要提交数据？")) {
        $("#goodTypeForm").submit();
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
