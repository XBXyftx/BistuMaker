<template>

  <el-tabs type="border-card" >
    <el-tab-pane label="添加文章">
      <article-add></article-add>
    </el-tab-pane>

    <el-tab-pane label="文章管理">
      <article-all-info></article-all-info>
    </el-tab-pane>>


  </el-tabs>
</template>

<script setup>
import ArticleAdd from "@/views/layout/components/article/articleAdd/articleAdd.vue";
import ArticleAllInfo from "@/views/layout/components/article/articleAllInfo/articleAllInfo.vue";
</script>

<style scoped>

</style>