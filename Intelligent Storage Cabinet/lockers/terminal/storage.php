<!DOCTYPE html>
<html lang="zh-cn">
<head>

    <title>智能储物柜</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />

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
            // if(value==1 && !confirm("确定存包吗？")){
            //     return 0;
            // }
            // if(value==2 && !confirm("确定取包吗？"))
            //     return 0;
            $('#go').attr("disabled", "true");
			xmlHttp.send("action=" + value + "&box_id=" + box_id + "&user_id=" + user_id);
            // alert("action=" + value + "&box_id=" + box_id + "&user_id=" + user_id);
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
    <script>
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
    <script>
	(function BackHome(time) {
            if (time <= 0) {
                top.location.href = "index.php";
                return;
            }
            time -= 1;
            $("#ny30").text(time);
            setTimeout(function () { BackHome(time); }, 1000);
        })(31);
	</script>
</head>
<body>

<body>


<div class="top">
    <span>智能储物柜</span>
    <button class="admin" onClick="location.href='index.php'">返回首页</button>
</div>




<?php
header("Content-Type:text/html;charset=utf-8");
require_once('../include/my_func.inc.php');
require_once('../include/db_info.inc.php');
require_once('../include/pdo.php');
if(isset($_GET['box_id']) && isset($_GET['user'])){
    $box_id=RemoveXSS($_GET['box_id']);
    $iuser=RemoveXSS($_GET['user']);
    
    //  echo "box_id:".$box_id."\n";
    //  echo "user:".$user."\n";

    $sql="SELECT * FROM `box` WHERE `box_id` = ? LIMIT 1";
    $result=pdo_query($sql,$box_id);
    foreach($result as $row){
        $flag=$row['flag'];
        $user=$row['unkonwuserid'];
        $time=$row['time'];
    }

    date_default_timezone_set('Asia/Shanghai');
    $now_time = date('Y-m-d H:i:s');
    $now_time=strtotime($now_time);
    $time=strtotime($time);
    $time_flag=0;
    if($now_time-$time>=10){
        $time_flag=1;
    }

    if($flag==0){
        require('storage-confirm.php');
        exit(0);
    }
    if($flag==2){
        if($user==$iuser){
            if($time_flag==0){
                require('storage-success.php');
                exit(0);
            }
        }
        else{
            require('occupy.php');
            exit(0);
        }
    }
    if($flag==1 || $flag==3){
        require('wait.php');
        exit(0);
    }


}else{
    $sql = "SELECT COUNT(flag) AS icount FROM box WHERE flag=0";
    $result=pdo_query($sql);
    foreach($result as $row){
        $icount=$row['icount'];
    }
    if($icount>=1){
        $sql="SELECT * FROM `box` WHERE `flag` = 0 LIMIT 1";
        $result=pdo_query($sql,$box_id);
        foreach($result as $row){
            $box=$row['box_id'];
        }
        $x=rand(1000,9999);
        $url="storage.php?box_id=".$box."&user=".$x;
        header("refresh:0;url={$url}");
    }
    else{
        require('full.php');
        exit(0);
    }
}

?>
