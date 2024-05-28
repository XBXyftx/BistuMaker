<script setup>
import { ref } from 'vue';

// 这是示例图片 URL
const url = 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg';

// 判断是否是移动端
const isMobile = ref(window.innerWidth < 768);

// 监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

// 示例数据
let data = ref([
  {
    id: 1,
    title: '硬件项目',
    content: `人工智能、机械手<br>
      <br>各种机器人如导航、防火<br>
      <br>自主编程拼接机器人小车、智能种植<br>
      <br>3D打印、激光雕刻机的使用<br>
      <br>树莓派、Arduino开发板教学<br>`,
    img: new URL('@/assets/Hardware.png', import.meta.url),
    router: ''
  },
  {
    id: 2,
    title: '大创竞赛',
    content: `大创项目、支撑毕业后自主创业<br>
      <br>创新杯、中国机器人大赛、挑战杯等校内外竞赛<br>
      <br>软硬件协同开发为主<br>
      <br>做出外形优、作用强的实物<br>`,
    img: new URL('@/assets/Competition.png', import.meta.url),
    router: ''
  },
  {
    id: 3,
    title: '软件学习',
    content: `鸿蒙开发入门及向导<br>
      <br>git使用教学<br>
      <br>web网页开发<br>
      <br>机器学习 ai训练<br>
      <br>百度AIstudio、腾讯PAI、阿里天池、Kaggle等<br>`,
    img: new URL('@/assets/Harmony.jpg', import.meta.url),
    router: ''
  }
]);
</script>

<template>
  <div v-for="(item, index) in data" :key="item.id">
    <div class="box" :class="{ 'mobile-padding': isMobile }" v-if="index % 2 === 0">
      <!-- 左图 -->
      <el-image class="img" :src="item.img" fit="contain" />
      <!-- 文字 -->
      <div class="text-content" :class="{ 'mobile-margin': isMobile }">
        <div class="title">{{ item.title }}</div>
        <div class="divider"></div>
        <div v-html="item.content"></div>
      </div>
    </div>

    <div class="box" :class="{ 'mobile-padding': isMobile }" v-else>
      <el-image class="img" :src="item.img" fit="contain" v-if="isMobile" />
      <!-- 文字 -->
      <div class="text-content" :class="{ 'mobile-margin': isMobile }">
        <div class="title">{{ item.title }}</div>
        <div class="divider"></div>
        <div v-html="item.content"></div>
      </div>
      <el-image class="img" :src="item.img" fit="contain" v-if="!isMobile" />
    </div>
  </div>
</template>

<style scoped>
* {
  font-family: 'HiraginoGBW33e0fef8d3e25b3e';
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  width: 100%;
  height: 100vh;
  background: linear-gradient(to right, #1565C0, #b92b27);
}

.box {
  display: flex;
  padding: 50px 50px 50px 120px;
}

.mobile-padding {
  padding: 40px;
}

.img {
  flex: 1;
  border-radius: 12px;
  transition: all 1s linear;
}

.img:hover {
  filter: saturate(220%);
}

.text-content {
  flex: 1;
  margin-left: 7.4vw;
  overflow: hidden;
}

.mobile-margin {
  margin-top: 2vw;
}

.title {
  font-size: 2rem;
  margin-bottom: 0.5em;
}

.divider {
  width: 100%;
  height: 10px;
  background: linear-gradient(135deg, #2BC0E4, #EAECC6);
}

.content {
  font-size: 1.1rem;
}

@media screen and (max-width: 768px) {
  .box {
    flex-direction: column;
  }

  .img {
    margin-bottom: 16px;
  }

  .text-content {
    font-size: 15px;
    margin-left: 0;
  }

  .title {
    font-size: 1.2rem;
    margin-bottom: 1em;
  }
}
</style>
