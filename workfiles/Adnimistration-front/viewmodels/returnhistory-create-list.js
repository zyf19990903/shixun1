var index = new Vue({
    el: '#index',
    data: {
        returnId: '',
        returnHistories: [],
        selectedReturnStatus: '',
        returnStatuses: [
            { value: 1, label: '待取货' },
            { value: 2, label: '正在处理' },
            { value: 3, label: '完成' },
            { value: 4, label: '拒绝' }
        ],
        customerNotified: false,
        comment: ''
    },
    mounted() {
        var url = new URL(location.href);
        this.returnId = url.searchParams.get("returnId");
        if (!this.returnId) {
            alert('returnId is null');
            return;
        }
        this.getHistoryByReturnId();
    },
    methods: {
        handleCreateClick() {
            this.createReturnHistory();
        },
        createReturnHistory() {
            axios.post('/returnhistory/create', {
                returnId: this.returnId,
                returnStatus: this.selectedReturnStatus,
                customerNotified: this.customerNotified,
                comment: this.comment,
            })
                .then(function (response) {
                    console.log(response);
                    alert('创建成功');
                  index.selectedReturnStatus = '';
                  index.customerNotified = false;
                  index.comment = '';
                  index.getHistoryByReturnId();
                })
                .catch(function (error) {
                    console.log(error);
                  alert('创建失败');
                });
        },
        getHistoryByReturnId() {
            axios.get('/returnhistory/list', {
                params: {
                    returnId: this.returnId
                }
            })
                .then(function (response) {
                    console.log(response);
                  index.returnHistories = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
