var returnList = new Vue({
    el: '#returnList',
    data: {
        pageInfo: '',
        pageNum: 1
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
        }
    }
})
