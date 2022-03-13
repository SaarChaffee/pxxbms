<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面 >> 商品修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="goodForm" name="goodForm" method="post" action="${pageContext.request.contextPath }/jsp/good.do">
            <input type="hidden" name="method" value="modifyexe">
            <input type="hidden" name="gid" value="${good.id }"/>
            <div>
                <label for="goodName">商品名称：</label>
                <input type="text" name="goodName" id="goodName" value="${good.goodName }">
                <font color="red"></font>
            </div>
            <div>
                <label for="goodCode">商品编号：</label>
                <input type="text" name="goodCode" id="goodCode" value="${good.goodCode}">
                <font color="red"></font>
            </div>
            <div>
                <label>商品类别：</label>
                <input type="hidden" value="${good.goodType }" id="tid"/>
                <select name="goodType" id="goodType"></select>
            </div>
            <div>
                <label for="inventory">库存：</label>
                <input type="text" name="inventory" id="inventory" value="${good.inventory }">
                <font color="red"></font>
            </div>
            <div>
                <label for="ownerName">所有者：</label>
                <input type="text" name="ownerName" id="ownerName" value="${good.ownerName }">
                <input type="hidden" name="owner" id="owner" value="${good.owner}">
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodmodify.js"></script>
