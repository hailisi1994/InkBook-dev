// pages/login/login.js
import wxRequest from '../../utils/wxRequest.js'
const { postRequest } = wxRequest;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone: '',
    password: '',
  },

  // 手机号
  bindPhoneInput(e) {
    this.setData({
      phone: e.detail.value,
    });
  },

  // 密码
  bindPasswordInput(e) {
    this.setData({
      password: e.detail.value,
    });
  },

  // 点击登录
  login() {
    const { phone, password } = this.data;
    if (!phone || phone === '') {
      wx.showToast({
        title: '请输入手机号',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }
    if (!password || password === '') {
      wx.showToast({
        title: '密码不能为空',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }

    wx.showLoading({
      title: '正在登录中...',
      mask: true,
    })
    postRequest('/user/login', {
      phone,
      password,
    }).then(
      (res) => {
        wx.hideLoading();
        const { data } = res;
        if (data.status === 200) {
          wx.showToast({
            title: '登录成功，正在跳转...',
            icon: 'none',
            mask: true,
            duration: 1500,
          });
          wx.setStorageSync('userInfo', data.data);
        } else {
          wx.showToast({
            title: data.msg,
            icon: 'none',
            mask: true,
            duration: 1500,
          });
        }
        console.log('res', res);
      }
    );
  },

  // 点击跳转到注册页面
  register: () => {
    console.log('register');
    wx.navigateTo({
      url: '../register/register',
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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