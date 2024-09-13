
import { createRouter, createWebHistory } from 'vue-router'
import Home from "@/views/home/home.vue";
import MessageBoard from "@/views/MessageBoard/MessageBoard.vue";
import Enrollment from "@/views/enrollment/enrollment.vue";
import Login from "@/views/login/login.vue";
import learn from "@/views/resources/resources.vue";
//定义路由关系
const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        meta: {
            title: '创客空间'
        }
    },
    {
        path: '/messages',
        name: 'messages',
        component: MessageBoard,
        meta: {
            title: '留言板'
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
        component:()=>import('@/views/background/background.vue'),
        meta: {
            title: '后台管理'
        },
        children:[
            {
                path:'/admin/user',
                name:'user',
                component:()=>import('@/views/background/components/user/userManagement.vue'),
                meta: {
                    title: '用户管理'
                }
            },
            {
                path:'/admin/article',
                name:'article',
                component:()=>import('@/views/background/components/article/articleManagement.vue'),
                meta: {
                    title: '文章管理'
                }
            },
            {
                path:'/admin/enrollment',
                name:'enrollment',
                component:()=>import('@/views/background/components/enrollment/enrollmentManagement.vue'),
                meta: {
                    title: '报名管理'
                }
            },
            {
                path:'/admin/notifications',
                name:'notifications',
                component:()=>import('@/views/background/components/notifications/notifications.vue'),
                meta: {
                    title: '通知管理'
                }
            },
            {
                path:'/admin/phoneAlbum',
                name:'phoneAlbum1',
                component:()=>import('@/views/background/components/phoneAlbum/phoneAlbum.vue'),
                meta: {
                    title: '相册管理'
                }
            },
            {
                path:'/admin/other',
                name:'other',
                component:()=>import('@/views/background/components/other/other.vue'),
                meta: {
                    title: '其他'
                }
            },
            {
                path:'/admin/home',
                name:'adminHome',
                component:()=>import('@/views/background/components/adminHome/adminHome.vue'),
                meta: {
                    title: '后台主页'
                }
            },
            {
                path:'/admin/message',
                name:'message1',
                component:()=>import('@/views/background/components/message/messageAllInfo.vue'),
                meta: {
                    title: '留言管理'
                }
            },
            {
                path:'/admin/imageshack',
                name:'imageshack',
                component:()=>import('@/views/background/components/Imageshack/Imageshack.vue'),
                meta: {
                    title: '图床管理'
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
        component:()=>import('@/views/Article/Article.vue'),
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
    },
    {
     path:'/404',
        name:'404',
        component:()=>import('@/views/article/404/a404.vue'),
        meta: {
            title: '404'
        }
    },
    {
        path:'/blog',
        name:'博文',
        component:()=>import('@/views/blog/blog.vue'),
        meta: {
            title: '博文'
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