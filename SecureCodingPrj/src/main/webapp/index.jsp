<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@property['redirect.url']" var="reurl" />
<!DOCTYPE html>
<html lang="ko">
<head>
<title>welcome</title>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/index.js"></script>
</head>
<body>
<input type="hidden" id="reurl" name="reurl" value="${reurl}">
</body>
</html>
