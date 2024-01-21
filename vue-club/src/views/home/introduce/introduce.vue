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
  </div>
</template>

<style scoped>
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
</script>