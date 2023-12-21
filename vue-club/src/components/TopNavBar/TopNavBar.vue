<template>
  <nav class="nav-container">
    <div class="logo-container">
      <img src="" alt="logo" />
    </div>
    <div class="menu-container" :class="{ 'menu-open': menuOpen }">
      <ul v-if="!isMobile">
        <li><a href="#">首页</a></li>
        <li><a href="#">关于</a></li>
        <li><a href="#">社团活动</a></li>
        <li><a href="#">社团相册</a></li>
        <li><a href="#">教学资源</a></li>
<!--        <li><a href="#">other2</a></li>-->
      </ul>
      <div class="burger-icon" @click="toggleMenu" v-if="isMobile">
        <div v-if="!menuOpen" class="burger-bars">
          <el-icon size="25px"><Expand /></el-icon>
        </div>
        <div v-else class="burger-close">
          <el-icon size="25px"><CloseBold /></el-icon>
        </div>
      </div>
      <ul class="mobile-menu" v-if="isMobile && menuOpen">
        <li><a href="#" @click="toggleMenu">Home</a></li>
        <li><a href="#" @click="toggleMenu">About Us</a></li>
        <li><a href="#" @click="toggleMenu">Price</a></li>
        <li><a href="#" @click="toggleMenu">Route</a></li>
        <li><a href="#" @click="toggleMenu">Other1</a></li>
        <li><a href="#" @click="toggleMenu">Other2</a></li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref, computed } from 'vue';
import {CloseBold, Expand, Operation} from "@element-plus/icons-vue";
//设置menu窗口界面，默认关闭
const menuOpen = ref(false);
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
console.log(isMobile);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const toggleMenu = () => {
  menuOpen.value = !menuOpen.value;
};
</script>

<style scoped>

.nav-container {
  margin: 0px;
  display: flex;
  justify-content: space-between;
  height: 60px;
  align-items: center;
  padding: 0.5rem 1rem;
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

.menu-container ul li {
  margin-right: 1rem;
}
.menu-container .mobile-menu li {
  margin-right: 0;
}
.menu-container ul li a {
  color: #1f1f1f;
  text-decoration: none;
}

.burger-icon {
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
  display: flex;
  flex-direction: column;
  //list-style-type: none;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 70px;
  left: 0;
  background-color: #ffffff;
  width: 100%;

  //height: calc(100vh - 100px);
  overflow-y: auto;
  z-index: 100000;
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