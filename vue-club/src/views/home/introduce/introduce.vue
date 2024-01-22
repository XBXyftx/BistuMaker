<!-- MyIntroduction.vue -->
<template>
  <div class="introduction-container">
    <h1 class="title" style="font-size: 50px">信息科大<span style="color:#4ab1f4">创客空间</span>  </h1>
    <div class="description">
      <!-- 这里填写具体的介绍内容 -->
      <p>四川大学飞扬俱乐部，创建于2003年9月
          <br>
        一个集技术交流、电脑维修、软件研发、网站小程序等建设维护于一体的专业公益IT社团
        <br>
        飞扬秉承“创新实践，服务川大”理念，旨在通过专业技术应用及研发服务大众
        <br>
        向川大师生提供免费的电脑维修维护及其他科技服务
        <br>
      </p>
    </div>

<!--  显示图片 居中-->

<!--    <img :src="img"  class="img" />-->
<!--  为我创建个div盒子里面一行放着几个div，div里面上面是图标下面是描述  -->

    <div class="container">
      <div class="item">
        <div class="icon" >
          <el-icon><SwitchFilled/></el-icon>
        </div>
        <p>编程与开发</p>
        <a>
          电脑报修是飞扬俱乐部提供的免费公益电脑维修服务
        </a>
      </div>
      <div class="item">
        <i class="fas fa-chart-line"></i>
        <p>数据分析</p>
      </div>
      <div class="item">
        <i class="fas fa-server"></i>
        <p>云计算服务</p>
      </div>


      <!-- 可以根据需要添加更多项目 -->
    </div>

  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.item:hover {
  background-color: rgb(67, 150, 202);
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
import {SwitchFilled} from "@element-plus/icons-vue";

const img = new URL('@/assets/img/homeImages/1.jpg',import.meta.url )
</script>