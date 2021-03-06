var ordershow = new Vue({
    el: '#ordershow',
    data: {
        orderId: '',
        customerId: '',
        customerName: '',
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
        orderHistories: [],
        orderStatuses: [
            { value: 0, label: '待处理' },
            { value: 1, label: '处理中' },
            { value: 2, label: '待发货' },
            { value: 3, label: '已发货' },
            { value: 4, label: '待签收' },
            { value: 5, label: '已签收' },
            { value: 6, label: '待支付' },
            { value: 7, label: '已支付' },
            { value: 8, label: '取消' },
            { value: 9, label: '拒绝' },
            { value: 10, label: '完成' },
            { value: 11, label: '待评价' },
            { value: 12, label: '已评价' }
        ],
        createHistoryOrderStatus: '',
        createHistoryCustomerNotified: false,
        createHistoryComment: '',
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
        ]
    },
    mounted() {
         var url = new URL(location.href);
        this.orderId = url.searchParams.get("orderId");
        if (!this.orderId) {
            alert('orderId is null');
            return;
        }
        this.getOrderById();
        this.getHistoryByOrderId();
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
                    var order = response.data;
                  ordershow.customerId = order.customerId;
                  ordershow.customerName = order.customerName;
                  ordershow.status = order.status;
                  ordershow.totalPrice = order.totalPrice;
                  ordershow.rewordPoints = order.rewordPoints;
                  ordershow.createTimestamp = order.createTimestamp;
                  ordershow.updateTimestamp = order.updateTimestamp;
                  ordershow.shipMethod = order.shipMethod;
                  ordershow.shipAddress = order.shipAddress;
                  ordershow.shipPrice = order.shipPrice;
                  ordershow.payMethod = order.payMethod;
                  ordershow.invoiceAddress = order.invoiceAddress;
                  ordershow.invoicePrice = order.invoicePrice;
                  ordershow.comment = order.comment;
                  ordershow.orderProducts = order.orderProducts;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
      handleCreateOrderHistoryClick() {
        this.createOrderHistory();
      },
      createOrderHistory() {
        axios.post('/orderhistory/create', {
          orderId: this.orderId,
          orderStatus: this.createHistoryOrderStatus,
          comment: this.createHistoryComment,
          customerNotified: this.createHistoryCustomerNotified
        })
          .then(function (response) {
            console.log(response);
            alert('订单历史添加成功');
            ordershow.createHistoryOrderStatus = '';
            ordershow.createHistoryCustomerNotified = false;
            ordershow.createHistoryComment = '';
            ordershow.getOrderById();
            ordershow.getHistoryByOrderId();
          })
          .catch(function (error) {
            console.log(error);
            alert('订单历史添加失败');
          });
      },
      getHistoryByOrderId() {
        axios.get('/orderhistory/getListByOrderId', {
          params: {
            orderId: this.orderId
          }
        })
          .then(function (response) {
            console.log(response);
            ordershow.orderHistories = response.data;
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
})
