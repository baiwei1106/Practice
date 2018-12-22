<?php 
    header("Content-Type:text/html;charset=utf-8");
    require_once('../include/my_func.inc.php');
    require_once('../include/db_info.inc.php');
    require_once('../include/pdo.php');
	btn();
	
	function btn()
	{
        $str="";
        $sql="SELECT * FROM `box` WHERE `box_id` <> '0' ORDER BY `box_id` LIMIT 0, 1000";
		$result=pdo_query($sql,$box);
		foreach($result as $row){
            $box=$row['box_id'];
            $flag=$row['flag'];
            if($flag==0){
                $str=$str."<div class=a0><span class=text>".$box."号柜</span><span class=flag>空闲</span></div>";
            }
            if($flag==1){
                $str=$str."<div class=a13><span class=text>".$box."号柜</span><span class=flag>操作中</span></div>";
            }
            if($flag==2){
                $str=$str."<div class=a2><span class=text>".$box."号柜</span><span class=flag>已占用</span></div>";
            }
            if($flag==3){
                $str=$str."<div class=a13><span class=text>".$box."号柜</span><span class=flag>操作中</span></div>";
            }
            if($flag==4){
                $str=$str."<div class=a4><span class=text>".$box."号柜</span><span class=flag>已停用</span></div>";
            }
        }
        echo $str;
        return $str;
	}
 
?>
