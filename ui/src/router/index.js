const routes = [
    {
        name: 'login',
        path: '/login',
        component: () => import('../view/Login.vue')
    },
    {
        path: '/',
        redirect: '/login'
    },
    {
        name: 'mark',
        path: '/mark',
        component: ()=>import('../view/Mark.vue')
    },
    {
        name: 'register',
        path: '/register',
        component: ()=>import('../view/Register.vue')
    }
];

import {createRouter} from 'vue-router'
import {createWebHashHistory} from "vue-router";

const router = createRouter({
    // history: createWebHistory(),
    history: createWebHashHistory(),
    routes: routes
})

export default router
