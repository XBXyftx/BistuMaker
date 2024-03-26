<template>

  <div class="content-container" :style="{display: isMobile ? 'inline' : 'flex'}">

    <div class="text-content" :style="{width: isMobile ? '95%' : '60%' }">
      <p style="font-size: 2.9rem;margin-top: 0px;margin-bottom: 0px;font-family: AlimamaDaoLiTi,serif;">
        关于<br>创客空间<br>社团活动<br>
      </p>
      <p style="font-weight: 500;font-size: 1rem ">
        平时社团活动也不仅仅有社课，我们还会举办讲座、夜烤、WorkShop、社游、期末聚…等超多活动给社员参加！当然，我们不只和葳格高中建立了长期的任教合作，身为志工性社团，我们还会和相关机构及国小一同举办各式各样的志工营队，落实将在社内所学回馈社会。
        <br>
        <br>
        iOS Club致力于软件开发，以及推广程序教育，但我们同时也期望能够给社员舒适的相处环境与丰富的交流活动，欢迎所有志在开发与充满教育热诚的人一起加入。
      </p>
    </div>
    <div class="image-content">
      <!-- 右侧图片 -->
      <img :src="hezhao" alt="右侧图片描述"
           style="height: auto;"
           :style="{width: isMobile ? '100%' : '650px' }"
      >
    </div>
  </div>


  <div style="width: auto;height: 150px;background-color: rgba(177,196,210);">
    <p style="text-align:center;padding-top: 40px">
      往下看更多
    </p>

    <div class="animate__animated  animate__infinite" style="text-align:center">
      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true" role="img"
           class="iconify" width="1em" height="1em" viewBox="0 0 24 24" style="color: rgb(128, 152, 181);">
        <g transform="rotate(180 12 12)">
          <path fill="currentColor"
                d="M10.285 3.858c.777-1.294 2.653-1.294 3.43 0l8.468 14.113c.8 1.333-.16 3.029-1.715 3.029H3.532c-1.554 0-2.514-1.696-1.715-3.029z"></path>
        </g>
      </svg>
    </div>

    <el-pagination layout="prev, pager, next"
                   :total="activitiesNums.length"
                   @current-change="handleCurrentChange"
    />

  </div>


  <div class="shell" ref="shell">
    <div class="header">
      <h2 class="title">社团活动</h2>
    </div>
    <div class="timeline">
      <div
          class="item"
          v-for="(activity, index) in activities"
          :key="index"
          :data-text="activity.title">
        <div class="content"
             :class="{
         active: index % 2 === 0 && isMobile === false,
         active1: isMobile !== false}"
             style="margin-top: 100px">
          <img :src="activity.coverImage" alt="" class="img"
               :class="{
         active: index % 2 === 0 && isMobile === false,
         active1: isMobile !== false}">
          <div class="btn-article" @click="getArticle(activity.id)">查看详细</div>
          <h2 class="content-title">{{ activity.createTime }}</h2>
          <p class="content-desc">{{ activity.synopsis }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import {ref, getCurrentInstance} from "vue";

const {proxy} = getCurrentInstance()
import {articleTypeQueryService} from '@/api/article.js';

const hezhao = new URL('@/assets/合照.jpg', import.meta.url)
const activities = ref([]);
const activitiesNums = ref([]);
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});
const getArticles = async () => {
  let res = await articleTypeQueryService("活动");
  for (let i = 0; i < res.data.length; i++) {
    res.data[i].coverImage = proxy.$baseURL + res.data[i].coverImage
    let ary = res.data[i].createTime.split(/(?: |-|:)/g);
    res.data[i].createTime = ary[0] + '年' + ary[1] + '月' + ary[2] + '日';
    res.data[i].createTime = res.data[i].createTime.replace(/T\S*/g, '');
    res.data[i].createTime = res.data[i].createTime + '日';
  }
  activitiesNums.value = res.data;
  activities.value = activitiesNums.value.slice(0, 10);
  console.log(activities.value.length)
};

onMounted(async () => {
  await getArticles();
  setupTimeline();
});

let queryInfo = ref({
  query: '', // 查询参数
  pageNum: 1, // 当前页码
  pageSize: 10 // 每页显示条数
});
const  handleCurrentChange = (i)=>{

activities.value=null
  activities.value = activitiesNums.value.slice((i-1)*10,i*10)
  console.log(activities.value)
}




const setupTimeline = () => {
  const shell = document.querySelector(".shell");
  const items = shell.querySelectorAll(".item");
  console.log(shell);
  console.log(items);
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        items.forEach(item => item.classList.remove("item--active"));
        entry.target.classList.add("item--active");
        shell.style.backgroundImage = `url(${entry.target.querySelector(".img").getAttribute("src")})`;
      }
    });
  }, {threshold: 0.5});
  items.forEach(item => observer.observe(item));
};

import { useRoute, useRouter } from 'vue-router'
const router = useRouter()
const getArticle=(id)=>{
  if (id) {
    router.push({path: `/article/${id}`})
  }
}



</script>


<style scoped>
.btn-article {
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center; /* 使内容垂直和水平居中 */
  background-color: rgba(0, 0, 0, 0.5);
  transition: all 0.3s ease; /* 将transition应用到所有属性，包括opacity */
  z-index: 1;
  border-radius: 5px;
  font-size: 20px;
  width: 100px;
  height: 40px;
  text-decoration: none; /* 去除默认的下划线，使其看起来更像按钮 */
  font-weight: bold; /* 加粗字体以增强按钮感 */
  cursor: pointer; /* 更改鼠标指针，提示用户该元素可点击 */
  user-select: none; /* 防止文本被选中 */
  outline: none; /* 移除聚焦时的轮廓线 */
  margin: 10px;
}


