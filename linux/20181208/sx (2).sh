#!/bin/bash
echo -n "Username:"
read username
echo -n "Password:"
read -s password
echo
if [ $username = "admin" ] && [ $password = "admin" ]
then
	echo "OK!"
else
	echo "Error!"
fi
