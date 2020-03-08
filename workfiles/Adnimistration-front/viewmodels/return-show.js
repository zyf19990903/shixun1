var returnShow = new Vue({
    el: '#returnShow',
    data: {
        returnId: '',
        orderId: '',
        orderTimestamp: '',
        customerId: '',
        customerName: '',
        mobile: '',
        email: '',
        status: '',
        action: '',
        productCode: '',
        productName: '',
        quantity: '',
        reason: '',
        opened: '',
        comment: '',
        createTimestamp: '',
        updateTimestamp: '',
        actions: [
            { value: 0, label: '退货' },
            { value: 1, label: '换货' },
            { value: 2, label: '修理' }
        ],
        selectedAction: ''
    },
    mounted() {
        var url = new URL(location.href);
        this.returnId = url.searchParams.get("returnId");
        if (!this.returnId) {
            alert('returnId is null');
            return;
        }

        this.getReturnById();
    },
    methods: {
        handleUpdateAction() {
            this.updateReturnAction();
        },
        updateReturnAction() {
            axios.post('/return/update', {
                returnId: this.returnId,
                action: this.selectedAction
            })
                .then(function (response) {
                    console.log(response);
                    alert('处理方式更新成功');
                  returnShow.getReturnById();
                })
                .catch(function (error) {
                    console.log(error);
                  alert('处理方式更新失败');
                });
        },
        getReturnById() {
            axios.get('/return/show', {
                params: {
                    returnId: this.returnId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var returns = response.data;
                  returnShow.orderId = returns.orderId;
                  returnShow.orderTimestamp = returns.orderTimestamp;
                  returnShow.customerId = returns.customerId;
                  returnShow.customerName = returns.customerName;
                  returnShow.mobile = returns.mobile;
                  returnShow.email = returns.email;
                  returnShow.status = returns.status;
                  returnShow.action = returns.action;
                  returnShow.selectedAction = returns.action;
                  returnShow.productCode = returns.productCode;
                  returnShow.productName = returns.productName;
                  returnShow.quantity = returns.quantity;
                  returnShow.reason = returns.reason;
                  returnShow.opened = returns.opened;
                  returnShow.comment = returns.comment;
                  returnShow.createTimestamp = returns.createTimestamp;
                  returnShow.updateTimestamp = returns.updateTimestamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
