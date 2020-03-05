var ordercheckout = new Vue({
    el: '#ordercheckout',
    data: {
        myAddresses: [],
        shipMethods: [
            { value: 0, label: 'EMS' },
            { value: 1, label: '顺丰' },
            { value: 2, label: '圆通' },
            { value: 3, label: '中通' },
            { value: 4, label: '申通' }
        ],
        payMethods: [
            { value: 0, label: '货到付款' },
            { value: 1, label: '借记卡' },
            { value: 2, label: '信用卡' },
            { value: 3, label: '微信支付' },
            { value: 4, label: '支付宝' }
        ],
        selectedShipAddressId: '',
        selectShipMethod: '',
        selectedInvoiceAddredssId: '',
        selectedPayMethod: '',
        comment: '',
        myShoppingCart: [],
        shipPrice: 5.0
    },
    computed: {
        totalPrice() {
            //遍历购物车商品，计算每个商品的价格
            var subTotalPrices = this.myShoppingCart.map(p => {
                return p.unitPrice * p.discount * p.quantity;
            });

            //遍历每个商品的价格，计算总价，历刚刚计算的每个商品的总价，然后两两相加，直至到0
            var totalPrice = subTotalPrices.reduce((a, b) => a + b, 0);
            //保留两位小数
            var totalPriceStr = totalPrice.toFixed(2);
            totalPrice = parseFloat(totalPriceStr);
            return totalPrice;
        },
        payPrice() {
            return this.totalPrice + this.shipPrice;
        },
        orderProducts() {
            //遍历购物车，取出每个商品id和数量 ，所有的订单商品
            var orderProducts = this.myShoppingCart.map(p => {
                var orderProduct = new Object();
                orderProduct.productId = p.productId;
                orderProduct.quantity = p.quantity;
                return orderProduct;
            });
            return orderProducts;
        }
    },
    mounted() {
        this.getMyAddress();
        //从模拟购物车中取出商品
        var myShoppingCartJson = localStorage['myShoppingCartJson'];
        //反序列化
        this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
    },
    methods: {
        handleConfirmOrder() {
            this.checkoutOrder();
        },
        getMyAddress() {
            axios.get('/address/getAddressByCustomerId')
                .then(function (response) {
                  ordercheckout.myAddresses = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        checkoutOrder() {
            axios.post('/order/checkout', {
                shipMethod: this.selectShipMethod,
                shipAddressId: this.selectedShipAddressId,
                payMethod: this.selectedPayMethod,
                invoiceAddressId: this.selectedInvoiceAddredssId,
                comment: this.comment,
                orderProducts: this.orderProducts
            })
                .then(function (response) {
                    console.log(response);
                    alert('下单成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('下单失败');
                });
        }
    }
})
