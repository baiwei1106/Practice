<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<link rel="stylesheet" href="css/style.css" />
	<!-- <script type="text/javascript" language="javascript">
		var xmlHttp;  
		function createXMLHttpRequest(){ 
			if(window.ActiveXObject){  
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  
			}  
			else if(window.XMLHttpRequest){  
				xmlHttp = new XMLHttpRequest();  
			}  
		}  
		function fun(value,box_id,user_id){
			createXMLHttpRequest();  
			var url="ajax.php";  
			xmlHttp.open("POST",url,true); 
			xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xmlHttp.onreadystatechange = callback;  
            if(value==1 && !confirm("确定存包吗？")){
                return 0;
            }
            if(value==2 && !confirm("确定取包吗？"))
                return 0;
            $('#go').attr("disabled", "true");
			xmlHttp.send("action=" + value + "&box_id=" + box_id + "&user_id=" + user_id);
		}  
		function callback(){  
			if(xmlHttp.readyState == 4){  
				if(xmlHttp.status == 200){
                    if(xmlHttp.responseText == 1)
					    alert('系统繁忙！请稍后再试！');
                    location.reload();
				}  
			}  
		} 
        setInterval('reloadView()',15000); //每15秒刷新一次页面下边显示的数据
	</script> -->

	<script>
    	var input=document.getElementById("text");
    	count=0;
		function on_click(x){
			if(count<6){
				count++;
				input.value+=x;
			}
		}
		function onclickc(){
			count=0;
			input.value=null;
		}
		function onclickok(){
			if(count!=6)
				alert('输入验证码位数不正确！');
			else
				alert(input.value);
		}
    </script>
	<script>
		function loadXMLDoc()
		{
			var xmlhttp;
			if (window.XMLHttpRequest)
			{
				xmlhttp=new XMLHttpRequest();
			}
			else
			{
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function()
			{
				if (xmlhttp.readyState==4 && xmlhttp.status==200)
				{
					document.getElementById("table").innerHTML=xmlhttp.responseText;
					//alert(xmlhttp.responseText);
				}
			}
			xmlhttp.open("GET","server.php",true);
			xmlhttp.send();
		}
		setInterval('loadXMLDoc()',2000);
    </script>
	

	</head>
	<body onload="loadXMLDoc();">
	<div class="top">
			<span>智能储物柜</span>
			<!-- <button class="admin">返回首页</button> -->
		</div>
		<div class="main">
			<div class="table" id="table">
				<div class="a0"><span class="text">1号柜</span><span class="flag">空闲</span></div>
				<div class="a2"><span class="text">2号柜</span><span class="flag">已占用</span></div>
				<div class="a4"><span class="text">3号柜</span><span class="flag">停用</span></div>
			</div>
			<div class="cun" onClick="location.href='storage.php'">存包</div>
			<div class="qu" onClick="location.href='collect.php'">取包</div>
		</div>
		
		<div class="fooder">
			<!-- <span><span>10</span>秒后自动返回首页！</span> -->
		</div>
		
	</body>
</html>



