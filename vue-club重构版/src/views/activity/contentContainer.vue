<script setup>
import {ref} from 'vue'
import animate from 'animate.css'
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const hezhao=new URL('@/assets/合照.jpg',import.meta.url )
</script>

<template>
  <div class="content-container" :style="{display: isMobile ? 'inline' : 'flex'}">

    <div class="text-content" :style="{width: isMobile ? '95%' : '60%' }">
      <p style="font-size: 2.9rem;margin-top: 0px;margin-bottom: 0px;font-family: AlimamaDaoLiTi,serif;">
        关于<br>创客空间<br>社团活动<br>
      </p>
      <p style="font-weight: 500;font-size: 1rem ">
        平时社团活动也不仅仅有社课，我们还会举办讲座、夜烤、WorkShop、社游、期末聚…等超多活动给社员参加！当然，我们不只和葳格高中建立了长期的任教合作，身为志工性社团，我们还会和相关机构及国小一同举办各式各样的志工营队，落实将在社内所学回馈社会。
        <br>
        <br>
        iOS Club致力于软件开发，以及推广程序教育，但我们同时也期望能够给社员舒适的相处环境与丰富的交流活动，欢迎所有志在开发与充满教育热诚的人一起加入。
      </p>
    </div>



    <div class="image-content">
      <!-- 右侧图片 -->
      <img :src="hezhao" alt="右侧图片描述"
           style="height: auto;"
           :style="{width: isMobile ? '100%' : '650px' }"
      >
    </div>
  </div>


</template>

<style scoped>

.content-container {
  margin-top: 50px;
//display: flex;
//align-items: center; /* 保持垂直居中 */
//justify-content: center; /* 添加这一行以实现水平居中 */

//margin: 0 auto; /* 如果父元素不是绝对定位，则可使用这种方式进行水平居中 */



}

.text-content {
  box-sizing: border-box;
//align-items: center; /* 保持垂直居中 */
//justify-content: center; /* 添加这一行以实现水平居中 */
//width: 90%; /* 或者给一个固定宽度，并且需要设置其父元素的定位为相对定位 */
//max-width: 90%; /* 示例：最大宽度为800px */
  margin: 0 auto 0 10px;
//left: 100px;
  right: 0;
//margin:100px auto;
}

.image-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
//margin-left: 10px;
  margin: 5px;
}

</style>