<template>
  <TopNavBar></TopNavBar>
  <div class="main">
    <div class="content-main" :style="{ width: isMobile ? '100%' : '80%'}">
      <learn></learn>
    </div>
  </div>
  <Footer></Footer>
</template>

<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import Footer from "@/components/Footer/Footer.vue";
import demo from "@/views/resources/learn.vue";
import { ref } from "vue";
import Learn from "@/views/resources/learn.vue";

</script>

<style scoped>
.main {
  background: linear-gradient(135deg, #5C258D, #4389A2);
  height: 100%;
  display: inline-block;
  align-items: center;
  justify-content: center;
}

.content-main {
  width: 80%;
  background-color: #ffffff;
  margin: 70px auto;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.3), -5px 0 10px rgba(0, 0, 0, 0.3);
  z-index: 100;
  padding: 20px;

  border-radius: 10px;
}

.content-main.mobile {
  width: 100%;
  box-shadow: none;
}
</style>
