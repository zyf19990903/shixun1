var adminlist = new Vue({
    el: '#adminlist',
    data: {
        pageInfo: '',
        pageNum: 1,
        selectedAdministrators: [],
        statuses: ['禁用', '启用']
    },
    computed: {
        selectedAdministratorIds() {
            return this.selectedAdministrators.map(a => a.administratorId);
        }
    },
    mounted() {
        this.getAdministrators();
    },
    methods: {
        handlePageChange(val) {
            this.pageNum = val;
            this.getAdministrators();
        },
        handleDelete(index, row) {
            if (confirm("确认删除？")) {
                this.deleteAdministrator(row.administratorId);
            }
        },
        handleBatchDeleteClick() {
            if (confirm("确认删除？")) {
                this.batchDeleteAdministrators();
            }
        },
        handleSelectionChange(val) {
            this.selectedAdministrators = val;
        },
        deleteAdministrator(administratorId) {
            axios.post('/administrator/delete', administratorId, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(function (response) {
                    alert('删除成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        batchDeleteAdministrators() {
            axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
                .then(function (response) {
                    alert('批删成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getAdministrators() {
            axios.get('/administrator/list', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                  adminlist.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
