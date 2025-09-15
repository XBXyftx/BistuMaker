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
  <div v-for="item1 in imageAlbumAllList" :key="item1.id" v-if="loading!==false">
    <h1 class="">{{item1.phoneAlbumName}}</h1>
    <Waterfall
      :list="item1.List"
      :gutter="20"
      :width="calculateColumnWidth()"
      :crossOrigin="false"
      :breakpoints="{
        1200: { rowPerView: 4 },
        900: { rowPerView: 3 },
        600: { rowPerView: 2 },
        300: { rowPerView: 1 }
      }"
    >
      <!-- 图片卡片的模板定义 -->
      <template #item="{ item, url, index }">
        <div class="card" :key="item.id || index">
          <img
            :src="`${baseURL + item.imagesUrl}`"
            :alt="item.imageName"
            @load="handleImageLoad"
            @error="handleImageError"
          >
        </div>
      </template>
    </Waterfall>
  </div>

</template>

<script setup>
import { LazyImg, Waterfall } from 'vue-waterfall-plugin-next'
import 'vue-waterfall-plugin-next/dist/style.css'
import {getCurrentInstance, ref, toRaw, onMounted, nextTick} from 'vue'
import {phoneAlbumAllInfoService} from "@/api/phoneAlbum.js";
import {selectImagesByImagesType} from "@/api/Images.js";

// 所有相册列表的引用
let imageAlbumAllList = ref([])

// 加载状态控制
let loading = ref(false)

// 响应式屏幕宽度
const screenWidth = ref(window.innerWidth)

// 监听窗口大小变化
window.addEventListener('resize', () => {
  screenWidth.value = window.innerWidth
})

// 计算瀑布流列宽度
const calculateColumnWidth = () => {
  const width = screenWidth.value
  if (width >= 1200) return 280
  if (width >= 900) return 250
  if (width >= 600) return 200
  return 150
}

// 图片加载成功处理
const handleImageLoad = (event) => {
  // 图片加载完成后，可以触发瀑布流重新计算布局
  nextTick(() => {
    // 这里可以添加重新布局的逻辑
    console.log('Image loaded successfully')
  })
}

// 图片加载失败处理
const handleImageError = (event) => {
  console.error('Image failed to load:', event.target.src)
  // 可以设置默认图片
  // event.target.src = '/default-image.jpg'
}

/**
 * 获取全部相册信息。
 * 该函数会首先请求所有相册的信息，然后为每个相册请求其图片列表，
 * 最后更新加载状态。
 */
const getImageAlbumAllInfo = async () => {
  try {
    const res = await phoneAlbumAllInfoService()
    imageAlbumAllList.value = res.data

    // 为每个相册获取图片列表
    for (let i = 0; i < imageAlbumAllList.value.length; i++) {
      imageAlbumAllList.value[i].List = await getImageAlbumList(imageAlbumAllList.value[i].id)
    }

    // 等待DOM更新完成再设置加载状态
    await nextTick()
    loading.value = true
  } catch (error) {
    console.error('Failed to load album info:', error)
    loading.value = true
  }
}

/**
 * 根据相册ID获取图片列表。
 * @param {String} type - 相册ID
 * @returns {Promise<Array>} 返回图片信息数组的Promise。
 */
const getImageAlbumList = async (type) => {
  try {
    const res = await selectImagesByImagesType(type)
    return res.data
  } catch (error) {
    console.error('Failed to load images for album:', type, error)
    return []
  }
}

// 组件挂载后获取数据
onMounted(() => {
  getImageAlbumAllInfo()
})

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
  transition: all 0.3s cubic-bezier(0.4, 0.0, 0.2, 1);
  border-radius: 8px;
  background: #fff;
  /* 移除 margin 和 padding，让瀑布流组件来控制间距 */
}

.card img {
  width: 100%;
  height: auto;
  display: block;
  transition: transform 0.3s cubic-bezier(0.4, 0.0, 0.2, 1);
  /* 确保图片完全加载后再显示 */
  opacity: 0;
  animation: fadeIn 0.3s ease-in-out forwards;
}

.card:hover {
  /* 减小缩放比例，避免影响布局 */
  transform: scale(1.05);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
  z-index: 10;
}

.card:hover img {
  /* 移除图片的额外缩放，避免双重缩放效果 */
  transform: none;
}

/* 图片淡入动画 */
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

/* 瀑布流容器样式优化 */
:deep(.vue-waterfall) {
  width: 100%;
  margin: 0 auto;
}

/* 确保瀑布流列项目的正确定位 */
:deep(.vue-waterfall-item) {
  position: absolute !important;
  transition: all 0.3s ease;
}


</style>