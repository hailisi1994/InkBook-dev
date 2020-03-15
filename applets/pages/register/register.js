// pages/register/register.js
import wxRequest from '../../utils/wxRequest.js'
const { postRequest } = wxRequest;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sexIndex: '0',
    isShowSex: true,
    // 性别
    sexArray: [
      {
        value: '男',
        code: '1',
      },
      {
        value: '女',
        code: '2',
      },
    ],
    gradeIndex: '0',
    isShowGrade: true,
    // 年级
    gradeArray: [
      {
        value: '大一',
        code: '1',
      },
      {
        value: '大二',
        code: '2',
      },
      {
        value: '大三',
        code: '3',
      },
      {
        value: '大四',
        code: '4',
      },

      {
        value: '研一',
        code: '5',
      },
      {
        value: '研二',
        code: '6',
      },
      {
        value: '研三',
        code: '7',
      },
    ],
    majorIndex: '0',
    isShowMajor: true,
    // 专业
    majorArray: [
      {
        value: '商务英语',
        code: '1',
      },
      {
        value: '土木工程',
        code: '2',
      },
      {
        value: '计算机网络工程',
        code: '3',
      },
      {
        value: '中国汉语学',
        code: '4',
      },
    ],

    name: '',
    gender: '',
    age: '',
    pro: '',
    grade: '',
    phone: '',
    password: '',
    confirmPwd: '',
    avatarUrl: '',
    openid: '',
    nickName: '',

    // 提交参数
    // params: {
    //   name: '',
    //   age: '',
    //   pro: '',
    //   grade: '',
    //   phone: '',
    //   password: '',
    //   confirmPwd: '',
    // },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  // 用户名
  bindUserNameInput(e) {
    this.setData({
      name: e.detail.value,
    });
  },

  // 密码
  bindPasswordInput(e) {
    this.setData({
      password: e.detail.value,
    });
  },

  // 确认密码
  bindSurePasswordInput(e) {
    this.setData({
      confirmPwd: e.detail.value,
    });
  },

  // 年龄
  bindAgeInput(e) {
    this.setData({
      age: e.detail.value,
    });
  },

  // 联系方式
  bindPhoneInput(e) {
    this.setData({
      phone: e.detail.value,
    });
  },

  // 选择年级
  bindGradeChange(e) {
    const { gradeArray } = this.data;
    this.setData({
      gradeIndex: e.detail.value,
      isShowGrade: false,
      grade: gradeArray[e.detail.value].code,
    });
  },

  // 选择性别
  bindSexChange(e) {
    const { sexArray } = this.data;
    this.setData({
      sexIndex: e.detail.value,
      isShowSex: false,
      gender: sexArray[e.detail.value].code,
    });
  },

  // 选择专业
  bindMajorChange(e) {
    const { majorArray } = this.data;
    this.setData({
      majorIndex: e.detail.value,
      isShowMajor: false,
      pro: majorArray[e.detail.value].code,
    });
  },

  // 点击注册
  register() {
    const that = this;
    const {
      name,
      password,
      confirmPwd,
      age,
      pro,
      grade,
      phone,
    } = that.data;

    if (!name || name === '') {
      wx.showToast({
        title: '名字不能为空',
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
    if (!confirmPwd || confirmPwd === '') {
      wx.showToast({
        title: '请确认密码',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }
    if (!grade || grade === '') {
      wx.showToast({
        title: '请选择年级',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }
    if (!pro || pro === '') {
      wx.showToast({
        title: '请选择专业',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }
    if (!phone || phone === '') {
      wx.showToast({
        title: '请填写联系方式',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }
    if (!age || age === '') {
      wx.showToast({
        title: '请填写年龄',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      return false;
    }

    if (password !== confirmPwd) {
      wx.showToast({
        title: '密码前后不正确',
        icon: 'none',
        mask: true,
        duration: 2000,
      });
      that.setData({
        confirmPwd: '',
      });
      return false;
    }

    // 获取用户信息
    wx.getUserInfo({
      success: function (res) {
        const {
          errMsg,
          userInfo,
        } = res;
        if (errMsg === 'getUserInfo:ok') {
          const {
            avatarUrl,
            gender,
            nickName,
          } = userInfo;
          const params = {
            avatarUrl,
            gender,
            nickName,
            name,
            age,
            pro,
            grade,
            phone,
            password,
            confirmPwd,
          };
          wx.showLoading({
            title: '正在注册中...',
            mask: true,
          });
          postRequest('/user/regist', params).then(
            (res) => {
              wx.hideLoading();
              const { data } = res;
              const userInfo = {

              };
              if (data.status === 200) {
                wx.setStorageSync('userInfo', data.data);
                wx.switchTab({
                  url: '../user/user',
                });
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
        } else {
          wx.showToast({
            title: '授权失败哦',
            icon: 'none',
            mask: true,
            duration: 2000,
          });
        }
      }
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