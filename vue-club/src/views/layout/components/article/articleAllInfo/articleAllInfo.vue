<script setup>

const articleAllInfo = async ()=> {
  let result = await  articleAllInfoService()
  articleList1.value=result.data
  result.data.forEach(article=>{
    if (article.content ){
      //如果太长了就截断些
      if(article.content.length>20){
        article.content = article.content.substring(1, 21)+'.......'
      }
    }
  })
  articleList.value=result.data
  // console.log(articleList.value)
}
articleAllInfo()
import {Delete, Edit} from "@element-plus/icons-vue";
import { ref } from 'vue'
//第一个是显示的数据第二个是编辑用到的数据
const articleList=ref([])
const articleList1=ref([])
import {articleAllInfoService, articleDeleteService, articleInfoService, articleUpdateService} from "@/api/article.js";
let ArticleModel = ref({
  id:'',
  title: '',
  author: '',
  content: ''
})



const dialogVisible = ref(false)

const articleInfo = async (id) => {
  let result = await articleInfoService(id)
  // console.log("查询"+result.data+id)
  ArticleModel.value=result.data
}
const showDialog = (row) => {
  dialogVisible.value = true;
  articleInfo(row.id)
}

//删除用户
import {ElMessage, ElMessageBox} from 'element-plus'
const deleteCategory = (row) => {
  //提示用户  确认框

  ElMessageBox.confirm(
      '你确认要删除该分类信息吗?',
      '温馨提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(async () => {
        //调用接口
        let result = await articleDeleteService(row.id)
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        //刷新列表
        await articleAllInfo()
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '用户取消了删除',
        })
      })
}

const updateArticle = async () => {
  let result = await articleUpdateService(ArticleModel.value)
  if (result.code == 0) {
    ElMessage({
      type: 'success',
      message: '修改成功',
    })
    dialogVisible.value = false;
    await articleAllInfo()
  }

}

</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>文章管理</span>
      </div>
    </template>
    <el-table :data="articleList" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="标题" prop="title"></el-table-column>

      <el-table-column label="作者" prop="author"></el-table-column>
      <el-table-column label="内容" prop="content"></el-table-column>
      <el-table-column label="操作" width="100" prop="id">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>


    <!-- 添加分类弹窗 -->
    <el-dialog v-model="dialogVisible" title="添加用户" width="80%">
      <el-form   label-width="100px" style="padding-right: 30px">
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



      </el-form>
      <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="updateArticle()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>





  </el-card>
</template>

<style scoped>

</style>