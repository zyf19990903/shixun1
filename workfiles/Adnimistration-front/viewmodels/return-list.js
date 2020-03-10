var returnList = new Vue({
    el: '#returnList',
    data: {
        pageInfo: '',
        pageNum: 1,
        returnId: '',
        orderId: '',
        customerName: '',
        productCode: '',
        productName: '',
        selectedStatus: '',
        statuses: [
          { value: 0, label: '待处理' },
          { value: 1, label: '待取货' },
          { value: 2, label: '正在处理' },
          { value: 3, label: '完成' },
          { value: 4, label: '拒绝' }
        ],
        startTime: '',
        endTime: '',
    },
    mounted() {
        this.findAll();
    },
    methods: {
        handlePageChange(val) {
            this.pageNum = val;
            this.findAll();
        },
        findAll() {
            axios.get('/return/list', {
                params: {
                  returnId: this.returnId,
                  orderId: this.orderId,
                  customerName: this.customerName,
                  productCode: this.productCode,
                  productName: this.productName,
                  status: this.selectedStatus,
                  startTimestamp: this.startTime ? this.startTime.getTime() : '',
                  endTimestamp: this.endTime ? this.endTime.getTime() : '',
                  pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                  returnList.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handleSearchClick() {
          this.pageNum = 1;
          this.findAll();
        },
        handleClearClick() {
          this.returnId = '';
          this.orderId = '';
          this.customerName = '';
          this.productCode = '';
          this.productName = '';
          this.selectedStatus = '';
          this.startTime = '';
          this.endTime = '';
        }
    }
})
