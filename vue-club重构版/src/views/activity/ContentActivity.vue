<template>
  <div style="background: linear-gradient(135deg, #5C258D, #4389A2);">
    <div class="content-container" :style="{display: isMobile ? 'inline' : 'flex'}">
      <div class="text-content" :style="{width: isMobile ? '95%' : '60%' }">
        <p style="font-size: 2.9rem;margin:0 40px;font-family: AlimamaDaoLiTi,serif;">
          <br>创客空间
        </p>
        <p style="font-weight: 500;font-size: 1rem" :style="{padding: isMobile ? '0' : '50px' }">
          创客空间社团，发于北京信息科技大学计算机学院，于去年由院级社团注册为校级社团，希望帮助更多的新生了解考研与实习的实际信息，也为新生提供学习上的帮助。
          <br><br>
          本社团代理学校计算机实验中心，向同学们提供嵌入式小车、3d打印机、各类树莓派开发板等硬件设备，同时引导新生入门鸿蒙生态、学习人工智能机器学习和大模型训练、web全栈开发等软件技术。
        </p>
      </div>
      <div class="image-content">
        <img :src="hezhao" alt="社团合照"
             style="height: auto;border-radius:10px;box-shadow: #232323 0 0 20px"
             :style="{width: isMobile ? '100%' : '750px' }"
        >
      </div>
    </div>
  </div>

  <div style="width: auto;height: 100px;background-color: rgb(0,0,0);">
    <p style="margin:0;text-align:center;padding-top: 30px;color: #fff;font-size: 1.5rem;">
      Go see more👇
    </p>
  </div>


  <div class="demo" ref="shell">
    <div class="header">
      <h2 class="title" style="padding-top: 20px">社团活动</h2>
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

    <!-- 分页器组件 -->
    <div class="pagination-container">
      <el-pagination
        style="margin: 20px 0;"
        layout="prev, pager, next"
        :total="activitiesNums.length"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="handleCurrentChange"
        background
      />
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import {onMounted} from "vue";
import {ref, getCurrentInstance} from "vue";
import {articleTypeQueryService} from '@/api/article.js';
const {proxy} = getCurrentInstance()


