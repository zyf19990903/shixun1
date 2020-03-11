var orderlist = new Vue({
    el: '#orderlist',
    data: {
      pageInfo:'',
      pageNum:1,
      statuses: [
        { value: 0, label: '待处理' },
        { value: 1, label: '处理中' },
        { value: 2, label: '待发货' },
        { value: 3, label: '已发货' },
        { value: 4, label: '待签收' },
        { value: 5, label: '已签收' },
        { value: 6, label: '待支付' },
        { value: 7, label: '已支付' },
        { value: 8, label: '取消' },
        { value: 9, label: '拒绝' },
        { value: 10, label: '完成' },
        { value: 11, label: '待评价' },
        { value: 12, label: '已评价' }
      ]
    },
  mounted(){
    this.findAll();
  },
  methods:{
    handlePageChange(val){
      this.pageNum = val;
      this.findAll();
    },
    findAll(){
      axios.get('/order/list',{
        params:{
          pageNum:this.pageNum
        }
      }).then(function (resp) {
        orderlist.pageInfo= resp.data;
      }).catch(function (err) {
        console.log(err)
      })
    }
  }
})
