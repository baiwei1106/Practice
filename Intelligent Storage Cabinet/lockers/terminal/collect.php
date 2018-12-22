<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
    <title>智能储物柜</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />



	</head>
	<body>
		
    <div class="top">
    <span>智能储物柜</span>
    <button class="admin" onClick="location.href='index.php'">返回首页</button>
</div>
		<div class="main2">
			<div>
				<span class="yzm">
					请输入验证码：
				</span>
				<br>
                <form action="collect-server.php" method="get" id="form">
				<input type="text" id="text" name="yzm"/>
                </form>
			</div>	

			<div class="inkey">
				<div>
			        <button class="key" onclick="on_click(1)">1</button>
			        <button class="key" onclick="on_click(2)">2</button>
			        <button class="key" onclick="on_click(3)">3</button>
			    </div>
			    <div>
			        <button class="key" onclick="on_click(4)">4</button>
			        <button class="key" onclick="on_click(5)">5</button>
			        <button class="key" onclick="on_click(6)">6</button>
			    </div>
			    <div>
			        <button class="key" onclick="on_click(7)">7</button>
			        <button class="key" onclick="on_click(8)">8</button>
			        <button class="key" onclick="on_click(9)">9</button>
			    </div>
			    <div>
			        <button class="key1" onclick="onclickc()">清除</button>
			        <button class="key" onclick="on_click(0)">0</button>
			        <button class="key1" onclick="onclickok()">确认</button>
			    </div>
			    <script type="text/javascript">
    	var input=document.getElementById("text");
    	count=0;
		function on_click(x){
			if(count<4){
				count++;
				input.value+=x;
			}
			
		}
		function onclickc(){
			count=0;
			input.value=null;
		}
		function onclickok(){
			if(count!=4)
				alert('输入验证码位数不正确！');
			else{
                var form = document.getElementById('form');

form.submit();
            }
				
		}
    </script>
			</div>
			
		</div>
        <div class="fooder">
    <span><span id="msc">30</span>秒后自动返回首页！</span>
</div>
<script type="text/javascript">
	(function BackHome(time) {
            if (time <= 0) {
                top.location.href = "index.php";
                return;
            }
            time -= 1;
            $("#msc").text(time);
            setTimeout(function () { BackHome(time); }, 1000);
        })(31);
	</script>
		
		
	</body>
</html>
