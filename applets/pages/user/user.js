// pages/user/user.js
import utils from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { getRequest } = wxRequest;
const { formatDate } = utils;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    loadingMask: true,
    // role: 0用户 1管理员
    role: 0,
    bookArr: [],
    totalNumber: '10',
    expiredNumber: '5',
    borrowCount: '', // 总借书
    expiredCount: '', // 已到期
    expiringCount: '', // 即将到期
    totalCount: '',
    userInfo: {},
  },

  // 退出登录
  logout() {
    console.log('logout');
    wx.removeStorageSync('userInfo');
    wx.redirectTo({
      url: '../login/login',
    });
  },

  // 获取学生的借阅书籍的记录
  getData() {
    const that = this;
    const { id } = wx.getStorageSync('userInfo');
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    getRequest(`/borrow/getBooksByUserId/${id}`).then(res => {
      wx.hideLoading();
      console.log(res);
      const { data } = res;
      if (data.status === 200) {
        that.setData({
          bookArr: data.data.books.map(item => ({
            ...item,
            borrowTime: formatDate(item.borrowTime),
            deadline: formatDate(item.deadline),
          })),
          borrowCount: data.data.counts.borrowCount,
          expiredCount: data.data.counts.expiredCount,
          expiringCount: data.data.counts.expiringCount,
          totalCount: data.data.counts.totalCount,
        });
      } 
    });
  },

  // 点击跳转到详情页
  jumpView: function (e) {
    const { bookArr } = this.data;
    const { bookid, borrowid } = e.currentTarget.dataset;
    // type: 1借书， 2还书
    wx.navigateTo({
      url: `../bookDetails/bookDetails?borrowId=${borrowid}&bookId=${bookid}&type=2`,
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const that = this;
    // 判断有没有登录过
    const userInfo = wx.getStorageSync('userInfo');
    if (userInfo) { // 有登录过
      const { role } = userInfo;
      // role: 0用户 1管理员
      if (role === 0) {
        that.getData();
      }
      that.setData({
        role,
        loadingMask: false,
        userInfo,
      });
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