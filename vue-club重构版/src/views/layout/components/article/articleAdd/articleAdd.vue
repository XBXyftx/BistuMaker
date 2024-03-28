<template>
<!--  {{baseUrl}}-->
  <h1>输入标题</h1>
  <el-input placeholder="输入标题" v-model="ArticleModel.title"></el-input>

  <h1>输入作者</h1>
  <el-input placeholder="输入作者" v-model="ArticleModel.author"></el-input>

  <h1>类型</h1>
  <el-select v-model="ArticleModel.type" placeholder="请选择类型">
    <el-option label="活动" value="活动"></el-option>
    <el-option label="文章" value="文章"></el-option>
  </el-select>

  <h1>简介(主要是社团活动类型)</h1>
  <el-input placeholder="请输入简介" v-model="ArticleModel.synopsis"></el-input>

<!--  {{baseURL+imageUrl}}-->
  <h1>背景图片</h1>
<!--  <upload-img></upload-img>-->
  <el-upload action=""
             :http-request="handleRequest"
             :show-file-list="false">
    <img
         :src= "imageUrl1"
         class="avatar"  alt=""
         style="width: 178px; height: auto;"/>
    />

    <div>
      <i class="el-icon-plus avatar-uploader-icon"></i>
      <div class="el-upload__text">点击上传封面</div>
    </div>
    <template #tip>
      <div v-if="!imageUrl"
           class="el-upload__tip">只能上传 jpg/png 文件，且不超过 500kb</div>
    </template>
  </el-upload>




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

<script setup>
import {getCurrentInstance, ref, watch} from 'vue'
import {articleAddService} from "@/api/article.js";
import {ElMessage} from "element-plus";



let ArticleModel = ref({
  title: '',
  author: '',
  content: '',
  type:'文章',//默认文章显示
  synopsis: '',
  coverImage: '',
})


import { uploadImg } from "@/api/uploadImg.js"
const {proxy} = getCurrentInstance()
console.log(proxy.$baseURL)
const handleUploadImage =async (event,insertImage,files)=>{
  let url;
  for(let i in files){

    const formData = new FormData()
    formData.append("file", files[i]);
    const res =await uploadImg(formData)
    url = res.data
  }
  insertImage({
    url:proxy.$baseURL+url,
    desc: '',
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
    author:ArticleModel.value.author,
    type:ArticleModel.value.type,
    synopsis:ArticleModel.value.synopsis,
    coverImage:imageUrl.value
  }
  console.log(Data)
  let result = await articleAddService(Data)
  ElMessage.success(result.msg ? result.msg : '上传成功')
}

const imageUrl = ref('')
const imageUrl1 = ref('')
const handleRequest = async (params) => {
  var { file } = params;
  var formData = new FormData();
  // formData.append("token", data.params.token);
  formData.append("file", file);
  const res =await uploadImg(formData)
  ArticleModel.coverImage = (res.data)
  // console.log(ArticleModel.coverImage)
  imageUrl.value = (res.data)

  imageUrl1.value = window.URL.createObjectURL(params.file)
  console.log(imageUrl1.value)
}
</script>