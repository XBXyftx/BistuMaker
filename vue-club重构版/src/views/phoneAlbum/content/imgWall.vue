<template>
  <!-- 骨架屏，用于显示内容加载中的占位图形 -->
  <div class='screen-root'  v-if="loading===false">
    <ul>
      <li/>
      <li/>
      <li/>
    </ul>
  </div>

  <!-- 加载完成后的实际内容展示 -->
  <div v-for="item1 in imageAlbumAllList"  v-if="loading!==false">
    <h1 class="">{{item1.phoneAlbumName}}</h1>
    <Waterfall :list="item1.List" :gutter="40"  :width="550" :crossOrigin="false">
      <!-- 图片卡片的模板定义 -->
      <template #item="{ item, url, index }">
        <div class="card" >
          <img :src="`${baseURL + item.imagesUrl}`" :alt="item.imageName">
        </div>
      </template>
    </Waterfall>
  </div>

</template>

<script setup>
import { LazyImg, Waterfall } from 'vue-waterfall-plugin-next'
import 'vue-waterfall-plugin-next/dist/style.css'
import {getCurrentInstance, ref, toRaw} from 'vue'
import {phoneAlbumAllInfoService} from "@/api/phoneAlbum.js";
import {selectImagesByImagesType} from "@/api/Images.js";

// 所有相册列表的引用
let imageAlbumAllList = ref([])

// 加载状态控制
let loading = ref(false)

/**
 * 获取全部相册信息。
 * 该函数会首先请求所有相册的信息，然后为每个相册请求其图片列表，
 * 最后更新加载状态。
 */
const getImageAlbumAllInfo = async () => {
  const res = await phoneAlbumAllInfoService()
  imageAlbumAllList.value=res.data
  for (let i = 0; i < imageAlbumAllList.value.length; i++) {
    imageAlbumAllList.value[i].List=await getImageAlbumList(imageAlbumAllList.value[i].id)
  }
  loading.value=true
}
getImageAlbumAllInfo()

/**
 * 根据相册ID获取图片列表。
 * @param {String} type - 相册ID
 * @returns {Promise<Array>} 返回图片信息数组的Promise。
 */
const getImageAlbumList = async (type) => {
  const res = await selectImagesByImagesType(type)

  return res.data
}
getImageAlbumList()

// 实例化基本信息，用于获取基础URL
const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL
</script>

<style scoped>
/* 标题样式定义 */
h1 {
  text-align: center;
  margin: 0;
  font-weight: 600;
  font-size: 36px;
  line-height: 1.2;
  color: #000000;
  text-transform: uppercase;
  letter-spacing: 1px;

  padding: 20px 0;

  /* 根据需要添加的样式 */
}

/* 响应式设计，调整屏幕宽度小于768px时的标题样式 */
@media (max-width: 768px) {
  h1 {
    font-size: 28px;
    text-shadow: none;
  }
}

/* 骨架屏样式 */
.screen-root {
}

ul {
  background-color: #fff;
  margin: 0 auto;
  padding: 20px;
  width: 40vw;
}

li {
  background-image: linear-gradient(90deg, #f2f2f2 25%, #e6e6e6 37%, #f2f2f2 63%);
  width: 100%;
  height: 0.6rem;
  list-style: none;
  background-size: 400% 100%;
  margin-top: 0.6rem;
  background-position: 100% 50%;
  animation: skeleton-loading 1.4s ease infinite;
}

li:first-child {
  width: 38%;
}

li:last-child {
  width: 61%;
}

/* 动画定义 */
@keyframes skeleton-loading {
  0% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0 50%;
  }
}

/* 图片卡片样式 */
.card {
  position: relative;
  overflow: hidden;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  padding: 10px; /* 内边距 */
  margin: 10px;
}

.card img {
  width: 100%; /* 确保图片填满卡片 */
  height: auto;
  transition: transform 0.3s ease;
}

.card:hover {
  transform: scale(1.1); /* 整个卡片放大 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 放大后的阴影 */
}

.card:hover img {
  transform: scale(1.1); /* 图片放大 */
}


</style>