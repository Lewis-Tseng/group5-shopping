<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.administrator.model.*"%>
<%
  AdmVO empVO = (AdmVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>EmpLogin</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath()%>/back_end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath()%>/back_end/css/sb-admin-2.css" rel="stylesheet">

<style type="text/css">
  .bg-gradient-primary {
  background: url(<%=request.getContextPath()%>/back_end/img/back.jpg);
  background-size: cover;
}
.bg-login-image {
  background: url(<%=request.getContextPath()%>/back_end/img/G5.png);
    background-position: center;
    background-size: cover;
}
.container{
	font-size:25px;
}
.my-5 {
  margin-top: 10rem !important;
}
.p-5 {
  padding:6rem !important;
}
.card {
    background-color: rgba(25,25,25,0.4);
  }
  @media (min-width: 992px)
.col-lg-6 {
    flex: 0 0 50%;
    max-width: 43%;
}
.text-gray-900 {
    color: #ffb700 !important;
}
</style>


</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row loginbg">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                     <h1 class="h4 text-gray-900 mb-4" ">歡迎JUST強身員工</h1>
                    <c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
							
						</c:if>
                  </div>
                  
                  <form class="user" method="post" action="adm.do">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="員工編號"
                      name="empid" value="<%= (empVO==null)? "" : empVO.getEmp_id()%>">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="密碼"
                      name="password" value="<%= (empVO==null)? "" : empVO.getEmp_psw()%>">
                    </div>
					<input type="hidden" name="action" value="login">
					<input type="submit" class="btn btn-primary btn-user btn-block" value=" Login">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="<%=request.getContextPath()%>/back_end/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/back_end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath()%>/back_end/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%=request.getContextPath()%>/back_end/js/sb-admin-2.min.js"></script>

</body>

</html>
