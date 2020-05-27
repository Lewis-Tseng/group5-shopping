<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*" %>
<%@ page import="com.order_details.model.*" %>
<%@ page import="com.product_category.model.*"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.coach.model.CoaVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>購物車 - ${memVO.mem_name }</title>
        <!---css--->
        <link href="<%=request.getContextPath()%>/front_end/css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="<%=request.getContextPath()%>/front_end/css/style.css" rel='stylesheet' type='text/css' />
        <!---css--->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!---js--->
        <script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
        <!---js--->
        <!--web-fonts-->
        <link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
        <!--//web-fonts-->
        <link href="<%=request.getContextPath()%>/front_end/css/galleryeffect.css" rel="stylesheet" type="text/css" media="all" />
        <link href="<%=request.getContextPath()%>/front_end/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!--JS for animate-->
        <link href="<%=request.getContextPath()%>/front_end/css/animate.css" rel="stylesheet" type="text/css" media="all">
        <script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
        <script>
        new WOW().init();
        </script>
        <!--//end-animate-->
        <!--CSS-->
        <style type="text/css">
        .banner1{
        height: 100px;
        }
        body{
        background:url("<%=request.getContextPath()%>/front_end/css_group/images/bg1.png");
        background-size:cover;
        background-repeat:no-repeat;
        background-attachment:fixed;
        background-position:center;
        }
        .proboxbase{
        background-color: rgba(0, 0, 0, 0.2);
        position: relative;
        width:100%;
        }
        .probox1{
        height:800px;
        display: inline-block;
        margin: 1px;
        width:100%;
        }
        .probox2{
        overflow: hidden;
        margin:0px;
        margin-bottom: 5px;
        margin-top: 5px;
        float: left;
        height: 385px;
        }
        .class-text{
        width: 100%;
        }
        .proimg1{
        height:200px;
        width: 100%;
        border:2px green dashed;
        }
        .pagesbtn1{
        margin: 0px;
        text-align: center;
        padding: 1em;
        color: #FFFFFF;
        }
        .pagesbtn2{
        margin: 0;
        text-align: center;
        padding: 1em; 
        }
        .pagesbtn2 a{
        color: #FFFFFF;
        }
        .table1{
        /*     border: 2px #000000 solid; */
        font-size: 17px;
        width: 100%;
        }
        .table1 tr th{
        width: 20%;
        }
        .table2{
        text-align: center;
        font-size: 30px;
        }
        .tablehead{
        font-size: 20px;
        }
         input[type=number]{
        width: 60px;
        } 
        /*Btn*/
        * {
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        box-sizing: border-box;
        }

       
        
        /*input css*/
        .ctrl {
        height: 27px;
        -webkit-box-flex: 0;
        -ms-flex: 0 0 auto;
        flex: 0 0 auto;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        border-bottom: 1px solid #D5DCE6;
        /* background-color: #fff;*/
        border-radius: 5px;
        font-size: 17px;
        }
        .ctrl-counter {
        position: relative;
        width: 60px;
        height: 100px;
        color: #333C48;
        text-align: center;
        overflow: hidden;
        }
        .ctrl-counter.is-input .ctrl-counter-num {
        visability: hidden;
        opacity: 0;
        -webkit-transition: opacity 100ms ease-in;
        transition: opacity 100ms ease-in;
        }
        .ctrl-counter.is-input .ctrl-counter-input {
        visability: visible;
        opacity: 1;
        -webkit-transition: opacity 100ms ease-in;
        transition: opacity 100ms ease-in;
        }
        .ctrl-counter-input {
        width: 100%;
        margin: 0;
        padding: 0;
        position: relative;
        z-index: 2;
        box-shadow: none;
        outline: none;
        border: none;
        color: #333C48;
        font-size: 30px;
        line-height: 100px;
        text-align: center;
        visability: hidden;
        opacity: 0;
        -webkit-transition: opacity 100ms ease-in;
        transition: opacity 100ms ease-in;
        }
        .ctrl-counter-num {
        position: absolute;
        z-index: 1;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        line-height: 100px;
        visability: visible;
        opacity: 1;
        -webkit-transition: opacity 100ms ease-in;
        transition: opacity 100ms ease-in;
        }
        .ctrl-counter-num.is-increment-hide {
        opacity: 0;
        -webkit-transform: translateY(-50px);
        transform: translateY(-50px);
        -webkit-animation: increment-prev 100ms ease-in;
        animation: increment-prev 100ms ease-in;
        }
        .ctrl-counter-num.is-increment-visible {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        -webkit-animation: increment-next 100ms ease-out;
        animation: increment-next 100ms ease-out;
        }
        .ctrl-counter-num.is-decrement-hide {
        opacity: 0;
        -webkit-transform: translateY(50px);
        transform: translateY(50px);
        -webkit-animation: decrement-prev 100ms ease-in;
        animation: decrement-prev 100ms ease-in;
        }
        .ctrl-counter-num.is-decrement-visible {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        -webkit-animation: decrement-next 100ms ease-out;
        animation: decrement-next 100ms ease-out;
        }
        .ctrl-button {
        width: 32px;
        line-height: 26px;
        text-align: center;
        color: #fff;
        cursor: pointer;
        background-color: #8498a7;
        -webkit-transition: background-color 100ms ease-in;
        transition: background-color 100ms ease-in;
        }
        .ctrl-button:hover {
        background-color: #90a2b0;
        -webkit-transition: background-color 100ms ease-in;
        transition: background-color 100ms ease-in;
        }
        .ctrl-button:active {
        background-color: #778996;
        -webkit-transition: background-color 100ms ease-in;
        transition: background-color 100ms ease-in;
        }
        .ctrl-button-decrement {
        border-radius: 5px 0 0 5px;
        }
        .ctrl-button-increment {
        border-radius: 0 5px 5px 0;
        }
        @-webkit-keyframes decrement-prev {
        from {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        }
        } @keyframes decrement-prev {
        from {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        }
        } @-webkit-keyframes decrement-next {
        from {
        opacity: 0;
        -webkit-transform: translateY(-50px);
        transform: translateY(-50px);
        }
        } @keyframes decrement-next {
        from {
        opacity: 0;
        -webkit-transform: translateY(-50px);
        transform: translateY(-50px);
        }
        } @-webkit-keyframes increment-prev {
        from {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        }
        } @keyframes increment-prev {
        from {
        opacity: 1;
        -webkit-transform: translateY(0);
        transform: translateY(0);
        }
        } @-webkit-keyframes increment-next {
        from {
        opacity: 0;
        -webkit-transform: translateY(50px);
        transform: translateY(50px);
        }
        } @keyframes increment-next {
        from {
        opacity: 0;
        -webkit-transform: translateY(50px);
        transform: translateY(50px);
        }
        }
         /*input css*/
        /*Btn*/
        /* **********************頭像************************************ */
