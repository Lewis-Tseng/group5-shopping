<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<!---css--->
	<link href="<%=request.getContextPath()%>/front_end/css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="<%=request.getContextPath()%>/front_end/css/style.css" rel='stylesheet' type='text/css' />

	<!---css--->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />

	<script type="<%=request.getContextPath()%>/front_end/application/x-javascript">
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!---js--->
	<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/js/bootstrap.js"></script>
	<!---js--->
	<!--web-fonts-->
	<link href='https://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
	<!--//web-fonts-->
	<script src="<%=request.getContextPath()%>/front_end/js/responsiveslides.min.js"></script>
	<script>
		$(function () {
			$("#slider").responsiveSlides({
				auto: true,
				nav: true,
				speed: 500,
				namespace: "callbacks",
				pager: true,
			});
		});
	</script>
	<style type="text/css">
	a{
	color:rgb(0,195,255);
	}
	</style>
	<!--JS for animate-->
	<link href="<%=request.getContextPath()%>/front_end/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/front_end/js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>
	<!--//end-animate-->

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
				<h1><a class="navbar-brand" href="首頁.html"><img  src="<%=request.getContextPath()%>/front_end/images/G5.png"/>Just強身</a></h1>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right wow fadeInRight animated animated" data-wow-delay="0.4s">
					<li class="active"><a class="nav-in" href="<%=request.getContextPath()%>/front_end/frod_end_Index/Index.jsp"><span data-letters="首頁" ><b>首頁</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/HealthMeasure/HealthMeasure.jsp"><span data-letters="健康量測"  ><b>健康量測</b></span></a> </li>
					<li><a class="nav-in" href="課程&教練.html"><span data-letters="課程&教練" ><b>課程&教練</b></span></a></li>
					<li><a class="nav-in" href="瀏覽揪團.html" ><span data-letters="揪團運動" ><b>揪團運動</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp" ><span data-letters="討論區"><b>討論區</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp"><span data-letters="購物商城" ><b>購物商城</b></span></a></li>
					<li><a class="nav-in" href="<%=request.getContextPath()%>/front_end/mem/MemLogin.jsp"><span data-letters="登入" ><b>登入</b></span></a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</nav>
	<!---banner--->
	<div class="banner-section">
		<div class="container">
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides" id="slider">
						<li>	 
							<div class="caption">
								<div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
									<p>你不需要很厲害才開始，但你需要開始才會很厲害</p>
									
								</div>
								<div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
									<h3>力量不是來自於勝利，你的痛苦造就了你的力量</h3>
								</div>
								<div class="clearfix"></div>
							</div>
						</li>
						<li>	 
							<div class="caption">
								<div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
									<p>放棄可以找一萬個理由，但堅持只有一個信念</p>
									
								</div>
								<div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
									<h3>既然沒有俊美的外表，那就來努力成為野獸般的身體</h3>
								</div>
								<div class="clearfix"></div>
							</div>
							
						</li>
						<li>	 
							<div class="caption">
								<div class="col-md-6 cap-left wow fadeInDownBig animated animated" data-wow-delay="0.4s">
									<p>不怕萬人阻擋，就怕自己投降，將來的你一定會感激現在拚命的自己，自己選擇的路，再艱難，跪著也要走完</p>
									
								</div>
								<div class="col-md-6 cap-right wow fadeInUpBig animated animated" data-wow-delay="0.4s">
									<h3>想要放棄的时候，想想當初為什麼開始？</h3>
								</div>
								<div class="clearfix"></div>
							</div>
							
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!---banner--->
	<!---content--->
	<div class="content">
		<!---train--->
		<div class="train w3-agile">
			<div class="container">
				<h2>最新消息</h2>
				<div class="train-grids">
					<div class="col-md-3 train-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
						<div class="train-top hvr-bounce-to-right">
							<div class="train-img">
								<img src="<%=request.getContextPath()%>/front_end/images/e1.png"/>
							</div>

							<h4><a href="#">瀏覽課程</a></h4>
							<p>JUST強身專門為會員規劃教練課程，讓您在隨時隨地享有專業的課程訓練。                                                                                                                                           </p>
						</div>
					</div>
					<div class="col-md-3 train-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
						<div class="train-top hvr-bounce-to-right">
							<div class="train-img">
								<img src="<%=request.getContextPath()%>/front_end/images/e2.png"/>
							</div>
							<h4><a href="#">一起揪團</a></h4>
							<p>想運動找不到人一起運動?怕運動太枯燥乏味?一起來揪團運動吧  </p>
						</div>
					</div>
					<div class="col-md-3 train-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
						<div class="train-top hvr-bounce-to-right">
							<div class="train-img">
								<img src="<%=request.getContextPath()%>/front_end/images/e3.png"/>
							</div>
							<h4><a href="<%=request.getContextPath()%>/front_end/product_front/shopping_mall_home.jsp">購物商城</a></h4>
							<p>JUST強身平台上買東西提供80萬以上商品，健身器材，破盤特價優惠，更有購物金消費回饋、信用卡分期0利率、免運費、退貨免費收件等服務，24H ...</p>
						</div>
					</div>
					<div class="col-md-3 train-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
						<div class="train-top hvr-bounce-to-right">
							<div class="train-img">
								<img src="<%=request.getContextPath()%>/front_end/images/e4.png"/>
							</div>
							<h4><a href="<%=request.getContextPath()%>/front_end/forum/listAllForum.jsp">討論區</a></h4>
							<p>歡迎大家來到討論板，一起來討論健康減肥健身的相關話題吧！喜愛，運動，健身，或有減肥困擾的朋友們 一起加入 健康減肥健身討論板吧 !</p>
						</div>
					</div>
					
				</div>
				
			</div>
		</div>
	</div>
	<!---train--->
	<div class="fit-section w3l-layouts">
		<div class="container">
			<div class="fit-grids">
				<div class="col-md-4 fit-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
					<div class="fit-left hvr-bounce-to-bottom">
						<h3 >瀏覽課程</h3>
						<p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy</p>
						<ul>
							<li>MONDAY - FRIDAY : 08:00 - 16:00</li>
							<li>SATURDAY : 09:30 - 15:30</li>
							<li>SUNDAY : Closed</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 fit-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
					<img src="<%=request.getContextPath()%>/front_end/images/f2.jpg" class="img-responsive" alt=""/>
				</div>
				<div class="col-md-4 fit-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
					<div class="fit-right hvr-bounce-to-right">
						<h3>預約教練</h3>
						<p>Improving technique that allows to change the body shape and weight and permanently fix the result achieved. It includes physical training combined with properly chosen diet And exercise. </p>
						
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="fit-grids1">
				<div class="col-md-8 fit-grid2 wow fadeInLeft animated animated" data-wow-delay="0.4s">
					<img src="<%=request.getContextPath()%>/front_end/images/f3.jpg" class="img-responsive" alt=""/>
				</div>
				<div class="col-md-4 fit-grid1 hvr-bounce-to-bottom wow fadeInDownBig animated animated" data-wow-delay="0.4s">
					<div class="fit-right">
						<h3>快速配對</h3>
						<p>Improving technique that allows to change the body shape and weight and permanently fix the result achieved. It includes physical training combined with properly chosen diet And exercise. </p>
						
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="fit-grids wow fadeInLeft animated animated" data-wow-delay="0.4s">
				<div class="col-md-4 fit-grid">
					<img src="<%=request.getContextPath()%>/front_end/images/f1.jpg" class="img-responsive" alt=""/>
				</div>
				<div class="col-md-4 fit-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
					<div class="fit-left hvr-bounce-to-bottom">
						<h3>排定課程</h3>
						<p>Lorem ipsum dolor sit amet, consectetuer adipig elit. Praesent vestibulummolestie lacus. Aenean nonummy</p>
						<ul>
							<li>MONDAY - FRIDAY 08:00 - 16:00</li>
							<li>SATURDAY 09:30 - 15:30</li>
							<li>SUNDAY 09:30 - 15:30</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4 fit-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
					<div class="fit-right hvr-bounce-to-right">
						<h3>揪團運動</h3>
						<p>Improving technique that allows to change the body shape and weight and permanently fix the result achieved. It includes physical training combined with properly chosen diet And exercise. </p>
						
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="fit-grids1">
				<div class="col-md-4 fit-grid1 hvr-bounce-to-bottom wow fadeInDownBig animated animated" data-wow-delay="0.4s">
					<div class="fit-right">
						<h3>購物商城</h3>
						<p>JUST強身平台上買東西提供80萬以上商品，健身器材，破盤特價優惠，更有購物金消費回饋、信用卡分期0利率、免運費、退貨免費收件等服務，24H ... </p>
						
					</div>
				</div>
				<div class="col-md-8 fit-grid2 wow fadeInUpBig animated animated" data-wow-delay="0.4s">
					<img src="<%=request.getContextPath()%>/front_end/images/f4.jpg" class="img-responsive" alt=""/>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="tlinks">Collect from <a href="http://www.cssmoban.com/" >网页模板</a></div>
	
