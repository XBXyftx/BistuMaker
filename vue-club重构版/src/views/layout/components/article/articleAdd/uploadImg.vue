<template>
  <el-upload action=""
             :http-request="handleRequest"
             :show-file-list="false">
    <img v-if="imageUrl"
         :src="imageUrl"
         class="avatar"
    alt=""/>
    <div>
      <i class="el-icon-plus avatar-uploader-icon"></i>
      <div class="el-upload__text">点击上传封面</div>

    </div>
    <template #tip>
      <div v-if="!imageUrl"
           class="el-upload__tip">只能上传 jpg/png 文件，且不超过 500kb</div>
    </template>
  </el-upload>
</template>

<script setup>
import { ref } from 'vue'
const imageUrl = ref('')
// const handleRequest = async (params) => {
//   var { file } = params;
//   var formData = new FormData();
//   // formData.append("token", data.params.token);
//   formData.append("file", file);
//   const res =await uploadImg(formData)
//
//   imageUrl.value = (res.data)
// }


//将图片预览为本地流形式
const handleRequest = async (params) => {
  imageUrl.value = window.URL.createObjectURL(params.file)
  console.log(imageUrl.value)
}

</script>

<style scoped>

</style>