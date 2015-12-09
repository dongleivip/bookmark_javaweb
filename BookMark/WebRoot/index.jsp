<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Author" content="donglei">
  <meta name="Keywords" content="bookmark">
  <meta name="Description" content="JS Bookmark Management">
  <title>BookMarks Manage</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script type="text/javascript" src="js/libs/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="js/libs/json2.js"></script>
  <script type="text/javascript" src="js/main.js"></script>
  <script type="text/javascript">var ctx = "${ctx}";</script>
</head>
 <body>
	<div id="page">
		<h1>BookMark Manage</h1>
		<div class="search">
			<input id="keyword" type="text" maxlength="20" placeholder="Key Words..."/>
			<span id="resultcount"></span>
 		</div>
		<div id="bookmarks">
			<ul id="list">
			</ul>
		</div>
		
		<div id="add_div">
		  <form id="form1" action="${ctx}/servlet/addBookMark">
		  <table>
		  	<tr>
		  	  <td>书签名称</td>
		  	  <td><input type="text" id="title" name="title"/></td>
		  	</tr>
		  	<tr>
		  	  <td>书签地址</td>
		  	  <td><input type="text" id="url" name="url"/></td>
		  	</tr>
		  	<tr>
		  	  <td colspan="2"><span id="errorMsg"></span></td>
		  	</tr>
		  	<tr><td colspan="2"><input type="button" id="btn_add" value="添加" onclick="javascript:addBookMark();"></td></tr>
		  </table>
		  </form>
		</div>
		
		<div>
			<input type="button" id="btn_query" value="查询" onclick="quertBookMarks();" />
		</div>
	</div>
  
 </body>
</html>
