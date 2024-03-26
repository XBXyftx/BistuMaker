<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";

import footer1 from "@/components/Footer/footer.vue";
import Content from "@/components/Content/content.vue";
import {ref} from 'vue'
import ArticleContent from "@/views/Article/ArticleContent.vue";
import {useRoute} from "vue-router";
const {query, params} = useRoute();   //2.在跳转页面定义router变量，解构得到指定的query和params传参的参数
const id = params.id
import {articleInfoService} from '@/api/article.js'
import A404 from "@/views/article/404/a404.vue";

const articleInfo=ref(null)

const getArticleInfo=async ()=>{
  const res = await articleInfoService(id)
  articleInfo.value=res.data
}
getArticleInfo()

</script>
<template>
  <TopNavBar>
  </TopNavBar>

  <ArticleContent v-if="articleInfo!==null"></ArticleContent>
  <div v-else>
    <a404></a404>
  </div>


  <footer1>
  </footer1>

</template>
<style scoped>

</style>