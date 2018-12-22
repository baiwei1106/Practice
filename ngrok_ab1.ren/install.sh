mkdir cert
cd cert
openssl genrsa -out rootCA.key 2048
openssl req -x509 -new -nodes -key rootCA.key -subj "/CN=ab1.ren" -days 5000 -out rootCA.pem
openssl genrsa -out device.key 2048
openssl req -new -key device.key -subj "/CN=ab1.ren" -out device.csr
openssl x509 -req -in device.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out device.crt -days 5000


ngrokd -tlsKey="snakeoil.key" -tlsCrt="tlsnakeoil.crt" -domain="bttcacm.cn"  -httpAddr=":8080" -httpsAddr=":8081" -tunnelAddr=":7878"


openssl genrsa -out rootCA.key 2048openssl req -x509 -new -nodes -key rootCA.key -subj "/CN=$NGROK_DOMAIN" -days 5000 -out rootCA.pemopenssl genrsa -out server.key 2048openssl req -new -key server.key -subj "/CN=$NGROK_DOMAIN" -out server.csropenssl x509 -req -in server.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out server.crt -days 5000


cp -n rootCA.pem ../ngrok/assets/client/tls/ngrokroot.crt
cp -n device.crt ../ngrok/assets/server/tls/snakeoil.crt
cp -n device.key ../ngrok/assets/server/tls/snakeoil.key


/usr/local/ngrok/bin/ngrokd -domain="ab1.ren" -tlsCrt="/usr/local/ngrok/server.crt" -tlsKey="/usr/local/ngrok/server.key" -httpAddr=":80" -httpsAddr=":443" -tunnelAddr=":4443"