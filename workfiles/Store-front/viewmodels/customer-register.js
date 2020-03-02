var register = new Vue({
    el: '#register',
    data: {
      username: '',
      realName: '',
      mobile: '',
      email: '',
      password: '',
      repassword: '',
      newsSubscribed: false
    },
  methods:{
    handleRegisterClick(){
      if(this.password != this.repassword){
          alert('两次密码不一致')
        return;
      }
      this.registerCustomer();
    },
    registerCustomer(){
      axios.post('/customer/register', {
        username: this.username,
        realName: this.realName,
        mobile: this.mobile,
        email: this.email,
        password: this.password,
        newsSubscribed: this.newsSubscribed
      })
        .then(function (response) {
          console.log(response);
          alert('注册成功');
        })
        .catch(function (error) {
          console.log(error);
          alert('注册失败');
        });
    }
  }
})
