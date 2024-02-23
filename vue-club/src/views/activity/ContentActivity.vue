<template>

  <div style="width: auto;height: 150px;background-color: rgba(177,196,210);">
    <p style="text-align:center;padding-top: 40px">
      往下看更多
    </p>

    <div class="animate__animated  animate__infinite" style="text-align:center">
      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true" role="img" class="iconify" width="1em" height="1em" viewBox="0 0 24 24" style="color: rgb(128, 152, 181);"><g transform="rotate(180 12 12)"><path fill="currentColor" d="M10.285 3.858c.777-1.294 2.653-1.294 3.43 0l8.468 14.113c.8 1.333-.16 3.029-1.715 3.029H3.532c-1.554 0-2.514-1.696-1.715-3.029z"></path></g></svg>
    </div>
  </div>

  <header>
    <h1>社团活动</h1>
  </header>

<!--  <ul class="timeline">-->
<!--    &lt;!&ndash; Item 1 &ndash;&gt;-->
<!--    <li v-for="(item,index) in items" :key="item.id">-->
<!--      <div :class="{'direction-l': index % 2 == 0, 'direction-r': index % 2 != 0}" >-->
<!--        <div class="flag-wrapper">-->
<!--&lt;!&ndash;          <span class="hexa"></span>&ndash;&gt;-->
<!--          <span class="flag">{{item.title}}</span>-->
<!--          <span class="time-wrapper">-->
<!--            <br>-->
<!--            <span class="time">{{item.createTime}}-->
<!--            </span>-->
<!--          </span>-->
<!--        </div>-->
<!--        <div class="desc">-->
<!--          {{item.synopsis}}-->
<!--          <img :src="item.coverImage" alt="" style="height: auto;width: 400px; ">-->
<!--          <a href="#">-->
<!--            <div class="mask">-->
<!--              <h3 @click="getArticle(item.id)">详细文章</h3>-->

<!--            </div>-->
<!--          </a>-->
<!--        </div>-->
<!--      </div>-->
<!--    </li>-->

<!--  </ul>-->

  <section id="cd-timeline" class="cd-container">
    <div class="cd-timeline-block" v-for="(item,index) in items" :key="item.id">
      <div class="cd-timeline-img cd-movie">
      </div> <!-- cd-timeline-img -->

      <div class="cd-timeline-content">
        <h2>{{item.title}}</h2>
        <p>{{item.synopsis}}</p>

        <img :src="item.coverImage" alt="" style="height: auto; " :style="{width:isMobile ? '70vw' : '22vw'}">
                  <a>
                    <div class="mask">
<!--                      <button @click="getArticle(item.id)">详细文章</button>-->

                      <el-button plain  @click="getArticle(item.id)">详细文章</el-button>
                    </div>
                  </a>
        <span class="cd-date">{{item.createTime}}</span>
      </div> <!-- cd-timeline-content -->
    </div> <!-- cd-timeline-block -->



  </section> <!-- cd-timeline -->


</template>

<script setup>



import {nextTick, reactive, ref, getCurrentInstance} from "vue";
const {proxy} = getCurrentInstance()
console.log(proxy.$baseURL)


//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});


import {articleTypeQueryService} from '@/api/article.js'

const items = ref([])
const getArticles = async () => {
  let res = await articleTypeQueryService("活动")
  for (let i = 0; i < res.data.length; i++) {
    res.data[i].coverImage = proxy.$baseURL + res.data[i].coverImage
    let ary = res.data[i].createTime.split(/(?: |-|:)/g)
    // console.log(res.data[i].coverImage)
    res.data[i].createTime = ary[0] +'年' +ary[1]+'月'+ary[2]+'日';
    res.data[i].createTime = res.data[i].createTime.replace(/T\S*/g, '');
    res.data[i].createTime = res.data[i].createTime + '日'
  }
  console.log(res.data)
  items.value = res.data

}
getArticles()
import { useRoute, useRouter } from 'vue-router'
const router = useRouter()
const getArticle=(id)=>{
  if (id) {
    router.push({path: `/article/${id}`})
  }
}


</script>

<style lang='less' scoped>
//@import url(https://fonts.googleapis.com/css?family=Raleway:400,900);

html, body {
  background-color: #222C32;
  height: 100%;
  font-family: 'Open Sans', sans-serif;
  box-sizing: border-box;
}

.cd-container {
  width: 90%;
  max-width: 1080px;
  margin: 0 auto;
  background: #f6f6f6;
  padding: 0 10%;
  border-radius: 2px;
}
.cd-container::after {
  content: '';
  display: table;
  clear: both;
}

/* --------------------------------

Main components

-------------------------------- */


#cd-timeline {
  position: relative;
  padding: 2em 0;
  margin-top: 2em;
  margin-bottom: 2em;
}
#cd-timeline::before {
  content: '';
  position: absolute;
  top: 0;
  left: 25px;
  height: 100%;
  width: 4px;
  background: #7E57C2;
}
@media only screen and (min-width: 1170px) {
  #cd-timeline {
    margin-top: 3em;
    margin-bottom: 3em;
  }
  #cd-timeline::before {
    left: 50%;
    margin-left: -2px;
  }
}

.cd-timeline-block {
  position: relative;
  margin: 2em 0;
}
.cd-timeline-block:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-block:first-child {
  margin-top: 0;
}
.cd-timeline-block:last-child {
  margin-bottom: 0;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-block {
    margin: 4em 0;
  }
  .cd-timeline-block:first-child {
    margin-top: 0;
  }
  .cd-timeline-block:last-child {
    margin-bottom: 0;
  }
}

