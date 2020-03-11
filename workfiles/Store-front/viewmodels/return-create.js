var returnCreate = new Vue({
    el: '#returnCreate',
    data: {
        orderId: '',
        orderTime: '',
        customerName: '',
        mobile: '',
        email: '',
        productId: '',
        productCode: '',
        productName: '',
        quantity: '',
        reason: '',
        reasons: [
          { value: 0, label: '发货过期' },
          { value: 1, label: '订单错误' },
          { value: 2, label: '收到错误商品' },
          { value: 3, label: '质量问题' }
        ],
        comment: '',
        opened: ''
    },
    computed: {
        orderTimestamp() {
            return this.orderTime.getTime();
        }
    },
    mounted() {
        var url = new URL(location.href);
        this.orderId = url.searchParams.get("orderId");
        if (!this.orderId) {
            alert('orderId is null');
            return;
        }

        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            alert('productId is null');
            return;
        }

        this.getOrderById();

    },
    methods: {
      handleCreateClick() {
            this.createReturn();
        },
        getOrderById() {
            axios.get('/order/show', {
                params: {
                    orderId: this.orderId
                }
            })
                .then(function (response) {
                    console.log(response);
                    //订单列表
                    var order = response.data;
                  returnCreate.orderTime = new Date(order.createTimestamp);
                   //订单里面的商品
                    var orderProducts = order.orderProducts;
                    var returnProduct = orderProducts.find(
                                                    p => p.productId == returnCreate.productId
                                                  );
                    console.log(returnProduct)
                  returnCreate.productCode = returnProduct.productCode;
                  returnCreate.productName = returnProduct.productName;
                  returnCreate.quantity = returnProduct.quantity;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        createReturn() {
            axios.post('/return/create', {
                orderId: this.orderId,
                orderTimestamp: this.orderTimestamp,
                customerName: this.customerName,
                mobile: this.mobile,
                email: this.email,
                productCode: this.productCode,
                productName: this.productName,
                quantity: this.quantity,
                reason: this.reason,
                opened: this.opened,
                comment: this.comment
            })
                .then(function (response) {
                    console.log(response);
                    alert('申请成功，请等待处理');
                })
                .catch(function (error) {
                    console.log(error);
                  alert('申请失败，请稍后。。。');
                });
        }
    }
})
