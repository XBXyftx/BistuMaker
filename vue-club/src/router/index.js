 
import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/views/home/home.vue";
import About from "@/views/about/about.vue";
import Enrollment from "@/views/enrollment/enrollment.vue";
import Login from "@/views/login/login.vue";
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
        path: '/enrollment',
        name: 'enrollment1',
        component: Enrollment
    },
    {
        path:'/admin',
        name:'admin',
        component:()=>import('@/views/layout/layout.vue'),
        children:[
            {
                path:'/admin/user',
                name:'user',
                component:()=>import('@/views/layout/components/user/userManagement.vue')
            },
            {
                path:'/admin/article',
                name:'article',
                component:()=>import('@/views/layout/components/article/articleManagement.vue')
            },
            {
                path:'/admin/enrollment',
                name:'enrollment',
                component:()=>import('@/views/layout/components/enrollment/enrollmentManagement.vue')
            }
            ]

    },
    {
        path:'/login',
        name:'login',
        component: Login
    },
    {
        path:'/article/:id',
        name:'article1',
        component:()=>import('@/components/Article/Article.vue')
    }


]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
