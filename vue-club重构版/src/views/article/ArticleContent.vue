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


        <div class="content-main-content">
          <p class="title">
            {{title}}
          </p>
          <p class="another">
            <svg t="1711289426746" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4260" width="200" height="200"><path d="M512.498688 574.347264c-60.248064 0-116.678656-27.23328-158.885888-76.678144-41.314304-48.397312-64.06144-112.515072-64.06144-180.543488 0-68.028416 22.747136-132.144128 64.06144-180.542464C395.820032 87.131136 452.245504 59.904 512.498688 59.904c60.256256 0 116.67968 27.23328 158.886912 76.673024 41.314304 48.398336 64.067584 112.519168 64.067584 180.547584 0 68.027392-22.754304 132.145152-64.067584 180.543488C629.179392 547.113984 572.754944 574.347264 512.498688 574.347264L512.498688 574.347264zM512.498688 106.155008c-97.429504 0-176.702464 94.63808-176.702464 210.970624 0 116.332544 79.27296 210.971648 176.702464 210.971648 97.435648 0 176.702464-94.639104 176.702464-210.971648C689.201152 200.793088 609.93536 106.155008 512.498688 106.155008L512.498688 106.155008zM512.498688 106.155008" fill="#525252" p-id="4261"></path><path d="M116.424704 964.727808c-0.892928-4.893696-21.103616-120.966144 58.790912-216.8832 66.341888-79.637504 179.893248-120.019968 337.503232-120.019968l0 46.253056c-78.189568 0-144.811008 10.5984-198.008832 31.505408-43.493376 17.09056-78.467072 41.2672-103.95136 71.86944-66.184192 79.44704-49.021952 177.928192-48.838656 178.917376L116.424704 964.727808 116.424704 964.727808zM116.424704 964.727808" fill="#525252" p-id="4262"></path><path d="M908.573696 964.727808l-45.489152-8.358912 22.742016 4.176896-22.75328-4.11648c0.044032-0.252928 4.458496-25.74848 0.367616-60.7744-5.3504-45.863936-22.03648-85.789696-49.589248-118.674432-25.52832-30.473216-60.514304-54.551552-103.977984-71.564288-53.12512-20.79744-119.610368-31.33952-197.597184-31.33952L512.27648 627.82464c157.613056 0 271.164416 40.383488 337.50528 120.019968C929.678336 843.761664 909.473792 959.834112 908.573696 964.727808L908.573696 964.727808zM908.573696 964.727808" fill="#525252" p-id="4263"></path></svg>
            作者:{{author}}
          </p>
          |
          <p class="date">
            <svg t="1711289636113" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5285" width="200" height="200"><path d="M384 256h256V213.333333h85.333333v42.666667h128v554.666667H170.666667V256h128V213.333333h85.333333v42.666667z m384 85.333333H256v384h512V341.333333z m-341.333333 85.333334v85.333333H341.333333v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m-256 128v85.333333H341.333333v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z" fill="#444444" p-id="5286"></path></svg>
            发表于:{{date}}
          </p>
          |

          <p class="zishu">
            <svg t="1711289636113" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5285" width="200" height="200"><path d="M384 256h256V213.333333h85.333333v42.666667h128v554.666667H170.666667V256h128V213.333333h85.333333v42.666667z m384 85.333333H256v384h512V341.333333z m-341.333333 85.333334v85.333333H341.333333v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m-256 128v85.333333H341.333333v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z m128 0v85.333333h-85.333334v-85.333333h85.333334z" fill="#444444" p-id="5286"></path></svg>
            字数{{text.length}}字
          </p>
|
          <p class="yuedushijian">
            <svg t="1711435598331" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4347" width="200" height="200"><path d="M675.328 117.717333A425.429333 425.429333 0 0 0 512 85.333333C276.352 85.333333 85.333333 276.352 85.333333 512s191.018667 426.666667 426.666667 426.666667 426.666667-191.018667 426.666667-426.666667c0-56.746667-11.093333-112-32.384-163.328a21.333333 21.333333 0 0 0-39.402667 16.341333A382.762667 382.762667 0 0 1 896 512c0 212.074667-171.925333 384-384 384S128 724.074667 128 512 299.925333 128 512 128c51.114667 0 100.8 9.984 146.986667 29.12a21.333333 21.333333 0 0 0 16.341333-39.402667zM533.333333 362.666667a21.333333 21.333333 0 0 0-42.666666 0v170.666666a21.333333 21.333333 0 0 0 6.250666 15.082667l128 128a21.333333 21.333333 0 0 0 30.165334-30.165333L533.333333 524.501333V362.666667z" fill="#3D3D3D" p-id="4348"></path></svg>
            阅读时间:{{Math.ceil((text.length)/400)}}分钟
          </p>
          |

        </div>



        <div >
          <div>

          </div>

        </div>


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
.content-main-content{
  overflow: auto;
  margin: 20px;
}
svg{
  width: 15px;
  height: 15px;
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
  font-size: 32px;
  color: #000;
  margin-bottom: 30px;
  text-align: center;
}
.another{
  font-size: 14px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
  display:inline;

}
.date{
  margin-left: 2px;
  display:inline;
  font-size: 14px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
  margin-top: 10px;
}
.date{
  margin-left: 2px;
  display:inline;
  font-size: 14px;
  color: #000;
  margin-bottom: 10px;
  text-align: center;
  margin-top: 10px;
}
.zishu{
    margin-left: 2px;
    display:inline;
    font-size: 14px;
    color: #000;
    margin-bottom: 10px;
    text-align: center;
    margin-top: 10px;

}
.yuedushijian{
  margin-left: 2px;
  display:inline;
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

/* 提供更丰富的视觉层次 */
.preview {
  padding: 20px;
  background-color: #fff; /* 白色背景 */
  border-radius: 10px; /* 圆角边框 */

  /* 添加内阴影以增加深度感 */
  box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.05),
  0 0 10px rgba(0, 0, 0, 0.1); /* 外阴影 */

  /* 添加微弱的文字阴影，提升可读性 */
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.05);

  /* 设置过渡动画，使元素在交互时更具流畅性 */
  transition: all 0.3s ease;

  /* 设置内容区域的最小高度，确保内容展示有足够的空间 */
  min-height: 200px;

  /* 添加渐变背景以增强视觉效果 */
  background-image: linear-gradient(to bottom right, #f9f9f9, white);

  /* 为内容添加一些内边距使其布局更加清晰 */
  overflow: hidden;
}

/* 当鼠标悬停时增加交互反馈 */
.preview:hover {
  box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.05),
  0 0 20px rgba(0, 0, 0, 0.2);
  transform: translateY(-5px); /* 提升元素以模拟悬浮效果 */
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
import ArticleComment from "@/views/Article/comment/ArticleComment.vue";
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
    console.log(res.data)
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
