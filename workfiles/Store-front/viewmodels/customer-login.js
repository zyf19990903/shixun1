var login = new Vue({
    el: '#login',
    data: {
      username : '',
      password : ''
    },
  methods:{
    handleLoginClick(){
      this.loginCustomer();
    },
    loginCustomer: function () {
      axios.get('/customer/login', {
        params: {
          username: this.username,
          password: this.password
        }
      }).then(function (resp) {
        var dto = resp.data;
        console.log(dto)
        alert('登录成功')
      }).catch(function (err) {
        console.log(err)
        alert('登录失败')
      })
    }
  }
})
