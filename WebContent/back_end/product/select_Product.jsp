<%@page import="com.administrator.model.AdmVO"%>
<%@page import="com.emp_auth.model.EaVO"%>
<%@page import="java.util.Set"%>
<%@page import="com.emp_auth.model.EaService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%   AdmVO empVO  =(AdmVO)session.getAttribute("empVO"); %>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="product_imageSvc" scope="page" class="com.product_image.model.Product_ImageService" />
<jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_OrderService" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>購物商城管理首頁</title>
    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/back_end/vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/back_end/css/sb-admin-2.css" rel="stylesheet">
    <style type="text/css">
     a{
			 
			font-size: 20px;
			text-align: center;
			}
    	.text-primary {
    		color: #ffffff !important;
    		font-size: 30px;
    		text-align: center;
		}
		.table {
  			color: #6ce0f2;
    		font-size: 20px;
    		text-align: center;
    		font-family:cursive;
		}
    body {
    background: url(<%=request.getContextPath()%>/back_end/img/back.jpg);
    background-size: cover;
    color: rgb(0,0,255);
    font-size: 20px;
    }
    .text-gray-600 {
    color: rgb(255,255,255) !important;
    }
    #wrapper #content-wrapper {
    background-color: rgba(0,0,0,0);
    }
    .rotate-n-15{
    transform: none;
    }
    .fa-laugh-wink:before{
    content: none;
    }
    .fa-laugh-wink img{
    width: 50%;
    height: 50%;
    }
    .card{
    background-color: rgba(25,25,25,0.4);
    border: 1px none rgb(25,25,25,0.4);
    }
    .bg-white {
    background-color: rgba(0,0,0,0.2) !important;
    }
    .card-header {
    background-color: rgba(0,0,0,0.3);
    }
    .fa-cog:before {
    content: url(<%=request.getContextPath()%>/back_end/img/user.png);
    }
    .fa-wrench:before {
    content:url(<%=request.getContextPath()%>/back_end/img/employee.png);
    }
    .fa-folder:before {
    content: url(<%=request.getContextPath()%>/back_end/img/shop.png);
    }
    .fa-point-area {
    content: url(<%=request.getContextPath()%>/back_end/img/debit-card.png);
    }
    .fa-chart-area:before {
    content: url(<%=request.getContextPath()%>/back_end/img/save-money.png);
    }
    .fa-table:before {
    content:url(<%=request.getContextPath()%>/back_end/img/news.png);
    }
    .fa-report:before {
    content:url(<%=request.getContextPath()%>/back_end/img/information-icon.png);
    }
    .addProduct-1{
    margin: 0% 0% 0% 0%;
    padding: 10px;
    float: left;
    width: 100%;
    height: 170px;
    overflow: auto;
    }
    .addProduct-1>.addProduct-1-1{
    float: left;
    padding: 10px;
    margin-right: 10px;
    font-size: 20px;
    }
    div>.errorMsgs{
    float: left;
    padding: 10px;
    font-size: 20px;
    color: #00FFB2;
    }
    .dataTable{
    clear: both;
    }
    #table_btn{
    position:absolute;
    bottom: 3px;
    right: 20px;
    }
    </style>
  </head>
  <body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      <!-- Sidebar -->
      <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
          <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink">
            <img src="<%=request.getContextPath()%>/back_end/img/yt.png">
            </i>
          </div>
          <div class="sidebar-brand-text mx-3">Just強身</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
          <a class="nav-link" href="index.html">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>後臺管理員</span></a>
          </li>
          <!-- Divider -->
          <hr class="sidebar-divider">
          <!-- Heading -->
 <!-- ****************************功能項目開始************************************************************************** -->
