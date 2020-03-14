var Promise = require('./es6-promise.js');
import config from '../config/config.js'
const {baseUrl} = config;

function wxPromisify(fn){
  return function(obj={}){
    return new Promise((resolve,reject) => {
      obj.success = function(res){resolve(res)}
      obj.fail = function (res) { reject(res) }
      fn(obj)
    })
  }
}

Promise.prototype.finally = function(callback){
  let P = this.constructor;
  return this.then(
    value => P.resolve(callback()).then(()=>value),
    reason => P.resolve(callback()).then(() => {throw reason})
  )
}

function getRequest(url,data){
  var getRequest = wxPromisify(wx.request);
  return getRequest({
    url: baseUrl+url,
    method: 'GET',
    data: data,
    header: {'Content-Type':'application/json'}
  })
}

function postRequest(url, data) {
  console.log('Url',baseUrl+url)
  var postRequest = wxPromisify(wx.request);
  return postRequest({
    url: baseUrl+url,
    method: 'POST',
    data: data,
    header: { 'Content-Type': 'application/json' }
  })
}

module.exports = {
  postRequest: postRequest,
  getRequest: getRequest
}