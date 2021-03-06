<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>레스토랑 등록</title>
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- font awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/font-awesome.min.css"
	media="screen" title="no title" charset="utf-8">
<!-- Custom style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/style.css"
	media="screen" title="no title" charset="utf-8">
<script src="//code.jquery.com/jquery.min.js"></script>

<style type="text/css">
#divArea {
	background-color: white;
	border: 1px solid #000;
}

#idForm {
	margin: auto;
	padding: 5px;
}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("#sName").keyup(function(){
			var textId =$("#sName");
			//alert(textId.val());
				$.ajax({
					type : "get",
					url : "${pageContext.request.contextPath}/DispatcherServlet?command=storeNameCheck",
					data : "storeName="+textId.val(),
					success : function(userData) {
						//alert(userData);
						if(userData =="false"){
							$("#checkResult").html("<font color='red'>사용 가능</font>");
						}
						else if(userData=="true"){
							$("#checkResult").html("<font color='red'>사용 불가능</font>");
						} 
					}
			});
		});
	});
	
	function cancel() {
		location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=adminStore";
	}
</script>
</head>
<body
	background="${pageContext.request.contextPath}/bootstrap/img/bg.jpg">
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<article class="container" id="divArea">
			<div class="page-header">
				<h1>
					레스토랑 등록 <small>MSG form</small>
				</h1>
			</div>
			<!-- 세션이 있을 떄, 없을 때로 나눠서 하기 -->
			<div class="col-lg-8 col-lg-offset-2">
				<form role="form" id="idForm"
					action="${pageContext.request.contextPath}/DispatcherServlet?command=insertStore"
					enctype="multipart/form-data" method="post">
					<div class="form-group">
						<label for="userid" id="id">이름</label>
						<div class="input-group">
							<input type="text" class="form-control" id="sName"
								required="required" name="storeName" placeholder="이름">
								<span id="checkResult"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="location">건물</label> <input type="text"
							class="form-control" id="sPla" placeholder="건물"
							required="required" name="storePla">
					</div>
					<div class="form-group">
						<label for="detail">주소</label> <input type="text"
							class="form-control" id="sLoc" placeholder="주소"
							required="required" name="storeLoc">
					</div>
					<div class="form-group">
						<label for="tel">전화번호</label> <input type="text"
							class="form-control" id="sTel" required="required"
							name="storeTel" placeholder="전화번호">
					</div>
					<div class="form-group">
						<label for="hour">영업시간</label> <input type="text"
							class="form-control" id="shour" required="required"
							name="storeHour" placeholder="영업시간">
					</div>
					<div class="form-group">
						<label for="file">가게 사진 업로드</label> <span
							class="glyphicons glyphicons-file-plus"></span><input type="file"
							name="attachedFile" required="required">
					</div>
					<!-- <table class="table table-bordered"> -->
					<table class="table table-bordered" >
							<tr>
								<td>메뉴<input type="text"  placeholder="1"readonly="readonly" class="form-control"></td>
								<td>메뉴이름 <input type="text" class="form-control"
									required="required" name="menuName1" placeholder="메뉴"></td>
								<td>메뉴가격 <input type="text" class="form-control"
									required="required" name="menuPrice1" placeholder="가격"></td>
								<td>메뉴사진 <span class="glyphicons glyphicons-file-plus"></span><input
									type="file" name="menuPic1" required="required"></td>
							</tr>
							<tr>
								<td>메뉴<input type="text"  placeholder="2" readonly="readonly" class="form-control"></td>
								<td>메뉴이름 <input type="text" class="form-control"
									required="required" name="menuName2" placeholder="메뉴"></td>
								<td>메뉴가격 <input type="text" class="form-control"
									required="required" name="menuPrice2" placeholder="가격"></td>
								<td>메뉴사진 <span class="glyphicons glyphicons-file-plus"></span><input
									type="file" name="menuPic2" required="required"></td>
							</tr>
							<tr>
								<td>메뉴<input type="text"  placeholder="3" readonly="readonly" class="form-control"></td>
								<td>메뉴이름 <input type="text" class="form-control"
									required="required" name="menuName3" placeholder="메뉴"></td>
								<td>메뉴가격 <input type="text" class="form-control"
									required="required" name="menuPrice3" placeholder="가격"></td>
								<td>메뉴사진 <span class="glyphicons glyphicons-file-plus"></span><input
									type="file" name="menuPic3" required="required"></td>
							</tr>
					</table>
					<div class="form-group text-center">
						<button type="submit" class="btn btn-info">
							등록<i class="fa fa-check spaceLeft"></i>
						</button>
						<!-- <input type="hidden" name="command" value="insertStore"> -->
						<button type="submit" class="btn btn-warning" id="cancelBtn"
							onclick="cancel()">
							취소<i class="fa fa-times spaceLeft"></i>
						</button>
					</div>
				</form>
			</div>
		</article>
	</div>
</body>
</html>