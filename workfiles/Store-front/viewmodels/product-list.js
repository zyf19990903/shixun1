var list = new Vue({
    el: '#list',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted(){
        this.findAll();
    },
    methods: {
        findAll() {
            axios.get('/product/list', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                  list.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        handlePageChange(val){
            this.pageNum = val;
            this.findAll();
        }

    }
})
