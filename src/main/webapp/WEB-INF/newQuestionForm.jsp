<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Question</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="col-7 mx-auto my-5">
		<h1>Create Question</h1>
		<div id="createForm">
			<form:form action="/questions/new" method="post"
				modelAttribute="question">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-prepend">
							<form:label path="questionText" class="input-group-text">Question:</form:label>
						</div>
						<textarea name="questionText" rows="5" cols="90"></textarea>
						<form:errors path="questionText" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-prepend">
							<form:label path="parsedTag" class="input-group-text">Tags:</form:label>
						</div>
						<form:input type="text" path="parsedTag" class="form-control" />
						<form:errors path="parsedTag" />
					</div>
				</div>
				<input type="submit" />
			</form:form>
		</div>
	</div>
</body>
</html>