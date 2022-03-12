var goodObj;

function deleteGood(obj) {
  $.ajax({
    type: "GET",
    url: path + "/jsp/good.do",
    data: {method: "delGood", uid: obj.attr("goodid")},
    dataType: "json",
    success: function (data) {
      if (data.delResult == "true") {//删除成功：移除删除行
        cancleBtn();
        obj.parents("tr").remove();
      } else if (data.delResult == "false") {//删除失败
        changeDLGContent("对不起，删除商品【" + obj.attr("goodname") + "】失败");
      } else if (data.delResult == "notexist") {
        changeDLGContent("对不起，商品【" + obj.attr("goodname") + "】不存在");
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
  $(".viewGood").on("click", function () {
    //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
    var obj = $(this);
    window.location.href = path + "/jsp/good.do?method=view&gid=" + obj.attr("goodid");
  });
  
  $(".modifyGood").on("click", function () {
    var obj = $(this);
    window.location.href = path + "/jsp/good.do?method=modify&gid=" + obj.attr("goodid");
  });
  
  $('#no').click(function () {
    cancleBtn();
  });
  
  $('#yes').click(function () {
    deleteUser(goodObj);
  });
  
  $(".deleteGood").on("click", function () {
    goodObj = $(this);
    changeDLGContent("你确定要删除用户【" + goodObj.attr("goodname") + "】吗？");
    openYesOrNoDLG();
  });
  
});
