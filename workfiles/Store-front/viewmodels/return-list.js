var returnList = new Vue({
    el: '#returnList',
    data: {
        pageInfo: '',
        pageNum: 1,
        statuses: [
          { value: 0, label: '待处理' },
          { value: 1, label: '待取货' },
          { value: 2, label: '正在处理' },
          { value: 3, label: '完成' },
          { value: 4, label: '拒绝' }
        ]
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
