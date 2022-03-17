<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 商品添加页面</span>
    </div>
    <div class="providerAdd">
        <form id="goodForm" name="goodForm" method="post" action="${pageContext.request.contextPath }/jsp/good">
            <!--div的class 为error是验证错误，ok是验证成功-->
            <input type="hidden" name="method" value="add">
            <div class="">
                <label for="goodCode">商品编号：</label>
                <input type="text" name="goodCode" class="text" id="goodCode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="goodName">商品名称：</label>
                <input type="text" name="goodName" id="goodName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="inventory">商品库存：</label>
                <input type="text" name="inventory" id="inventory" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="ownerCode">商家账号：</label>
                <input type="text" name="ownerCode" id="ownerCode" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="ownerName">商家姓名：</label>
                <input type="text" name="ownerName" id="ownerName" value="" readonly="readonly">
                <input type="hidden" name="ownerId" id="ownerId" value="">
            </div>
            <div>
                <label for="goodType">商品类型：</label>
                <select name="goodType" id="goodType"></select>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodadd.js"></script>
