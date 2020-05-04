<%@page import="com.authority.model.AuthVO"%>
<%@page import="com.authority.model.AuthService"%>
<%@page import="com.emp_auth.model.EaService"%>
<%@page import="com.emp_auth.model.EaVO"%>
<%@page import="com.administrator.model.AdmVO"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>

<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO");

%>
<%   AdmVO empVO  =(AdmVO)session.getAttribute("empVO"); %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Just強身</title>

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/back_end/vendor/fontawesome-free/css/all.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back_end/css/sb-admin-2.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/authBtn.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back_end/css/switchBtn.css" rel="stylesheet">
<style type="text/css">
#logoutModal{
	color:rgb(0,0,0);
}
body {
	background: url(<%=request.getContextPath()%>/back_end/img/back.jpg);
	background-size: cover;
	color: rgb(255, 255,255);
}

.text-gray-600 {
	color: rgb(255, 255, 255) !important;
}

#wrapper #content-wrapper {
	background-color: rgba(0, 0, 0, 0);
}

.rotate-n-15 {
	transform: none;
}

.fa-laugh-wink:before {
	content: none;
}

.fa-laugh-wink img {
	width: 50%;
	height: 50%;
}

.card {
	background-color: rgba(25, 25, 25, 0.4);
	border: 1px none rgb(25, 25, 25, 0.4);
}

.bg-white {
	background-color: rgba(0, 0, 0, 0.2) !important;
}

.card-header {
	background-color:rgba(0, 0, 0, 0.3);
}

.fa-cog:before {
	content: url(<%=request.getContextPath()%>/back_end/img/user.png);
}

.fa-wrench:before {
	content:url(<%=request.getContextPath()%>/back_end/img/employee.png);
}

.fa-folder:before {
	content:url(<%=request.getContextPath()%>/back_end/img/shop.png);
}

.fa-point-area {
	content:url(<%=request.getContextPath()%>/back_end/img/debit-card.png);
}

.fa-chart-area:before {
	content:url(<%=request.getContextPath()%>/back_end/img/save-money.png);
}

.fa-table:before {
	content: url(<%=request.getContextPath()%>/back_end/img/news.png);
}

.fa-report:before {
	content:url(<%=request.getContextPath()%>/back_end/img/information-icon.png);
}

#info {
	color:#0000D6;
}

table {
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
	color: #ffe153;
	font-size: 25px;
}

table, th, td {
	border: 2px double #000088;
}

th, td {
	padding: 5px;
	text-align: center;
}

a{
font-size:20px;
}
</style>


</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="index.jsp">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"> <img src="<%=request.getContextPath()%>/back_end/img/yt.png">
					</i>
				</div>
				<div class="sidebar-brand-text mx-3">Just強身</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="index.jsp">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>後臺管理員</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
