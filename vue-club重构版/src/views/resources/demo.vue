<template>

  <div class="container" v-if="!isMobile">

    <div class="left">
      <transition name="fade">
        <img :key="page.label" :src="page.img" alt="Image" style="width: 100%; height: 100%;" />
      </transition>
    </div>

    <div class="right">
      <div class="page">
        <div class="page_content">
          <h1>{{ page.title }}</h1>
          <p>{{ page.subtitle }}</p>
          <span>{{ page.content }}</span>
          <br>
          <label v-for="(item, index) in pages" :key="index" class="button" @click="changeState(index)">
            {{ item.label }}
          </label>
        </div>
      </div>
    </div>

  </div>

  <div class="mobile" v-else>

    <div class="top">
      <img :key="page.label" :src="page.img" alt="Image" />
    </div>

    <div class="bottom">
      <div class="page">
        <div class="page_content" style="padding: 20px">
          <h1>{{ page.title }}</h1>
          <p>{{ page.subtitle }}</p>
          <span v-html="page.content"></span>
          <br>
          <br>
          <label v-for="(item, index) in pages" :key="index" class="button" style="display: table-cell;margin: 10px" @click="changeState(index)">
            {{ item.label }}
          </label>
          <br>
        </div>
      </div>
    </div>

  </div>

</template>

<script setup>

import {ref} from "vue";

const changeState = (row) => {
  console.log(row);
  page.value = pages[row];
};

//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const page = ref({
  label: '校外',
  title: '参加科技大会',
  subtitle: '鸿蒙技术开发',
  content: `鸿蒙系统（HarmonyOS）是由华为公司开发的一款面向全场景的分布式操作系统，旨在打破传统设备之间的界限，实现跨设备间的无缝协同和资源共享。\n它基于微内核设计，采用了先进的分布式架构，旨在提供高度的灵活性和效率，支持从智能手机、平板电脑、智能穿戴设备到智能家居等多种类型的硬件设备。鸿蒙开发作为国家未来主推方向，计算机相关专业从业人员及学生有必要对其有所了解。\n由刘子安学长牵头，数名大二负责人辅助，带领社团成员前往国家会议中心参与“第二届开放原子开源基金会OpenHarmony技术大会”，参与“技术筑生态，智联赢未来”主题讲座，对鸿蒙系统开发有来更深的了解。`,
  img: new URL('@/assets/Harmony.jpg', import.meta.url).href,
});

const pages = [
  {
    label: '校外',
    title: '参加科技大会',
    subtitle: '鸿蒙技术开发',
    content: `鸿蒙系统（HarmonyOS）是由华为公司开发的一款面向全场景的分布式操作系统，旨在打破传统设备之间的界限，实现跨设备间的无缝协同和资源共享。\n它基于微内核设计，采用了先进的分布式架构，旨在提供高度的灵活性和效率，支持从智能手机、平板电脑、智能穿戴设备到智能家居等多种类型的硬件设备。鸿蒙开发作为国家未来主推方向，计算机相关专业从业人员及学生有必要对其有所了解。\n由刘子安学长牵头，数名大二负责人辅助，带领社团成员前往国家会议中心参与“第二届开放原子开源基金会OpenHarmony技术大会”，参与“技术筑生态，智联赢未来”主题讲座，对鸿蒙系统开发有来更深的了解。`,
    img: new URL('@/assets/Harmony.jpg', import.meta.url).href,
  },
  {
    label: '校内',
    title: '学习硬件技术',
    subtitle: '解魔方机器人',
    content: `机器人，这一科技领域的璀璨明珠，不仅代表了人类智慧的结晶，也是科技进步的重要标志。\n制作机器人，不仅是工程师的工作，也是创新者、梦想家和未来探索者的共同事业。\n机器人是一种能够执行预设任务的自动化设备,它可以被编程来完成各种复杂的任务，包括但不限于搬运、焊接、装配、清洁、甚至与人类进行互动。机器人通常由机械结构、控制系统、传感器和动力系统组成，它们的智能程度可以从简单的逻辑响应到复杂的自我学习和适应。`,
    img: new URL('@/assets/puzzleRobot.png', import.meta.url).href,
  },
  {
    label: '假期',
    title: '假期长期任务',
    subtitle: 'AI识图训练材料筹备',
    content: `为了提升社团成员在人工智能领域的知识和技能，特别是图像识别技术，我们计划在假期期间组织一次长期的AI识图训练活动。\n此次活动将以指导老师的引领为核心，通过系统的资料准备和实践操作，让成员们掌握AI识图的基本原理和应用。通过此次假期长阶段活动，我们期待社团成员能深入理解AI识图技术，提升编程和问题解决能力，同时增强团队协作和创新思维,这将为他们在未来的学习和工作中打下坚实的基础。`,
    img: new URL('@/assets/Hardware.png', import.meta.url).href,
  }
];
</script>

<style scoped>

.container {
  display: flex;
  align-items: center;
}

@keyframes fadeInRight {
  0% {
    transform: translateX(5%);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}

.left {
  width: 40vw;
  padding-left: 30px;
}
.right {
  flex: 2;
  animation: fadeInRight 1s ease-in-out 0s forwards;
}
img {
  max-width: 100%;
  width: 100%;
  height: auto;
  position: relative;
  display: block;
}

.button {
  display: inline-block;
  width: 15%;
  color: #000000;
  font-size: 30px;
  border-bottom: #565656 solid 4px;
  text-align: center;
  bottom: 1px;
  margin: 20px;
  z-index: 999;
  transition: 0.3s;
}

.page_content {
  box-sizing: border-box;
  height: 100%;
  padding: 50px;
  color: #000000;
  animation-name: fade;
  animation-duration: 0.7s;
  animation-iteration-count: 1;
  opacity: 1;
}

.page h1 {
  font-size: 50px;
}

.page p {
  font-size: 30px;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

.page span {
  font-size: 18px;
  letter-spacing: 2px;
  color: rgb(56, 56, 56);
}

label:hover {
  cursor: pointer;
  border-bottom: solid 4px rgba(0, 0, 0, 0.3);
}

</style>
