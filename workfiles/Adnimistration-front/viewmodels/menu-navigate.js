var app = new Vue({
  router: router,
  el: '#app',
  data: {
    subMenus: [
      {
        name: '商品管理',
        index: '1',
        icon: 'el-icon-goods',
        menuItems: [

        ]
      },
      {
        name: '客户管理',
        index: '2',
        icon: 'el-icon-s-custom',
        menuItems: [

        ]
      },
      {
        name: '订单管理',
        index: '3',
        icon: 'el-icon-s-order',
        menuItems: [

        ]
      },
      {
        name: '用户管理',
        index: '4',
        icon: 'el-icon-user',
        menuItems: [

        ]
      }
      ]
  },
  methods: {
  }
})
