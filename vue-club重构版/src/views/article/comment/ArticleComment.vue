<template>
  <div class="commentListCpn">



    <!--  -->
    <div class="commentListCont">
      <!-- 一级评论 输入 -->
      <div class="levelOneCommentInput commentInput">

        <div class="input">
<!--          <input type="text" placeholder="文本中的评论将被发送" v-model.lazy.trim='levelOneCommentContent'>-->
          <el-input v-model="nike" placeholder="输入匿名" />
          <el-input v-model="email" placeholder="邮箱" />
          <el-input v-model="levelOneCommentContent" placeholder="输入内容" />
          <button @click='handlerlevelOneComment()'>评论</button>
        </div>
      </div>


      <!--      骨架屏-->
      <div class='screen-root' v-if="loading===false">
        <ul>
          <li/>
          <li/>
          <li/>
        </ul>
      </div>
      <!-- 评论渲染 -->
      <div class="commentList" v-else>
        <!-- 评论item -->
        <div class="commentItemCont" v-for='item in renderData' :key='item.id'>
          <!-- ！！！！一级评论 -->
          <div class="levelOneComment commentItem">
            <!-- 头像 -->
            <!--            <div class="userPhoto">-->
            <!--              <img src="https://c2.im5i.com/2022/09/14/50syn.jpg" alt="">-->
            <!--            </div>-->
            <!-- 评论内容 -->
            <div class="userComment">
              <p class="userName">{{item.nickName}}</p>
              <p class="content">{{item.content}}</p>
              <p class="operationBar">
                <span class="commentDate">{{item.createTime}}</span>
              </p>
            </div>
          </div>
           <div class="isShowLine"></div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
// 引入
import {onMounted,onUpdated,ref,reactive,computed,watch,nextTick} from 'vue'
const props = defineProps({
  articleId:String
})


// // 申明 响应式数据 ====================
let originCommentListData = reactive({

})
let loading = ref(false)
import {commentInfoService} from "@/api/comment.js"
const getCommentList = async (id)=>{
  let result= await commentInfoService(id)
  renderData.value=result.data
  loading.value=true
}
getCommentList(props.articleId)









// 整合数据
let renderData = ref([])

// 监听 并创建 新的渲染评论列表 数据


// 更新整合函数数据
// function updateCommentData(){
//   // 深拷贝 消除 影响源数据
//   let deepCloneOriginData = JSON.parse(JSON.stringify(originCommentListData.data))
//   // 筛出 一级评论
//   let parentData = deepCloneOriginData.filter(item => item.parent_id === 0)
//   // 筛出 二级评论
//   let childData = deepCloneOriginData.filter(item => item.parent_id !== 0)
//   // 时间 倒序排序处理
//   parentData.sort((a,b) => b.time-a.time )
//   childData.sort((a,b) => b.time-a.time )
//   // 时间毫秒转换处理
//   parentData.map(item=>{item.time = timeToggle(item.time)})
//   childData.map(item=>{item.time = timeToggle(item.time)})
//   let newRenderData = []
//   for (let i = 0; i < parentData.length; i++) {
//     newRenderData.push({
//       itemId:parentData[i].id,
//       parent: {...parentData[i]},
//       child: JSON.parse(JSON.stringify(childData.filter(item=>item.parent_id===parentData[i].id))),
//       isShowChild:false
//     })
//   }
//   renderData.value = JSON.parse(JSON.stringify(newRenderData))
//   console.log(renderData.value)
//   // console.log(JSON.parse(JSON.stringify(renderData.value)))
//   // 返回筛出数据
//   return newRenderData
// }
// // 展开 二级控制
// function showChild(_id){
//   renderData.value.map(item=>{
//     if(item.itemId===_id) {
//       // console.log(item.isShowChild)
//       item.isShowChild = !item.isShowChild
//       // console.log(item.isShowChild)
//
//     }
//   })
// }



// 一级评论内容
let levelOneCommentContent = ref()
// 一级评论内容
let email= ref('')
// 一级评论内容
let nike = ref('')
// 二级评论内容
let levelTwoCommentContent = ref()