const hezhao = new URL('@/assets/2023.png', import.meta.url)
const activities = ref([]);
const activitiesNums = ref([]);
// 分页相关变量
const currentPage = ref(1);
const pageSize = ref(5);
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});
const getArticles = async () => {
  // 注释原有API请求逻辑
  let res = await articleTypeQueryService("活动");
  
  // 使用本地测试数据
//   activitiesNums.value = [
//   {
//     id: 1,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年03月15日',
//     synopsis: '春季招新宣讲会，欢迎新同学加入创客大家庭',
//     title: '招新活动'
//   },
//   // 重复10次类似结构，这里展示5个示例
//   {
//     id: 2,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年03月20日',
//     synopsis: '嵌入式开发入门工作坊',
//     title: '嵌入式培训'
//   },
//   {
//     id: 3,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年03月25日',
//     synopsis: '机器学习基础理论与实践',
//     title: 'AI训练营'
//   },
//   {
//     id: 4,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年04月01日',
//     synopsis: '鸿蒙应用开发实战教学',
//     title: '鸿蒙开发'
//   },
//   {
//     id: 5,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年04月05日',
//     synopsis: 'Vue3 + SpringBoot全栈开发课程',
//     title: '全栈训练'
//   },
//   {
//     id: 6,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年03月15日',
//     synopsis: '春季招新宣讲会，欢迎新同学加入创客大家庭',
//     title: '招新活动'
//   },
//   // 重复10次类似结构，这里展示5个示例
//   {
//     id: 7,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年03月20日',
//     synopsis: '嵌入式开发入门工作坊',
//     title: '嵌入式培训'
//   },
//   {
//     id: 8,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年03月25日',
//     synopsis: '机器学习基础理论与实践',
//     title: 'AI训练营'
//   },
//   {
//     id: 9,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年04月01日',
//     synopsis: '鸿蒙应用开发实战教学',
//     title: '鸿蒙开发'
//   },
//   {
//     id: 10,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年04月05日',
//     synopsis: 'Vue3 + SpringBoot全栈开发课程',
//     title: '全栈训练'
//   },
//   {
//     id: 11,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年03月15日',
//     synopsis: '春季招新宣讲会，欢迎新同学加入创客大家庭',
//     title: '招新活动'
//   },
//   // 重复10次类似结构，这里展示5个示例
//   {
//     id: 12,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年03月20日',
//     synopsis: '嵌入式开发入门工作坊',
//     title: '嵌入式培训'
//   },
//   {
//     id: 13,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/be8cfadf-5982-44bc-8f2b-63eca68c97ca.jpg',
//     createTime: '2024年03月25日',
//     synopsis: '机器学习基础理论与实践',
//     title: 'AI训练营'
//   },
//   {
//     id: 14,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年04月01日',
//     synopsis: '鸿蒙应用开发实战教学',
//     title: '鸿蒙开发'
//   },
//   {
//     id: 15,
//     coverImage: 'http://bistumaker.cn/images/2024/06/02/b44f453b-9806-4b35-9adb-5aa76720ffb1.jpg',
//     createTime: '2024年04月05日',
//     synopsis: 'Vue3 + SpringBoot全栈开发课程',
//     title: '全栈训练'
//   }];
  // 先按创建时间降序排序，最新的活动排在前面（使用原始时间进行排序）
  res.data.sort((a, b) => new Date(b.createTime) - new Date(a.createTime));

  // //格式化时间成2024年03月28日样
  for (let i = 0; i < res.data.length; i++) {
    res.data[i].coverImage = proxy.$baseURL + res.data[i].coverImage
    let ary = res.data[i].createTime.split(/(?: |-|:)/g);
    res.data[i].createTime = ary[0] + '年' + ary[1] + '月' + ary[2] + '';
    res.data[i].createTime = res.data[i].createTime.replace(/T\S*/g, '');
    res.data[i].createTime = res.data[i].createTime + '日';
  }

  activitiesNums.value = res.data;
  // 初始化显示第一页（5个活动）
  updateDisplayedActivities();
};

onMounted(async () => {
  await getArticles();
  setupTimeline();
});

// 更新显示的活动数据
const updateDisplayedActivities = () => {
  const startIndex = (currentPage.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  activities.value = activitiesNums.value.slice(startIndex, endIndex);

  // 重新设置时间轴动画
  setTimeout(() => {
    setupTimeline();
  }, 100);
};

// 分页器页码改变事件
const handleCurrentChange = (page) => {
  currentPage.value = page;
  updateDisplayedActivities();
};




const setupTimeline = () => {
  const shell = document.querySelector(".demo");
  const items = shell.querySelectorAll(".item");

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
  display: flex;
  padding: 80px;
}

.text-content {
  box-sizing: border-box;
  color: #fff;
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

.demo {
  background-size:100%;
  width: 100%;
  position: relative;
  //padding: 80px 0; transition: 0.3s ease 0s; background-attachment: fixed;

}

.demo:before {
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
/* 分页器容器样式 */
.pagination-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 0;
  background: rgba(99, 99, 99, 0.8);
  position: relative;
}

/* 分页器样式 */
.pagination-container .el-pagination {
  background: transparent;
}

.pagination-container .el-pagination .number,
.pagination-container .el-pagination button:disabled,
.pagination-container .el-pagination .btn-next,
.pagination-container .el-pagination .btn-prev {
  background: rgba(255, 255, 255, 0.1);
  color: #fff;
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.pagination-container .el-pagination .number:hover,
.pagination-container .el-pagination .btn-next:hover,
.pagination-container .el-pagination .btn-prev:hover {
  background: rgba(255, 255, 255, 0.2);
  color: #fff;
}

.pagination-container .el-pagination .number.is-active {
  background: rgba(255, 255, 255, 0.3);
  color: #fff;
  border-color: rgba(255, 255, 255, 0.5);
}

/* 响应式设计 */
@media screen and (max-width: 768px) {
  .pagination-container {
    padding: 20px 10px;
  }

  .pagination-container .el-pagination {
    scale: 0.8;
  }
}
</style>