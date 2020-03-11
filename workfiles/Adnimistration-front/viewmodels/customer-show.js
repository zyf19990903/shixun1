var customershow = new Vue({
    el: '#customershow',
    data: {
        customerId: '',
        username: '',
        realName: '',
        avatarUrl: '',
        mobile: '',
        email: '',
        status: '',
        createTimestamp: '',
        newsSubscribed: '',
        rewordPoints: '',
        defaultAddressId: '',
        defaultAddress: '',
        statuses: [
          { value: 0, label: '禁用' },
          { value: 1, label: '启用' },
          { value: 2, label: '不安全' }
        ]
    },
    mounted() {
        var url = new URL(location.href);
        this.customerId = url.searchParams.get("customerId");
        if (!this.customerId) {
            alert('customerId is null');
            return;
        }

        this.getCustomerById();
    },
    methods: {
        getCustomerById() {
            axios.get('/customer/show', {
                params: {
                    customerId: this.customerId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var customer = response.data;
                  customershow.username = customer.username;
                  customershow.realName = customer.realName;
                  customershow.avatarUrl = customer.avatarUrl;
                  customershow.mobile = customer.mobile;
                  customershow.email = customer.email;
                  customershow.status = customer.status;
                  customershow.createTimestamp = customer.createTimestamp;
                  customershow.newsSubscribed = customer.newsSubscribed;
                  customershow.rewordPoints = customer.rewordPoints;
                  customershow.defaultAddressId = customer.defaultAddressId;
                  customershow.defaultAddress = customer.defaultAddress;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
