var orderlist = new Vue({
    el: '#orderlist',
    data: {
        pageInfo: '',
        pageNum: 1
    },
    mounted() {
        this.findAll();
    },
    methods: {
        handlePageChange() {
            this.pageNum = val;
            this.findAll();
        },
        findAll() {
            axios.get('/order/list', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                  orderlist.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
