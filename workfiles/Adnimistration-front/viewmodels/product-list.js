var list = new Vue({
    el: '#list',
    data: {
      pageInfo : '',
      pageNum:1,
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
        }).then(function (resp) {
            list.pageInfo = resp.data;
        }).catch(function (err) {
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
    }
  }
})