.memPic{
	display: inline-block;
	font-family:'test';
	position:fixed;
	right:30px;
	top:20px;
	width:70px;
	height:70px;
	color:rgb(220,220,220);
	z-index:1155;
}
.memPic>a>img{
	width:100%;
	height: 100%;
	border-radius:50%;
	z-index:1156;
}
.memPic>span{
	width: 50%;
    font-size: 2em;
	z-index:1156;
}
.memInfo{
	font-family:'test';
	display: inline-block;
	position:fixed;
	right:30px;
	top:120px;
	width:230px;
	height:115px;
	background:rgba(20,20,20,0.8);
	color:rgb(220,220,220);
	border-radius:10px;
	z-index:1155;
	text-align: center;
	transition:0.3s;
	opacity:0;
}

.box{
	position:fixed;
	top: 89;
    right: 50;
	display:inline-block;
	border-right: 13px solid rgba(0,0,0,0);
	border-left: 13px solid rgba(0,0,0,0);
	border-bottom: 25px solid rgba(20,20,20,0.8);
	display:inline-block;
	width:30px;
	height:30px;
    background-color:rgba(0,0,0,0);
    transition:0.3s;
    border-radius:0px;
    z-index:1150;
    
}
.memText h1,.memText h2{
	font-family:'test';
	margin-top:12px;
}
/* ***********************頭像*********************************** */
        </style>
    </head>
    <body>
        <!---header--->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header wow fadeInLeft animated animated" data-wow-delay="0.4s">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img  src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s "style=" margin-top: 0%;margin-right: -15%;">
                        <li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
                        <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
                        <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/crs/crsEntrance.jsp"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
                        <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
                        <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp" ><span data-letters="討論區"><b>討論區</b></span></a></li>
                        <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
