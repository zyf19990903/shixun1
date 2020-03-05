var orderlist = new Vue({
    el: '#orderlist',
    data: {
      pageInfo:'',
      pageNum:1
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
