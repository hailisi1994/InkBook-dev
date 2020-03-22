// pages/search/search.js
import wxRequest from '../../utils/wxRequest.js'
import utils from '../../utils/util.js'
const { postRequest } = wxRequest;
const { formatDate } = utils;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    curentPage: 1, // 当前页数
    totalPage: 0, // 内容总条数
    bookList: [],
    sortIndex: '0',
    sortArray: [
      {
        value: '书名',
        id: '1',
      },
      {
        value: '分类',
        id: '2',
      },
      {
        value: '作者',
        id: '3',
      },
    ],
    sort1Index: '0',
    sort1Array: [
      {
        value: '小说',
        id: '1',
      },
      {
        value: '文学',
        id: '2',
      },
      {
        value: '经济',
        id: '3',
      },
      {
        value: '其他',
        id: '4',
      },
    ],
    searchText: '',
    isLoading: true,
  },

  // 获取列表数据
  getData: function (isRefresh = false) {
    const that = this;
    // isRefresh 是否刷新请求
    const {
      sortIndex,
      sort1Index,
      searchText,
      sort1Array,
      curentPage,
      bookList,
    } = that.data;
    let book = {};
    if (sortIndex === '0') {
      book = {
        title: searchText,
      };
    } else if (sortIndex === '1') {
      book = {
        sort: sort1Array[sort1Index].id,
      };
    } else if (sortIndex === '2') {
      book = {
        author: searchText,
      };
    }
    const params = {
      book,
      pagination: {
        page: isRefresh ? 1 : Number(curentPage),
        pageSize: 5,
      },
    };
    console.log('params', params);
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    postRequest('/book/list', params).then(res => {
      wx.hideLoading();
      console.log('res', res);
      const { data } = res;
      let { bookList } = that.data;
      if (isRefresh) { // isRefresh 是否刷新请求
        bookList = [];
      }
      if (data.status === 200) {
        that.setData({
          bookList: bookList.concat(data.data.bookList.map(item => ({
            ...item,
            publication: formatDate(item.publication),
          }))),
          curentPage: data.data.pagination.page,
          totalPage: data.data.pagination.total,
          isLoading: false,
        });
      }
    });
  },


  // 点击第一级分类
  bindPickerChange: function (e) {
    this.setData({
      sortIndex: e.detail.value,
      searchText: '',
      sort1Index: '0',
    });
  },

  // 点击第二级分类
  bindPickerChange1: function (e) {
    const that = this;
    that.setData({
      sort1Index: e.detail.value,
      searchText: '',
    }, () => {
      that.getData(true); // 刷新请求
    });
  },

  // 获取搜索框的内容
  getSearchText: function (e) {
    this.setData({
      searchText: e.detail.value,
    });
  },

  // 点击搜索
  search: function (e) {
    const that = this;
    that.getData(true);
  },

  // 点击跳转到详情页
  jumpView: function (e) {
    const { bookList } = this.data;
    const { id } = e.currentTarget.dataset;
    wx.navigateTo({
      url: `../bookDetails/bookDetails?id=${id}`,
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {},

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
  onReachBottom: function (e) {
    const that = this;
    const { bookList, totalPage, curentPage } = that.data;
    // 判断是否数据全部加载完了
    if (totalPage === bookList.length) return false;
    that.setData({
      isLoading: true,
      curentPage: curentPage + 1,
    }, () => {
      that.getData();
    });
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})