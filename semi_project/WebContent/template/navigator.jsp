<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
			<a class="navbar-brand" href="/main.jsp">Menu Selector Guide</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/main.jsp">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=storeAllList">레스토랑</a></li>
					<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=randSelect">레스토랑 추천</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">검색할 장소를 눌러주세요 ! <span class="caret"></span></a>
					<ul class="dropdown-menu" >
							<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=storeList&loc=유스">유스페이스</a></li>
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=storeList&loc=스퀘어">H스퀘어</a></li>
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=storeList&loc=삼환">삼환하이펙스</a></li>
					</ul>
				</li>
				
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>