<!-- ****************************功能項目開始************************************************************************** -->
<hr class="sidebar-divider">
<div class="sidebar-heading">功能項目</div>
<% 
EaService eaSvc1=new EaService();
Set<EaVO> empAuth=eaSvc1.getAuthsByEmp(empVO.getEmp_id());
for(EaVO set:empAuth){
String Auth=set.getAuth_id();
System.out.println(Auth);
pageContext.setAttribute("Auth",Auth);%>

<!-- Nav Item - Pages Collapse Menu -->

<%if(Auth.equals("AU00003")){ %>

	<li class="nav-item" value="AU00003">
	<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo"> 
		<i class="fas fa-fw fa-cog"></i> <span>帳戶管理</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
			<a class="collapse-item" href="buttons.html">帳戶查詢</a> 
			<a class="collapse-item" href="cards.html">教練審核</a>
			</div>
			</div>
	</li>
<%	}else if(Auth.equals("AU00004")){%>
 		<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item" value="AU00004">
			<a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities"> 
			<i class="fas fa-fw fa-wrench"></i> <span>員工管理</span>
			</a>
			<div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<h6 class="collapse-header">員工管理:</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/adm/SelectEmp.jsp">查詢員工資料</a> 
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/adm/update_Emp_Auth.jsp">修改員工權限</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/adm/AddEmp.jsp">新增員工</a>
				</div>
				</div>
			</li>
<%}else if(Auth.equals("AU00005")) {%>

		 <li class="nav-item"value="AU00005">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
              <i class="fas fa-fw fa-folder"></i>
              <span>購物商城管理</span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">購物商城管理主頁:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product/select_Product.jsp">商品管理</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product/select_Product.jsp">訂單管理</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product/listAllProduct.jsp">所有商品</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product_order/listAllProduct_Order.jsp">所有商品訂單</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp">新增商品</a>
              </div>
            </div>
          </li>
  <%}else if(Auth.equals("AU00001")) { %> 
		
			<!-- Nav Item - Charts -->
		
			<li class="nav-item" value="AU00001">
			<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsepoint"
				aria-expanded="true" aria-controls="collapsepoint">
			 <i class="fas fa-fw fa-point-area"></i> <span>點數管理</span></a>
				<div id="collapsepoint" class="collapse" aria-labelledby="headingpoint" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">會員儲值管理</h6>
						<a class="collapse-item" href="login.html">會員儲值金額 </a>
						<h6 class="collapse-header">教練提領管理</h6>
						<a class="collapse-item" href="login.html">匯款</a>
						<a class="collapse-item" href="login.html">匯款紀錄</a>
					</div>
				</div>
			</li>
	<%}else if(Auth.equals("AU00002")) { %> 
	 <li class="nav-item" value="AU00002">
			<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseReport"
				aria-expanded="true" aria-controls="collapseReport"> 
				<i class="fas fa-fw fa-report"></i> <span>檢舉管理</span></a>
				<div id="collapseReport" class="collapse" aria-labelledby="headingReport" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/reportcoach/SelectReportCoach.jsp">檢舉教練</a>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/reportmember/SelectReportMember.jsp">檢舉會員</a>
						<a class="collapse-item" href="login.html">檢舉文章</a> 
						<a class="collapse-item" href="login.html">檢舉揪團</a> 
						<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/reportcourse/SelectReportCourse.jsp">檢舉課程</a>
 				</div>
 			</li>

<%}else{}%>

<%}%>
<!-- Nav Item - Tables -->
		<li class="nav-item"><a class="nav-link collapsed" href="#"data-toggle="collapse" data-target="#collapseAd" aria-expanded="true" aria-controls="collapseAd">
		 <i class="fas fa-fw fa-table"></i> <span>前台管理</span></a>
			<div id="collapseAd" class="collapse" aria-labelledby="headingAd" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
			<a class="collapse-item" href="<%=request.getContextPath()%>/back_end/news/SelectNews.jsp">最新消息管理</a>
			</div>
		</li>
<!-- ****************************功能項目結束************************************************************************** -->
	
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="Search for..." aria-label="Search"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
								<span class="badge badge-danger badge-counter">3+</span>
						</a> <!-- Dropdown - Alerts -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 class="dropdown-header">Alerts Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-primary">
											<i class="fas fa-file-alt text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 12, 2019</div>
										<span class="font-weight-bold">A new monthly report is
											ready to download!</span>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-success">
											<i class="fas fa-donate text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 7, 2019</div>
										$290.29 has been deposited into your account!
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-warning">
											<i class="fas fa-exclamation-triangle text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 2, 2019</div>
										Spending Alert: We've noticed unusually high spending for your
										account.
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Show All Alerts</a>
							</div></li>

						<!-- Nav Item - Messages -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
								<!-- Counter - Messages --> <span
								class="badge badge-danger badge-counter">7</span>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="messagesDropdown">
								<h6 class="dropdown-header">Message Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
										<div class="status-indicator bg-success"></div>
									</div>
									<div class="font-weight-bold">
										<div class="text-truncate">Hi there! I am wondering if
											you can help me with a problem I've been having.</div>
										<div class="small text-gray-500">Emily Fowler · 58m</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">
										<div class="status-indicator"></div>
									</div>
									<div>
										<div class="text-truncate">I have the photos that you
											ordered last month, how would you like them sent to you?</div>
										<div class="small text-gray-500">Jae Chun · 1d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">
										<div class="status-indicator bg-warning"></div>
									</div>
									<div>
										<div class="text-truncate">Last month's report looks
											great, I am very happy with the progress so far, keep up the
											good work!</div>
										<div class="small text-gray-500">Morgan Alvarez · 2d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
										<div class="status-indicator bg-success"></div>
									</div>
									<div>
										<div class="text-truncate">Am I a good boy? The reason I
											ask is because someone told me that people say this to all
											dogs, even if they aren't good...</div>
										<div class="small text-gray-500">Chicken the Dog · 2w</div>
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Read More Messages</a>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

 <!--********************************* 個人資訊開始********************************************* -->
