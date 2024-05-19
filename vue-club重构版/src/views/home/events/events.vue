<script setup>
import {ref} from 'vue'
const url =
    'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

let data = ref([
  {
    id:1,
    title: '硬件项目',
    content:  `人工智能、机械手</br>
      <br>各种机器人如导航、防火</br>
      <br>自主编程拼接机器人小车、智能种植</br>
      <br>3D打印、激光雕刻机的使用</br>
      <br>树莓派、Arduino开发板教学</br>
    `,
    img:  new URL('@/assets/Hardware.png',import.meta.url),
    router:''
  },
  {
    id:2,
    title: '大创竞赛',
    content: `大创项目、支撑毕业后自主创业</br>
      <br>创新杯、中国机器人大赛、挑战杯等校内外竞赛</br>
      <br>软硬件协同开发为主</br>
      <br>做出外形优、作用强的实物</br>
    `,
    img: new URL('@/assets/Competition.png',import.meta.url),
    router:''
  },
  {
    id:1,
    title: '软件学习',
    content: `鸿蒙开发入门及向导</br>
      <br>git使用教学</br>
      <br>web网页开发</br>
      <br>机器学习 ai训练</br>
      <br>百度AIstudio、腾讯PAI、阿里天池、Kaggle等</br>
    `,
    img: new URL('@/assets/Harmony.jpg',import.meta.url),
    router:''
  },


])
import animate from 'animate.css'


</script>

<template>
  <div v-for="(item,id) in data " :key="item.id">
    <div class="box" :style="isMobile?'  padding: 40px':'  padding: 50px 50px 50px 120px;'"   v-if="id%2===0">
<!--      {{item}}-->
      <!-- 左图 -->
      <el-image class="img"   :src="item.img" fit="contain" />
      <!-- 文字 -->
      <div class="text-Content" :style="isMobile?' ':'  margin-top: 2vw;'">
        <div class="title">{{item.title}}</div>

        <div style="width: 100%;height: 10px;background: linear-gradient(135deg, #2BC0E4, #EAECC6)"></div>
        <br>
        <div v-html="item.content"></div>
        <!-- 按钮 -->
<!--        <button class="btn" @click>Learn More</button>-->
      </div>
    </div>


    <div class="box" :style="isMobile?'  padding: 40px':'  padding: 50px 50px 50px 120px;'"  v-else>
      <el-image class="img"   :src="url" fit="contain" v-if="id%2===1 && isMobile"/>
      <!-- 文字 -->
      <div class="text-Content" style="margin-right: 4vw;margin-left: 0">
        <div class="title">{{item.title}}</div>

        <div style="width: 100%;height: 10px;background: linear-gradient(135deg, #EAECC6, #2BC0E4)"></div>
        <br>

        <div v-html="item.content"></div>
        <!-- 按钮 -->
<!--        <button class="btn">Learn More</button>-->
      </div>
      <!--    右图-->
      <el-image class="img" style="width: auto; height: 100%" :src="item.img" fit="contain" v-if="!isMobile"/>
    </div>
  </div>




</template>

<style scoped>

* {
  /*有字库苹果丽黑*/
  font-family:'HiraginoGBW33e0fef8d3e25b3e';
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {

  width: 100%;
  height: 100vh;
  /* flex 子元素 水平居中对其*/
  //display: flex;
  //justify-content: center;
  //align-items: center;
  /* 渐变色背景 */
  background: linear-gradient(to right, #1565C0, #b92b27);
}

.box {
  display: flex;
  //width: 90%;
  //height: 65%;
  padding: 50px 50px 50px 150px;

}

.img {
  flex: 1;
  //background: url(https://th.bing.com/th?id=OIP.DsEFwxJWlliMzQLItlcm4gHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2);
  background-size: cover;
  background-position: center center;
  border-radius: 12px;
  transition: all 1s linear;
}

.img:hover {
  filter: saturate(220%);
}

.text-Content {
  flex: 1;
  //margin-top: 55px;
  margin-left: 7.4vw;
  overflow: hidden;
}

.title {
  font-size: 2rem;
  margin-bottom: 0.5em;
}

.content {
  font-size: 1.1rem;
}

.btn {
  margin-top: 10%;
  width: 150px;
  height: 50px;
  border-radius: 20px;
  background-color: rgba(0, 0, 0, 0.607);
  color: #ffffffc2;
  transition: all 0.8s;
  cursor: pointer;
  font-size: 1rem;
  border: 0;
}

.btn:hover {
  background-color: rgba(255, 255, 255, 0.747);
  color: rgba(0, 0, 0, 0.795);
}

@media screen and (max-width: 768px) {
  .box {
    flex-direction: column;
  }

  .img {
    margin-bottom: 16px;
  }

  .text-Content {
    font-size: 15px;
    margin-left: 0;
  }

  .title {
    font-size: 1.2rem;
    margin-bottom: 1em;
  }

}
</style>