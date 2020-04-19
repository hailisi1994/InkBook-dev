// pages/bookDetails/bookDetails.js
import utils from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { getRequest, postRequest } = wxRequest;
const { formatDate } = utils;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    borrowType: '1', // 1借书， 2还书，  3管理
    borrowId: '',
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
      location: '暂无',
    },
    isEdit: true,
    sortIndex: '0',
    sortArr: [
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
      // console.log('res', res);
      that.setData({
        bookData: {
          ...res.data,
          publication: formatDate(res.data.publication),
        },
      });
    });
  },

  // 点击借阅或者还书
  borrowOrReturn: function () {
    const userInfo = wx.getStorageSync('userInfo');
    const { id: userId } = userInfo;
    const { bookData, borrowType, borrowId } = this.data;
    const { id: bookId } = bookData;
    // 1借书， 2还书
    if (borrowType === '1') {
      wx.navigateTo({
        url: `../qrCode/qrCode?userId=${userId}&bookId=${bookId}&type=1`,
      });
    } else {
      wx.navigateTo({
        url: `../qrCode/qrCode?borrowId=${borrowId}&type=2`,
      });
    }
    
  },

  // 点击编辑
  editOrSave: function () {
    const that = this;
    const { isEdit } = that.data;
    if (!isEdit) { // 保存时候
      const { bookData } = that.data;
      const eventChannel = that.getOpenerEventChannel();
      const params = {
        ...bookData,
        publication: new Date(bookData.publication),
      };
      // console.log('params', params);
      wx.showLoading({
        title: '保存中...',
        mask: true,
      });
      postRequest('/book/save', params).then(res => {
        // console.log('保存嘻嘻', res);
        wx.hideLoading();
        const { data } = res;
        if (data.status === 200) {
          eventChannel.emit('onHandChange');
          wx.showToast({
            title: '保存成功',
            icon: 'success',
            mask: true,
            duration: 1500,
          });
        } else {
          wx.showToast({
            title: data.error,
            icon: 'none',
            mask: true,
            duration: 1500,
          });
        }
      });
    }
    this.setData({
      isEdit: !isEdit,
    });
  },

  // 点击删除
  delete: function () {
    const that = this;
    const eventChannel = that.getOpenerEventChannel();
    wx.showLoading({
      title: '删除中...',
      mask: true,
    });
    getRequest(`/book/delete/${that.data.bookId}`).then(res => {
      wx.hideLoading();
      // console.log('res', res);
      if (res.statusCode === 200) {
        eventChannel.emit('onHandChange');
        wx.showToast({
          title: '删除成功',
          icon: 'success',
          mask: true,
          duration: 1500,
          success: function () {
            wx.navigateBack();
          },
        });
      } else {
        wx.showToast({
          title: '删除失败',
          icon: 'none',
          mask: true,
          duration: 1500,
        });
      }
    });
  },

  // 修改书名
  modifyTitle: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        title: e.detail.value,
      },
    });
  },

  // 修改作者
  modifyAuthor: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        author: e.detail.value,
      },
    });
  },

  // 修改出版社
  modifyPub: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        pub: e.detail.value,
      },
    });
  },

  modifyTranslator: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        translator: e.detail.value,
      },
    });
  },

  // 修改出版时间
  modifyPublication: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        publication: e.detail.value,
      },
    });
  },

  // 修改页数
  modifyPages: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        pages: e.detail.value,
      },
    });
  },

  // 修改定价
  modifyPrice: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        price: e.detail.value,
      },
    });
  },

  // 修改装帧
  modifyFraming: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        framing: e.detail.value,
      },
    });
  },

  // 修改丛书
  modifySeries: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        series: e.detail.value,
      },
    });
  },

  // 修改ISBN
  modifyISBN: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        isbn: e.detail.value,
      },
    });
  },

  // 修改存放位置
  modifyLocation: function (e) {
    const { bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        location: e.detail.value,
      },
    });
  },

  // 选择分类类型
  bindPickerChange: function (e) {
    const { sortArr, bookData } = this.data;
    this.setData({
      bookData: {
        ...bookData,
        sort: sortArr[e.detail.value].id,
      },
      sortIndex: e.detail.value,
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log('options', options);
    const { bookId, type } = options;
    this.setData({
      borrowType: type,
      bookId,
      isEdit: type === '3',
    });
    this.getData(bookId);
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