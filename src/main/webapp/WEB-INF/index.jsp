<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dojo Overflow</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container border mt-5">
		<div class="my-2">
			<a href="/questions/new" class="btn btn-success mr-3">Add
				Question</a>
		</div>
		<div>
			<table class="table table-hover border">
				<thead>
					<tr>
						<th>Questions</th>
						<th>Tags</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${ questions }' var='question'>
						<tr>
							<td>
								<h5>${ question.questionText }</h5>
							</td>
							<td><c:forEach items="${ question.tags }" var="tag">
									<p>- ${ tag.subject }</p>
								</c:forEach></td>
							<td><a class="btn btn-info mx-2"
								href="/questions/${ question.id }">Show</a> <a
								class="btn btn-danger mx-2"
								href="/questions/delete/${ question.id }">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>