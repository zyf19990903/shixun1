var list = new Vue({
    el: '#list',
    data: {
      pageInfo : '',
      pageNum:1
    },
  mounted(){
      this.findAll();
  },
  methods:{
      findAll(){
        axios.get('/product/list',{
                    params:{
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
    }
  }
})
