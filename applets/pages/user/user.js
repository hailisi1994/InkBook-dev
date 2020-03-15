// pages/user/user.js
import util from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { postRequest } = wxRequest;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    bookArr: [
      {
        id: 1,
        img: '../../images/index/index-logo.png',
        number: '455421',
        name: '鲁滨孙漂流记',
        dueTime: '2020年3月15日',
        remainTime: '1天',
      },
      {
        id: 2,
        img: '../../images/index/index-logo.png',
        number: '240421',
        name: '西游记',
        dueTime: '2020年4月15日',
        remainTime: '30天',
      },
      {
        id: 3,
        img: '../../images/index/index-logo.png',
        number: '455421',
        name: '红楼梦',
        dueTime: '2020年3月15日',
        remainTime: '1天',
      },
      {
        id: 4,
        img: '../../images/index/index-logo.png',
        number: '240421',
        name: '水浒传',
        dueTime: '2020年4月15日',
        remainTime: '30天',
      },
    ],
    totalNumber: '10',
    expiredNumber: '5',
  },

  // 退出登录
  logout() {
    console.log('logout');
    wx.removeStorageSync('userInfo');
    wx.redirectTo({
      url: '../login/login',
    });
  },


  getData() {
    const { id } = wx.getStorageSync('userInfo');
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    postRequest(`/borrow/getMyBooks`, { id }).then(res => {
      wx.hideLoading();
      console.log(res);
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 判断有没有登录过
    const userInfo = wx.getStorageSync('userInfo');
    if (userInfo) { // 有登录过
      this.getData();
    } else { // 退回登录页面
      wx.redirectTo({
        url: '../login/login',
      });
    }
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