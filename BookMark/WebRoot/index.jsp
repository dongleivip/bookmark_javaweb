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
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
  <link rel="stylesheet" href="css/dialog/ui-dialog.css" type="text/css" />
  <script type="text/javascript" src="js/libs/jquery-1.9.0.js"></script>
  <script type="text/javascript" src="js/dialog.js"></script>
  <script type="text/javascript" src="js/libs/dialog-min.js"></script>
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
		  <!-- <span id="addBtn" class="addBtn" onclick="javascript:showAddBox();">添加</span> -->
		  <input id="addBtn" type="button" class="addBtn" onclick="javascript:showAddBox();" value="添加">
		</div>
		<div id="pager"></div>
	</div>
 </body>
</html>
