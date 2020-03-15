const routes = [
    { path: '/administrator/updateprofile', component: AdministratorUpdateProfileRoutePage },
    { path: '/administrator/update/:administratorId', component: AdministratorUpdateRoutePage },
    { path: '/administrator/create', component: AdministratorCreateRoutePage },

    { path: '/product/list', component: ProductSearchRoutePage },
    { path: '/product/create', component: ProductCreateRoutePage },
    { path: '/product/update', component: ProductUpdateRoutePage },


    { path: '/customer/list', component: CustomerSearchRoutePage },
    { path: '/customer/show', component: CustomerShowRoutePage },


    { path: '/address/list', component: AddressSearchRoutePage },
    { path: '/address/show', component: AddressShowRoutePage },



    { path: '/order/list', component: OrderSearchRoutePage },
    { path: '/order/show', component: OrderShowRoutePage },


    { path: '/return/list', component: ReturnSearchRoutePage },
    {
      path: '/return/edit/:returnId',
      component: ReturnEditRoutePage,
      children: [
        {
          path: 'show',
        },
        {
          path: 'history',
        }
      ]
    },
    { path: '/admin/list', component: AdminSearchRoutePage }

];

const router = new VueRouter({
    routes: routes
});
