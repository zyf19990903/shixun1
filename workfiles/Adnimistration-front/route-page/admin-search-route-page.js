const AdminSearchRoutePage = {
  template: `
  <div id="adminlist">
        <el-button type="primary" @click="handleCreateClick">添加</el-button>
        <el-button type="danger" @click="handleBatchDeleteClick">批量删除</el-button>
        <el-table :data="pageInfo.list" style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="administratorId" label="Id">
            </el-table-column>
            <el-table-column prop="username" label="用户名">
            </el-table-column>
            <el-table-column prop="realName" label="姓名">
            </el-table-column>
            <el-table-column label="状态">
                <template slot-scope="scope">
                    {{statuses[scope.row.status]}}
                </template>
            </el-table-column>
            <el-table-column label="创建时间">
                <template slot-scope="scope">
                    {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        </el-table>

        <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
        </el-pagination>
    </div>
    `,
  data() {
    return {
      pageInfo: '',
      pageNum: 1,
      selectedAdministrators: [],
      statuses: ['禁用', '启用']
    }
  },
  computed: {
    selectedAdministratorIds() {
      return this.selectedAdministrators.map(a => a.administratorId);
    }
  },
  mounted() {
    this.getAdministrators();
  },
  methods: {
    handlePageChange(val) {
      this.pageNum = val;
      this.getAdministrators();
    },
    handleDelete(index, row) {
      if (confirm("确认删除？")) {
        this.deleteAdministrator(row.administratorId);
      }
    },
    handleBatchDeleteClick() {
      if (confirm("确认删除？")) {
        this.batchDeleteAdministrators();
      }
    },
    handleSelectionChange(val) {
      this.selectedAdministrators = val;
    },
    deleteAdministrator(administratorId) {
      axios.post('/administrator/delete', administratorId, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then((response) =>{
          alert('删除成功');
          location.reload();
        })
        .catch((error) =>{
          console.log(error);
        });
    },
    batchDeleteAdministrators() {
      axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
        .then((response) =>{
          alert('批删成功');
          location.reload();
        })
        .catch((error) =>{
          console.log(error);
        });
    },
    getAdministrators() {
      axios.get('/administrator/list', {
        params: {
          pageNum: this.pageNum
        }
      })
        .then((response) =>{
          console.log(response);
          this.pageInfo = response.data;
        })
        .catch( (error) =>{
          console.log(error);
        });
    },
    handleEdit(index, row) {
      this.$router.push('/administrator/update/' + row.administratorId);
    },
    handleCreateClick() {
      console.log('create click');
      this.$router.push('/administrator/create');
    }
  }
}
