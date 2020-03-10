var forgetPwd = new Vue({
    el: '#forgetPwd',
    data: {
        email: ''
    },
    methods: {
        handleFindBackPwdClick() {
            this.getPwdResetCode();
        },
        getPwdResetCode() {
            axios.get('/customer/getPwdResetCode', {
                params: {
                    email: this.email
                }
            })
                .then(function (response) {
                    console.log(response);
                    alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('发送失败')
                });
        }
    }
})
