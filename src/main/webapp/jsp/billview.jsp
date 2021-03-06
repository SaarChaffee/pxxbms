<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>订单管理页面 >> 信息查看</span>
    </div>
    <div class="providerView">
        <p><strong>订单编号：</strong><span>${bill.billCode }</span></p>
        <p><strong>商品编号：</strong><span>${bill.goodCode }</span></p>
        <p><strong>商品名称：</strong><span>${bill.goodName }</span></p>
        <p><strong>购买数量：</strong><span>${bill.quantity }</span></p>
        <p><strong>单价：</strong><span>${bill.goodPrice }</span></p>
        <p><strong>总金额：</strong><span>${bill.totalPrice }</span></p>
        <p><strong>顾客账号：</strong><span>${bill.customerCode }</span></p>
        <p><strong>顾客姓名：</strong><span>${bill.customerName }</span></p>
        <p><strong>付款方式：</strong><span>${bill.paymentMethodName}</span></p>
        <p><strong>付款时间：</strong><span>${bill.billTime}</span></p>
        <p><strong>配送时间：</strong><span>${bill.deliveryTime}</span></p>
        <div class="providerAddBtn">
            <input type="button" id="back" name="back" value="返回">
        </div>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billview.js"></script>
