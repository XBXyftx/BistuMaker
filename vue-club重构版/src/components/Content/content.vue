<template>
  <div class="main">
    <div class="content-main" :style="{ width: isMobile ? '100%' : '80%'}">

      <notice>
      </notice>

      <introduce>
      </introduce>

      <carousel>
      </carousel>

      <events>
      </events>

    </div>

  </div>
</template>

<script setup>

import Notice from "@/components/Content/notice/notice.vue";
import {ref} from 'vue';
import Carousel from "@/components/Content/notice/carousel.vue";
import Answer from "@/views/home/answer/answer.vue";
import Introduce from "@/views/home/introduce/introduce.vue";
import events from "@/views/home/events/events.vue";
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});


</script>


<style scoped>
.main {
  background: linear-gradient(135deg, #5C258D, #4389A2);

}

.content-main {
  align-items: center;
  width: 80%;
  justify-content: center;
  height: 100%;
  background-color: #ffffff;
  margin: 0 auto;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.3),
  -5px 0 10px rgba(0, 0, 0, 0.3);
  z-index: 100;
}
</style>