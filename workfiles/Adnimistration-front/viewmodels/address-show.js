var addressShow = new Vue({
    el: '#addressShow',
    data: {
        addressId: '',
        tag: '',
        content: '',
        receiverName: '',
        receiverMobile: ''
    },
    mounted() {
        var url = new URL(location.href);
        this.addressId = url.searchParams.get("addressId");
        if (!this.addressId) {
            alert('addressId is null');
            return;
        }

        this.getAddressById();
    },
    methods: {
        getAddressById() {
            axios.get('/address/show', {
                params: {
                    addressId: this.addressId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var address = response.data;
                  addressShow.tag = address.tag;
                  addressShow.content = address.content;
                  addressShow.receiverName = address.receiverName;
                  addressShow.receiverMobile = address.receiverMobile;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
