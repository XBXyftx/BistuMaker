<!-- MyIntroduction.vue -->
<template>
  <div class="introduction-container">

    <h1 class="title" style="font-size: 50px" v-if="!isMobile">
      BistuMaker
      <span style="color:#4ab1f4">创客空间</span>
    </h1>
    <h1 class="title" style="font-size: 50px;padding: 0;color:#4ab1f4" v-else>
      创客空间<br>
      <span style="color:#0a0a0a">BistuMaker</span>
    </h1>


    <div class="description">
      <!-- 这里填写具体的介绍内容 -->
      “创客空间”社团成立11-12年，管理正规、环境设备齐全，是本社成员锻炼自己、拓展见识的小天地。
      <br>
      创客空间的建立目标是提高社员技术能力，让同学们增长见识，提升合作、表达、管理规划等能力，结交人脉、建立友谊，让每个成员的大学四年更加充实、全面进步。
    </div>

<!--  显示图片 居中-->

<!--    <img :src="img"  class="img" />-->
<!--  为我创建个div盒子里面一行放着几个div，div里面上面是图标下面是描述  -->

    <div class="container">
      <div class="item">
        <div class="icon" >
          <el-icon><ChromeFilled /></el-icon>
        </div>
        <p>软件学习</p>
        <a>
          鸿蒙APP、WEB设计
        </a>
      </div>
      <div class="item">
        <i class="fas fa-chart-line"></i>
        <div class="icon" >
          <el-icon><Cpu /></el-icon>
        </div>
        <p>硬件学习</p>
        <a>
          嵌入式开发、单片机
        </a>
      </div>
      <div class="item">
        <div class="icon">
          <el-icon><MessageBox /></el-icon>
        </div>
        <i class="fas fa-server"></i>
        <p>学业竞赛</p>
        <a>
          大创、机器人大赛
        </a>
      </div>


      <!-- 可以根据需要添加更多项目 -->
    </div>

  </div>
</template>

<style scoped>
.container {
  margin-top: 100px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.item:hover {
  background: linear-gradient(135deg, #4d31e8, #2BC0E4 );
  border-radius: 20%;
  color: white;
  transform: perspective(1000px) rotateX(1deg) rotateY(-5deg) scale(1.05)
}
.item {
  transition: all 0.1s ease;
  background-color: #f5f5f5;
  border-radius: 10px;
  width: 22%;
  //padding: 20px;
  box-sizing: border-box;
  text-align: center;
  margin-bottom: 20px;
  cursor: grab;
  p{
    font-weight: bold;
    font-size: 1.2rem;
    transition: all 0.1s ease;
  }
}

.item i {
  font-size: 3rem;
  margin-bottom: 10px;
}


.introduction-container {
  //display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 20px;
}

.title {
  //font-size: 24px;
  //margin-bottom: 20px;
  padding-top: 40px;
  padding-right: 50px;
}

.description {
  font-size: 16px;
  line-height: 1.5;
  font-family: 'AlimamaDaoLiTi',serif;

}

@media (min-width: 768px) {
  .introduction-container {
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }

  .title {
    font-size: 32px;
  }

  .description {
    font-size: 20px;
  }
}
</style>

<script setup>// 这个脚本部分无需编写任何逻辑，因为这个组件没有动态数据或方法
import {ChromeFilled, Cpu, MessageBox, Platform, SwitchFilled} from "@element-plus/icons-vue";
import {ref} from "vue";

const img = new URL('@/assets/img/homeImages/1.jpg',import.meta.url )
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

</script>
<!--乐了 更新记录写了，但是你点提交并推送呀1-->