<!-- ***************************************頭像登出********************************************** -->
				<%	
MemVO memVO = (MemVO) session.getAttribute("memVO");
CoaVO coaVO = (CoaVO) session.getAttribute("coaVO");
			if(memVO!=null || coaVO!=null){
 	    	%>
					<li><a class="nav-in"herf="#">
			<span data-letters="登出">
					<form action="<%=request.getContextPath()%>/mem.do" method="POST">
						<input type="submit" value="" style="position: absolute;background-color: rgb(0,0,0,0); border-color: rgb(0,0,0,0);z-index: 2;width: 100%;height: 100%;">  
						<input type="hidden" name="action" value="signout"><b>登出</b> 
					</form> 
			</span>
						</a>
					</li>
			<%}%>
<!-- 	******************登入************************************************************************ -->
		<%if(memVO==null&&coaVO==null){ %>
	<li><a href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp"><span data-letters="登入"><b>登入</b></span></a></li>
	<%} %>
<!-- 	******************結束************************************************************************ -->
<li><a href="<%=request.getContextPath()%>/front_end/product_front/Product_Cart.jsp" title="購物車連結"><img src="<%=request.getContextPath()%>/front_end/product_front/product_front_images/icon_Cart (2).png" alt="購物車" border="0"></a></li>
          </ul>
        </div>
      </div>
    </nav>
    <% 	if(memVO!=null){%>
    	<div class="memPic">
		
		<a href="<%=request.getContextPath()%>/front_end/mem/MemManagePersonal.jsp" style="width:100%;height:100%;">
			<c:if test="${memVO.mem_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${memVO.mem_id}"> 
			</c:if>
			<c:if test="${memVO.mem_pic==null}">
				<c:if test='${memVO.mem_gender.equals("男")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
				</c:if>
				<c:if test='${memVO.mem_gender.equals("女")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
				</c:if>
			</c:if>
		</a>
	</div>
<!-- ---------------詳細------------------ -->
	<div class="memInfo">
		<div class="memText">
			<h1>歡迎! <%=memVO.getMem_name()%></h1>
			<h2>點數/<%=memVO.getMem_point() %></h2>
		</div>
	</div>
	<div class="memInfo box"></div>
     <%}else if(coaVO!=null){ %>    <%--*************************************************************** --%>
    <div class="memPic">
		<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}">
		<a href="<%=request.getContextPath()%>/front_end/coa/CoaManagePersonal.jsp" style="width:100%;height:100%;"> 
			<c:if test="${coaVO.coa_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=${coaVO.coa_id}"> 
			</c:if>
			<c:if test="${memVO.mem_pic==null}">
				<c:if test='${coaVO.coa_gender.equals("男")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/men.png">
				</c:if>
				<c:if test='${coaVO.coa_gender.equals("女")}'>
					<img src="<%=request.getContextPath()%>/front_end/css_group/images/women.png">
				</c:if>
			</c:if>
		</a>
	</div>
<!-- ---------------詳細------------------ -->
	<div class="memInfo">
		<div class="memText">
			<h1>歡迎! <%=coaVO.getCoa_name()%></h1>
			<h2>點數/99999</h2>
		</div>
	</div>
	<div class="memInfo box"></div>
    <%}%>
