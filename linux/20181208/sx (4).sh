#!bin/bash
echo "input1: "
read a

echo "input2: "
read b

echo "input3: "
read c

if [ $a \> $b ]; then
	t=$a; a=$b; b=$t
fi
if [ $b \> $c ]; then
	t=$b; b=$c; c=$t
fi
if [ $a \> $b ]; then
	t=$a; a=$b; b=$t
fi
echo "$a < $b < $c"
