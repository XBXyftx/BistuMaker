 
import { createRouter, createWebHistory } from 'vue-router'

 



import Home from "@/views/home/home.vue";
import About from "@/views/about/about.vue";




//定义路由关系
const routes = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/about',
        name: 'about',
        component: About
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/login.vue')
    },
    {
        path:'/admin',
        name:'admin',
        component:()=>import('@/views/layout/layout.vue'),


    }



]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
