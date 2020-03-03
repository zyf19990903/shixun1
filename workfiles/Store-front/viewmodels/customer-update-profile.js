var customerupdate = new Vue({
    el: '#customerupdate',
    data: {
        username: '',
        realName: '',
        mobile: '',
        email: '',
        mobileVerified: '',
        emailVerified: ''
    },
    mounted() {
        this.getMyProfile();
    },
    methods: {
        handleUpdateClick() {
            this.updateMyProfile();
        },
        getMyProfile() {
            axios.get('/customer/getProfile')
                .then(function (response) {
                    console.log(response);
                    var me = response.data;
                    app.username = me.username;
                    app.realName = me.realName;
                    app.mobile = me.mobile;
                    app.mobileVerified = me.mobileVerified.toString();
                    app.email = me.email;
                    app.emailVerified = me.emailVerified.toString();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        updateMyProfile() {
            axios.post('/customer/updateProfile', {
                realName: this.realName,
                mobile: this.mobile,
                email: this.email
            })
                .then(function (response) {
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                  alert('更新失败');
                });
        }
    }
})
