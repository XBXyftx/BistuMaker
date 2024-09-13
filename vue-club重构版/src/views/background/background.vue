<template>
  <!-- element-plus中的容器 -->
  <el-container class="layout-container" >
    <!-- 左侧菜单 -->
    <el-aside width="200px" @click="onRouteName()">
      <div class="el-aside__logo"></div>
      <!-- element-plus的菜单标签 -->
      <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff"
               router>

        <el-menu-item index="/">
          <template #title>
<!--            <el-icon><HomeFilled /></el-icon>-->
            <el-icon><House /></el-icon>
            <span>回到官网</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/admin/home">
          <template #title>
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/admin/user">
          <el-icon><Avatar /></el-icon>
          <span>个人资料</span>
        </el-menu-item>

        <el-menu-item index="/admin/article">
          <el-icon><Memo /></el-icon>
          <span>文章管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/enrollment">
          <el-icon><Checked /></el-icon>
          <span>报名管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/notifications">
          <el-icon><Bell /></el-icon>
          <span>通知管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/phoneAlbum">
          <el-icon><Picture /></el-icon>
          <span>相册管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/message">
          <el-icon><ChatSquare /></el-icon>
          <span>留言墙管理</span>
        </el-menu-item>

        <el-menu-item index="/admin/imageshack">
          <el-icon><Files /></el-icon>
          <span>图床管理</span>
        </el-menu-item>

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
        <div style="width: 99%; height: 80%;">
          <div>
            <router-view></router-view>
          </div>
        </div>
      </el-main>
      <!-- 底部区域 -->
      <el-footer>创客空间后台管理 ©2024 Created by 神乐之魂 | Maintained by 三速彗星犬</el-footer>
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
  CaretBottom,
  Bell,
  Memo,
  Message,
  Picture,
  Edit,
  HomeFilled,
  MagicStick,
  House,
  Notebook,
  Avatar,
  Checked,
  ChatSquare,
  Files
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

  .el-main{
    background: linear-gradient(135deg, #5C258D, #4389A2);
  }

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
    box-shadow: #3d3d3d 0 0 7px;
    z-index: 100;
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
    background-color: #525252;
    color: #fff;
    height: 45px;
  }
}

.header-title {
  font-size: 20px;
  color: #333;
  margin-right: 20px;
}


</style>