<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>商品管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/good">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>商品名：</span>
            <input name="queryGoodName" class="input-text" type="text" value="${queryGoodName}">
            <span>商家名：</span>
            <input name="queryOwnerName" class="input-text" type="text" value="${queryOwnerName}">

            <span>商品类型：</span>
            <select name="queryGoodType">
                <c:if test="${goodTypeList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="type" items="${goodTypeList}">
                        <option
                                <c:if test="${type.id == queryGoodType}">selected="selected"</c:if>
                                value="${type.id}">${type.typeName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="${pageContext.request.contextPath}/jsp/goodadd.jsp">添加商品</a>
        </form>
    </div>
    <!--商品-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">商品编号</th>
            <th width="20%">商品名称</th>
            <th width="10%">商品类型</th>
            <th width="10%">库存</th>
            <th width="20%">所有者</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="good" items="${goodList }" varStatus="status">
            <tr>
                <td>
                    <span>${good.goodCode }</span>
                </td>
                <td>
                    <span>${good.goodName }</span>
                </td>
                <td>
                    <span>${good.goodTypeName}</span>
                </td>
                <td>
                    <span>${good.inventory}</span>
                </td>
                <td>
                    <span>${good.ownerName}</span>
                </td>
                <td>
                    <span><a class="viewGood" href="javascript:;" goodid=${good.id } goodname=${good.goodName }><img
                            src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyGood" href="javascript:;" goodid=${good.id } goodname=${good.goodName }><img
                            src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteGood" href="javascript:;" goodid=${good.id } goodname=${good.goodName }><img
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodlist.js"></script>
