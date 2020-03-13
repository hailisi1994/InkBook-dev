const SERVER_URL = "http://127.0.0.1:8080";//定义服务器地址

const postReq = (param,url) => {
  wx.request({
    url: SERVER_URL + url,
    method: 'POST',
    data: param,
    header: {"Content-Type": "application/json"},
    success(res){
      console.log(res);
    }
  })
}

module.exports = {
  postReq: postReq
}