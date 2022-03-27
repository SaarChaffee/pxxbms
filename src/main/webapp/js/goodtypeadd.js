var typeCode = null;
var typeName = null;
var addBtn = null;
var backBtn = null;

$(function () {
  typeCode = $("#typeCode");
  typeName = $("#typeName");
  addBtn = $("#add");
  backBtn = $("#back");
  
  //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
  typeCode.next().html("*");
  typeName.next().html("*");
  
  /*
   * 验证
   * 失焦\获焦
   * jquery的方法传递
   */
  typeCode.on("blur", function () {
    if (typeCode.val() !== null && typeCode.val() !== "" && typeCode.val() > 0 && /^\d+$/.test(typeCode.val())) {
      $.ajax({
        type: "GET",
        url: path + "/jsp/good",
        data: {method: "getGoodTypeByCode", typeCode: typeCode.val()},
        dataType: "json",
        success: function (data) {
          if (data != null) {
            if (data.flag === false) {
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
  
  typeName.on("focus", function () {
    validateTip(typeName.next(), {"color": "#666666"}, "* 请输入商品类型名称", false);
  }).on("blur", function () {
    if (typeName.val() != null && typeName.val() !== "") {
      validateTip(typeName.next(), {"color": "green"}, imgYes, true);
    } else {
      validateTip(typeName.next(), {"color": "red"}, imgNo + " 商品类型名称不能为空，请重新输入", false);
    }
    
  });
  
  
  addBtn.on("click", function () {
    if (typeCode.attr("validateStatus") !== "true") {
      typeCode.blur();
    } else if (typeName.attr("validateStatus") !== "true") {
      typeName.blur();
    } else {
      if (confirm("是否确认提交数据")) {
        $("#goodTypeForm").submit();
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
