var shoppingCart = new Vue({
  el: '#shoppingCart',
  data: {
    myShoppingCart:[]
  },
  computed:{//用来计算的函数
    totalPrice(){
      //遍历购物车里面的商品,计算此商品的总价
      var subTotalPrices = this.myShoppingCart.map(p =>{
        return p.unitPrice * p.quantity * p.discount;
      })
      //相当于遍历刚刚计算的每个商品的总价，然后两两相加，直至到0
      var totalPrice = subTotalPrices.reduce((a,b) => a+b,0);
      var totalPriceStr = totalPrice.toFixed(2);//四舍五入2位小数
      totalPrice = parseFloat(totalPriceStr);
      return totalPrice;
    }
  },
  mounted(){
      var myShoppingCartJson = localStorage['myShoppingCartJson'];
      //如果有值则反序列化，没值就是空数组
      this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson):[];
  },
  methods:{
    handleUpdate(index, row){
      localStorage['myShoppingCartJson'] = JSON.stringify(this.myShoppingCart);
      this.$message.success('修改购物车成功');
    },
    handleDelete(index, row){
      if(confirm('确定要删除吗')){
        this.myShoppingCart.splice(index,1);
        localStorage['myShoppingCartJson']  = JSON.stringify(this.myShoppingCart);
        this.$message.success('删除购物车成功')
      }
    },
    handleClearCart(){
      if(confirm('你确定要清空吗')){
        this.myShoppingCart = [];
        localStorage.removeItem('myShoppingCartJson');
      }
    }
  }
})
