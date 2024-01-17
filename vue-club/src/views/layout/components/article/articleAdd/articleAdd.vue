<script setup>

import {ref, reactive, watch} from 'vue'
import {useArticleInfoStore} from '@/stores/articleInfo.js'
import {userInfoService} from "@/api/user.js";
import {articleAddService} from "@/api/article.js";
import {ElMessage} from "element-plus";


let ArticleModel = ref({
  title: '',
  author: '',
  content: ''
})
const articleStore = useArticleInfoStore();
if(articleStore.info!=null){
  ArticleModel.content=articleStore.info.content
  ArticleModel.author=articleStore.info.author
  ArticleModel.title=articleStore.info.title
}





const handleUploadImage=(event,insertImage,files)=>{
      // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
      // 此处只做示例
      insertImage({
        url:
            'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1269952892,3525182336&fm=26&gp=0.jpg',
        desc: '七龙珠',
      });
  }
// articleStore.setInfo()

watch(ArticleModel, () => {
  // if (ArticleModel==null)return
  console.log(ArticleModel)
  articleStore.setInfo(ArticleModel)
})

const publish = async()=>{
  // const userStore = useArticleInfoStore();
  // ArticleModel.value.author=userStore.info.username
  // console.log("上传前的数据"+ArticleModel)
  if(ArticleModel.value.title==null||ArticleModel.value.title===""){
    ElMessage.error("标题不能为空")
    return
  }
  let Data={
    title:ArticleModel.value.title,
    content:ArticleModel.value.content,
    author:ArticleModel.value.author
  }
  console.log(Data)
  let result = await articleAddService(Data)
  ElMessage.success(result.msg ? result.msg : '上传成功')
}


</script>

<template>
  <h1>输入标题</h1>
  <el-input placeholder="输入标题" v-model="ArticleModel.title"></el-input>

  <h1>输入作者</h1>
  <el-input placeholder="输入作者" v-model="ArticleModel.author"></el-input>

  <h1>文章内容</h1>
  <v-md-editor
      v-model="ArticleModel.content"
      :disabled-menus="[]"
      @upload-image="handleUploadImage"
      height="500px"
  />


  <el-button @click="publish()">发布</el-button>
<!--  <el-button>保存草稿</el-button>-->



</template>

<style scoped>

</style>