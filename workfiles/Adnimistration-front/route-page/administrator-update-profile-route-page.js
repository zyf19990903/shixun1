const AdministratorUpdateProfileRoutePage = {
    template: `
    <div id="updateprofile">
        <el-page-header @back="handleGoBack"></el-page-header>
        用户名:{{username}} <br>
        姓名:<el-input v-model="realName" placeholder="请输入姓名"></el-input>
        邮箱:<el-input v-model="email" placeholder="请输入邮箱"></el-input>
        头像:<el-input v-model="avatarUrl" placeholder="请输入头像"></el-input>
        创建时间:{{(new Date(createTimestamp)).toLocaleString()}}
        <br>
        <el-button type="primary" @click="handleUpdateClick">更新</el-button>
    </div>
    `,
    data() {
        return {
          administratorId: '',
          username: '',
          realName: '',
          email: '',
          avatarUrl: '',
          createTimestamp: ''
        }
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
          .then( (resp) =>{
            console.log(resp);
            var da = resp.data;
            this.administratorId = da.administratorId;
            this.username = da.username;
            this.realName = da.realName;
            this.email = da.email;
            this.avatarUrl = da.avatarUrl;
            this.createTimestamp = da.createTimestamp;
          })
          .catch( (error) =>{
            console.log(error);
          });
      },
      updateMyProfile() {
        axios.post('/administrator/updateProfile', {
          realName: this.realName,
          email: this.email,
          avatarUrl: this.avatarUrl,
        })
          .then( (response) =>{
            console.log(response);
            alert('更新成功');
          })
          .catch( (error) =>{
            console.log(error);
            alert('更新失败');
          });
      },
      handleGoBack(){
        this.$router.back();
      }
    }
}
