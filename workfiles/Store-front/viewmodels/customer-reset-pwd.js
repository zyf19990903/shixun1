var resetPwd = new Vue({
    el: '#resetPwd',
    data: {
        email: '',
        resetCode: '',
        newPwd: '',
        reNewPwd: ''
    },
    methods: {
        handleResetPwdClick() {
           if(this.newPwd !== this.reNewPwd){
                alert('密码不一致');
                return;
            }

            this.resetCustomerPwd();
        },
        resetCustomerPwd() {
            axios.post('/customer/resetPwd', {
                email: this.email,
                resetCode: this.resetCode,
                newPwd: this.newPwd
            })
                .then(function (response) {
                    console.log(response);
                    alert('重置成功');
                })
                .catch(function (error) {
                    console.log(error);
                   alert('重置失败');
                });
        }
    }
})
