var addresslist = new Vue({
    el: '#addresslist',
    data: {
        addresses: []
    },
    mounted() {
        this.getMyAddresses();
    },
    methods: {
        getMyAddresses() {
            axios.get('/address/getAddressByCustomerId')
                .then(function (response) {
                    console.log(response);
                  addresslist.addresses = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
