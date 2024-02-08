<template>
  <Suspense>
  <div class="main">
    <div class="content-main" :style="{ width: isMobile ? '100%' : '75%',marginLeft: isMobile ? '0px' : '200px'}">
    <div class="left" v-if="!isMobile">
      <div
          :key="anchor"
          v-for="anchor in titles"
          :style="{ padding: `10px 0 10px ${anchor.indent * 20}px` }"
          @click="handleAnchorClick(anchor)"
      >
        <a style="cursor: pointer">{{ anchor.title }}</a>
      </div>
    </div>
      <div class="content" :style=" { marginLeft: !isMobile ? '300px' : '0px' }">
        <v-md-preview class="preview"
                      :text="text"
                      ref="preview"></v-md-preview>
        <el-icon><Comment /></el-icon>
        <ArticleComment :articleId="id"  ></ArticleComment>
      </div>
    </div>
  </div>
  </Suspense>
  <!--  <v-md-editor v-model="text" height="400px"></v-md-editor>-->
</template>

<style scoped>
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

  border-radius: 10px;
margin: 20px 0; //box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.content-main {
//padding-left: 150px;
//border-radius: 10px; //box-shadow: 0 0 10px rgba()

}

.content {
  //margin-left: 300px;  /* 值为固定侧的宽度 */
  //height: 200px;
  //background-color: pink;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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


onMounted(async () => {
  // console.log(text.value)
  await articleInfoService(id).then(res=>{
    text.value = res.data.content
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
