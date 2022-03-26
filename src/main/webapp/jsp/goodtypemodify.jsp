<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>商品类型管理页面 >> 商品类型修改页面</span>
  </div>
  <div class="providerAdd">
    <form id="goodTypeForm" name="goodTypeForm" method="post" action="${pageContext.request.contextPath }/jsp/good">
      <input type="hidden" name="method" value="typemodifyexe">
      <input type="hidden" name="gtid" value="${goodType.id }"/>
      <div>
        <label for="typeName">商品类型名称：</label>
        <input type="text" name="typeName" id="typeName" value="${goodType.typeName }">
        <font color="red"></font>
      </div>
      <div>
        <label for="typeCode">商品类型编号：</label>
        <input type="text" name="typeCode" id="typeCode" value="${goodType.typeCode}">
        <font color="red"></font>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存"/>
        <input type="button" id="back" name="back" value="返回"/>
      </div>
    </form>
  </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodtypemodify.js"></script>
