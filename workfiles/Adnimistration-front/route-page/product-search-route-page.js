const ProductSearchRoutePage = {
    template: `<div id="list">

        <el-input v-model="productCode" placeholder="请输入商品代号"></el-input>
        <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
        <el-input v-model="price" placeholder="请输入价格"></el-input>
        <el-input v-model="stockQuantity" placeholder="请输入库存"></el-input>
        <br>

        <el-select v-model="selectedStatus" placeholder="请选择状态">
            <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <br>
        <el-button type="primary" @click="handleSearchClick">搜索</el-button>
        <el-button type="primary" @click="handleClearClick">清空条件</el-button>

        <el-table :data="pageInfo.list" style="width: 100%">
            <el-table-column label="主图">
                <template slot-scope="scope">
                    <el-image style="width: 100px; height: 100px" :src="scope.row.mainPicUrl" fit="fill"></el-image>
                </template>
            </el-table-column>
            <el-table-column prop="productCode" label="商品代码">
            </el-table-column>
            <el-table-column prop="productName" label="商品名称">
            </el-table-column>
            <el-table-column label="价格">
                <template slot-scope="scope">
                    <s>{{scope.row.price}}</s> <br>
                    {{(scope.row.price * scope.row.discount).toFixed(2)}}
                </template>
            </el-table-column>
            <el-table-column prop="stockQuantity" label="库存">
            </el-table-column>
            <el-table-column label="状态">
                <template slot-scope="scope">
                    {{statuses[scope.row.status].label}}
                </template>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">修改</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
        </el-pagination>
    </div>
    `,
    data() {
        return {
            pageInfo: '',
            pageNum: 1,
            productCode: '',
            productName: '',
            price: '',
            stockQuantity: '',
            selectedStatus: '',
            statuses: [
                { value: 0, label: '下架' },
                { value: 1, label: '上架' },
                { value: 2, label: '待审核' }
            ]
        }
    },
    mounted(){
      this.findAll();
    },
    methods:{
      findAll(){
        axios.get('/product/list',{
          params:{
            productCode: this.productCode,
            productName: this.productName,
            price: this.price,
            stockQuantity: this.stockQuantity,
            status: this.selectedStatus,
            pageNum:this.pageNum
          }
        }).then((resp) =>{
          this.pageInfo = resp.data;
        }).catch((err) =>{
          console.log(err);
        })
      },
      handlePageChange(val){
        this.pageNum = val;
        this.findAll();
      },
      handleSearchClick() {
        this.pageNum = 1;
        this.findAll();
      },
      handleClearClick() {
        this.productCode = '';
        this.productName = '';
        this.price = '';
        this.stockQuantity = '';
        this.selectedStatus = '';
      },
      handleClick(row){
        console.log(row);
        //this.$router.push({name:'update',params:{productId : row.productId}})
        this.$router.push({path: '/product/update',query:{productId : row.productId}})
      }
    }
}
