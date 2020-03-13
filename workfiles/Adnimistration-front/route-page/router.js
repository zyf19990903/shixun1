const routes = [
    { path: '/product/list', component: ProductSearchRoutePage },
    { path: '/customer/list', component: CustomerSearchRoutePage }
];

const router = new VueRouter({
    routes: routes
});
