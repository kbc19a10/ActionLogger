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
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">プロフィール確認</h1>
	</div>
	<table>
		<tr>
			<td>ユーザーID :</td>
			<!-- DBのユーザID呼び出し -->
			<td>${userid}</td>
		<tr>
			<td>氏名 :</td>
			<!-- DBの名前呼び出し -->
			<td>${name}</td>
		</tr>
		<tr>
			<td>パスワード :</td>
			<td>*******</td>
		</tr>
		<tr>
			<td>住所 :</td>
			<!-- DBの住所呼び出し -->
			<td>${address}</td>
		</tr>
		<tr>
			<td>電話番号 :</td>
			<!-- DBの電話番号呼び出し -->
			<td>${tel}</td>
		</tr>
		<tr>
			<td>メールアドレス :</td>
			<!-- DBのメールアドレス呼び出し -->
			<td>${email}</td>
		</tr>


	</table>
</body>
</html>