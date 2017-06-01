<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- spring 的form标签 -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理</title>

  </head>
  
  <body>
  	
  	<sf:form method="post" modelAttribute="user" enctype="multipart/form-data">
  		username:<sf:input path="username"/> <sf:errors path="username"/><br>
  		password:<sf:password path="password"/> <sf:errors path="password"/><br>
  		email:<sf:input path="email"/> <sf:errors path="email"/><br>
  		Attach:<input type="file" name="attachs"/><br>
  		Attach:<input type="file" name="attachs"/><br>
  		<input type="submit" value="添加用户"/>
  	</sf:form>
  
  </body>
</html>
