<script setup>
import {ref} from 'vue'
const url =
    'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});

let data = ref([
  {
    id:1,
    title: '编程部门',
    content: '编程部门是公司技术团队的核心组成部分，致力于软件开发与维护。我们的团队拥有高度专业化的工程师，擅长多种编程语言和技术栈。通过协作与创新，我们不断推动项目的进展，保障产品质量与性能。在追求卓越的同时，我们注重团队合作与知识分享，以促进成员个人成长。我们的目标是通过创造性的编码解决方案，为客户提供卓越的技术支持，实现公司的使命和愿景。',
    img:  new URL('@/assets/编程.jpg',import.meta.url),
    router:''
  },
  {
    id:2,
    title: '嵌入式开发',
    content: '\n' +
        '嵌入式开发是一种专注于嵌入式系统设计的软件工程领域。这类系统通常嵌入在各种设备中，如智能手机、家用电器、汽车控制系统等。嵌入式开发涉及硬件与软件的密切合作，开发者需要深入了解目标硬件架构和实时操作系统，以实现高效、稳定的嵌入式应用程序。常见的编程语言包括C、C++和汇编语言。嵌入式开发的关键目标是实现小型、低功耗、高性能的系统，以满足特定应用领域的需求。',
    img: new URL('@/assets/嵌入式.jpg',import.meta.url),
    router:''
  },
  {
    id:1,
    title: '学业规划',
    content: '\n' +
        '学业规划是为了实现个人职业和学术目标而设定的长期计划。首先，明确自己的兴趣和目标，了解所学专业的就业前景和发展方向。其次，规划学科选择，确定主修和辅修方向，并在此基础上选择相关的课程。建议注重综合素质的培养，包括技术能力、沟通能力和团队协作能力。同时，积极参与实习、项目和社会活动，建立实际工作经验和人际网络。定期评估和更新学业规划，适应个人成长和行业变化。最重要的是保持学习的热情和不断追求新知识。',
    img: new URL('@/assets/学业规划.jpg',import.meta.url),
    router:''
  },


])



</script>

<template>
  <div v-for="(item,id) in data " :key="item.id">
    <div class="box" :style="isMobile?'  padding: 40px':'  padding: 50px 50px 50px 120px;'"   v-if="id%2===0">
<!--      {{item}}-->
      <!-- 左图 -->
      <el-image class="img"   :src="item.img" fit="contain" />
      <!-- 文字 -->
      <div class="text-Content" :style="isMobile?' ':'  margin-top: 2vw;'">
        <div class="title">{{item.title}}</div>

        <div style="width: 100%;height: 10px;background-color: #a3e4ff"></div>
        <br>
        <div class="content">
          {{item.content}}
        </div>
        <!-- 按钮 -->
<!--        <button class="btn" @click>Learn More</button>-->
      </div>
    </div>


    <div class="box" :style="isMobile?'  padding: 40px':'  padding: 50px 50px 50px 120px;'"  v-else>
      <el-image class="img"   :src="url" fit="contain" v-if="id%2===1 && isMobile"/>
      <!-- 文字 -->
      <div class="text-Content" style="margin-right: 4vw;margin-left: 0">
        <div class="title">{{item.title}}</div>

        <div style="width: 100%;height: 10px;background-color: #a3e4ff"></div>
        <br>

        <div class="content">
          {{item.content}}
        </div>
        <!-- 按钮 -->
<!--        <button class="btn">Learn More</button>-->
      </div>
      <!--    右图-->
      <el-image class="img" style="width: auto; height: 100%" :src="item.img" fit="contain" v-if="!isMobile"/>
    </div>
  </div>




</template>

<style scoped>
/* CDN 服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
@font-face {
  font-family: "站酷酷黑 Regular";
  font-weight: 400;
  src: url("//at.alicdn.com/wf/webfont/pXW6WcZ4aH3n/wrZZ7zVs0VrlZEOKyNJqi.woff2") format("woff2"),
  url("//at.alicdn.com/wf/webfont/pXW6WcZ4aH3n/3fDVYyJQ0o7NFKAX_5-Z0.woff") format("woff");
  font-display: swap;
}

* {
  font-family: "站酷酷黑 Regular",serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {

  width: 100%;
  height: 100vh;
  /* flex 子元素 水平居中对其*/
  //display: flex;
  //justify-content: center;
  //align-items: center;
  /* 渐变色背景 */
  background: linear-gradient(to right, #1565C0, #b92b27);
}

.box {
  display: flex;
  //width: 90%;
  //height: 65%;
  padding: 50px 50px 50px 150px;

}

.img {
  flex: 1;
  //background: url(https://th.bing.com/th?id=OIP.DsEFwxJWlliMzQLItlcm4gHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.1&pid=3.1&rm=2);
  background-size: cover;
  background-position: center center;
  border-radius: 12px;
  transition: all 1s linear;
}

.img:hover {
  filter: saturate(220%);
}

.text-Content {
  flex: 1;
  //margin-top: 55px;
  margin-left: 7.4vw;
  overflow: hidden;
}

.title {
  font-size: 2rem;
  margin-bottom: 0.5em;
}

.content {
  font-size: 1.1rem;
}

.btn {
  margin-top: 10%;
  width: 150px;
  height: 50px;
  border-radius: 20px;
  background-color: rgba(0, 0, 0, 0.607);
  color: #ffffffc2;
  transition: all 0.8s;
  cursor: pointer;
  font-size: 1rem;
  border: 0;
}

.btn:hover {
  background-color: rgba(255, 255, 255, 0.747);
  color: rgba(0, 0, 0, 0.795);
}

@media screen and (max-width: 768px) {
  .box {
    //width: 90%;
    //height: 100vh;
    flex-direction: column;
  }

  .img {
    margin-bottom: 16px;
  }

  .text-Content {
    font-size: 15px;
    margin-left: 0;
  }

  .title {
    font-size: 1.2rem;
    margin-bottom: 1em;
  }

  .content {
    font-size: 1rem;
  }
}
</style>