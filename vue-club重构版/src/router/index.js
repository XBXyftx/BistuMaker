
import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/views/home/home.vue";
import About from "@/views/about/about.vue";
import Enrollment from "@/views/enrollment/enrollment.vue";
import Login from "@/views/login/login.vue";
import learn from "@/views/learn/learn.vue";
//定义路由关系
const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        meta: {
            title: '创客空间欢迎你~'
        }
    },
    {
        path: '/about',
        name: 'about',
        component: About,
        meta: {
            title: '关于创客空间'
        }
    },
    {
        path: '/enrollment',
        name: 'enrollment1',
        component: Enrollment,
        meta: {
            title: '报名'
        }
    },
    {
        path: '/learn',
        name: 'learn',
        component: learn,
        meta: {
            title: '学习资源'
        }
    },
    {
        path:'/admin',
        name:'admin',
        component:()=>import('@/views/layout/layout.vue'),
        meta: {
            title: '后台管理'
        },
        children:[
            {
                path:'/admin/user',
                name:'user',
                component:()=>import('@/views/layout/components/user/userManagement.vue'),
                meta: {
                    title: '用户管理'
                }
            },
            {
                path:'/admin/article',
                name:'article',
                component:()=>import('@/views/layout/components/article/articleManagement.vue'),
                meta: {
                    title: '文章管理'
                }
            },
            {
                path:'/admin/enrollment',
                name:'enrollment',
                component:()=>import('@/views/layout/components/enrollment/enrollmentManagement.vue'),
                meta: {
                    title: '报名管理'
                }
            },
            {
                path:'/admin/notifications',
                name:'notifications',
                component:()=>import('@/views/layout/components/notifications/notifications.vue'),
                meta: {
                    title: '通知管理'
                }
            },
            {
                path:'/admin/phoneAlbum',
                name:'phoneAlbum1',
                component:()=>import('@/views/layout/components/phoneAlbum/phoneAlbum.vue'),
                meta: {
                    title: '相册管理'
                }
            },
            {
                path:'/admin/home',
                name:'adminHome',
                component:()=>import('@/views/layout/components/adminHome/adminHome.vue'),
                meta: {
                    title: '后台主页'
                }
            }
        ]

    },
    {
        path:'/login',
        name:'login',
        component: Login,
        meta: {
            title: '登录'
        }
    },
    {
        path:'/article/:id',
        name:'article1',
        component:()=>import('@/components/Article/Article.vue'),
        meta: {
            title: '文章详情'
        }
    },
    {
        path:'/activity',
        name:'activity1',
        component:()=>import('@/views/activity/activity.vue'),
        meta: {
            title: '活动'
        }
    },
    {
        path:'/phoneAlbum',
        name:'phoneAlbum',
        component:()=>import('@/views/phoneAlbum/phoneAlbum.vue'),
        meta: {
            title: '相册'
        }
    }


]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
router.beforeEach((to, from, next) => {
    document.title = to.meta.title
    next()
})

//导出路由
export default router