<hr class="sidebar-divider">
<div class="sidebar-heading">功能項目</div>
<% 
EaService eaSvc=new EaService();
Set<EaVO> empAuth=eaSvc.getAuthsByEmp(empVO.getEmp_id());
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
                      <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                        </button>
                        <!-- Topbar Search -->
                        <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                          <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
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
                          <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                              <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                  <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                  <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                    </button>
                                  </div>
                                </div>
                              </form>
                            </div>
                          </li>
                          <!-- Nav Item - Alerts -->
                          <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              <i class="fas fa-bell fa-fw"></i>
                              <!-- Counter - Alerts -->
                              <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                              <h6 class="dropdown-header">
                              Alerts Center
                              </h6>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-primary">
                                    <i class="fas fa-file-alt text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 12, 2019</div>
                                  <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-success">
                                    <i class="fas fa-donate text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 7, 2019</div>
                                  $290.29 has been deposited into your account!
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                  <div class="icon-circle bg-warning">
                                    <i class="fas fa-exclamation-triangle text-white"></i>
                                  </div>
                                </div>
                                <div>
                                  <div class="small text-gray-500">December 2, 2019</div>
                                  Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                              </a>
                              <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                          </li>
                          <!-- Nav Item - Messages -->
                          <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              <i class="fas fa-envelope fa-fw"></i>
                              <!-- Counter - Messages -->
                              <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                              <h6 class="dropdown-header">
                              Message Center
                              </h6>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                  <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
                                  <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                  <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>
                                  <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                  <img class="rounded-circle" src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">
                                  <div class="status-indicator"></div>
                                </div>
                                <div>
                                  <div class="text-truncate">I have the photos that you ordered last month, how would you like them sent to you?</div>
                                  <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                  <img class="rounded-circle" src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">
                                  <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                  <div class="text-truncate">Last month's report looks great, I am very happy with the progress so far, keep up the good work!</div>
                                  <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                              </a>
                              <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                  <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
                                  <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                  <div class="text-truncate">Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</div>
                                  <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                              </a>
                              <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                          </li>
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
                      <div class="container" style="text-align:center;">
                        <h1 style="text-align:center">購物商城管理</h1></td></tr>
                        <!-- Page Heading -->
                		 <div class="errorMsgs" >
                          <%-- 錯誤表列 --%>
                          <c:if test="${not empty errorMsgs}">
                          <font>請修正以下錯誤:</font>
                          <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                            <li>${message}</li>
                            </c:forEach>
                          </ul>
                          </c:if>
                           <div class="errorblock">${errorMessage1}</div>
                        </div>
			 <div class="card shadow mb-4 dataTable">
                        <div class="card-header py-3">
                          <h6 class="m-0 font-weight-bold text-primary">商品搜尋 </h6>
                        </div>
                        <div class="card-body">
                          <div class="table-responsive">
                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product/getOne_For_Display" name="form1">
                              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                  <tr>
                                    <th>搜尋單筆商品</th>
                                    <th>選擇商品編號</th>
                                    <th>選擇商品名稱</th>
                                    <th>搜尋所有商品</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td>
                                      <form METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product/getOne_For_Display" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                        <div class="input-group">
                                          <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" name="pro_no">
                                          <input type="hidden" name="action" value="getOne_For_Display">
                                          <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>
                                            </button>
                                          </div>
                                        </div>
                                      </form>
                                    </td>
                                    <td>
                                      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product/getOne_For_Display" >
                                        <select size="1" name="pro_no">
                                          <c:forEach var="productVO" items="${productSvc.all}" >
                                          <option value="${productVO.pro_no}">${productVO.pro_no}
                                            </c:forEach>
                                          </select>
                                          <input type="hidden" name="action" value="getOne_For_Display">
                                          <input type="submit" value="送出">
                                        </FORM>
                                      </td>
                                      <td>
                                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product/getOne_For_Display" >
                                          <select size="1" name="pro_no">
                                            <c:forEach var="productVO" items="${productSvc.all}" >
                                            <option value="${productVO.pro_no}">${productVO.pro_nam}
                                              </c:forEach>
                                            </select>
                                            <input type="hidden" name="action" value="getOne_For_Display">
                                            <input type="submit" value="送出">
                                          </FORM>
                                        </td>
                                        <td>
                                          <a href="<%=request.getContextPath()%>/back_end/product/listAllProduct.jsp" class="btn btn-success btn-icon-split">
                                            <span class="icon text-white-50">
                                              <i class="fas fa-search fa-sm"></i>
                                            </span>
                                            <span class="text">All Product</span>
                                          </a>
                                          <div class="my-2"></div>
                                        </td>
                                      </tr>
                                    </tbody>
                                  </table>
                                </FORM>
                                <!-- DataTales 第2Table頭 -->
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                  <thead>
                                    <tr>
                                      <th>搜尋商品圖片編號</th>
                                      <th>選擇商品圖片名稱</th>
                                      <th>搜尋所有商品類別名稱</th>
                                      <th>搜尋所有商品圖片</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    <tr>
                                      <td>
                                        <form METHOD="post" ACTION="<%=request.getContextPath()%>/product_image/product_image.do" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                          <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" name="pro_img_no">
                                            <input type="hidden" name="action" value="getOne_For_Display">
                                            <div class="input-group-append">
                                              <button class="btn btn-primary" type="submit">
                                              <i class="fas fa-search fa-sm"></i>
                                              </button>
                                            </div>
                                          </div>
                                        </form>
                                      </td>
                                      <td>
                                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_image/product_image.do" >
                                          <select size="1" name="pro_img_no">
                                            <c:forEach var="product_imageVO" items="${product_imageSvc.all}" >
                                            <option value="${product_imageVO.pro_img_no}">${product_imageVO.img_nam}
                                              </c:forEach>
                                            </select>
                                            <input type="hidden" name="action" value="getOne_For_Display">
                                            <input type="submit" value="送出">
                                          </FORM>
                                        </td>
                                        <td>
                                          <a href="<%=request.getContextPath()%>/back_end/product_category/listAllProduct_Category.jsp" class="btn btn-info btn-icon-split">
                                            <span class="icon text-white-50">
                                              <i class="fas fa-search fa-sm"></i>
                                            </span>
                                            <span class="text">All Product_Category</span>
                                          </a>
                                          <div class="my-2"></div>
                                        </td>
                                        <td>
                                          <a href="<%=request.getContextPath()%>/back_end/product_image/listAllProduct_Image.jsp" class="btn btn-warning btn-icon-split">
                                            <span class="icon text-white-50">
                                              <i class="fas fa-search fa-sm"></i>
                                            </span>
                                            <span class="text">All Product_Image</span>
                                          </a>
                                          <div class="my-2"></div>
                                        </td>
                                      </tr>
                                    </tbody>
                                  </table>
                                  <!-- DataTales 第2Table尾 -->
                                </div>
                              </div>
                            </div>
                            <!-- /DataTable end-->
                            <!-- DataTales Example -->
                            <div class="card shadow mb-4 dataTable">
                              <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">商品管理</h6>
                              </div>
                              <div class="card-body">
                                <div class="table-responsive">
                                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                      <thead>
                                        <tr>
                                          <th>新增商品類別名稱</th>
                                          <th>新增商品</th>
                                          <th>新增商品圖片</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <tr>
                                          <td>
                                            <a href="<%=request.getContextPath()%>/back_end/product_category/addProduct_Category.jsp" class="btn btn-light btn-icon-split">
                                              <span class="icon text-gray-600">
                                                <i class="fas fa-arrow-right"></i>
                                              </span>
                                              <span class="text" style="color: rgb(33, 29, 22);">Add a new Product_Category</span>
                                            </a>
                                            <div class="mb-4"></div>
                                          </td>
                                          <td>
                                            <a href="<%=request.getContextPath()%>/back_end/product/addProduct" class="btn btn-light btn-icon-split">
                                              <span class="icon text-gray-600">
                                                <i class="fas fa-arrow-right"></i>
                                              </span>
                                              <span class="text" style="color: rgb(33, 29, 22);">Add a new Product</span>
                                            </a>
                                            <div class="mb-4"></div>
                                          </td>
                                          <td>
                                            <a href="<%=request.getContextPath()%>/back_end/product_image/addProduct_Image.jsp" class="btn btn-light btn-icon-split">
                                              <span class="icon text-gray-600">
                                                <i class="fas fa-arrow-right"></i>
                                              </span>
                                              <span class="text" style="color: rgb(33, 29, 22);">Add a new Product_Image</span>
                                            </a>
                                            <div class="mb-4"></div>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </FORM>
                                </div>
                              </div>
                            </div>
                            <!-- /DataTable end-->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                  <h6 class="m-0 font-weight-bold text-primary">商品訂單管理</h6>
                                </div>
                                <div class="card-body">
                                  <div class="table-responsive">
                                    <table class="table">
                                      <thead class="thead-dark">
                                        <tr>
                                          <th scope="col">搜尋訂單編號</th>
                                          <th scope="col">選擇訂單編號</th>
                                          <th scope="col">選擇訂單日期</th>
                                          <th scope="col">搜尋所有商品訂單</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <tr>
                                          <th scope="row">
                                            <form METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product_order/product_order.do" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                              <div class="input-group">
                                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" name="ord_no">
                                                <input type="hidden" name="action" value="getOne_For_Display">
                                                <div class="input-group-append">
                                                  <button class="btn btn-primary" type="submit">
                                                  <i class="fas fa-search fa-sm"></i>
                                                  </button>
                                                </div>
                                              </div>
                                            </form>
                                          </th>
                                          <td>
                                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product_order/product_order.do" >
                                              <select size="1" name="ord_no">
                                                <c:forEach var="product_orderVO" items="${product_orderSvc.all}" >
                                                <option value="${product_orderVO.ord_no}">${product_orderVO.ord_no}
                                                  </c:forEach>
                                                </select>
                                                <input type="submit" value="送出">
                                                <input type="hidden" name="action" value="getOne_For_Display">
                                              </FORM>
                                            </td>
                                            <td>
                                              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product_order/product_order.do" >
                                                <select size="1" name="ord_no">
                                                  <c:forEach var="product_orderVO" items="${product_orderSvc.all}" >
                                                  <option value="${product_orderVO.ord_no}">${product_orderVO.ord_dat}
                                                    </c:forEach>
                                                  </select>
                                                  <input type="submit" value="送出">
                                                  <input type="hidden" name="action" value="getOne_For_Display">
                                                </FORM>
                                              </td>
                                              <td>
                                                <a href="<%=request.getContextPath()%>/back_end/product_order/listAllProduct_Order.jsp" class="btn btn-danger btn-icon-split">
                                                  <span class="icon text-white-50">
                                                    <i class="fas fa-search fa-sm"></i>
                                                  </span>
                                                  <span class="text">All Product_Order</span>
                                                </a>
                                                <div class="my-2"></div>
                                              </td>
                                            </tr>
                                            <tr>
                                              <th scope="row"></th>
                                              <td></td>
                                              <td></td>
                                              <td></td>
                                            </tr>
                                          </tbody>
                                        </table>
                                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/product_order/product_order.do" name="form1">
                                          <table class="table">
                                            <thead class="thead-light">
                                              <tr><div style="font-size: 30px;text-align: center;font-family:cursive;">訂單查詢</div></tr>
            								</thead>
                                            <tbody>
                                              <tr>
                                                <th scope="row">輸入訂單編號:</th>
                                                <td><input type="text" name="ord_no"></td>
                                                <th>輸入訂單日期:</th>                                                                    
                                                <td><input name="ord_dat" id="f_date1" type="text" autocomplete="off"></td>
                                              </tr>
                                              <tr>
                                                <th scope="row">選擇訂單狀態:</th>
                                                <td> <input type="radio" name="ord_sta"
                                                  value="0"  />已付款
                                                  <input type="radio" name="ord_sta"
                                                value="1" checked="true" />已完成訂單</td>
                                                <th>選擇付款方式:</th>
                                                <td><input type="radio" name="pay_met"
                                                  value="點數" checked="true" />點數
                                                  <input type="radio" name="pay_met"
                                                value="信用卡"  />信用卡</td>
                                              </tr>
                                              <tr>
                                                <th scope="row">輸入會員編號:</th>
                                                <td><input type="text" name="mem_id"></td>
                                                <th>輸入訂單地址:</th>
                                                <td><input type="text" name="del_add"></td>
                                              </tr>
                                              <tr>
                                                <th scope="row"></th>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                              </tr>
                                            </tbody>
                                          </table>
                                          <div class="input-group-append" id="table_btn">
                                            <input type="hidden" name="action" value="listProduct_Orders_ByCompositeQuery">
                                            <button class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>送出查詢
                                            </button>
                                          </div>
                                        </FORM>
                                      </div>          
                                  </div>
                                </div>
                                <!-- /DataTales Example -->
                              </div>
                              <!-- /.container-fluid -->
                            </div>
                            <!-- End of Main Content -->
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
                        <!-- Scroll to Top Button-->
                        <a class="scroll-to-top rounded" href="#page-top">
                          <i class="fas fa-angle-up"></i>
                        </a>
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
                        <!-- Core plugin JavaScript-->
                        <script src="<%=request.getContextPath()%>/back_end/vendor/jquery-easing/jquery.easing.min.js"></script>
                        <!-- Custom scripts for all pages-->
                        <script src="<%=request.getContextPath()%>/back_end/js/sb-admin-2.min.js"></script>
                        <!-- Page level plugins -->
                        <script src="<%=request.getContextPath()%>/back_end/vendor/chart.js/Chart.min.js"></script>
                        <!-- Page level custom scripts -->
                        <script src="<%=request.getContextPath()%>/back_end/js/demo/chart-area-demo.js"></script>
                        <script src="<%=request.getContextPath()%>/back_end/js/demo/chart-pie-demo.js"></script>
                        
                        <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
                        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.datetimepicker.css" />
                        <script src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.js"></script>
                        <script src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.datetimepicker.full.js"></script>
                        <script>
                        $.datetimepicker.setLocale('zh');
                        $('#f_date1').datetimepicker({
                        theme: '',              //theme: 'dark',
                        timepicker:false,       //timepicker:true,
                        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
                        format:'Y-m-d',         //format:'Y-m-d H:i:s',
                        value: '',              // value:   new Date(),
                        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
                        //startDate:              '2017/07/10',  // 起始日
                        //minDate:               '-1970-01-01', // 去除今日(不含)之前
                        maxDate:               '+1970-01-01'  // 去除今日(不含)之後
                        });
                        
                        
                        
                        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
                        //      1.以下為某一天之前的日期無法選擇
                        //      var somedate1 = new Date('2017-06-15');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //            if (  date.getYear() <  somedate1.getYear() ||
                        //               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||
                        //               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});
                        
                        //      2.以下為某一天之後的日期無法選擇
                        //      var somedate2 = new Date('2017-06-15');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //            if (  date.getYear() >  somedate2.getYear() ||
                        //               (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||
                        //               (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});
                        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
                        //      var somedate1 = new Date('2017-06-15');
                        //      var somedate2 = new Date('2017-06-25');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //            if (  date.getYear() <  somedate1.getYear() ||
                        //               (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||
                        //               (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                        //                 ||
                        //                date.getYear() >  somedate2.getYear() ||
                        //               (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||
                        //               (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});
                        
                        </script>
                      </body>
                    </html>