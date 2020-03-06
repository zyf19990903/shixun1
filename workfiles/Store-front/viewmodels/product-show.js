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
        otherPicUrls: [],
        quantity: 1,
        myShoppingCart: []
    },
    computed:{
      otherPicUrlsJson() {
        return this.otherPicUrls.toString();
      }
    },
    mounted(){
      var myShoppingCartJson = localStorage['myShoppingCartJson'];
      this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];

        var url = new URL(location.href);
        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
          alert('productId is null');
            return;
        }
        this.getProductById();
    },
    methods: {
      handleAddToCartClick(){
        var myShoppingCartJson = localStorage['myShoppingCartJson'];
        this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
        //遍历购物车里面的商品，一个一个比对，看是否购物车里面的商品与此商品一样，如果全等,添加的话数量会改变
        var cartProduct = this.myShoppingCart.find(p => p.productId === this.productId);
        if(cartProduct){
          console.log('cart product exist');
          var originQuantity = parseInt(cartProduct.quantity);
          var addQuantity = parseInt(this.quantity);
          cartProduct.quantity = originQuantity + addQuantity;
        }else{
          //不存在的话就直接添加进这个对象
          cartProduct = {
            productId: this.productId,
            productCode: this.productCode,
            productName: this.productName,
            mainPicUrl: this.mainPicUrl,
            unitPrice: this.price,
            discount: this.discount,
            quantity: this.quantity
          };
          this.myShoppingCart.push(cartProduct);
        }
        //重新序列化存储在这个对象中
        localStorage['myShoppingCartJson'] = JSON.stringify(this.myShoppingCart);
        this.$message.success('添加购物车成功')
      },
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
