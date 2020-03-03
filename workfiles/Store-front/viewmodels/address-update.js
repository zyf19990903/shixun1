var addressupdate = new Vue({
    el: '#addressupdate',
    data: {
        addressId: '',
        tag: '',
        receiverName: '',
        receiverMobile: '',
        content: ''
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
        handleUpdateClick() {
           this.updateAddress();
        },
        getAddressById() {
            axios.get('/address/getAddressByCustomerId', {
                params: {
                    addressId: this.addressId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var address = response.data;
                  addressupdate.tag = address.tag;
                  addressupdate.receiverName = address.receiverName;
                  addressupdate.receiverMobile = address.receiverMobile;
                  addressupdate.content = address.content;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        updateAddress() {
            axios.post('/address/update', {
                addressId: this.addressId,
                tag: this.tag,
                receiverName: this.receiverName,
                receiverMobile: this.receiverMobile,
                content: this.content
            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
