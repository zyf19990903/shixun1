const routes = [
    { path: '/administrator/updateprofile', component: AdministratorUpdateProfileRoutePage },
    { path: '/administrator/update/:administratorId', component: AdministratorUpdateRoutePage },
    { path: '/administrator/create', component: AdministratorCreateRoutePage },

    { path: '/product/list', component: ProductSearchRoutePage },
    { path: '/product/create', component: ProductCreateRoutePage },
    { path: '/product/update', component: ProductUpdateRoutePage },
    { path: '/customer/list', component: CustomerSearchRoutePage },
    { path: '/order/list', component: OrderSearchRoutePage },
    { path: '/return/list', component: ReturnSearchRoutePage },
    { path: '/admin/list', component: AdminSearchRoutePage }
];

const router = new VueRouter({
    routes: routes
});
