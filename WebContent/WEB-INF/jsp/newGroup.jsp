<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">グループ表示</h1>
</div>

<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>名前</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="newgroup" items="${newgroupname}">
				<tr>
					<!-- データベースから呼び出し  -->
					<!-- 名前 -->
					<td><c:out value="${newgroup.gname}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
