<template>
  <Suspense>



  <div class="main"  >

    <div class="content-main" :style="{ width: isMobile ? '100%' : '100%',marginLeft: isMobile ? '0px' : '00px'}">
<!--    <div class="left" v-if="!isMobile">-->
<!--      <div-->
<!--          :key="anchor"-->
<!--          v-for="anchor in titles"-->
<!--          :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"-->
<!--          @click="handleAnchorClick(anchor)"-->
<!--      >-->
<!--        <a style="cursor: pointer">{{ anchor.title }}</a>-->
<!--      </div>-->
<!--    </div>-->

      <div class="content"  :style="{width:isMobile?'100%' : '60%'}">
        <!--      骨架屏-->
        <div class='screen-root' v-if="loading===false">
          <ul>
            <li/>
            <li/>
            <li/>
          </ul>
        </div>


        <div class="title">{{title}}</div>
        <div class="another">{{author}}</div>
        <div class="date">{{date}}</div>
        <v-md-preview class="preview"
                      :text="text"
                      ref="preview"
                    v-if="loading===true"
        ></v-md-preview>
        <el-icon><Comment /></el-icon>
        <ArticleComment :articleId="id"  ></ArticleComment>
      </div>
    </div>
  </div>
  </Suspense>
  <!--  <v-md-editor v-model="text" height="400px"></v-md-editor>-->
</template>


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

li:first-child {
  width: 38%;
}

li:last-child {
  width: 61%;
}

@keyframes skeleton-loading {
  0% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0 50%;
  }
}



.title{
  margin-top: 50px;
  font-size: 26px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
}
.another{
  font-size: 14px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
}
.date{
  font-size: 14px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
  margin-top: 10px;

}

.left{
  float: left;    /* 固定侧浮动 */
  margin-top: 100px;
  margin-left: 10px;
  background-color: #f6f6f6;
  //background-color: skyblue;
  z-index: 99999999;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  //position: fixed;


}
.main {

  width: 100%;
  height: 100%;
  overflow:hidden;
  border-radius: 10px;

}

.content-main {
  //width: 100px;
  margin: 0 auto;

}

.content {
  //margin-left: 300px;  /* 值为固定侧的宽度 */
  //height: 200px;
  //background-color: pink;
  //padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 50%;
  margin: 0 auto;
}

.anchor {
  padding: 10px 0 10px 20px;
  cursor: pointer;
  background-color: #f0f0f0;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  position: fixed;
}

.preview {
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}


</style>

<style>
.github-markdown-body{
  img{
    display: block;
    text-align: center;
    border-radius: 2px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin: 0 auto;
    padding-top: 20px;
  }
  padding: 5px;
}
p{
  font-size: 20px;
}
center{
  font-size:14px;
  color: #6c6c6c;
}
</style>

<script setup>
import {reactive, computed, onMounted, ref} from 'vue';
import ArticleComment from "@/components/Article/comment/ArticleComment.vue";
import {articleInfoService} from '@/api/article.js'
import {Comment} from "@element-plus/icons-vue";
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});
import { useRoute } from 'vue-router';  //1.先在需要跳转的页面引入useRouter
let loading = ref(false);


//关于提md目录的变量
const titles = ref([]);
const preview = ref();


function handleAnchorClick(anchor) {
  const heading = preview.value.$el.querySelector(
      `[data-v-md-line="${anchor.lineIndex}"]`
  );
  if (heading) {
    // preview.value.$el.scrollTop = heading.offsetTop;//能实现锚点的跳转，但是不能平滑滚动
    //存在页面整体向上偏移的bug,对整个div进行设置为固定 position:fixed可解决问题
    heading.scrollIntoView({
      behavior: "smooth",
      block: 'start'
    });

    // v-md-preview组件自带的滚动方法
    // preview.value.scrollToTarget({
    //   target: heading,
    //   scrollContainer: window,
    //   top: 60,
    // });
  }
}
const {query, params} = useRoute();   //2.在跳转页面定义router变量，解构得到指定的query和params传参的参数
const id = params.id
let text = ref('')

// text.value = res.data.content

const title = ref('');
const author= ref('');
const date = ref('');
//将后端返回的时间删除带T的
function toTime(oldDate) {
  const [year, month, day] = oldDate.split('T')[0].split('-');
  return `${year}年${month}月${day}日`;
}

onMounted(async () => {
  // console.log(text.value)
  await articleInfoService(id).then(res=>{
    console.log(res.data)
    text.value = res.data.content
    title.value = res.data.title
    author.value = res.data.author
    date.value = toTime(res.data.createTime)
    loading.value=true
  })

  const anchors = preview.value.$el.querySelectorAll("h1,h2,h3,h4,h5,h6");
  titles.value = Array.from(anchors).filter(
      (title) => !!title.innerText.trim() //过滤文本内容不为空的要素

  );


  if (!titles.value.length) {
    titles.value = [];
    return;
  }

  const hTags = Array.from(
      new Set(titles.value.map((title) => title.tagName))
  ).sort();

  titles.value = titles.value.map((el) => ({
    title: el.innerText,
    lineIndex: el.getAttribute("data-v-md-line"),
    indent: hTags.indexOf(el.tagName),
  }));
});






</script>
