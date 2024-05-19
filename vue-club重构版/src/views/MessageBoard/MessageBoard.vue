<template>
  <div class="main">
    <TopNavBar />
    <MessageBoard />
    <br><br><br>
    <Footer />
  </div>
</template>

<script setup>
// 导入组件
import Footer from "@/components/Footer/Footer.vue";
import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import MessageBoard from "@/components/Content/MessageBoard/MessageBoard.vue";
</script>

<style scoped>
.main {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(135deg, #5C258D, #4389A2);
}
</style>