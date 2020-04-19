// pages/user/user.js
import utils from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { getRequest } = wxRequest;
const { formatDate } = utils;

//引入echart
import * as echarts from '../../ec-canvas/echarts';
const app = getApp();

function setOption(chart,data) {
 
  var option = {
    backgroundColor: "#ffffff",
    color: [ "#32C5E9", "#67E0E3", "#91F2DE", "#FFDB5C", "#FF9F7F"],
    series: [{
      label: {
        normal: {
          fontSize: 14
        }
      },
      type: 'pie',
      center: ['50%', '50%'],
      radius: ['40%', '55%'],
      data: data
    }]
  };

  chart.setOption(option);
  return chart;
}



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
    ec: { 
      // 将 lazyLoad 设为 true 后，需要手动初始化图表
      lazyLoad: true
     }
  },

  // 退出登录
  logout() {
    console.log('logout');
    wx.removeStorageSync('userInfo');
    wx.redirectTo({
      url: '../login/login',
    });
  },
  //初始化统计图
  initChart(data) {
    this.ecComponent.init((canvas, width, height, dpr) => {
      // 获取组件的 canvas、width、height 后的回调函数
      // 在这里初始化图表
      const chart = echarts.init(canvas, null, {
        width: width,
        height: height,
        devicePixelRatio: dpr // new
      });
      setOption(chart,data);
      // 将图表实例绑定到 this 上，可以在其他成员函数（如 dispose）中访问
      this.chart = chart;
      this.setData({
        isLoaded: true
      });
      // 注意这里一定要返回 chart 实例，否则会影响事件处理等
      return chart;
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
    const that = this;
    // 获取echart组件
    this.ecComponent = this.selectComponent('#mychart-dom-pie');
    const userInfo = wx.getStorageSync('userInfo');
    if (userInfo && userInfo.role === 1) {
      //管理员获取统计数据
      getRequest(`/borrow/getPieChartData`).then(
        res => {
          if (res.data.status == 200) {
            var pieChartData = res.data.data.pieChartData
            console.log(pieChartData)
            that.initChart(pieChartData);
          }
        }
      );

    }
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