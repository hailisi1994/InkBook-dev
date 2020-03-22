// pages/scan/scan.js
import utils from '../../utils/util.js'
import wxRequest from '../../utils/wxRequest.js'
const { postRequest } = wxRequest;
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
    // 扫描按钮的状态信息
    scanLoading: {
      status: false,
      text: '扫描条形码',
    },
    endDate: formatDate(new Date()),
    isShowInfo: false, // 默认不显示录入信息
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

  // 点击扫描
  openScan: function () {
    const that = this;
    that.setData({
      scanLoading: {
        status: true,
        text: '扫描中...' 
      }
    });
    wx.scanCode({
      scanType: ['barCode', 'qrCode', 'datamatrix', 'pdf417'],
      success(res) {
        //扫码获取图书信息
        postRequest('/main/scanBarcode', res).then(res1 => {
          that.setData({
            scanLoading: {
              status: false,
              text: '扫描条形码',
            },
          });
          const book = res1.data.data;
          if (res1.data.status === 200) {
            const { sortArr, bookData } = that.data;
            let sortIndex = '';
            sortArr.forEach((item, index) => {
              if (item.id === res1.data.data.sort) {
                sortIndex = index;
              }
            });
            // console.log(111, sortIndex);
            that.setData({
              isShowInfo: true,
              sortIndex,
              bookData: {
                ...bookData,
                ...res1.data.data,
                publication: formatDate(res1.data.data.publication),
                sort: res1.data.data.sort,
              },
            });
          }
        });
      },
      fail(err) {
        that.setData({
          scanLoading: {
            status: false,
            text: '扫描条形码',
          },
        });
        console.log('error:', err);
      },
    });
  },

  // 提交修改并上传信息
  formSubmit: function (e) {
    const that = this;
    // console.log('e', e.detail.value);
    const { bookData } = that.data;
    const params = {
      ...bookData,
      ...e.detail.value,
      publication: new Date(e.detail.value.publication),
    };
    console.log(params);
    wx.showLoading({
      title: '录入中...',
      mask: true,
    });
    postRequest('/book/save', params).then(res => {
      // console.log('保存嘻嘻', res);
      wx.hideLoading();
      const { data } = res;
      if (data.status === 200) {
        wx.showToast({
          title: '录入成功',
          icon: 'success',
          mask: true,
          duration: 1500,
          success: function () {
            that.setData({
              isShowInfo: false,
            });
          },
        });
      } else {
        wx.showToast({
          title: data.msg,
          icon: 'none',
          mask: true,
          duration: 1500,
        });
      }
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
