var show = new Vue({
    el: '#show',
    data: {
        productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        description: '',
        stockQuantity: '',
        mainPicUrl: '',
        otherPicUrls: ''
    },
    mounted(){
        var url = new URL(location.href);
        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            return;
        }
        this.getProductById();
    },
    methods: {
        getProductById() {
            axios.get('/product/show', {
                params: {
                    productId: this.productId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var product = response.data;
                  show.productId = product.productId;
                  show.productCode = product.productCode;
                  show.productName = product.productName;
                  show.price = product.price;
                  show.discount = product.discount;
                  show.mainPicUrl = product.mainPicUrl;
                  show.otherPicUrls = product.otherPicUrls;
                  show.description = product.description;
                  show.stockQuantity = product.stockQuantity;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})
