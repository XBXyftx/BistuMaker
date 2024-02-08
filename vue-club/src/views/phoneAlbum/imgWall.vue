<script setup>
import { LazyImg, Waterfall } from 'vue-waterfall-plugin-next'
import 'vue-waterfall-plugin-next/dist/style.css'
import {getCurrentInstance, ref, toRaw} from 'vue'
import {phoneAlbumAllInfoService} from "@/api/phoneAlbum.js";
import {selectImagesByImagesType} from "@/api/Images.js";
// let list =[
//     {
//       url: 'https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/image1.png'
//     },
//   {
//     url: 'https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/image2.jpeg'
//   },
//   {
//     url: 'https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/image3.png'
//   },
//
// ]

let imageAlbumAllList = ref([])
const getImageAlbumAllInfo = async () => {
  const res = await phoneAlbumAllInfoService()
  console.log(res)
  imageAlbumAllList.value=res.data
  // console.log(imageAlbumAllList.value)
  for (let i = 0; i < imageAlbumAllList.value.length; i++) {
    // console.log(imageAlbumAllList.value[i].id)
    imageAlbumAllList.value[i].List=await getImageAlbumList(imageAlbumAllList.value[i].id)
  }
  // PhoneAlbumNameList.value=res.data
  console.log(toRaw(imageAlbumAllList.value))

}
getImageAlbumAllInfo()
const getImageAlbumList = async (type) => {
  const res = await selectImagesByImagesType(type)
  // console.log(res)
  return res.data
}
getImageAlbumList()

// console.log(imageAlbumAllList.value)
const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL
</script>

<template>
  <div v-for="item1 in imageAlbumAllList">
    <h1 class="">{{item1.phoneAlbumName}}</h1>
    <Waterfall :list="item1.List" :gutter="40"  :width="550" :crossOrigin="false">
      <template #item="{ item, url, index }">
        <div class="card" >
          <LazyImg :url="`${baseURL+item.imagesUrl}`" :title="item.imageName"/>

        </div>
      </template>
    </Waterfall>
  </div>

</template>

<style scoped>
h1 {
  text-align: center;
  margin: 0; /* 或者可以设置一个舒适的顶部和底部间距，例如：margin: 20px 0; */
  font-weight: 600;
  font-size: 36px; /* 根据设计需求调整字体大小 */
  line-height: 1.2; /* 确保行高舒适 */
  color: #000000; /* 改变字体颜色以提供对比，假设背景为深色 */
  //text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 增加文本阴影的深度和模糊度 */
  text-transform: uppercase;
  letter-spacing: 1px;

  /* 可选，添加内边距以增加视觉空间 */
  padding: 20px 0;

  /* 可选，添加背景或者边框以增强视觉效果 */
  //background-color: #333; /* 或者使用渐变、图片等 */
  //border-bottom: 2px solid #ff4500; /* 或者使用其他颜色来强调标题 */

  /* 可选，使用动画或过渡效果 */
  transition: all 0.3s ease-in-out;
}

/* 如果需要响应式设计，可以根据屏幕尺寸调整样式 */
@media (max-width: 768px) {
  h1 {
    font-size: 28px;
    text-shadow: none;
  }
}
</style>