</div>
<div class="testimonials-section">
	<div class="container">
		<h3>Testimonials</h3>
		<div class="testimonials-grids">
			<div class="col-md-2 testimonials-grid1 test1 wow fadeInDownBig animated animated" data-wow-delay="0.4s">
				<img src="<%=request.getContextPath()%>/front_end/images/j1.jpg" class="img-responsive" alt=""/>
			</div>
			<div class="col-md-10 testimonials-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
				<p>Epsum factorial non deposit quid pro quo hic escorol. Olypian quarrels et gorilla congolium sic ad nauseum. Souvlaki ignitus carborundum e pluribus unum. Defacto lingo est igpay atinlay. Marquee selectus non provisio incongruous feline nolo contendre. Gratuitous octopus niacin, sodium glutimate. Quote meon an estimate et non interruptus stadium.</p>
				<h5>Antonio Brightman</h5>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="testimonials-grids">
			<div class="col-md-10 testimonials-grid wow fadeInUpBig animated animated" data-wow-delay="0.4s">
				<p>Epsum factorial non deposit quid pro quo hic escorol. Olypian quarrels et gorilla congolium sic ad nauseum. Souvlaki ignitus carborundum e pluribus unum. Defacto lingo est igpay atinlay. Marquee selectus non provisio incongruous feline nolo contendre. Gratuitous octopus niacin, sodium glutimate. Quote meon an estimate et non interruptus stadium.</p>
				<h5>Brightman</h5>
			</div>
			<div class="col-md-2 testimonials-grid1 test wow fadeInLeft animated animated" data-wow-delay="0.4s">
				<img src="<%=request.getContextPath()%>/front_end/images/t2.png" class="img-responsive" alt=""/>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!---Testimonials--->
<!---footer--->
<div class="footer-section">
	<div class="copy-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
		<div class="container">
			<div class="social-icons">
				<a href="https://www.facebook.com/"><i class="icon"></i></a>
				<a href="https://twitter.com/?lang=zh-tw"><i class="icon1"></i></a>
				<a href="https://www.google.com.tw/webhp?tab=rw"><i class="icon2"></i></a>
				<a href="https://www.instagram.com/?hl=zh-tw"><i class="icon3"></i></a>
			</div>
			<p>Copyright &copy; 2019.JAVA雲端服務技術養成班第71期
				<a href="#https://www.tibame.com/" target="_blank" title="資策會">中壢資策會第71期出來必是精品</a> - Collect from <a href="#https://www.tibame.com/" title="資策會" target="_blank">中壢資策會第71期出來必是精品</a></p>
			</div>
		</div>
	</div>
	<!--copy-->
</body>
</html>