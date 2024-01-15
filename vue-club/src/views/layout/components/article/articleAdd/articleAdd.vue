<script>

import { ref } from 'vue'
import ArticleManagement from "@/views/layout/components/article/articleManagement.vue";


//创建一个标题名和内容的对象
const ArticleModel = ref({
  title: '',
  author: '',
  date: '',
  content: ''
})

export default {
  methods:{
    handleUploadImage(event,insertImage,files){
      // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
      console.log(files);
      console.log(123123)
      // 此处只做示例
      insertImage({
        url:
            'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1269952892,3525182336&fm=26&gp=0.jpg',
        desc: '七龙珠',
        // width: 'auto',
        // height: 'auto',
      });
    }
  }
}

</script>

<template>
  <v-md-editor
      v-model="text"
      :disabled-menus="[]"
      @upload-image="handleUploadImage"
      height="500px"
  />
</template>

<style scoped>

</style>