<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="page" value="${_page}" scope="request"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 数据分页开始 -->
<script type="text/javascript">
	//查询当前页数 点击go按钮
	function $$$submit(){
		var t=document._$pform['curPage'];
		if(t.value==""){
			alert("请输入页数");
			return false;
		}
		if(t.value>${page.totalPages}||t.value<1){
			alert("总共有  ${page.totalPages}  页数据,不能跳转到第   "+t.value+"  个页面")
			return false;
		}
		document._$pform.submit();
	}
	/*
	function $$go(n){
	   var t=document._$pform['_page.curPage'];
	   t.value=n;		
	   document._$pform.submit();
	   
	}*/
</script>

<form action="${pageUrl}" method="post"  name="_$pform">
			<!-- 分页查询带条件 beg 改动需通知-->
			<c:forEach items="${_page.params}" var="d">
				<input name="${d.key}" value="${d.value}" type="hidden"/>
			</c:forEach>
			<!-- 分页查询带条件 end -->
			共${_page.totalRows}条 /
		    &nbsp;共 ${_page.totalPages} 页
		    <!-- 首页按钮 -->
			<input type="button" value="首页" style="border: 0;" onClick="javascript:this.form['curPage'].value=${_page.firstPage},this.form.submit()" />
			<!-- 上一页按钮 -->
			<input type="button" value="上一页" style="border: 0;" onClick="javascript:this.form['curPage'].value=${_page.previousPage},this.form.submit()" />
			  <input type="text" class="pagetext" value="${_page.curPage }" onkeyup="value=value.replace(/[^\d]/g,'')" name="curPage"/>
			<!-- 下一页按钮 -->
			<input type="button" value="下一页" class="y2" style="border: 0;" onClick="javascript:this.form['curPage'].value=${_page.nextPage},this.form.submit()" />
			
			<!-- 尾页按钮 -->
			<input type="button" value="尾页" class="y1" style="border: 0;" onClick="javascript:this.form['curPage'].value=${_page.lastPage},this.form.submit()" /></li>
			<!-- 跳转按钮 -->
            <input name="" type="button" class="go"  value="GO" id="t1" onclick="$$$submit()"/>               

</form>
<!-- 数据分页结束-->