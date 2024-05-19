<template>

  <TopNavBar></TopNavBar>

  <div class="main">
    <div class="content-main" :style="{ width: isMobile ? '100%' : '80%'}">

      <demo></demo>

    </div>

  </div>

  <Footer></Footer>

</template>

<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import Footer from "@/components/Footer/Footer.vue";
import demo from "@/views/resources/demo.vue";
import { ref } from "vue";

</script>

<style scoped>


.main {
  background: linear-gradient(135deg, #5C258D, #4389A2);
  //background: linear-gradient(135deg, #001f3f, #0088a9, #00c9a7, #92d5c6, #ebf5ee);
}

.content-main {
  //display: flex;
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
