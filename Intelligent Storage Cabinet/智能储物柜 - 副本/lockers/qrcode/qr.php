<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta content="width=device-width, height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"> 
    <link rel="stylesheet" href="css/qrcode.css" />
    <title>智能储物柜</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" language="javascript">
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
	</script>
</head>
<body>
<?php
header("Content-Type:text/html;charset=utf-8");
require_once('../include/my_func.inc.php');
require_once('../include/db_info.inc.php');
require_once('../include/pdo.php');
if(isset($_GET['box_id']) && isset($_GET['user_id'])){
    $box_id=RemoveXSS($_GET['box_id']);
    $user_id=RemoveXSS($_GET['user_id']);
    
    //  echo "box_id:".$box_id."\n";
    //  echo "user_id:".$user_id."\n";

    $sql = "SELECT COUNT(box_id) AS icount FROM box WHERE box_id=?";
    $result=pdo_query($sql,$box_id);
    foreach($result as $row){
        $icount=$row['icount'];
    }

    $sql = "SELECT COUNT(id) AS icount FROM user WHERE id=?";
    $result=pdo_query($sql,$user_id);
    foreach($result as $row){
        $usercount=$row['icount'];
    }
    if($icount==0){
        require('top-error.php');
    }
    else{   
        require('top.php');
    }

    if($icount==0 || $usercount==0){
        require('access-volation.php');
        exit(0);
    }

    $sql="SELECT * FROM `box` WHERE `box_id` = ? LIMIT 1";
    $result=pdo_query($sql,$box_id);
    foreach($result as $row){
        $flag=$row['flag'];
        $user=$row['userid'];
        $time=$row['time'];
    }

    date_default_timezone_set('Asia/Shanghai');
    $now_time = date('Y-m-d H:i:s');
    $now_time=strtotime($now_time);
    $time=strtotime($time);
    $time_flag=0;
    if($now_time-$time>=5){
        $time_flag=1;
    }

    if($flag==0){
        if($time_flag==0){
            require('collect-success.php');
            exit(0);
        }
        require('storage.php');
        // echo "柜空闲";
        // echo "<button type=button class=btn id=btn1 onclick=fun(1,".$box_id.",".$user_id.")>存</button>";
        exit(0);
    }
    if($flag==2 ){
        if($user==$user_id){
            if($time_flag==0){
                require('storage-success.php');
                exit(0);
            }
            require('collect.php');
            // echo "本人存的柜";
            // echo "<button type=button class=btn id=btn1 onclick=fun(2,".$box_id.",".$user_id.")>取</button>";
            exit(0);
        }
        else{
            require('occupy.php');
            exit(0);
        }
    }
    if($flag==4){
        require('stop.php');
        exit(0);
    }
    if($flag==1 || $flag==3){
        require('wait.php');
        exit(0);
    }
}else{
    if(isset($_GET['box_id'])){
        $box_id=RemoveXSS($_GET['box_id']);
        require('top.php');
    }
    else{
        require('top-error.php');
    }

    require('unapp.php');
    exit(0);
}

?>
</body>
</html>