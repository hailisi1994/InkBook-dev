import util from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { postRequest} = wxRequest;
const default_book = {
  title: '暂无',
  abstract: '暂无',
  cover_url: 'https://file02.sg560.com/upimg02/2014/12/02/14100212868875.jpg',
  rating: { star_count: 0, rating_info: '暂无' }
};

Page({
  data: {
    book: default_book,
    loading: {
      status:false,
      tips: '点击扫描条形码'
      }
  },
  onLoad: function () {},
  scanBarcode: function () {
    let that = this;
    that.setData({
      loading: {
        status: true,
        tips: '扫描中...'
      }
     })
    wx.scanCode({
      success(res) {
        //扫码获取图书信息
        console.log(res)
        postRequest('/passport/scanBarcode', res).then(
          (res) => {
            const book = res.data.data;
            that.setData({ 
              book: book,
              loading: {
                status: false,
                tips: '点击扫描条形码'
              }
             })
          }
        )
      }
    })
  },
  mySetData: function(k,v){
    console.log(k)
    console.log(v)
    this.setData({k,v})
  }
})
