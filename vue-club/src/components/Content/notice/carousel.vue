<script setup>
import {ref} from "vue";

const images = ref([
  {id: 0, idView: new URL('@/assets/img/homeImages/1.jpg',import.meta.url )},
  {id: 1, idView: new URL('@/assets/img/homeImages/2.jpg',import.meta.url )},
  {id: 2, idView: new URL('@/assets/img/homeImages/3.jpg',import.meta.url )},
  {id: 3, idView: new URL('@/assets/img/homeImages/4.jpg',import.meta.url )},
])
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
const Height = ref('100rpx');
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
  Height.value=isMobile.value? '200px' : '30rem';
});
console.log("12312"+Height.value)


</script>

<template>
  <div class="carousel">
    <el-carousel :interval="2000" arrow="always" height="25vw" type="card" v-if="!isMobile">
      <el-carousel-item v-for="item in images" :key="item.id">
        <img :src="item.idView" alt="" height="100%" width="auto">
      </el-carousel-item>
    </el-carousel>

    <el-carousel :interval="2000" arrow="always" height="60vw"  v-else>
      <el-carousel-item v-for="item in images" :key="item.id">
        <img :src="item.idView" alt="" height="100%" width="auto">
      </el-carousel-item>
    </el-carousel>
  </div>

</template>

<style scoped>



</style>