<li class="nav-item dropdown no-arrow">
<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> 
		<span class="mr-2 d-none d-lg-inline text-gray-600 small">${empVO.emp_name}</span>
<img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/EmpReaderPicServlet?emp_id=${empVO.emp_id}"> 
</a> 
<!-- ************************************個人資訊結束 **************************************************************************************-->
	
<!-- 	********************************************************** -->
	<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
		 <a class="dropdown-item" href="#"> <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile </a> 
		 <a class="dropdown-item" href="#"> <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Settings </a> 
		 <a class="dropdown-item" href="#"> <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Activity Log </a>
	<div class="dropdown-divider"></div>
		 <a class="dropdown-item" data-toggle="modal" data-target="#logoutModal"> 
		 <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>登出 </a>
 	</div>
 	</li>
	</ul>
	</nav>
<!--  	********************************************************************************************************** -->
 	
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
				<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					 <h4><a href="<%=request.getContextPath()%>/back_end/adm/update_Emp_Auth.jsp">回到修改員工權限</a></h4>
					<table class="newstable">
						<tr>
						<th>員工編號</th>
						<th>員工姓名</th>
						<th style="width:60%;">員工權限</th>

					</tr>

			<tr>
			<% 
				EaService eaSvc = new EaService();
				EaVO eaVO = new EaVO();
				Set<EaVO> set = eaSvc.getAuthsByEmp(admVO.getEmp_id());
				
				AuthService authSvc = new AuthService();
				AuthVO authVO1 = new AuthVO();
				List<AuthVO> list = authSvc.getAll();
				pageContext.setAttribute("list",list);
			%>
			
				<td>${admVO.emp_id}</td>
				<td>${admVO.emp_name}</td>
<!-- 				=================================================================================================== -->
			<td>
	
		<%
		int count=0;
		String authId=null ,authIdByList;
		
		for(EaVO a:set){
			authId += a.getAuth_id();
		}
		
		for(AuthVO b:list){
			authIdByList=b.getAuth_id();
			count++;
			String name = "authid"+ Integer.toString(count);
			if(authId.contains(authIdByList)){%>
			<div>
			<p style="display: inline-block; width: 220px;"><%=b.getAuth_name()%></p>
		<div class="authArea" style="display: inline-block; width: 327px;">
			<div class='wrappera'>
  				<div role='button' class='retro-btn lg primary'>
    				<a class='btn' id="<%=name+"new"%>"style="cursor: not-allowed;"> 
      					<span class='btn-inner'>
        					<span class='content-wrapper'>
          						<span class='btn-content' id="new" name="<%=name%>" value="<%=authIdByList %>"style="pointer-events: none;">
            						
            						<span class='btn-content-inner' style="font-size: 1.4em;font-family:'test';pointer-events: none;" label='新增權限'>
            						
            						</span>
          						</span>
        					</span>
      					</span>
    				</a>
  				</div>
			</div>
			
			<div class='wrappera'>
  				<div role='button' class='retro-btn lg primary'>
    				<a class='btn'id="<%=name+"del"%>" style="cursor: pointer;"> 
      					<span class='btn-inner'>
        					<span class='content-wrapper'>
          						<span class='btn-content' id="del" name="<%=name%>" value="<%=authIdByList %>" >
            						<span class='btn-content-inner' style="font-size: 1.4em;font-family:'test';pointer-events: auto;" label='刪除權限'>
            						
            						</span>
          						</span>
        					</span>
      					</span>
    				</a>
  				</div>
			</div>
		</div>
			</div>
			<%}else {%>
				<div>
			<p style="display: inline-block; width: 220px;"><%=b.getAuth_name()%></p>
			<div class="authArea" style="display: inline-block; width: 327px;">
				<div class='wrappera' >
  				<div role='button' class='retro-btn lg primary'>
    				<a class='btn'id="<%=name+"new"%>" style="cursor: pointer;"> 
      					<span class='btn-inner'>
        					<span class='content-wrapper'>
          						<span class='btn-content' id="new" name="<%=name%>" value="<%=authIdByList %>" >
            						<span class='btn-content-inner' style="font-size: 1.4em;font-family:'test';pointer-events: auto;" label='新增權限'>
            						
            						</span>
          						</span>
        					</span>
      					</span>
    				</a>
  				</div>
			</div>
			
			<div class='wrappera'>
  				<div role='button' class='retro-btn lg primary'>
    				<a class='btn'id="<%=name+"del"%>"style="cursor: not-allowed;"> 
      					<span class='btn-inner'>
        					<span class='content-wrapper'>
          						<span class='btn-content' id="del" name="<%=name%>" value="<%=authIdByList %>" style="pointer-events: none;">
            						<span class='btn-content-inner' style="font-size: 1.4em;font-family:'test';pointer-events: none;" label='刪除權限' >

            						</span>
          						</span>
        					</span>
      					</span>
    				</a>
  				</div>
			</div>
		</div>
			</div>
			<%}
			
		}%>

			</td>
		</tr>
