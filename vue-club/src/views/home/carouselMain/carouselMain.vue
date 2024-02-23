<script setup>
import {getCurrentInstance, ref, watch} from "vue";
import {getPhoneAlbumAllImage} from "@/api/Images.js";
// onMounted(() => {
//   setTimeout(()=>{
//     carousel.value.setActiveItem(0)
//   },500)
// })
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
// console.log("12312"+Height.value)

const {proxy} = getCurrentInstance()
const baseURL = proxy.$baseURL

const imagesList=ref({})
const getImages = async () => {
  let res = await getPhoneAlbumAllImage()
  imagesList.value = res.data
  // console.log(res.data)
  // console.log(imagesList.value)
}
getImages()

import animate from 'animate.css'




const scroll_ = () => {

  window.scrollTo({
    top: innerHeight||document.documentElement.clientHeight||document.body.clientHeight,
    behavior: "smooth"
  });
  // scrollTo(0,innerHeight||document.documentElement.clientHeight||document.body.clientHeight)
}
</script>

<template>



  <div class="carousel">
    <el-carousel       height="100vh">
      <el-carousel-item v-for="item in 1" :key="item.id">
        <img :src="`https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/image3.png`" alt=""  class="carousel-image" >
        <div class="carousel-text">
          <div class="animate__animated animate__bounceInUp  animate__slow  carousel-text-title">信息科大创客空间</div>
          <div class="animate__animated animate__bounceInUp animate__delay animate__slow  carousel-text-title">Study hard and make progress every day</div>
          <div class="click animate__animated animate__bounceInUp animate__delay-1s animate__slow">
            <a  @click="scroll_(123)">
            开始探险>
            </a>
          </div>
          <div class="carousel-text-content">  </div>
        </div>

      </el-carousel-item>
    </el-carousel>
  </div>

</template>

<style scoped>
.carousel {
  background-color: #f5f5f5;
  position: relative;
  overflow: hidden;
  background-size: cover;
  width: 100%;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  filter: blur(4px);
}

.carousel-text {
  position: absolute;
  top: 30%;
  left: 6%;
  //text-align: center;
  color: #fff;
  font-size: 50px;

}

.carousel-text-title {
  font-size: 3rem;
  font-weight: 600;

}

.click{
  text-align: center;
  margin-top: 10px;
  font-size: 20px;
  color: #fff;
  border: 1px solid #fff;
  padding: 10px;
  border-radius: 10px;
  background-color: rgba(185, 166, 166, 0.9);
  width: 100px;

  a{
    color: #fff;

  }

}



.el-carousel__indicators--horizontal {
  position: absolute;
  left: auto;
  right: 10px;
  bottom: 10px;
  text-align: right;

  .el-carousel__indicator--horizontal button {
    width: 8px;
    height: 8px;
    background: #ffffff;
    border-radius: 50%;
    opacity: 0.5;
  }

  .el-carousel__indicator--horizontal.is-active button {
    width: 24px;
    height: 8px;
    background: #ffffff;
    opacity: 0.5;
    border-radius: 10px;
  }
}

</style>