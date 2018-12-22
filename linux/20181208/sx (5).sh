#!/bin/bash
echo "input1:"
read a
echo "input2:"
read c
echo "input3:"
read b
if [ $c = "+" ]
then
	echo -n "$a+$b="
	expr $a + $b
fi
if [ $c = "-" ]
then
	echo -n "$a-$b="
	expr $a - $b
fi
if [ $c = "*" ]
then
	echo -n "$a*$b="
	expr $a * $b
fi
if [ $c = "/" ]
then
	echo -n "$a/$b="
	expr $a / $b
fi
if [ $c = "%" ]
then
	echo -n "$a%$b="
	expr $a % $b
fi
