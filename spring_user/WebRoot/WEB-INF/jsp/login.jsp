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
  	
  	<form  method="post">
  		username:<input type="text" name="username"/><br>
  		password:<input type="password" name="password"/><br>
  		<input type="submit" value="登录"/>
  	</form>
  
  </body>
</html>