<!-- ****************************************頭像*************************************************** -->
    <!---/header--->
    <!---banner--->
    <div class="container banner1">
        
    </div>
    <!---/banner--->
    <!---ShooppingBox頭--->
    <div class="trainers-section wow bounceIn animated container" data-wow-delay="0.4s" style="visibility: visible; -webkit-animation-delay: 0.4s;">
        <div class="proboxbase">
            <div class="pagesbtn1 col">
                <font size="+3">購物車</font>
                <div class="erroMsgs">
							<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
							</c:if>
					</div>
            </div>
            <div class="probox1">
                <!--商品視窗-->
                <div class="col class">
                    <% @SuppressWarnings("unchecked")
                    Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");%>
                        <%if (buylist != null && (buylist.size() > 0)) {%>
                        <div class="panel panel-default">
                            <!-- Default panel contents -->
                            <div class="panel-heading tablehead">Just強身</div>
                            <table class="table table-responsive table1" cellspacing="0" >
                                <thead>
                                    <tr>
                                        <th>商品類別</th>
                                        <th>商品名稱</th>
                                        <th>商品編號</th>
                                        <th>單價</th>
                                        <th>數量</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <%
                                for (int index = 0; index < buylist.size(); index++) {
                                Order_Details_ProductVO order_details = (Order_Details_ProductVO) buylist.get(index);
                                %>
                                <%
                                	Product_CategoryService product_categorySvc = new Product_CategoryService();
                                                                Product_CategoryVO product_categoryVO = product_categorySvc.getOneProduct_Category(order_details.getProduct_categoryVO());
                                %>
                                <tbody>
                                    <tr>
                                        <td><%=product_categoryVO.getCat_nam()%></td>
                                        <td><%=order_details.getPro_nam()%></td>
                                        <td><%=order_details.getPro_no()%></td>
                                        <td><%=order_details.getPro_pri()%></td>
                                        <td><%=order_details.getPro_quantity()%></td>
                                        <form name="deleteForm" action="<%=request.getContextPath()%>/front_end/shopping_product/shopping_product.do" method="POST">
                                            <td>
                                             <input name="pro_quantity_new" min="1" max="99" type="number" value="1">
<!--                                                       有bug input -->
<!--                                                 <div class='ctrl'> -->
<!--                                                     <div class='ctrl-button ctrl-button-decrement'>-</div> -->
<!--                                                     <div class='ctrl-counter'> -->
<!--                                                         <input name="pro_quantity_new" class='ctrl-counter-input' maxlength='10' type='text' value='1'> -->
<%--                                                         <div class='ctrl-counter-num <%=order_details.getPro_no()%>'>1</div> --%>
<!--                                                     </div> -->
<!--                                                     <div class='ctrl-button ctrl-button-increment'>+</div> -->
<!--                                                 </div> -->
                                            </td>
                                            <td>
                                                <input type="hidden" name="action"  value="ADD">
                                                <input type="hidden" name="add" value="<%= index %>">
                                                <input type="submit" prono="<%=order_details.getPro_no()%>" value="修改數量" class="button" id="send">
                                            </form></td>
                                            <td>
                                                <form name="deleteForm" action="<%=request.getContextPath()%>/front_end/shopping_product/shopping_product.do" method="POST">
                                                    <input type="hidden" name="action"  value="DELETE">
                                                    <input type="hidden" name="del" value="<%= index %>">
                                                    <input type="submit" value="刪 除" class="button">
                                                </form></td>
                                            </tr>
                                        </tbody>
                                        <%}%>
                                    </table>
                                </div>
                                <div class="pagesbtn2">
                                    <form name="checkoutForm" action="<%=request.getContextPath()%>/front_end/shopping_product/shopping_product.do" method="POST">
                                        <input type="hidden" name="action"  value="CHECKOUT">
                                        <input type="submit" value="付款結帳" class="button">
                                    </form>
                                </div>
                                <div class="pagesbtn2">
                                    <a href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><font size="+1">繼 續 購 物</font></a>
                                </div>
                            </div>
                            <!--/商品視窗-->
                            <div class="clearfix"></div>
                            <%} else { %>
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading tablehead">Just強身</div>
                                <table class="table table-responsive table2" cellspacing="0" >
                                    <thead>
                                        <tr>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                購物車尚無商品
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="pagesbtn2">
                                <a href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><font size="+1">回到購物商城</font></a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <!--購物車集合end-->
                </div>
                </div><!-- 去掉此div footer會跑版 -->
                <!---/ShooppingBox尾--->
                <!-- //gallery -->
                <!---footer--->
                <div class="footer-section">
                    <div class="container">
                        <div class="footer-grids">
                            <div class="col-md-3 footer-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                                <h4>About</h4>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy hendrerit mauris. Praesent vestibulummolestie lacus.</p>
                            </div>
                            <div class="col-md-3 footer-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                                <h4>Categories</h4>
                                <ul>
                                    <li>Beauty</li>
                                    <li>Diet & Fitness</li>
                                    <li>Lifestyle</li>
                                    <li>Help Desk</li>
                                    <li>Pregnancy</li>
                                    <li>Performance Metrics</li>
                                </ul>
                            </div>
                            <div class="col-md-3 footer-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
                                <h4>Work</h4>
                                <ul>
                                    <li>Customer Support</li>
                                    <li>Platinum Support</li>
                                    <li>Gold Support</li>
                                    <li>Training</li>
                                    <li>Workshops</li>
                                    <li>Online Training</li>
                                </ul>
                            </div>
                            <div class="col-md-3 footer-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
                                <h4>Contact</h4>
                                <p>7801 Marmora Road</p>
                                <p>Glasgow, DO5 98GR.</p>
                                <p>Freephone: +1 800 558 8990</p>
                                <p>Telephone: +1 659 803 9035</p>
                                <p>FAX: + 1 314 889 9898</p>
                                <a href="mailto:example@mail.com"> example@mail.com</a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <!---footer--->
                <!--copy-->
                <div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                    <div class="container">
                        <div class="social-icons">
                            <a href="#"><i class="icon"></i></a>
                            <a href="#"><i class="icon1"></i></a>
                            <a href="#"><i class="icon2"></i></a>
                            <a href="#"><i class="icon3"></i></a>
                        </div>
                    </div>
                </div>
                <!--copy-->
                <script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
                <script type="text/javascript">
                
                (function() {
                'use strict';
                function ctrls() {
                var _this = this;
                this.counter = 1;
                this.els = {
                decrement: document.querySelector('.ctrl-button-decrement'),
                counter: {
                container: document.querySelector('.ctrl-counter'),
                num: document.querySelector('.ctrl-counter-num'),
                input: document.querySelector('.ctrl-counter-input')
                },
                increment: document.querySelector('.ctrl-button-increment')
                };
                this.decrement = function() {
                var counter = _this.getCounter();
                var nextCounter = (_this.counter > 0) ? --counter: counter;
                _this.setCounter(nextCounter);
                };
                this.increment = function() {
                var counter = _this.getCounter();
                var nextCounter = (counter < 9999999999) ? ++counter: counter;
                _this.setCounter(nextCounter);
                };
                this.getCounter = function() {
                return _this.counter;
                };
                this.setCounter = function(nextCounter) {
                _this.counter = nextCounter;
                };
                this.debounce = function(callback) {
                setTimeout(callback, 100);
                };
                this.render = function(hideClassName, visibleClassName) {
                _this.els.counter.num.classList.add(hideClassName);
                setTimeout(function() {
                _this.els.counter.num.innerText = _this.getCounter();
                _this.els.counter.input.value = _this.getCounter();
                _this.els.counter.num.classList.add(visibleClassName);
                },
                100);
                setTimeout(function() {
                _this.els.counter.num.classList.remove(hideClassName);
                _this.els.counter.num.classList.remove(visibleClassName);
                },
                200);
                };
                this.ready = function() {
                _this.els.decrement.addEventListener('click',
                function() {
                _this.debounce(function() {
                _this.decrement();
                _this.render('is-decrement-hide', 'is-decrement-visible');
                });
                });
                _this.els.increment.addEventListener('click',
                function() {
                _this.debounce(function() {
                _this.increment();
                _this.render('is-increment-hide', 'is-increment-visible');
                });
                });
                _this.els.counter.input.addEventListener('input',
                function(e) {
                var parseValue = parseInt(e.target.value);
                if (!isNaN(parseValue) && parseValue >= 0) {
                _this.setCounter(parseValue);
                _this.render();
                }
                });
                _this.els.counter.input.addEventListener('focus',
                function(e) {
                _this.els.counter.container.classList.add('is-input');
                });
                _this.els.counter.input.addEventListener('blur',
                function(e) {
                _this.els.counter.container.classList.remove('is-input');
                _this.render();
                });
                };
                };
                // init
                var controls = new ctrls();
                document.addEventListener('DOMContentLoaded', controls.ready);
                })();
                
                </script>
                <script type="text/javascript">
                $('input[class*="send"]').click(function(){
                var prono = $(this).attr('prono');
                var select = "input[class=\""+prono+"\"]";
                var pro_quantity_new =$(select).val();
                
                });
                </script>
            </body>
        </html>