import { defineProps } from 'vue';
import {commentAddService} from "@/api/comment.js";
import article from "@/views/article/Article.vue";

// console.log(props)
function  handlerlevelOneComment()
{
  //请为我写个判断邮箱是否正确的格式
  if(email.value && email.value.indexOf('@') !== -1 && email.value.indexOf('.') !== -1){
    if(levelOneCommentContent.value && email.value && nike.value){
      // 时间处理
      let nowTime = new Date()
      console.log(nowTime+'---')
      // 增加 评论
      originCommentListData.data.push({
        id: originCommentListData.data.length+1,
        nick_name: nike.value,
        content: levelOneCommentContent.value,
        parent_id: 0,
        time:nowTime,
        boolChild: false
      })



      // console.log(props.articleId+'---')
      const res = {
        articleId: props.articleId,
        nickName: nike.value,
        content: levelOneCommentContent.value,
        // time: nowTime,
        creatorEmail: email.value
      };
      commentAddService(res);
      renderData.value.unshift(res)
      // getCommentList(props.articleId)
      //清空输入框
      levelOneCommentContent.value = ''
      nike.value=''
      email.value=''



      ElMessage({
        message:'评论成功！',
        type:'success',
        showClose:true
      })
    }else{
      ElMessage({
        message:'请勿输入空值！',
        type:'error',
        showClose:true
      })
    }
  }else{
    ElMessage({
      message:'邮箱格式错误！',
      type:'error',
      showClose:true
    })
  }



}




// 时间戳 转换 处理
function timeToggle(_time){
  let commentDate = new Date(_time)
  let year = commentDate.getFullYear()
  let month = commentDate.getMonth() + 1
  let date = commentDate.getDate()
  let commentClock = commentDate.toString().split(' ')[4]

  return `${year}/${month}/${date} ${commentClock}`
}
</script>



<style scoped>
/* 组件整体样式 */
.commentListCpn {
  padding: 5px;
  flex-direction: column;
}

/* 标题 */
h1 {
  font-size: 25px;
  font-weight: bold;
}

/* 分割线 */
hr {
  border-top: 1px solid #eee;
  width: 100%;
}

/* 评论区容器 */
.commentListCont {
  margin: 10px auto;
  /* 若需要限制宽度，请取消注释下面一行 */
  /* width: 80%; */
}

/* 用户头像 */
.userPhoto {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 15px;
}
.userPhoto img {
  width: 100%;
  height: 100%;
}

/* 评论输入框 */
.commentInput {
  width: 100%;

  background-color: #dedfe0;
  height: 60px;
  display: flex;
  align-items: center;
}
.commentInput .input {
  flex: 1;
  position: relative;
}
.commentInput .input input {
  width: 100%;
  height: 100%;
  padding: 0 15px; /* 可根据需要添加内边距 */
  box-sizing: border-box;
}
.commentInput .input button {
  position: absolute;
  right: 0;
  top: 0;
  border-radius: 20px;
  height: 30px;
  padding: 0 15px;
  border: none;
  box-sizing: border-box;
  box-shadow: 1px 1px 5px 5px #999;
  transition: box-shadow 0.3s ease;
}
.commentInput .input button:active {
  box-shadow: 1px 1px 5px 5px #999 inset;
}

/* 评论列表 */
.commentList {
  flex: 1;
  width: 80%;
  margin: 0 auto;
}

/* 一级评论 */
.levelOneComment {}

/* 二级评论 */
.levelTwoComment {
  display: none;
  padding-left: 80px;
}

/* 二级评论展示控制 */
.levelTwoCommentShow {
  display: block;
}

/* 评论项 */
.commentItem {
  padding: 10px 15px;
  color: #666 !important;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
}

.commentItem .userComment {
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  word-wrap: break-word;
}
.commentItem .userComment .userName {
  font-weight: bold;
}
.commentItem .userComment .content {
  flex: 1;
  align-items: center;
  margin-top: -10px;
  word-wrap: break-word;
}
.commentItem .userComment .operationBar {
  display: inline-flex;
  justify-content: space-between;
  color: #999;
  margin-top: -10px; /* 调整负值，避免重叠 */
}

/* 骨架屏样式 */
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
</style>