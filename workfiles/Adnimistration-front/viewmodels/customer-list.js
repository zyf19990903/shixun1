var customerlist = new Vue({
    el: '#customerlist',
    data: {
        pageInfo: '',
        pageNum: 1,
        statuses: [
            { value: 0, label: '禁用' },
            { value: 1, label: '启用' },
            { value: 2, label: '不安全' }
        ]
    },
    mounted() {
        this.findAll();
    },
    methods: {
        handlePageChange(val) {
            this.pageNum = val;
            this.findAll();
        },
        handleUpdateStatus(index, row) {
            this.updateCustomerStatus(row.customerId, row.status);
        },
      findAll() {
            axios.get('/customer/list', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                  customerlist.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        updateCustomerStatus(customerId, status) {
            axios.post('/customer/setStatus', {
                customerId: customerId,
                status: status
            })
                .then(function (response) {
                    console.log(response);
                    alert('状态更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                  alert('状态更新失败');
                });
        }
    }
})
