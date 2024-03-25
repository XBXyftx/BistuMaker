<script setup>

import { ref } from 'vue'
import { uploadImg } from '@/api/uploadImg.js'
const imageUrl = ref('')


const handleRequest = async (params) => {
  var { file } = params;
  var formData = new FormData();
  // formData.append("token", data.params.token);
  formData.append("file", file);
  const res =await uploadImg(formData)
  imageUrl.value = (res.data)
}


</script>

<template>
  <el-upload action=""
             :http-request="handleRequest"
             :show-file-list="false">
    <img v-if="imageUrl"
         :src="imageUrl"
         class="avatar" />
    <div v-else>
      <i class="el-icon-plus avatar-uploader-icon"></i>
      <div class="el-upload__text">点击上传封面</div>

    </div>
    <template #tip>
      <div v-if="!imageUrl"
           class="el-upload__tip">只能上传 jpg/png 文件，且不超过 500kb</div>
    </template>
  </el-upload>

<!--  <p class="g_signBtn" @click="submit">提交</p>-->

</template>

<style scoped>

</style>