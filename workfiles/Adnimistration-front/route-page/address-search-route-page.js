const AddressSearchRoutePage = {
  template: `
    <div id="addresslist">
    <el-page-header @back="handleGoBack" content="地址列表"></el-page-header>
        <el-table :data="addresses" style="width: 100%">
            <el-table-column prop="tag" label="标签">
            </el-table-column>
            <el-table-column prop="content" label="内容">
            </el-table-column>
            <el-table-column prop="receiverName" label="收货人姓名">
            </el-table-column>
            <el-table-column prop="receiverMobile" label="收货人手机">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="handleShowClick(scope.row)">详情
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    `,
  data() {
    return {
      customerId: '',
      addresses: []
    }
  },
    mounted() {
      this.customerId = this.$route.query.customerId;
      if (!this.customerId) {
        alert('customerId is null');
        return;
      }
      this.getAddressByCustomerId();
    },
    methods: {
      getAddressByCustomerId() {
        axios.get('/address/getListByCustomerId', {
          params: {
            customerId: this.customerId
          }
        })
          .then( (response) =>{
            console.log(response);
            this.addresses = response.data;
          })
          .catch( (error) =>{
            console.log(error);
          });
      },
      handleShowClick(row) {
        this.$router.push({path:'/address/show',query:{addressId:row.addressId}});
      },
      handleGoBack(){
        this.$router.back();
      }
    }
}
