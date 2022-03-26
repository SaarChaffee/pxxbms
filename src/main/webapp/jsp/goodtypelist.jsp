<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>商品类型管理页面</span>
  </div>
  <div class="search">
    <form>
      <a href="${pageContext.request.contextPath}/jsp/goodtypeadd.jsp">添加商品类型</a>
    </form>
  </div>
  <!--商品类型-->
  <table class="providerTable" cellpadding="0" cellspacing="0">
    <tr class="firstTr">
      <th width="20%">类型编号</th>
      <th width="30%">类型名称</th>
    </tr>
    <c:forEach var="goodtype" items="${goodTypeList }" varStatus="status">
      <tr>
        <td>
          <span>${goodtype.typeCode }</span>
        </td>
        <td>
          <span>${goodtype.typeName }</span>
        </td>
        <td>
          <span><a class="modifyGoodType" href="javascript:;"
                   goodtypeid=${goodtype.id } goodtypename=${goodtype.typeName }><img
                  src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
          <span><a class="deleteGoodType" href="javascript:;"
                   goodtypeid=${goodtype.id } goodtypename=${goodtype.typeName }><img
                  src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
  <div class="removerChid">
    <h2>提示</h2>
    <div class="removeMain">
      <p>你确定要删除该商品吗？</p>
      <a href="#" id="yes">确定</a>
      <a href="#" id="no">取消</a>
    </div>
  </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodtypelist.js"></script>
