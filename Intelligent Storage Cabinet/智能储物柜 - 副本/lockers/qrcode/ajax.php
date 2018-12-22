<?php 
    header("Content-Type:text/html;charset=utf-8");
    require_once('../include/my_func.inc.php');
    require_once('../include/db_info.inc.php');
    require_once('../include/pdo.php');
	if (isset($_POST['action']) && isset($_POST['box_id']) && isset($_POST['user_id']))
	{
		switch($_POST['action'])
		{
			case "1":btn1($_POST['box_id'],$_POST['user_id']);break;
			case "2":btn2($_POST['box_id'],$_POST['user_id']);break;
			default:break;
		}
	}
	
	function btn1($box,$user)
	{
		$sql="SELECT * FROM `box` WHERE `box_id` = ? LIMIT 1";
		$result=pdo_query($sql,$box);
		foreach($result as $row){
			$flag=$row['flag'];
		}
		if($flag!=0){
			echo "1";
			return;
		}

		$sql="UPDATE `box` set `flag`=1,`userid`=?,`operateflag`=0 WHERE `box_id`=?";
		@pdo_query($sql,$user,$box) ;
			echo "0";

		$sql="insert INTO `memory`(`user_id`,`box_id`,`flag`,`date`) VALUES(?,?,?,now())";
		pdo_query($sql,$user,$box,1) ;
	}
	function btn2($box,$user)
	{
		$sql="SELECT * FROM `box` WHERE `box_id` = ? LIMIT 1";
		$result=pdo_query($sql,$box);
		foreach($result as $row){
			$flag=$row['flag'];
		}
		if($flag!=2){
			echo "1";
			return;
		}

		$sql="UPDATE `box` set `flag`=3,`userid`=null,`operateflag`=null WHERE `box_id`=?";
		@pdo_query($sql,$box) ;
			echo "0";

		$sql="insert INTO `memory`(`user_id`,`box_id`,`flag`,`date`) VALUES(?,?,?,now())";
		pdo_query($sql,$user,$box,0) ;
	}
?>
