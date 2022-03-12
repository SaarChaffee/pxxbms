<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>订单管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/bill.do">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商品名称：</span>
            <input name="queryGoodName" type="text" value="${queryGoodName }">

            <span>顾客姓名：</span>
            <input name="queryname" class="input-text" type="text" value="${queryCustomerName}">

            <span>付款方式：</span>
            <select name="queryPaymentMethod">
                <c:if test="${paymentMethodList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="paymentMethod" items="${paymentMethodList}">
                        <option
                                <c:if test="${paymentMethod.id == queryPaymentMethod}">selected="selected"</c:if>
                                value="${paymentMethod.id}">${paymentMethod.typeName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath }/jsp/billadd.jsp">添加订单</a>
        </form>
    </div>
    <!--账单表格 样式和供应商公用-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">订单编号</th>
            <th width="20%">商品名称</th>
            <th width="10%">顾客姓名</th>
            <th width="10%">订单金额</th>
            <th width="10%">付款方式</th>
            <th width="10%">创建时间</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="bill" items="${billList }" varStatus="status">
            <tr>
                <td>
                    <span>${bill.billCode }</span>
                </td>
                <td>
                    <span>${bill.goodName }</span>
                </td>
                <td>
                    <span>${bill.customerName}</span>
                </td>
                <td>
                    <span>${bill.totalPrice}</span>
                </td>
                <td>
                    <span>${bill.paymentMethodName}</span>
                </td>
                <td>
					<span>
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                </td>
                <td>
                    <span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img
                            src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img
                            src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img
                            src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>