.cd-timeline-img {
  position: absolute;
  top: 8px;
  left: 12px;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  box-shadow: 0 0 0 4px #7E57C2, inset 0 2px 0 rgba(0, 0, 0, 0.08), 0 3px 0 4px rgba(0, 0, 0, 0.05);
}
.cd-timeline-img {
  background: #673AB7;
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-img {
    width: 30px;
    height: 30px;
    left: 50%;
    margin-left: -15px;
    margin-top: 15px;
    /* Force Hardware Acceleration in WebKit */
    -webkit-transform: translateZ(0);
    -webkit-backface-visibility: hidden;
  }
}

.cd-timeline-content {
  position: relative;
  margin-left: 60px;
  margin-right: 30px;
  //background: #333C42;
  border-radius: 2px;
  padding: 1em;
  .timeline-content-info {
    //background: #2B343A;
    padding: 5px 10px;
    //color: rgba(255,255,255,0.7);
    font-size: 12px;
    box-shadow:  inset 0 2px 0 rgba(0, 0, 0, 0.08);
    border-radius: 2px;
    i {
      margin-right: 5px;
    }
    .timeline-content-info-title, .timeline-content-info-date {
      width: calc(50% - 2px);
      display: inline-block;
    }
    @media (max-width: 500px) {
      .timeline-content-info-title, .timeline-content-info-date {
        display: block;
        width:100%;
      }
    }
  }
  .content-skills {
    font-size: 12px;
    padding:0;
    margin-bottom: 0;
    display:flex;
    flex-wrap: wrap;
    justify-content: center;
    li {
      background: #40484D;
      border-radius: 2px;
      display: inline-block;
      padding: 2px 10px;
      color: rgba(255,255,255,0.7);
      margin: 3px 2px;
      text-align: center;
      flex-grow: 1;
    }
  }
}
.cd-timeline-content:after {
  content: "";
  display: table;
  clear: both;
}
.cd-timeline-content h2 {
  color: rgba(30, 28, 28, 0.9);
  margin-top:0;
  margin-bottom: 5px;
}
.cd-timeline-content p, .cd-timeline-content .cd-date {
  color: rgba(114, 63, 63, 0.7);
  font-size: 13px;
  font-size: 0.8125rem;
}
.cd-timeline-content .cd-date {
  display: inline-block;
}
.cd-timeline-content p {
  margin: 1em 0;
  line-height: 1.6;
}

.cd-timeline-content::before {
  content: '';
  position: absolute;
  top: 16px;
  right: 100%;
  height: 0;
  width: 0;
  border: 7px solid transparent;
  border-right: 7px solid #333C42;
}

@media only screen and (min-width: 768px) {
  .cd-timeline-content h2 {
    font-size: 20px;
    font-size: 1.25rem;
  }
  .cd-timeline-content p {
    font-size: 16px;
    font-size: 1rem;
  }
  .cd-timeline-content .cd-read-more, .cd-timeline-content .cd-date {
    font-size: 14px;
    font-size: 0.875rem;
  }
}
@media only screen and (min-width: 1170px) {
  .cd-timeline-content {
    color: #000000;
    margin-left: 0;
    padding: 1.6em;
    width: 36%;
    margin: 0 5%
  }
  .cd-timeline-content::before {
    top: 24px;
    left: 100%;
    border-color: transparent;
    border-left-color: #333C42;
  }
  .cd-timeline-content .cd-date {
    position: absolute;
    width: 100%;
    left: 122%;
    top: 25px;
    font-size: 16px;
    font-size: 1rem;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content {
    float: right;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content::before {
    top: 24px;
    left: auto;
    right: 100%;
    border-color: transparent;
    border-right-color: #333C42;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-read-more {
    float: right;
  }
  .cd-timeline-block:nth-child(even) .cd-timeline-content .cd-date {
    //margin: 55px;
    left: auto;
    right: 122%;
    text-align: right;
  }
}


.iconify{
  @keyframes myfirst {
    0% {
      transform: translate(0px, 0px);
    }
    50% {
      transform: translate(0px, -10px);
    }
    100% {
      transform: translate(0px, 0px);
    }
  }
  animation: myfirst 2s infinite;

}


body{
  font-family: 'Raleway', sans-serif;
  color: #333;
}

@import url(https://fonts.googleapis.com/css?family=Raleway:400,900);

body{
  font-family: 'Raleway', sans-serif;
  color: #000000;
}

header h1{
  text-align: center;
  font-weight: bold;
  margin-top: 0;
}

header p{
  text-align: center;
  margin-bottom: 0;
}


.mask{
  //position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  //background: rgba(0,0,0,0.5);
  z-index: 100;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 1;

}
.button::after{
  /*其他样式*/
  opacity: 0;
  box-shadow: 0 0 0 6px var(--primary-color);
  transition: .3s;
}
/*点击*/
.button:active::after{
  box-shadow: none;
  opacity: 0.4;
  transition: 0s; /*取消过渡*/
}
.button{
  padding: 5px 16px;
  color: #000000d9;
  border: 1px solid #d9d9d9;
  background-color: transparent;
  border-radius: 2px;
  line-height: 1.4;
  box-shadow: 0 2px #00000004;
  cursor: pointer;
  transition: .3s;
}
.button:hover{
  color: var(--primary-color);
  border-color: currentColor;
}

</style>
