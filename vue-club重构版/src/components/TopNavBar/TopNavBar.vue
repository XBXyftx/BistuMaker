<template>
  <nav class="nav-container ">
    <div class="logo-container">
      <a href="/" class="logo-name" >创客空间</a>
    </div>
    <div>

    </div>
    <div class="menu-container" :class="{ 'menu-open': menuOpen }">
      <ul v-if="!isMobile">
        <li><a href="/">首页</a></li>
        <li><a href="/learn">社团资源</a></li>
        <li><a href="/activity">社团活动</a></li>
        <li><a href="/phoneAlbum">社团相册</a></li>
        <li><a href="/blog">博文</a></li>
        <li><a href="#">关于</a></li>
        <div class="nav-box"></div>
<!--        <li><a href="#">教学资源</a></li>-->
        <li>
          <a
             href="/enrollment"
             style="background-color: #4ab1f4;
              margin: 10px;
              padding: 0.5rem 1rem;
              line-height: 40px; /* 使文本垂直居中 */
              text-align: center; /* 使文本水平居中 */
              border-radius: 15px; /* 设置圆角 */
              color: white; /* 文本颜色（可选） */
              font-size: 15px; /* 字体大小（可选） */"
          >
            加入我们
<!--            <router-link to="/login">登录</router-link>-->
          </a>
        </li>

      </ul>
      <div class="burger-icon"  v-if="isMobile">
          <a
              href="/enrollment"
              style="background-color: #4ab1f4;
              margin-right: 20px;
              margin-top: 6px;
              padding: 0.5rem 1rem;
              line-height: 20px; /* 使文本垂直居中 */
              text-align: center; /* 使文本水平居中 */
              border-radius: 15px; /* 设置圆角 */
              color: white; /* 文本颜色（可选） */
              font-size: 15px; /* 字体大小（可选） */"
          >
            加入我们
            <!--            <router-link to="/login">登录</router-link>-->
          </a>
          <div  @click="toggleMenu">
            <div v-if="!menuOpen" class="burger-bars">
              <el-icon size="25px"><Expand /></el-icon>
            </div>
            <div v-else class="burger-close">
              <el-icon size="25px"><CloseBold /></el-icon>
            </div>
          </div>

      </div>

      <ul class="mobile-menu" v-if="isMobile && menuOpen">
        <li><a   @click="toggleMenu">关闭</a></li>
        <li><a href="/" @click="toggleMenu">首页</a></li>

        <li><a href="/activity" @click="toggleMenu">社团活动</a></li>
        <li><a href="/phoneAlbum" @click="toggleMenu">社团相册</a></li>
        <li><a href="/blog">博文</a></li>
        <li><a href="#" @click="toggleMenu">关于</a></li>
      </ul>

    </div>

  </nav>
</template>

<script setup>
import { ref, computed } from 'vue';
import {CloseBold, Expand, Operation} from "@element-plus/icons-vue";
import router from "@/router/index.js";
//设置menu窗口界面，默认关闭
const menuOpen = ref(false);
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const toggleMenu = () => {
  menuOpen.value = !menuOpen.value;
};


</script>

<style scoped>
.logo-name{
  padding-left: 30px;
  font-size: 1.5rem;
  font-weight: bold;
  color: #1f1f1f;
  text-decoration: none;
}

.nav-container {
  left: 0; right: 0;
  height: 60px;
  //position: fixed;
  width: 100%;
  display: flex;
  justify-content: space-between;

  align-items: center;
  //padding: 0.5rem 1rem;
  background-color: #ffffff;
}

.logo-container img {
  height: 30px;
}

.menu-container {
  display: flex;
  align-items: center;
}

.menu-container ul {
  display: flex;
  list-style-type: none;
  margin-left: 12px;
  padding: 00px;
}

.menu-container ul li:not(:last-child) {

  margin-right: 2rem;
  margin-top: 10px;
}
.menu-container .mobile-menu li {

  margin-right: 0;
}
.menu-container ul li a {

  color: #1f1f1f;
  text-decoration: none;
}

.burger-icon {
  //margin: 25px;
  display: flex;
  justify-content: center;
  height: 30px;
  align-items: center;
  cursor: pointer;
}

.burger-bars {
  width: 25px;
  height: 3px;
  //background-color: #fff;
  position: relative;
  margin-bottom: 15px;
}
.burger-close {
  position: relative;
  width: 25px;
  height: 3px;
  margin-bottom: 15px;

}



.mobile-menu {
  z-index: 2147483647;
  display: flex;
  flex-direction: column;
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: fixed;
  top: 0;
  left: -15px;
  background-color: #ffffff;
  width: 100%;

  //height: calc(100vh - 100px);
  overflow-y: auto;

}

.mobile-menu li a {
  color: #fff;
  text-decoration: none;
  padding: 0.5rem 1rem;
  display: block;
}

.mobile-menu li a:hover {
  background-color: #ffffff;
}
</style>