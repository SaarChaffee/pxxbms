<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>订单管理页面 >> 订单添加页面</span>
    </div>
    <div class="providerAdd">
        <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/bill.do">
            <input type="hidden" name="method" value="modifysave">
            <input type="hidden" name="id" value="${bill.id }">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <div class="">
                <label for="billCode">订单编号：</label>
                <input type="text" name="billCode" id="billCode" value="${bill.billCode }">
            </div>
            <div>
                <label>顾客姓名：</label>
                <input style="color: black" id="customerCode" readonly="readonly" value="${bill.customerName }">
            </div>
            <div>
                <label for="goodName">商品名称：</label>
                <input style="color: black" id="goodName" readonly="readonly" value="${bill.goodName }">
            </div>
            <div>
                <label for="quantity">商品数量：</label>
                <input type="text" name="quantity" id="quantity" value="${bill.quantity }">
                <font color="red"></font>
            </div>
            <div>
                <label for="goodPrice">商品价格：</label>
                <input type="text" name="goodPrice" id="goodPrice" value="${bill.goodPrice }">
                <font color="red"></font>
            </div>
            <div>
                <label for="totalPrice">总金额：</label>
                <input type="text" name="totalPrice" readonly="readonly" id="totalPrice" value="${bill.totalPrice}">
            </div>
            <div>
                <label>付款方式：</label>
                <!-- 列出所有的角色分类 -->
                <input type="hidden" value="${bill.paymentMethod }" id="rid"/>
                <select name="paymentMethod" id="paymentMethod"></select>
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="save" id="save" value="保存">
                <input type="button" id="back" name="back" value="返回">
            </div>
        </form>
    </div>

</div>
</section>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billmodify.js"></script>
