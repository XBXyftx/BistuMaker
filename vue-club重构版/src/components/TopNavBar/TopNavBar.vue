<template>
  <nav class="nav-container">
    <div class="logo-container">
      <a href="/" class="logo-name">
        <span class="logo-bistu">Bistu</span>
        <span class="logo-maker">Maker</span>
      </a>
    </div>
    <div class="menu-container" :class="{ 'menu-open': menuOpen }">
      <ul v-if="!isMobile" class="menu-list">
        <li><a href="/">首页</a></li>
        <li><a href="/learn">社团资源</a></li>
        <li><a href="/activity">社团活动</a></li>
        <li><a href="/phoneAlbum">社团相册</a></li>
        <li><a href="/blog">博文</a></li>
        <li><a href="/about">留言板</a></li>
        <li class="nav-enrollment">
          <a href="/enrollment" class="enrollment-button">
            加入我们
          </a>
        </li>
      </ul>
      <a href="/enrollment"   v-if="isMobile" class="enrollment-button-mobile">加入我们</a>

      <div class="burger-icon" v-if="isMobile" @click="toggleMenu">
        <el-icon size="25px">
          <template v-if="!menuOpen">
            <Expand />
          </template>
          <template v-else>
            <CloseBold />
          </template>
        </el-icon>
      </div>
      <ul class="mobile-menu" v-if="isMobile && menuOpen">
        <li><a @click="toggleMenu">关闭</a></li>
        <li><a href="/" @click="toggleMenu">首页</a></li>
        <li><a href="/learn" @click="toggleMenu">社团资源</a></li>
        <li><a href="/activity" @click="toggleMenu">社团活动</a></li>
        <li><a href="/phoneAlbum" @click="toggleMenu">社团相册</a></li>
        <li><a href="/blog" @click="toggleMenu">博文</a></li>
        <li><a href="/about" @click="toggleMenu">留言板</a></li>
<!--        <li><a href="/enrollment" @click="toggleMenu" class="enrollment-button-mobile">加入我们</a></li>-->
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { CloseBold, Expand } from '@element-plus/icons-vue';

const menuOpen = ref(false);
const isMobile = ref(window.innerWidth < 768);

window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const toggleMenu = () => {
  menuOpen.value = !menuOpen.value;
};

onMounted(() => {
  isMobile.value = window.innerWidth < 768;
});
</script>

<style scoped>
.logo-name {
  padding-left: 30px;
  font-size: 1.7rem;
  font-weight: bold;
  text-decoration: none;
  letter-spacing: 2px;
}

.logo-bistu {
  color: #0e343d;
}

.logo-maker {
  color: #8db5be;
}

.nav-container {
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);


  top: 0;
  z-index: 30000000000000000000000000000000000;
}

.menu-container {
  display: flex;
  align-items: center;

}

.menu-list {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.menu-list li {
  margin-right: 2rem;
}

.menu-list li a {
  color: #333;
  text-decoration: none;
  padding: 0.5rem 0;
  transition: color 0.3s;
}

.menu-list li a:hover {
  color: #4ab1f4;
}

.nav-enrollment .enrollment-button {
  background-color: #4ab1f4;
  padding: 0.5rem 1rem;
  border-radius: 15px;
  color: white;
  font-size: 15px;
  text-align: center;
  transition: background-color 0.3s;
}

.nav-enrollment .enrollment-button:hover {
  background-color: #3a92d6;
}

.burger-icon {

}

.mobile-menu {
  flex: 1;
  display: flex;
  flex-direction: column;
  list-style: none;
  padding: 0;
  margin: 0;
  position: fixed;
  top: 60px;
  left: 0;
  width: 100%;
  background: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}


.mobile-menu li {
  padding-left: 16px;
  padding-top: 10px;
  padding-bottom: 5px;
}

.mobile-menu li a {
  color: #333;
  text-decoration: none;
  transition: background-color 0.3s;
}

.mobile-menu li a:hover {
  background-color: #e0e0e0;
}

.enrollment-button-mobile {
  background-color: #4ab1f4;
  color: white;
  text-align: center;
  padding: 0.5rem;
  border-radius: 15px;
  margin: 1rem 0;
  transition: background-color 0.3s;
}

.enrollment-button-mobile:hover {
  background-color: #3a92d6;
}
</style>
