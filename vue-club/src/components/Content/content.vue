<template>
  <div class="main">
    <div class="content-main" :style="{ width: isMobile ? '100%' : '85%'}">
      <notice>
      </notice>

      <carousel>
      </carousel>


      <answer></answer>


    </div>

  </div>

<!--  <v-md-editor v-model="text" height="400px"></v-md-editor>-->
</template>

<style scoped>
.main{
  background-color: #bebcff;
}
.content-main {
  //display: flex;
  align-items: center;
  width: 85%;
  justify-content: center;
  margin: 0 auto;
  height: 100%;
  background-color: #ffffff;
}


</style>


<script setup>

import Notice from "@/components/Content/notice/notice.vue";
import { ref } from 'vue';
import Carousel from "@/components/Content/notice/carousel.vue";
import Answer from "@/views/home/answer/answer.vue";
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});


const text = ref('')
console.log(text.value)

</script>
