var ordershow = new Vue({
    el: '#ordershow',
    data: {
        orderId: '',
        status: '',
        totalPrice: '',
        rewordPoints: '',
        createTimestamp: '',
        updateTimestamp: '',
        shipMethod: '',
        shipAddress: '',
        shipPrice: '',
        payMethod: '',
        invoiceAddress: '',
        invoicePrice: '',
        comment: '',
        orderProducts: [],
        orderHistories: []
    },
    mounted() {
        var url = new URL(location.href);
        this.orderId = url.searchParams.get("orderId");
        if (!this.orderId) {
            alert('orderId is null');
            return;
        }
        this.getOrderById();
    },
    methods: {
        getOrderById() {
            axios.get('/order/show', {
                params: {
                    orderId: this.orderId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var orderDTO = response.data;
                  ordershow.status = orderDTO.status;
                  ordershow.totalPrice = orderDTO.totalPrice;
                  ordershow.rewordPoints = orderDTO.rewordPoints;
                  ordershow.createTimestamp = orderDTO.createTimestamp;
                  ordershow.updateTimestamp = orderDTO.updateTimestamp;
                  ordershow.shipMethod = orderDTO.shipMethod;
                  ordershow.shipAddress = orderDTO.shipAddress;
                  ordershow.shipPrice = orderDTO.shipPrice;
                  ordershow.payMethod = orderDTO.payMethod;
                  ordershow.invoiceAddress = orderDTO.invoiceAddress;
                  ordershow.invoicePrice = orderDTO.invoicePrice;
                  ordershow.comment = orderDTO.comment;
                  ordershow.orderProducts = orderDTO.orderProducts;
                  ordershow.orderHistories = orderDTO.orderHistories;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
