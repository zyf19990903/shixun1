const routes = [
    { path: '/product/list', component: ProductSearchRoutePage },
    { path: '/customer/list', component: CustomerSearchRoutePage },
    { path: '/order/list', component: OrderSearchRoutePage },
    { path: '/return/list', component: ReturnSearchRoutePage },
    { path: '/admin/list', component: AdminSearchRoutePage }
];

const router = new VueRouter({
    routes: routes
});
