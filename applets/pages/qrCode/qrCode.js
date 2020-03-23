// pages/qrCode/qrCode.js
import drawQrcode from '../../utils/weapp.qrcode.esm.js'; // 适用于低于2.90版本使用

Page({

  /**
   * 页面的初始数据
   */
  data: {
    qrcode_w: 200,
  },

  // 生成二维码
  getQrcode: function (canvasId, canvasText) {
    const that= this;
    let { qrcode_w } = that.data;
    const query = wx.createSelectorQuery();
    query.select('#canvas-inner').boundingClientRect(function (rect) {
      qrcode_w = rect.width;
      drawQrcode({
        width: qrcode_w,
        height: qrcode_w,
        canvasId,
        text: canvasText,
        // ctx: wx.createCanvasContext(canvasId),
        // v1.0.0+版本支持在二维码上绘制图片
        // image: {
        //   imageResource: '../../images/icon.png',
        //   dx: 70,
        //   dy: 70,
        //   dWidth: 60,
        //   dHeight: 60
        // },
      });
      that.setData({
        qrcode_w,
      });
    }).exec();
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('options', options);
    const that = this;
    const { userId, bookId, isbn } = options;
    that.getQrcode('myCanvas', `${userId}@${bookId}@${isbn}`);
    // that.getQrcode('myCanvas', `11121212121212`);
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