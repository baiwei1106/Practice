#!/bin/bash

read score
if [ $score -lt 0 ] || [ $score -gt 100 ]
then
	echo "Error!"
elif [ $score -ge 90 ]
then
	echo "A!"
elif [ $score -ge 80 ] 
then
	echo "B!"
elif [ $score -ge 70 ]
then
	echo "C!"
elif [ $score -ge 60 ]
then
	echo "D!"
else
	echo "E!"
fi