.content-container {
  display: flex; //align-items: center; /* 保持垂直居中 */ //justify-content: center; /* 添加这一行以实现水平居中 */ //margin: 0 auto; /* 如果父元素不是绝对定位，则可使用这种方式进行水平居中 */
  margin: 100px;
}

.text-content {
  box-sizing: border-box;
//align-items: center; /* 保持垂直居中 */ //justify-content: center; /* 添加这一行以实现水平居中 */ //width: 90%; /* 或者给一个固定宽度，并且需要设置其父元素的定位为相对定位 */ //max-width: 90%; /* 示例：最大宽度为800px */ margin: 0 auto 0 10px; //left: 100px; right: 0; //margin:100px auto;
}

.image-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

}

</style>
<style scoped>
.active {
  margin-top: 222px;
  right: 100%;
}

.active1 {
  width: 55%;
  right: 40px;
}

.shell {
  background-size:100%;
  width: 100%;
  position: relative;
//padding: 80px 0; transition: 0.3s ease 0s; background-attachment: fixed;

}

.shell:before {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(99, 99, 99, 0.8);
  content: "";
}

.header {
  width: 100%;
  text-align: center;
  margin-bottom: 80px;
  position: relative;
}

.title {
  color: #fff;
  font-size: 46px;
  font-weight: normal;
  margin: 0;
}

.timeline {
  display: flex;
  margin: 0 auto;
  flex-wrap: wrap;
  flex-direction: column;
  max-width: 700px;
  position: relative;
}

.content-title {
  font-weight: normal;
  font-size: 25px;
  margin: -30px 0 0 0;
  transition: 0.4s;
  padding: 0 10px;
  box-sizing: border-box;
  color: #fff;
}

.content-desc {
  margin: 0;
  font-size: 15px;
  box-sizing: border-box;
  color: rgba(255, 255, 255, 0.7);
  line-height: 25px;
}

.timeline:before {
  position: absolute;
  left: 50%;
  width: 2px;
  height: 100%;
  margin-left: -1px;
  content: "";
  background: rgba(255, 255, 255, 0.07);
}

.item {
  padding: 40px 0;
  opacity: 0.3;
  filter: blur(2px);
  transition: 0.5s;
  box-sizing: border-box;
  width: calc(50% - 40px);
  display: flex;
  position: relative;
  transform: translateY(-80px);
  margin-top: 30px;

}

.item:before {
  content: attr(data-text);
  letter-spacing: 3px;
  width: 100%;
  position: absolute;
  color: rgba(255, 255, 255, 0.5);
  font-size: 13px;
  border-left: 2px solid rgba(255, 255, 255, 0.5);
  top: 70%;
  margin-top: -5px;
  padding-left: 15px;
  opacity: 0;
  right: calc(-100% - 56px);
  font: 900 20px '';
  letter-spacing: 5px;
}

.item:nth-child(even) {
  align-self: flex-end;
}

.item:nth-child(even):before {
  right: auto;
  text-align: right;
  left: calc(-100% - 56px);
  padding-left: 0;
  border-left: none;
  border-right: 2px solid rgba(255, 255, 255, 0.5);
  padding-right: 15px;
}

.item--active {
  opacity: 1;
  transform: translateY(0);
  filter: blur(0px);
}

.item--active:before {
  top: 50%;
  transition: 0.3s all 0.2s;
  opacity: 1;
}

.item--active .content-title {
  margin: 10px 0 20px 0;
}

.content {
//display: flex; position: relative; justify-content: center; /* 添加这一行以实现水平居中 */
  align-items: center; /* 如果需要垂直居中，可以添加这一行 */
}

.img {
  display: block;
  margin: auto; /* 将原来的 'margin: 0 auto;' 修改为 'margin: auto;' */
  width: 200%;
  height: auto;
  box-shadow: 0 10px 15px rgba(0, 0, 0, 0.4);
}

.subtitle {
  color: rgba(255, 255, 255, 0.5);
  font-size: 16px;
  letter-spacing: 5px;
  margin: 10px 0 0 0;
  font-weight: normal;
}


@media only screen and (max-width: 767px) {
  .item {
    align-self: baseline !important;
    width: 100%;
    padding: 0 30px 150px 80px;
  }

  .item:before {
    left: 10px !important;
    padding: 0 !important;
    top: 50px;
    text-align: center !important;
  //width: 60px; border: none !important;
  }

  .item:last-child {
    padding-bottom: 40px;
  }
}

@media only screen and (max-width: 767px) {
  .timeline:before {
    left: 40px;
  }
}

</style>
<style>
.el-pagination .number,
.el-pagination button:disabled,
.el-pagination .btn-next {
  background: transparent;
}
.el-pagination .btn-first {
  background: transparent;
}
.el-pagination .btn-prev {
  background: transparent;
}
.el-pagination .btn-next {
  background: transparent;
}
.el-pagination {
}
//这里可能有坑这个100px,如果不设置手机端就回超出空白部分,有些难绷
/* 当屏幕宽度小于等于某个阈值时（例如768px，适合移动设备） */
@media screen and (max-width: 768px) {
  .el-pagination {
    text-align: center;
    position: relative;

    padding: 10px 1000px 0px 0px;
    background-color: red;
  }
}
@media screen and (min-width: 769px) {
  .el-pagination {

    text-align: center;
    position: relative;
    padding: 0px 10px 10px 85px;
  //padding-right: 10px;
  }
}
</style>