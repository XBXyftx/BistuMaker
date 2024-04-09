<template>
    <TopNavBar></TopNavBar>
    <div class="container" v-if="!isMobile">
      <div class="left">
        <transition name="fade">
          <img :key="page.label" :src="page.img" alt="Image" />
        </transition>
      </div>
      <div class="right">
        <div class="page">
          <div class="page_content">
            <h1>{{ page.title }}</h1>
            <p>{{ page.subtitle }}</p>
            <span>{{ page.content }}</span>
            <br />
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
            <h1 style="font-size: 30px">{{ page.title }}</h1>
            <p style="font-size: 20px"> {{ page.subtitle }}</p>
            <span style="font-size: 16px">{{ page.content }}</span>
            <br />
            <label v-for="(item, index) in pages" :key="index" class="button" style="display: table-cell;margin: 10px" @click="changeState(index)">
              {{ item.label }}
            </label>
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>
</template>

<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import Footer from "@/components/Footer/Footer.vue";
import { ref } from "vue";

const page = ref({
  label: '校外',
  title: '参加前沿科技大会',
  subtitle: '鸿蒙技术开发',
  content: '再看山顶，云下百尺，一处小院里铺着地砖摆着家具，小到碗筷靠垫，大到衣柜被褥，无不齐全。藕衣人不知从哪踱出来，进了前厅往八仙椅上一坐，打开那摞账本，从倒数第三册里抽出一页，轻轻抖动，纸的夹缝里漏出一颗围棋黑子，落地清脆，“脯咙”一声。',
  img: new URL('@/assets/HeadPic.jpg', import.meta.url).href,
});
const pages = [
  {
    label: '校外',
    title: '参加前沿科技大会',
    subtitle: '鸿蒙技术开发',
    content: '再看山顶，云下百尺，一处小院里铺着地砖摆着家具，小到碗筷靠垫，大到衣柜被褥，无不齐全。藕衣人不知从哪踱出来，进了前厅往八仙椅上一坐，打开那摞账本，从倒数第三册里抽出一页，轻轻抖动，纸的夹缝里漏出一颗围棋黑子，落地清脆，“瑭啷”一声。',
    img: new URL('@/assets/HeadPic.jpg', import.meta.url).href,
  },
  {
    label: '校内',
    title: '学习硬件技术',
    subtitle: '激光切割机',
    content: '绩眉头一挑，道：“生意场，你死我活的地方，得罪几个人再寻常不过。只是除了司岁台谁又知道我们谁是谁？种稻子的叫技术天师，打铁的算豪放大姐，我充其量一个季老板，再恨我的人能有多恨呢。”',
    img: new URL('@/assets/合照.jpg', import.meta.url).href,
  },
  {
    label: '假期',
    title: '假期长期任务',
    subtitle: '跟进社团指导老师',
    content: '他随即想到那三张老脸。人的寿命之于岁兽不过蚍蜉，然而蚍蜉亦有渡海之志，年轻的雄心一轮滚过一轮，老脸复老脸，大炎早已由人类话事，司岁台一个当差的小子都敢同他大声说话。',
    img: new URL('@/assets/HeadPic.jpg', import.meta.url).href,
  }
];

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

</script>

<style scoped>
.container {
  display: flex;
  align-items: center;
}
.left {
  width: 60vw;
  padding-right: 20px;
}
.right {
  flex: 2;
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
  width: 10%;
  color: #000000;
  font-size: 30px;
  border-bottom: #565656 solid 4px;
  text-align: center;
  bottom: 1px;
  margin: 20px;
  left: calc(50% + (var(--i) * 17%));
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
  font-size: 70px;
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
