const AdministratorCreateRoutePage = {
  template: `
    <div id="admincreat">
        <el-page-header @back="handleGoBack" content="用户添加"></el-page-header>
        <el-input v-model="username" placeholder="请输入用户名"></el-input>
        <el-input v-model="password" placeholder="请输入密码"></el-input>
        <el-input v-model="realName" placeholder="请输入姓名"></el-input>
        <el-input v-model="email" placeholder="请输入邮箱"></el-input>
        <el-input v-model="avatarUrl" placeholder="请输入头像"></el-input>
        <el-select v-model="selectedStatus" placeholder="请选择状态">
            <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <br>

        <el-button type="primary" @click="handleCreateClick">创建</el-button>
    </div>
    `,
  data() {
    return {
      username: '',
      password: '',
      realName: '',
      email: '',
      avatarUrl: '',
      selectedStatus: 1,
      statuses: [
        { value: 0, label: '禁用' },
        { value: 1, label: '启用' }
      ],
    }
    },
    methods: {
      handleCreateClick(){
        this.createAdministrator();
      },
      createAdministrator() {
        axios.post('/administrator/create', {
          username: this.username,
          password: this.password,
          realName: this.realName,
          email: this.email,
          avatarUrl: this.avatarUrl,
          status: this.selectedStatus
        })
          .then( (response) =>{
            console.log(response);
            alert('创建成功');
            this.$router.push('/admin/list');
          })
          .catch( (error) =>{
            console.log(error);
          });
      },
      handleGoBack(){
        this.$router.back();
      }
    }
}
