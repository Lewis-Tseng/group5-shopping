
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.res.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>


<%
	CoaService coaSvc = new CoaService();
	List<CoaVO> list = coaSvc.getAll();
	pageContext.setAttribute("list", list);
%>	

<%
	ResVO resVO = (ResVO) request.getAttribute("resVO");
%>

<% 
	CoaVO coaVO =(CoaVO) request.getAttribute("coaVO");
%>

<% 
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<html>

<head>
    <title>Classes</title>
    <!---css--->
    <link href="<%=request.getContextPath()%>/front_end/css/bootstrap_poan.css" rel='stylesheet' type='text/css' />
    <link href="<%=request.getContextPath()%>/front_end/css/style_poan.css" rel='stylesheet' type='text/css' />
    <link href="https://fonts.googleapis.com/css?family=Baloo|Ubuntu" rel="stylesheet">
    <mce:script src="<%=request.getContextPath()%>/front_end/js/jquery.1.4.2.js" mce_src="js/jquery-1.5.1.min.js" type="text/javascript"></mce:script>
    <!--新增datepicker支援-->
    <mce:script src="<%=request.getContextPath()%>/front_end/js/jquery.ui.core.js" mce_src="js/jquery.ui.core.js" type="text/javascript"></mce:script>
    <mce:script src="<%=request.getContextPath()%>/front_end/js/jquery.ui.datepicker.js" mce_src="js/jquery.ui.datepicker.js" type="text/javascript">
    </mce:script>
    <!---css- -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script
        type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---js--->
    <script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.0.min.js"
        integrity="sha256-ihAoc6M/JPfrIiIeayPE9xjin4UWjsx2mjW/rtmxLM4=" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.js"
        integrity="sha256-6HSLgn6Ao3PKc5ci8rwZfb//5QUu3ge2/Sw9KfLuvr8=" crossorigin="anonymous"></script>
    <!---js--->
    <!--web-fonts-->
    <link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
    <link
        href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
        rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic'
        rel='stylesheet' type='text/css'>
    <!--//web-fonts-->
    <!--JS for animate-->
    <link href="<%=request.getContextPath()%>/front_end/css/animate_poan.css" rel="stylesheet" type="text/css" media="all">
    <script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <style type="text/css">
    
   	 	@font-face {
			font-family: 'test';
			src: url('<%=request.getContextPath()%>/front_end/SentyTEA-20190904.ttf');
		}
        body {
            display: flex;
            justify-content: center;
            flex-direction: column;
            font-family: "test";
        }

        #centerdiv {
            border: 2px solid;
            border-color: blanchedalmond;
            width: 70%;
            height: 1500px;
            margin: 5% 15%;

        }

        #title {
            width: 100%;
            height: 200px;
            color: cornsilk;
            text-align: center;
            font-family: "test";
        }

        #title h1 {
            text-align: center;
            margin-top: 5%;
            margin-bottom: 2%;
        }

        #left {
            float: left;
            width: 40%;
            height: 86.64%;
            background-color: white;
            border: 3px solid;
        }

        #right {
            background-color: lightyellow;
            margin-left: 40%;
            height: 500px;
            border: 3px solid;
        }

        #right img {
            width: 100%;
            height: 100%;
        }

        #underRight {
            background-color: #ccc;
            border: 3px solid;
            height: 16.565%;
            width: 60%;
            /* margin-right: 21%; */
            margin-left: 40%;
        }

        #middleRight {
            background-color: #ccc;
            border: 3px solid;
            height: 36.6%;
            /*53.17 */
            width: 60%;
            margin-left: 40%;
        }

        #profile {

            height: 5%;
            font-family: "test";
            text-align: center;
        }

        #roundedPic {
            border-radius: 50%;
            width: 55%;
            height: 475%;
            margin-top: 10%;
            margin-bottom: 10%;
        }

        #profile p {
            font-family: "test";
            font-size: 25px;
            margin: 5% 8%;
            text-align: center;
        }

        #coach {
            margin-top: 20px;
        }


        .button {
            width: 90%;
            height: 30%;
            margin: 3.5% 5%;
            background-color: #f53030de;
            border-radius: 10px;
            color: white;
            font-family: "test";
            font-size: 50px;
        }

        h1 {
            display: inline-block;
        }

        h2 {
            display: inline-block;
        }

        button {
            border-style: none;
            padding: 5px;
            position: relative;
        }

        button:nth-child(1) {
            left: -50px;
        }

        button#next {
            left: 50px;
        }

        button:hover {
            background-color: #900C3F;
            color: #FFF;
            cursor: pointer;
        }

        .calendar {
            width: 100%;
            height: 100%;
            background: #fff;
            box-shadow: 0px 1px 1px rgba(0, 0, 0, 0.1);
            padding: 10px;
        }

        .title {
            text-align: center;
        }

        .body-list ul {
            width: 100%;
            font-family: arial;
            font-weight: bold;
            font-size: 14px;
        }

        .body-list ul li {
            width: 14.28%;
		    height: 69.5px;
		    line-height: 65px;
		    list-style-type: none;
		    display: block;
		    box-sizing: border-box;
		    float: left;
		    text-align: center;
        }

        .lightgrey {
            color: #AAA;
            cursor:not-allowed;
            user-select: none;
        }

        .darkgrey {
            color: #444;
            user-select: none;
        }

        .fontColor {
            color: hsl(0, 97%, 49%);
        }
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
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
         <h1><a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><img src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png" />Just強身</a></h1>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
                   <li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index_Login.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
                    </li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"><b>健康量測</b></span></a> </li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/res/CoachPage.jsp"><span data-letters="瀏覽教練"><b>瀏覽教練</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/group/index_Group.jsp"><span data-letters="揪團運動"><b>揪團運動</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp"><span data-letters="討論區"><b>討論區</b></span></a></li>
                    <li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城"><b>購物商城</b></span></a></li>
      <!-- ***************************************頭像登出********************************************** -->
				<%	

			if(memVO!=null || coaVO!=null){
 	    	%>
					<li><a class="nav-in"herf="#">
			<span data-letters="登出">
					<form action="mem.do" method="POST">
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

          </ul>
        </div>
      </div>
    </nav>
    <% 	if(memVO!=null){%>
    	<div class="memPic">
		
		<a href="<%=request.getContextPath()%>/front_end/mem/MemManagePersonal.jsp" style="width:100%;height:100%;">
			<c:if test="${memVO.mem_pic!=null}">
				<img src="<%=request.getContextPath()%>/DBGifReaderMem?mem_id=<%=memVO.getMem_id() %>"> 
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
		<img src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=<%=coaVO.getCoa_id() %>">
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
   <script>
	$('.memPic').mouseover(function(){
		$('.memInfo').css("opacity","1");
	});
	$('.memPic').mouseleave(function(){
		$('.memInfo').css("opacity","0");
	});
</script>
    <!---banner--->
    <div class="banner wow fadeInDownBig animated animated" data-wow-delay="0.4s">
        <div class="container">
            <h2 style="color: #000000;">教練資訊</h2>
        </div>
    </div>
    <!---banner--->

    <!---reservation Form--->
    <div id="centerdiv">
        <div id="title">
            <h1>${coaVO.coa_name}</h1>
        </div>

        <div id="left">
            <div id="profile">
                <img id="roundedPic" src="<%=request.getContextPath()%>/coa/DBGifReaderCoach1?coa_id=${coaVO.coa_id}" alt="" onerror="nofind()">
                <p style="color:red;">專長: </p>
             	<p>${coaVO.expert}</p>
                <p style="color:red;">自我介紹:</p>
                <p>${coaVO.coa_intro}</p>
            </div>
        </div>

        <div id="right">
            <!-- <img src="/Just強身整合/images/chris_hemsworth3.jpg" alt=""> -->
            <iframe width="100%" height="100%" src="${coaVO.coa_video}" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen></iframe>
        </div>

        <div id="middleRight">
	        <form METHOD="post" ACTION="res.do" name="reservationCoach">
		            <div class="calendar">
		                <div class="title">
		                    <button id="prev">
		                        <<</button>&nbsp;&nbsp; <h2 class="fontColor small" id="calendar-year">Year</h2>&nbsp;
		                            <h1 class="fontColor" id="calendar-title">Month</h1>&nbsp;&nbsp;
		                            <button id="next">>></button>
		                </div>
		                <div class="body">
		                    <div class="lightgrey body-list" style="color:black">
		                        <ul>
		                            <li>MON</li>
		                            <li>TUE</li>
		                            <li>WED</li>
		                            <li>THU</li>
		                            <li>FRI</li>
		                            <li>SAT</li>
		                            <li>SUN</li>
		                        </ul>
		                    </div>
		                    <div class="darkgrey body-list">
		                        <ul id="days">
		                        </ul>
		                    </div>
		                </div>
		            </div>	            
		                <input type="hidden" name="res_day" id = "resDay" >
		                <input type="hidden" name="coa_id" value="${coaVO.coa_id}">
		                <input type="hidden" name="mem_id" value="${memVO.mem_id}">
		        </div>
		
		        <div id="underRight">   
		            <input type="hidden" name="action" value="insert"> 
		            <input type="submit" class="button" value="預約體驗" /><br>
		            <input class="button" type="button" value="聯繫教練" />
		        </div>
	     </form>   
    </div>
			<%
				ResService resSvc = new ResService();
				//List<ResVO> listt = resSvc.getAll();
				List<ResVO> listt = resSvc.getResDay(coaVO.getCoa_id());
				pageContext.setAttribute("listt", listt);				 
			%>  
			<ul id ="reservedDay">
			<c:forEach var="resDay" items="${listt}">
				<li style="color: red ;display:none">${resDay.res_day}</li>
			</c:forEach>
			</ul>
    <!---reservation Form--->

    <!---footer--->
    <div class="footer-section">
        <div class="container">
            <div class="footer-grids">
                <div class="col-md-3 footer-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                    <h4>About</h4>
                    <p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean
                        nonummy hendrerit mauris. Praesent vestibulummolestie lacus.</p>
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
            <p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期
                <a href="https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a
                    href="https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
        </div>
    </div>
    <!--copy-->


</body>
<script type="text/javascript">
    //兩種不同年的年份
    var month_olympic = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; //閏年
    var month_normal = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; //平年
    var month_name = ["January", "Febrary", "March", "April", "May", "June", "July", "Auguest", "September", "October", "November", "December"];

    var holder = document.getElementById("days"); //<ul id='day'>
    var prev = document.getElementById("prev");
    var next = document.getElementById("next");
    var ctitle = document.getElementById("calendar-title");
    var cyear = document.getElementById("calendar-year");

    var my_date = new Date(); //獲取當前時間
    var my_year = my_date.getFullYear(); //獲取當前年份
    var my_month = my_date.getMonth(); //獲取當前月份
    var my_day = my_date.getDate(); //獲取當前日期

    //獲取某年某月的第一天是星期幾
    function dayStart(month, year) {
        var tmpDate = new Date(year, month, 1);
        // new Date(year, month[, day]);
        console.log(tmpDate.getDay());
        return (tmpDate.getDay());
    }
    //計算是不是閏年（前年份除以4的餘數）
    /*
     1.西元年份除以4不可整除，為平年。
     2.西元年份除以4可整除，且除以100不可整除，為閏年。
     3.西元年份除以100可整除，且除以400不可整除，為平年。
     4.西元年份除以400可整除，為閏年。
    */
    function daysMonth(month, year) {
        var tmp = year % 4;
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0)) {
            return (month_olympic[month]);
        } else {
            return (month_normal[month]);
        }
    }

    //前月
    prev.onclick = function (e) {
        e.preventDefault();
        my_month--;
        if (my_month < 0) {
            my_year--;
            my_month = 11;
        }
        refreshDate();
        getDayId();
    }
    //後月
    next.onclick = function (e) {
        e.preventDefault();
        my_month++;
        if (my_month > 11) {
            my_year++;
            my_month = 0;
        }
        refreshDate();
        getDayId();
    }

    function refreshDate() {
        var str = "";  //設置日期顯示，預設為空 line153
        var totalDay = daysMonth(my_month, my_year); //獲取該月天數
        var firstDay = dayStart(my_month, my_year); //獲取該月第一天星期幾
        var myclass; //設置css
        for (var i = 1; i < firstDay; i++) {
            str += "<li> </li>"; //期使日期之前空白
        }
        for (var i = 1; i <= totalDay; i++) {
            if ((i < my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) || my_year < my_date.getFullYear() || (my_year == my_date.getFullYear() && my_month < my_date.getMonth())) {
                myclass = " class='lightgrey'"; //在當日期今天之前，灰色字
            } else if (i == my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) {
                myclass = " class='fontColor colorbox'"; //當天日期背景顯示
            } else {
                myclass = " class='darkgrey'"; //在當日期今天之後，黑色字
            }
            if (i < 10) {
                idDay = '0' + i;
            } else {
                idDay = i;
            }

            if ((my_month + 1) < 10) {
                idMonth = '0' + (my_month + 1);
            } else {
                idMonth = (my_month + 1);
            }
            str += "<li" + myclass + " id='" + my_year + "-" + idMonth + "-" + idDay + "'>" + i + "</li>"; //創建日期節點
        }
        holder.innerHTML = str; //設置日期顯示
        ctitle.innerHTML = month_name[my_month]; //設置英文月份顯示
        cyear.innerHTML = my_year; //設置年份顯示
        
        reserved();//抓取已預約日期並加入不可預約的class
    }


    var reservationDay;
    var lastClick;
    
    function getDayId() {
        var calendarDay = document.querySelectorAll("#days li");

        for (let i = 0; i < calendarDay.length; i++) {
            if ((i < my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) || my_year < my_date.getFullYear() || (my_year == my_date.getFullYear() && my_month < my_date.getMonth()) ||  calendarDay[i].className =="lightgrey"  ) {
                // calendarDay[i].addEventListener('mouseover', function (e) {
                //     calendarDay[i].style.backgroundColor = "grey";
                // }, false);
                // calendarDay[i].addEventListener('mouseout', function (e) {
                //     calendarDay[i].style.backgroundColor = "";
                // }, false);
                //在當日期今天之前，灰色字
            } else {
				
					calendarDay[i].addEventListener('click', function (e) {
                    if (calendarDay[i].style.backgroundColor == "") {
                        calendarDay[i].style.backgroundColor = "lightblue";
                        reservationDay = this.id;

                        document.getElementById("resDay").value = this.id;
                    }
                    if (lastClick != null) {
                        lastClick.style.backgroundColor = "";
                    }
                    lastClick = calendarDay[i];
                    //window.alert( $("li.reserved").length);
                }, false);
                calendarDay[i].addEventListener('mouseover', function (e) {
                    // calendarDay[i].style.backgroundColor = "#CCC";
                    calendarDay[i].style.border = "1px solid";
                }, false);

                calendarDay[i].addEventListener('mouseout', function (e) {
                    // calendarDay[i].style.backgroundColor = "";
                    calendarDay[i].style.border = "";
                }, false);
            }
        }
    }
    
    
    function reserved(){//取得從資料庫抓出來的已預約日期
    	var liLength = $("#reservedDay > li").length;
    	var reservedArray = new Array();

    	$("#reservedDay > li").each(function(){
    		reservedArray.push($(this).text());
    	    });  	
    	
    	for(var j=0; j <= liLength ; j++){
    		var date = reservedArray[j];
    		$("#"+date).attr('class' , 'lightgrey');
    	}
    }
    
    function nofind(){
		  var img=event.srcElement;
		  img.src="<%=request.getContextPath()%>/front_end/images/G5_LOGO_BLACK.png";
		  img.onerror=null; //控制不要一直觸發錯誤
	}
   	
    window.onload = function () {
        refreshDate(); //執行函數
        getDayId();
    }



</script>

</html>