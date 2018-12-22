#!/bin/bash
echo -n "INPUT: "
read dir
if [ -d "/home/${dir}" ];then
	echo "文件夹存在"
else
	echo "文件夹不存在"
	mkdir "/home/${dir}"
fi
