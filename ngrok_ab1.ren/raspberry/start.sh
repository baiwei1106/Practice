#/bin/bash
chmod +x ngrok
./ngrok -config=ngrok.cfg -log=log/ngrok.log start acm ssh urp > log/1.log 2>log/2.log &

