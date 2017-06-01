<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理</title>

  </head>
  
  <body>
  <c:if test="${not empty loginUser}">
  <a href="add">添加</a>
  <a href="logout">退出</a>
  当前用户：${loginUser.username }
  </c:if>
  
  <br>
  	<c:forEach items="${_page.data }" var="um">
  		<a href="${um.id }">${um.username}</a>------${um.password}---------${um.email}
  		<a href="${um.id }/update">修改</a><a href="${um.id }/delete">删除</a>
  		<br>
  	
  	</c:forEach>
  	<c:set var="pageUrl" value="users" scope="request"></c:set>
  	<jsp:include page="/WEB-INF/jsp/pager.jsp"></jsp:include>
  </body>
</html>
