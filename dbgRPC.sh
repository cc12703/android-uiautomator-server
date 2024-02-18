

if [ $1 = 'chkUrlByCellular' ] ; then
  curl -X POST -H "Content-type:application/json" -d '{"jsonrpc": "2.0", "id": "1f0f2655716023254ed2b57ba4198815", "method": "chkUrlByCellular", "params": [ ["https://www.baidu.com", "https://www.sina.com.cn"] ] }' 'http://127.0.0.1:9008/jsonrpc/0'
elif [ $1 = 'deviceInfo' ] ; then
  curl -X POST -d '{"jsonrpc": "2.0", "id": "1f0f2655716023254ed2b57ba4198815", "method": "deviceInfo", "params": {}}' 'http://127.0.0.1:9008/jsonrpc/0'
elif [ $1 = 'getSimcardInfo' ] ; then
  curl -X POST -d '{"jsonrpc": "2.0", "id": "1f0f2655716023254ed2b57ba4198815", "method": "getSimcardInfo", "params": {}}' 'http://127.0.0.1:9008/jsonrpc/0'
fi