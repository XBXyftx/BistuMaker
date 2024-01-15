<script>

import {defineComponent} from "vue";
import ArticleAdd from "@/views/layout/components/article/articleAdd/articleAdd.vue";

export default defineComponent({
  components: {ArticleAdd}
})
</script>

<template>

  <el-tabs type="border-card" >
    <el-tab-pane label="添加文章">
      <article-add></article-add>
    </el-tab-pane>

  </el-tabs>
</template>

<style scoped>

</style>