</table>


			<!-- Scroll to Top Button-->
			<a class="scroll-to-top rounded" href="#page-top"> <i
				class="fas fa-angle-up"></i>
			</a>
<!-- Footer -->
					<footer class="sticky-footer bg-white">
						<div class="container my-auto">
							<div class="copyright text-center my-auto">
								<span>Copyright &copy; Your Website 2019</span>
							</div>
						</div>
					</footer>
					<!-- End of Footer -->

				</div>
				<!-- End of Content Wrapper -->

			</div>
			<!-- End of Page Wrapper -->
	<!--************************************** Logout 開始 ********************************************************************************************************* -->
			<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">登出</h5>
							<button class="close" type="button" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">確定登出按下登出鍵</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
				<a class="btn btn-primary " style="width: 12%;"
				<span>
				<form action="<%=request.getContextPath()%>/back_end/adm/adm.do" method="POST">
				<input type="submit" value="登出" style="position: absolute;background-color: rgb(0,0,0,0); border-color: rgb(0,0,0,0);z-index:2;width: 8%;height:15%; margin-left: -4%;">
				<input type="hidden" name="action" value="signout"> 
				</form>
				</span>></a>
						</div>
					</div>
				</div>
			</div>
			<!--***************************************************** Logout 結束*****************************************************************************  -->
<!-- Bootstrap core JavaScript-->

  <script src="<%=request.getContextPath()%>/back_end/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back_end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back_end/js/sb-admin-2.min.js"></script>




