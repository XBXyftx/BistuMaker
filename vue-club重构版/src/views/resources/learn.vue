<template>
  <br>
  <br>
  <div class="container" :class="{ mobile: isMobile }">
    <div class="content">
      <div class="image">

        <transition name="fade">
          <img
              :style="isMobile?'  width: 70vw;':'  width:30vw;'"
              :key="page.label" :src="page.img" alt="Image"/>
        </transition>
      </div>
      <div class="text">
        <h1>{{ page.title }}</h1>
        <p>{{ page.subtitle }}</p>
        <span v-html="page.content"></span>
      </div>
    </div>
    <div class="buttons">
      <button class="arrow left-arrow" @click="prevPage">&#9664;</button>
      <button class="arrow right-arrow" @click="nextPage">&#9654;</button>
    </div>
  </div>
  <br>
  <br>
</template>

<script setup>
import {ref} from 'vue';

const pages = [
  {
    label: '校外',
    title: '参加科技大会',
    subtitle: '鸿蒙技术开发',
    content: `鸿蒙系统（HarmonyOS）是由华为公司开发的一款面向全场景的分布式操作系统，旨在打破传统设备之间的界限，实现跨设备间的无缝协同和资源共享。\n它基于微内核设计，采用了先进的分布式架构，旨在提供高度的灵活性和效率，支持从智能手机、平板电脑、智能穿戴设备到智能家居等多种类型的硬件设备。鸿蒙开发作为国家未来主推方向，计算机相关专业从业人员及学生有必要对其有所了解。\n由刘子安学长牵头，数名大二负责人辅助，带领社团成员前往国家会议中心参与“第二届开放原子开源基金会OpenHarmony技术大会”，参与“技术筑生态，智联赢未来”主题讲座，对鸿蒙系统开发有了更深的了解。`,
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
    content: `为了提升社团成员在人工智能领域的知识和技能，特别是图像识别技术，我们计划在假期期间组织一次长期的AI识图训练活动。\n此次活动将以指导老师的引领为核心，通过系统的资料准备和实践操作，让成员们掌握AI识图的基本原理和应用。通过此次假期长阶段活动，我们期待社团成员能深入理解AI识图技术，提升编程和问题解决能力，同时增强团队协作和创新思维，这将为他们在未来的学习和工作中打下坚实的基础。`,
    img: new URL('@/assets/Hardware.png', import.meta.url).href,
  },
];

const page = ref(pages[0]);

const changeState = (index) => {
  page.value = pages[index];
};

const isMobile = ref(window.innerWidth < 768);
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});
let currentIndex = ref(0);
const nextPage = () => {
  const nextIndex = (currentIndex.value + +1) % pages.length;
  currentIndex.value=nextIndex;
  console.log(nextIndex);
  changeState(nextIndex);
};

const prevPage = () => {
  const prevIndex = (currentIndex.value - 1 + pages.length) % pages.length;
  currentIndex.value=prevIndex;
  console.log(prevIndex);
  changeState(prevIndex);
};
</script>


<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.content {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  width: 100%;
  position: relative;
}

.image {
  width: 40%;
  padding: 20px;
}

.image img {
  width: 70vw;
  height: auto;
  border-radius: 10px;
}

.text {
  width: 60%;
  padding: 20px;
}

.text h1 {
  font-size: 2em;
  margin-bottom: 0.5em;
}

.text p {
  font-size: 1.5em;
  margin-bottom: 1em;
}

.text span {
  display: block;
  font-size: 1em;
  color: #333;
  line-height: 1.5;
  margin-bottom: 2em;
}

.buttons {
  display: flex;
  justify-content: space-between;
  width: 100%;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}

.arrow {
  background-color: transparent;
  color: #007BFF;
  border: none;
  font-size: 2em;
  cursor: pointer;
  padding: 10px;
  transition: color 0.3s;
}

.arrow:hover {
  color: #0056b3;
}

.mobile .content {
  flex-direction: column;
  align-items: center;
}

.mobile .image {
  width: 100%;
  padding: 0;
}

.mobile .text {
  width: 100%;
  padding: 10px;
  text-align: center;
}

.fade-enter-active, .fade-leave-active {
  transition: all 0.5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
  transform: translateX(20px);
}

.fade-leave-active {
  position: absolute;
}

@keyframes fadeInRight {
  from {
    transform: translateX(20px);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

</style>