var changepwd = new Vue({
    el: '#changepwd',
    data: {
        originPwd: '',
        newPwd: '',
        reNewPwd: ''
    },
    methods: {
        handleChangeClick() {
            if (this.newPwd != this.reNewPwd) {
                alert('密码不一致');
                return;
            }
            this.changeMyPwd();
        },
        changeMyPwd() {
            axios.post('/customer/changePwd', {
                originPwd: this.originPwd,
                newPwd: this.newPwd
            })
                .then(function (response) {
                    console.log(response);
                    alert('修改成功');
                })
                .catch(function (error) {
                    console.log(error);
                  alert('修改失败');
                });
        }
    }
})
