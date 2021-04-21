<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Question</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div>
			<div class="row">
				<h1>${ question.questionText }</h1>
			</div>
			<div class="row">
				<h3>Tags:</h3>
				<div class="col-9 d-flex justify-content-around">
					<c:forEach items="${ question.tags }" var="tag">
						<div class="col-3 btn btn-outline-dark">${ tag.subject }</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="d-flex justify-content-between">
			<div class="col-6 p-0 mt-3 border">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Answers</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items='${ question.answers }' var='answer'>
							<tr>
								<td>
									<h5>${ answer.answerText }</h5>
								</td>
								<td>
									<a href="/answers/delete/${ answer.id }">Delete Answer</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-5 p-0 mt-3 border">
				<div class="col-11 mx-auto my-4">
					
					<div id="createForm">
						<form:form action="/questions/addAnswer" method="post"
							modelAttribute="answer">
							<form:input type="hidden" path="question" value="${ question.id }"/>
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<form:label path="answerText" class="input-group-text">
											Answer:
										</form:label>
									</div>
									<textarea name="answerText" rows="3" cols="40"></textarea>
									<form:errors path="answerText" />
								</div>
							</div>
							<input type="submit" />
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
