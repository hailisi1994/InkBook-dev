// pages/bookDetails/bookDetails.js
import utils from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { getRequest } = wxRequest;
const { formatDate } = utils;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    bookData: {
      title: '暂无',
      author: '暂无',
      pub: '暂无',
      translator: '暂无',
      pages: '暂无',
      price: '暂无',
      coverUrl: 'https://file02.sg560.com/upimg02/2014/12/02/14100212868875.jpg',
      isbn: '暂无',
      publication: '暂无',
      framing: '暂无',
      series: '暂无',
      sort: '1',
    },
  },

  // 获取书籍内容数据
  getData: function (id) {
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    const that = this;
    getRequest(`/book/selectOne?id=${id}`).then(res => {
      wx.hideLoading();
      console.log('res', res);
      that.setData({
        bookData: {
          ...res.data,
          publication: formatDate(res.data.publication),
        },
      });
    });
  },

  // 点击借阅
  borrow: function () {
    const userInfo = wx.getStorageSync('userInfo');
    const { id: userId } = userInfo;
    const { bookData } = this.data;
    const { id: bookId, isbn } = bookData;
    wx.navigateTo({
      url: `../qrCode/qrCode?userId=${userId}&bookId=${bookId}&isbn=${isbn}`,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('options', options);
    // 20200322140807768
    const { id } = options;
    this.getData(id);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})