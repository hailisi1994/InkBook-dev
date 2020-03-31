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
    bookData: {},
    userData: {},
    borrowData: {},
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
            if (that.data.borrowType === '2') {
              const { borrow } = res1.data.data;
              that.setData({
                borrowData: { ...borrow },
              });
            }
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
    wx.showLoading({
      title: '加载中...',
      mask: true,
    });
    if (borrowType === '1') {
      const { id: bookId } = bookData;
      const { id: userId } = userData;
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
      const { id } = borrowData;
      const params = {
        borrowId: id,
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
    const that = this;
    console.log('options', options);
    const { borrowType } = options;
    wx.setNavigationBarTitle({
      title: borrowType === '1' ? '借书详情' : '还书详情',
    })
    that.setData({
      borrowType, // 1借书， 2还书
    }, () => {
      that.openScan();
    });
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