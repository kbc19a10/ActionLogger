<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<!-- Custom styles for this template -->
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
</head>
<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">グループ</h1>
	</div>

	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/NewGroupFrom"
				method="post">
				<h4 h3 mb-3 font-weight-normal>グループ作成</h4>

				<!-- 名前入力、必須 -->
				<div class="mb-3">
					<label for="gname">名前</label> <input type="text"
						class="form-control" id="gname" name="gname" placeholder="名前"
						　	required>
					<div class="invalid-feedback">必須</div>
				</div>


				<%-- フォームの正当性確認データ --%>
				<input type="hidden" name="vKey" value="${validationKey.value}">
				<input type="submit" class="btn btn-secondary btn-block btn-lg"
					id="enterRoom" value="登録"></input>
			</form>
		</div>
		<div class="col"></div>

	</div>
</body>
</html>