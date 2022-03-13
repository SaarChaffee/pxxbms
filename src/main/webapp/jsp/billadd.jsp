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
            <!--div的class 为error是验证错误，ok是验证成功-->
            <input type="hidden" name="method" value="add">
            <div class="">
                <label for="billCode">订单编号：</label>
                <input type="text" name="billCode" class="text" id="billCode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="goodName">商品名称：</label>
                <input type="text" name="goodName" id="goodName" value="">
                <font color="red"></font>
                <input type="hidden" name="goodCode" id="goodCode" value="">
            </div>
            <div>
                <label for="goodPrice">商品单价：</label>
                <input type="text" name="goodPrice" id="goodPrice" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="quantity">商品数量：</label>
                <input type="text" name="quantity" id="quantity" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="totalPrice">总金额：</label>
                <input type="text" name="totalPrice" id="totalPrice" value="" readonly="readonly">
            </div>
            <div>
                <label for="customerCode">顾客账号：</label>
                <input type="text" name="customerCode" id="customerCode" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="customerName">顾客姓名：</label>
                <input type="text" name="customerName" id="customerName" value="" readonly="readonly">
                <input type="hidden" name="customerId" id="customerId" value="">
            </div>
            <div>
                <label for="address">地址：</label>
                <input type="text" name="address" id="address" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="paymentMethod">付款方式：</label>
                <select name="paymentMethod" id="paymentMethod"></select>
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="保存">
                <input type="button" id="back" name="back" value="返回">
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billadd.js"></script>
