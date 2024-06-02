<template>
  <div class="post" v-for="item in blogList" :style="isMobile? 'width: 90%;':'width: 50%;' " v-if="loading">
    <header class="entry-header">
      <p class="entry-title">
        <a>{{item.title}}</a>
      </p>
      <div class="entry-meta">
        <span class="post-category" v-if="item.label">标签:{{item.label}}</span>

        <span class="post-date">
          <time class="entry-date">时间:{{item.createTime}}</time></span>
        <br>
        <span class="post-author">作者:{{item.author}}</span>
<!--        <span class="comments-link"><a href="#">4 评论</a></span>-->
        <span class="views-count">阅读量:{{item.visits}}</span>
      </div>
    </header>

    <div class="entry-content clearfix">
      <p> </p>
      <div class="read-more cl-effect-14">
        <a class="more-link" @click="getArticle(item.id)">继续阅读 <span class="meta-nav">→</span></a>
      </div>
    </div>
  </div>

<!--  骨架屏-->
  <div v-else>
    <div class='screen-root'  v-if="loading===false" v-for="i in 5">
      <ul>
        <li/>
        <li/>
        <li/>
      </ul>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {blogQueryService} from "@/api/article.js";
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

const blogList = ref([]);
const getBlogList =async () => {
  let result = await blogQueryService();
  blogList.value=result.data;

  loading.value=true;
}
getBlogList()

import { useRouter } from 'vue-router'
const router = useRouter()
const getArticle=(id)=>{
  if (id) {
    router.push({path: `/article/${id}`})
  }
}

//加载
const loading = ref(false)

</script>



<style scoped>
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


.post {
  background: #fff;
  padding: 5px 5px 0;

  height: auto;

  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  overflow: hidden;
  transition: all 0.3s ease;
  position: relative;
  text-align:center;
  margin: auto;
  //display: flex;
  //flex-direction: column;
  margin-top: 20px;
  margin-bottom: 20px;

}

.entry-title {
  text-align: center;
  font-size: 1.9em;
  margin-bottom: 20px;
  line-height: 1.6;
  padding: 10px 20px 0;
  color: #0a0a0a;
}

.entry-meta {
  text-align: center;
  color: #DDDDDD;
  font-size: 13px;
  margin-bottom: 30px;
}

.entry-content {
  font-size: 18px;
  line-height: 1.9;
  font-weight: 300;
  color: #000;
}

.post-category::after,
.post-date::after,
.post-author::after,
.comments-link::after {
  content: ' ·';
  color: #000;
}

.read-more {
  font-family: 'Ubuntu', sans-serif;
  font-weight: 400;
  word-spacing: 1px;
  letter-spacing: 0.01em;
  text-align: center;
  margin-top: 20px;
}

.cl-effect-14 a {
  padding: 0 20px;
  height: 45px;
  line-height: 45px;
  position: relative;
  display: inline-block;
  margin: 15px 25px;
  letter-spacing: 1px;
  font-weight: 400;
  text-shadow: 0 0 1px rgba(255, 255, 255, 0.3);
}

.cl-effect-14 a::before,
.cl-effect-14 a::after {
  position: absolute;
  width: 45px;
  height: 1px;
  background: #C3C3C3;
  content: '';
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  transition: all 0.3s;
  pointer-events: none;
}

.cl-effect-14 a::before {
  top: 0;
  left: 0;
  -webkit-transform: rotate(90deg);
  -moz-transform: rotate(90deg);
  transform: rotate(90deg);
  -webkit-transform-origin: 0 0;
  -moz-transform-origin: 0 0;
  transform-origin: 0 0;
}

.cl-effect-14 a::after {
  right: 0;
  bottom: 0;
  -webkit-transform: rotate(90deg);
  -moz-transform: rotate(90deg);
  transform: rotate(90deg);
  -webkit-transform-origin: 100% 0;
  -moz-transform-origin: 100% 0;
  transform-origin: 100% 0;
}

.cl-effect-14 a:hover::before,
.cl-effect-14 a:hover::after,
.cl-effect-14 a:focus::before,
.cl-effect-14 a:focus::after {
  background: #000;
}

.cl-effect-14 a:hover::before,
.cl-effect-14 a:focus::before {
  left: 50%;
  -webkit-transform: rotate(0deg) translateX(-50%);
  -moz-transform: rotate(0deg) translateX(-50%);
  transform: rotate(0deg) translateX(-50%);
}

.cl-effect-14 a:hover::after,
.cl-effect-14 a:focus::after {
  right: 50%;
  -webkit-transform: rotate(0deg) translateX(50%);
  -moz-transform: rotate(0deg) translateX(50%);
  transform: rotate(0deg) translateX(50%);
}


*{
  color:#0a0a0a;
}

span{
  font-size: 1.1rem;
}
</style>