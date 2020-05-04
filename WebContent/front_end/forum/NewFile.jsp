<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="<%=request.getContextPath()%>/front_end/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<select id="CityPicker">

<option></option>
<option >台北</option>
<option >台中</option>
<option >台南</option>

</select>


<div id=result></</div>



</body>
<script>
$("#CityPicker").change(
	function(){
	
	  console.log("Change!");
	  if(!(this.value==null || this.value==""))
	{
		ajaxCallJsonP("https://zlcsc.cyc.org.tw/api");
	 }else{
	 
// 			$("#result").html("");	 
	 }
	}
	);
	
	function ajaxCallJsonP(url)
	{
		var data=$.getJSON(url);
		
		data.success(function(msg){
			
			console.log(msg.gym[0]);
		});
		data.error(function(msg){
			
			
		});
		
	}
	</script>

</html>