<template>
  <TopNavBar>
  </TopNavBar>

  <ArticleContent v-if="articleInfo!==null"></ArticleContent>
  <div v-else>
    <a404></a404>
  </div>


  <Footer>
  </Footer>

</template>

<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";

import Footer from "@/components/Footer/Footer.vue";
import Content from "@/components/Content/content.vue";
import {ref} from 'vue'
import ArticleContent from "@/views/Article/ArticleContent.vue";
import {useRoute} from "vue-router";
const {query, params} = useRoute();   //2.在跳转页面定义router变量，解构得到指定的query和params传参的参数
const id = params.id
import {articleAddVisitsService, articleInfoService} from '@/api/article.js'
import A404 from "@/views/article/404/a404.vue";

const articleInfo=ref(1)

const getArticleInfo=async ()=>{
  const res = await articleInfoService(id)
  articleInfo.value=res.data
}
getArticleInfo()

articleAddVisitsService(id)

</script>

<style scoped>

</style>