var goodTypeObj;

function deleteGoodType(obj) {
  $.ajax({
    type: "GET",
    url: path + "/jsp/good",
    data: {method: "delGoodType", gtid: obj.attr("goodtypeid")},
    dataType: "json",
    success: function (data) {
      if (data.delResult === "true") {//删除成功：移除删除行
        cancleBtn();
        window.location.href = path + "/jsp/good?method=querygoodtype";
      } else if (data.delResult === "false") {//删除失败
        changeDLGContent("对不起，删除商品类型【" + obj.attr("goodtypename") + "】失败");
      } else if (data.delResult === "notexist") {
        changeDLGContent("对不起，商品类型【" + obj.attr("goodtypename") + "】不存在");
      }
    },
    error: function (data) {
      changeDLGContent("对不起，删除失败");
    }
  })
}

function openYesOrNoDLG() {
  $('.zhezhao').css('display', 'block');
  $('#removeUse').fadeIn();
}

function cancleBtn() {
  $('.zhezhao').css('display', 'none');
  $('#removeUse').fadeOut();
}

function changeDLGContent(contentStr) {
  var p = $(".removeMain").find("p");
  p.html(contentStr);
}

$(function () {
  //通过jquery的class选择器（数组）
  //对每个class为viewUser的元素进行动作绑定（click）
  /**
   * bind、live、delegate
   * on
   */
  
  $(".modifyGoodType").on("click", function () {
    var obj = $(this);
    window.location.href = path + "/jsp/good?method=modifygoodtype&gtid=" + obj.attr("goodtypeid");
  });
  
  $('#no').click(function () {
    cancleBtn();
  });
  
  $('#yes').click(function () {
    deleteGoodType(goodTypeObj);
  });
  
  $(".deleteGoodType").on("click", function () {
    goodTypeObj = $(this);
    changeDLGContent("你确定要删除商品类型【" + goodTypeObj.attr("goodtypename") + "】吗？");
    openYesOrNoDLG();
  });
  
});
