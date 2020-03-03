var adminupdate = new Vue({
    el: '#adminupdate',
    data: {
        administratorId: '',
        username: '',
        realName: '',
        password: '',
        email: '',
        avatarUrl: '',
        selectedStatus: '',
        statuses: [
            { value: 0, label: '禁用' },
            { value: 1, label: '启用' }
        ]
    },
    mounted(){
        var url = new URL(location.href);
        this.administratorId = url.searchParams.get("administratorId");
        if (!this.administratorId) {
            alert('administratorId is null');
            return;
        }
        this.getAdministratorById();
    },
    methods: {
        handleUpdateClick(){
            this.updateAdministrator();
        },
        getAdministratorById(){
            axios.get('/administrator/show', {
                params: {
                  administratorId: this.administratorId
                }
              })
              .then(function (response) {
                console.log(response);
                var administrator = response.data;
                adminupdate.username = administrator.username;
                adminupdate.realName = administrator.realName;
                adminupdate.email = administrator.email;
                adminupdate.avatarUrl = administrator.avatarUrl;
                adminupdate.selectedStatus = administrator.status;
              })
              .catch(function (error) {
                console.log(error);
              });
        },
        updateAdministrator() {
            axios.post('/administrator/update', {
                administratorId: this.administratorId,
                password: this.password,
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl,
                status: this.selectedStatus
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
