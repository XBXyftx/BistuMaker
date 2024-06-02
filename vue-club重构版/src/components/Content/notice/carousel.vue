<script setup>
import {getCurrentInstance, ref} from "vue";
import {getPhoneAlbumAllImage} from "@/api/Images.js";

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


const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL

const imagesList=ref({})
const getImages = async () => {
  let res = await getPhoneAlbumAllImage()
  imagesList.value = res.data


}
getImages()

</script>

<template>
  <div class="carousel">
    <el-carousel :interval="2000" arrow="always" height="25vw" type="card" v-if="!isMobile">
      <el-carousel-item v-for="item in imagesList" :key="item.id">
        <img :src="`${baseURL+item.imagesUrl}`" alt=""  width="100%" height="100%" >
        {{`${baseURL+item.imagesUrl}`}}
      </el-carousel-item>
    </el-carousel>

    <el-carousel :interval="2000" arrow="always" height="60vw"  v-else>
      <el-carousel-item v-for="item in imagesList" :key="item.id">
        <img :src="`${baseURL+item.imagesUrl}`" alt="" height="100%" width="100%">
      </el-carousel-item>
    </el-carousel>
  </div>

</template>

<style>

.el-carousel__indicators {
  display: none;
}

</style>