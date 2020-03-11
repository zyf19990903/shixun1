var updateprofile = new Vue({
    el: '#updateprofile',
    data: {
        administratorId: '',
        username: '',
        realName: '',
        email: '',
        avatarUrl: '',
        createTimestamp: ''
    },
    mounted() {
        console.log('view mounted');
        this.getMyProfile();
    },
    methods: {
        handleUpdateClick() {
            console.log('update click');
            this.updateMyProfile();
        },
        getMyProfile() {
            axios.get('/administrator/getProfile')
                .then(function (resp) {
                    console.log(resp);
                  var da = resp.data;
                  updateprofile.administratorId = da.administratorId;
                  updateprofile.username = da.username;
                  updateprofile.realName = da.realName;
                  updateprofile.email = da.email;
                  updateprofile.avatarUrl = da.avatarUrl;
                  updateprofile.createTimestamp = da.createTimestamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        updateMyProfile() {
            axios.post('/administrator/updateProfile', {
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl,
            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('更新失败');
                });
        }
    }
})
