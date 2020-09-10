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
		<h1 class="h2">活動記録</h1>
	</div>

	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" action="/ActionLogger/AddActionForm"
				method="post">
				<h4 h3 mb-3 font-weight-normal>行動記録登録</h4>
				<!-- 日付入力、必須 -->
				<div class="mb-3">
					<label for="day">日付</label> <input type="date" class="form-control"
						id="day" name="day" placeholder="日付" 　	required>
					<div class="invalid-feedback">必須</div>
				</div>

				<!-- 開始時刻入力、必須 -->
				<div class="mb-3">
					<label for="starttime">開始時刻</label> <input type="time"
						class="form-control" id="starttime" name="starttime"
						placeholder="開始時刻" 　required>
					<div class="invalid-feedback">必須</div>
				</div>

				<!-- 終了時刻入力、必須 -->
				<div class="mb-3">
					<label for="finishtime">終了時刻</label> <input type="time"
						class="form-control" id="finishtime" name="finishtime"
						placeholder="終了時刻" 　required>
					<div class="invalid-feedback">必須</div>
				</div>

				<!-- 場所入力、必須 -->
				<div class="mb-3">
					<label for="place">場所</label> <input type="text"
						class="form-control" id="place" name="place" placeholder="場所"
						required>
				</div>

				<!-- 理由入力、必須 -->
				<div class="mb-3">
					<label for="reason">理由</label> <input type="text"
						class="form-control" id="reason" name="reason" placeholder="理由"
						required>
				</div>

				<!-- 備考入力、必須 -->
				<div class="mb-3">
					<label for="remark">備考</label> <input type="text"
						class="form-control" id="remark" name="remark" placeholder="備考">
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