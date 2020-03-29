// pages/borrowDetails/borrowDetails.js
import wxRequest from '../../utils/wxRequest.js'
const { postRequest } = wxRequest;

Page({
  /**
   * 页面的初始数据
   */
  data: {
    loadingMask: true,
    borrowType: '1', // 1借书， 2还书
    bookData: {
      id: "20200328210821125",
      isbn: "9787535421036",
      title: "乾隆皇帝（全六册）",
      author: "二月河",
      pub: "长江文艺出版社",
      ifOn: 1,
      sort: "4",
      createTime: "2020-03-28T13:08:21.000+0000",
      updateTime: "2020-03-28T13:08:21.000+0000",
      doubanUrl: "https://book.douban.com/subject/1095889/",
      coverUrl: "https://img9.doubanio.com/view/subject/l/public/s9076791.jpg",
      framing: "平装",
      series: "",
      translator: "",
      price: 144.0,
      pages: 3392,
      publication: "2001-02-01T00:00:00",
    },
    userData: {
      id: "b95e4cb8-6f41-448e-9d10-448cae3aae93",
      name: "yehao",
      nickName: "คิดถึง",
      age: 11,
      pro: "3",
      grade: "4",
      gender: "1",
      phone: "13119583082",
      integrity: 10,
      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKqNeRAvXmbmG23wUwYfxWRGEMIprxg5Q5vfudzzojWm2DrVrxTYXScoHyvs3yfRGt5QFibwHvSuLg/132",
      password: "123456",
      role: 0,
    },
  },

  // 点击扫描(学生的二维码)
  openScan: function () {
    const that = this;
    wx.scanCode({
      scanType: ['barCode', 'qrCode', 'datamatrix', 'pdf417'],
      success(res) {
        console.log('res', res.result);
        const params = JSON.parse(res.result);
        console.log('params', params);
        // console.log('params', params);
        //扫码获取图书信息
        postRequest('/borrow/scanBorrowInfo', params).then(res1 => {
          console.log('res1', res1);
          if (res1.data.status === 200) {
            const { user, book } = res1.data.data;
            that.setData({
              bookData: { ...book },
              userData: { ...user },
              loadingMask: false,
            });
          }
        });
      },
      fail(err) {
        wx.navigateBack();
        console.log('error:', err);
      },
    });
  },

  // 确定借书或者还书
  sureBorrowOrReturn: function () {
    const that = this;
    const { bookData, userData, borrowType } = that.data;
    const { id: bookId, borrowId } = bookData;
    const { id: userId } = userData;
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    if (borrowType === '1') {
      const params = {
        userId,
        bookId,
      };
      postRequest('/borrow/save', params).then(res => {
        wx.hideLoading();
        console.log('res', res);
        if (res.data.status === 200) {
          wx.showToast({
            title: '已借阅',
            mask: 'true',
            icon: 'success',
            duration: 1500,
          });
          // that.setData({});
        }
      });
    } else {
      const params = {
        borrowId,
      };
      postRequest('/borrow/returnBook', params).then(res => {
        wx.hideLoading();
        console.log('res', res);
        if (res.data.status === 200) {
          wx.showToast({
            title: '已归还',
            mask: 'true',
            icon: 'success',
            duration: 1500,
          });
        }
      });
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('options', options);
    const { borrowType } = options;
    this.setData({
      borrowType, // 1借书， 2还书
    });
    this.openScan();
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