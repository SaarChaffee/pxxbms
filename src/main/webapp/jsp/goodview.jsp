<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 信息查看</span>
    </div>
    <div class="providerView">
        <p><strong>商品编号：</strong><span>${good.goodCode }</span></p>
        <p><strong>商品名称：</strong><span>${good.goodName }</span></p>
        <p><strong>商品类型：</strong><span>${good.goodTypeName }</span></p>
        <p><strong>库存：</strong><span>${good.inventory }</span></p>
        <p><strong>商家账号：</strong><span>${good.owner }</span></p>
        <p><strong>商家姓名：</strong><span>${good.ownerName }</span></p>
        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回">
        </div>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodview.js"></script>
