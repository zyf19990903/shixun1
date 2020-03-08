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
        },
        findAll() {
            axios.get('/return/list', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                  returnList.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