</body>
	<script>
	var buttons = document.querySelectorAll('.btn');

	for(var i = 0; i < buttons.length; i++) {
	  // Click
	  buttons[i].addEventListener('mousedown', function() {
	     this.classList.add('btn-active');
	  });
	  buttons[i].addEventListener('mouseup', function() {
	     this.classList.remove('btn-active');
	  });

	  // Hover
	  buttons[i].addEventListener('mouseleave', function() {
	     this.classList.remove('btn-center', 'btn-right', 'btn-left', 'btn-active');
	  });

	  buttons[i].addEventListener("mousemove", function(e) {
	     var leftOffset = this.getBoundingClientRect().left;
	     var btnWidth = this.offsetWidth;
	     var myPosX = e.pageX;
	     var newClass = "";
	     // if on left 1/3 width of btn
	     if(myPosX < (leftOffset + .3 * btnWidth) ) {
	       newClass = 'btn-left'
	     } else {
	       // if on right 1/3 width of btn
	       if(myPosX > (leftOffset + .65 * btnWidth) ) {
	         newClass = 'btn-right';
	       } else {
	         newClass = 'btn-center';
	       }
	     }
	     // remove prev class
	     var clearedClassList = this.className.replace(/btn-center|btn-right|btn-left/gi, "").trim();
	     this.className = clearedClassList + " " + newClass;
	  });
	}


	//
	// ---Retro Submit Button---
	//
	var pButton = document.querySelector('.loader-button');

	// Click
	// pButton.addEventListener('mousedown', function() {
	//   this.classList.add('btn-active');
	// });
	// pButton.addEventListener('mouseup', function() {
	//   this.classList.remove('btn-active');
	// });


	// Classie Helper Functions
	// https://github.com/desandro/classie

	function classReg( className ) {
	  return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
	}

	// classList support for class management
	// altho to be fair, the api sucks because it won't accept multiple classes at once
	var hasClass, addClass, removeClass;

	if ( 'classList' in document.documentElement ) {
	  hasClass = function( elem, c ) {
	     return elem.classList.contains( c );
	  };
	  addClass = function( elem, c ) {
	     elem.classList.add( c );
	  };
	  removeClass = function( elem, c ) {
	     elem.classList.remove( c );
	  };
	}
	else {
	  hasClass = function( elem, c ) {
	     return classReg( c ).test( elem.className );
	  };
	  addClass = function( elem, c ) {
	     if ( !hasClass( elem, c ) ) {
	       elem.className = elem.className + ' ' + c;
	     }
	  };
	  removeClass = function( elem, c ) {
	     elem.className = elem.className.replace( classReg( c ), ' ' );
	  };
	}

	function toggleClass( elem, c ) {
	  var fn = hasClass( elem, c ) ? removeClass : addClass;
	  fn( elem, c );
	}

	var classie = {
	  // full names
	  hasClass: hasClass,
	  addClass: addClass,
	  removeClass: removeClass,
	  toggleClass: toggleClass,
	  // short names
	  has: hasClass,
	  add: addClass,
	  remove: removeClass,
	  toggle: toggleClass
	};

	// transport
	if ( typeof define === 'function' && define.amd ) {
	  // AMD
	  define( classie );
	} else {
	  // browser global
	  window.classie = classie;
	}


	// Loader Progress Functionality

	function extend( a, b ) {
	  for( var key in b ) { 
	     if( b.hasOwnProperty( key ) ) {
	       a[key] = b[key];
	     }
	  }
	  return a;
	}

	function LoaderButton( el, options ) {
	  this.button = el;
	  this.options = extend( {}, this.options );
	  extend( this.options, options );
	  this._init();
	}

	LoaderButton.prototype.options = {
	  statusTime : 1500
	};

	LoaderButton.prototype._init = function() {
	  this._create();
	  this._initEvents();
	};

	LoaderButton.prototype._create = function() {
	  var textEl = document.createElement( 'span' );
	  textEl.className = 'content';
	  textEl.innerHTML = this.button.innerHTML;
	  var progressEl = document.createElement( 'span' );
	  progressEl.className = 'progress';
	  
	  var progressInnerEl = document.createElement( 'span' );
	  progressInnerEl.className = 'progress-inner';
	  progressEl.appendChild( progressInnerEl );
	  
	  this.button.innerHTML = '';

	  this.button.appendChild( textEl );
	  this.button.appendChild( progressEl );

	  // element for progress bar
	  this.progress = progressInnerEl;

	  this.progressProp = 'width';
	  this._enable();
	};

	LoaderButton.prototype._setProgress = function( val ) {
	  this.progress.style[ this.progressProp ] = 100 * val + '%';
	};

	LoaderButton.prototype._initEvents = function() {
	  var self = this;
	  this.button.addEventListener( 'click', function() {
	     // disable button
	     self.button.setAttribute( 'disabled', '' );
	     // add class state-loading to button
	     classie.remove( self.progress, 'notransition' );
	     classie.add( this, 'state-loading' );

	     setTimeout( function() {
	       if( typeof self.options.callback === 'function' ) {
	         self.options.callback( self );
	       }
	       else {
	         self._setProgress( 1 );
	         var onEndTransFn = function( ev ) {
	           if( ev.propertyName !== self.progressProp ) { 
	             return; 
	           }
	           this.removeEventListener( 'transitionend', onEndTransFn );
	           self._stop();
	         };

	         self.progress.addEventListener( 'transitionend', onEndTransFn );
	       }
	     }, 
	     200 ); // 200ms timeout
	  } );
	};

	LoaderButton.prototype._stop = function( status ) {
	  var self = this;

	  setTimeout( function() {
	     // fade out progress bar
	     self.progress.style.opacity = 0;
	     var onEndTransFn = function( ev ) {
	       if( ev.propertyName !== 'opacity' ) return;
	       this.removeEventListener( 'transitionend', onEndTransFn );
	       classie.add( self.progress, 'notransition' );
	       self.progress.style[ self.progressProp ] = '0%';
	       self.progress.style.opacity = 1;
	     };
	    
	     self.progress.addEventListener( 'transitionend', onEndTransFn );
	    
	     // add class state-success to button
	     if( typeof status === 'number' ) {
	       var statusClass = status >= 0 ? 'state-success' : 'state-error';
	       classie.add( self.button, statusClass );
	       // after options.statusTime remove status icon
	       setTimeout( function() {
	         classie.remove( self.button, statusClass );
	         self._enable();
	       }, self.options.statusTime );
	     }
	     else {
	       self._enable();
	     }

	     // remove class state-loading from the button
	     classie.remove( self.button, 'state-loading' );
	  }, 100 );
	};

	// enable button
	LoaderButton.prototype._enable = function() {
	  this.button.removeAttribute( 'disabled' );
	}

	// add to global namespace
	window.LoaderButton = LoaderButton;


	// Initialize Submit Button

	[].slice.call( document.querySelectorAll( 'button.loader-button' ) ).forEach( function( bttn ) {
	  new LoaderButton( bttn, {
	     callback : function( instance ) {
	       var progress = 0,
	           interval = setInterval( function() {
	             // progress is randomly calculated
	             progress = Math.min( progress + Math.random() * 0.1, 1 );
	             instance._setProgress( progress );

	             if( progress === 1 ) {
	               instance._stop(1);
	               clearInterval( interval );
	             }
	           }, 200 ); // 200ms interval
	     }
	  } );
	} );

	</script>
	
	
	
  <script type="text/javascript">

	var as = "span[class*=\"btn-content\"]";
	$(as).click(function(event){
		var stat = event.target.getAttribute("id"); 
		var b = event.target.getAttribute("name"); 
		var authid = event.target.getAttribute("value"); 
		
		
// 		var changeA = "a[id=\""+b+"new\"]";
// 		var changeB = "a[id=\""+b+"del\"]";
// 		var changePointA ="span[id=\"new\"],span[name=\""+b+"\"]";
// 		var changePointB ="span[id=\"del\"],span[name=\""+b+"\"]";
		
		if(stat=="new"){
// 			$(changeB).css("cursor","pointer");
// 			$(changeA).css("cursor","not-allowed");
// 			$(changeA).attr("id",b+"del");
// 			$(changeB).attr("id",b+"new");
			
// 			$(changePointA).css("pointer-events","none");
// 			$(changePointB).css("pointer-events","auto");
// 		alert("新增中");
		$.ajax({
			url:"adm.do",
			type:"post",
			data:{
				action:'AddAuth',
				empid:'${admVO.emp_id}',
				authid:authid
			},
			success:function(data){
				alert("add");
			}
		});
	}else{
		
// 		$(changeA).css("cursor","pointer");
// 		$(changeB).css("cursor","not-allowed");
// 		$(changeA).attr("id",b+"del");
// 		$(changeB).attr("id",b+"new");
		
// 		$(changePointB).css("pointer-events","none");
// 		$(changePointA).css("pointer-events","auto");
// 		alert("刪除中");
		$.ajax({
			url:"adm.do",
			type:"post",
			data:{
				action:'DeleteAuth',
				empid:'${admVO.emp_id}',
				authid:authid
			},
			success:function(data){
				alert("delete");
			}
		});
	}
});

  </script>
</html>
