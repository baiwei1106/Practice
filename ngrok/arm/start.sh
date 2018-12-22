#!/bin/bash
chmod +x ngrok
./ngrok -config=ngrok.cfg -log=ngrok.log start acm ssh urp &
