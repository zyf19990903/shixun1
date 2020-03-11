var returnShow = new Vue({
    el: '#returnShow',
    data: {
        returnId: '',
        orderId: '',
        orderTimestamp: '',
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
        returnHistories: [],
        statuses: [
          { value: 0, label: '待处理' },
          { value: 1, label: '待取货' },
          { value: 2, label: '正在处理' },
          { value: 3, label: '完成' },
          { value: 4, label: '拒绝' }
        ],
        actions: [
          { value: 0, label: '退货' },
          { value: 1, label: '换货' },
          { value: 2, label: '修理' }
        ],
        reasons: [
          { value: 0, label: '发货过期' },
          { value: 1, label: '订单错误' },
          { value: 2, label: '收到错误商品' },
          { value: 3, label: '质量问题' }
        ]
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
                  returnShow.customerName = returns.customerName;
                  returnShow.mobile = returns.mobile;
                  returnShow.email = returns.email;
                  returnShow.status = returns.status;
                  returnShow.action = returns.action;
                  returnShow.productCode = returns.productCode;
                  returnShow.productName = returns.productName;
                  returnShow.quantity = returns.quantity;
                  returnShow.reason = returns.reason;
                  returnShow.opened = returns.opened;
                  returnShow.comment = returns.comment;
                  returnShow.createTimestamp = returns.createTimestamp;
                  returnShow.updateTimestamp = returns.updateTimestamp;
                  returnShow.returnHistories = returns.returnHistories;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
