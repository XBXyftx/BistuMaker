<template>
  <!-- element-plus中的容器 -->
  <el-container class="layout-container" >
    <!-- 左侧菜单 -->
    <el-aside width="200px" @click="onRouteName()">
      <div class="el-aside__logo"></div>
      <!-- element-plus的菜单标签 -->
      <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff"
               router>

        <el-menu-item index="/admin/home">
          <template #title>
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </template>
        </el-menu-item>

        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <UserFilled/>
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item index="/admin/user">
            <el-icon><Edit /></el-icon>
            <span>基本资料</span>
          </el-menu-item>
        </el-sub-menu>


        <!--        文章管理-->
        <el-sub-menu index="2">
          <template #title>
            <el-icon><Memo /></el-icon>
            <span>文章管理</span>
          </template>
          <el-menu-item index="/admin/article">
            <el-icon><Edit /></el-icon>
            <span>文章管理</span>
          </el-menu-item>
        </el-sub-menu>

        <!--        报名管理-->
        <el-sub-menu index="3">
          <template #title>
            <el-icon><Message /></el-icon>
            <span>报名名单</span>
          </template>
          <el-menu-item index="/admin/enrollment">
            <el-icon><Edit /></el-icon>
            <span>报名管理</span>
          </el-menu-item>
        </el-sub-menu>


<!--        通知表-->

        <el-sub-menu index="4">
          <template #title>
            <el-icon><Bell /></el-icon>
            <span>通知表</span>
          </template>
          <el-menu-item index="/admin/notifications">
            <el-icon><Edit /></el-icon>
            <span>通知管理</span>
          </el-menu-item>
        </el-sub-menu>

<!--        相册管理-->
        <el-sub-menu index="5">
          <template #title>
            <el-icon><Picture /></el-icon>
            <span>相册管理</span>
          </template>
          <el-menu-item index="/admin/phoneAlbum">
            <el-icon><Edit /></el-icon>
            <span>相册管理</span>
          </el-menu-item>
        </el-sub-menu>



        <!--        其他管理-->
        <el-sub-menu index="6">
          <template #title>
            <el-icon><MagicStick /></el-icon>

            <span>其他管理</span>
          </template>
          <el-menu-item index="/admin/other">
            <el-icon><Edit /></el-icon>
            <span>其他</span>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <!-- 右侧主区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div>用户：<strong>{{ userInfoStore.info.role }}</strong></div>
        <!-- 下拉菜单 -->
        <!-- command: 条目被点击后会触发,在事件函数上可以声明一个参数,接收条目对应的指令 -->
        <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
<!--                        <el-avatar :src="avatar"/>-->
                        <el-icon>
                            <CaretBottom/>
                        </el-icon>
                    </span>
          <template #dropdown>
            <el-dropdown-menu>
<!--              <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>-->
<!--              <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>-->
<!--              <el-dropdown-item command="resetPassword" :icon="EditPen">重置密码</el-dropdown-item>-->
              <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <!-- 中间区域 -->
      <el-main>
        <div style="width: 99%; height: 80%;border: 1px solid ;">
          <div>
            <router-view></router-view>
          </div>
        </div>




      </el-main>
      <!-- 底部区域 -->
      <el-footer>创客空间后台管理 ©2024 Created by 神乐之魂</el-footer>
    </el-container>
  </el-container>
</template>

<script setup>
import {
  Management,
  Promotion,
  UserFilled,
  User,
  Crop,
  EditPen,
  SwitchButton,
  CaretBottom, Bell, Memo, Message, Picture, Edit, HomeFilled, MagicStick
} from '@element-plus/icons-vue'
// import avatar from '@/assets/default.png'

import {userInfoService} from '@/api/user.js'
import useUserInfoStore from '@/stores/userInfo.js'
import {useTokenStore} from '@/stores/token.js'
import {nextTick, reactive, ref, getCurrentInstance,watch} from "vue";
const tokenStore = useTokenStore();
const userInfoStore = useUserInfoStore();

// 调用函数,获取用户详细信息
const getUserInfo = async () => {
  //调用接口
  let result = await userInfoService();
  //数据存储到pinia中
  userInfoStore.setInfo(result.data);
}

getUserInfo();
//条目被点击后,调用的函数
import {useRouter} from 'vue-router'

const router = useRouter();
import {ElMessage, ElMessageBox} from 'element-plus'

const handleCommand = (command) => {
  if (command === 'logout') {
    ElMessageBox.confirm(
        '您确认要退出吗?',
        '温馨提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
    )
        .then(async () => {
          //退出登录
          //1.清空pinia中存储的token以及个人信息
          tokenStore.removeToken()
          userInfoStore.removeInfo()

          //2.跳转到登录页面
          await router.push('/login')
          ElMessage({
            type: 'success',
            message: '退出登录成功',
          })

        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: '用户取消了退出登录',
          })
        })
  } else {
    //路由
    router.push('/user/' + command)
  }
}
//获取当前目录的路径
let routePath = ref(router.currentRoute.value.path);

console.log(routePath.value)
router.afterEach((to) => {
  routePath.value = to.path;
});

function onRouteName() {
  routePath = ref(router.currentRoute.value.path)
}
if (routePath.value==='/admin')
  router.push('/admin/home')

</script>



<style scoped>
.layout-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;

    &__logo {
      height: 120px;

    }

    .el-menu {
      border-right: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}

.header-title {
  font-size: 20px;
  color: #333;
  margin-right: 20px;
}


</style>