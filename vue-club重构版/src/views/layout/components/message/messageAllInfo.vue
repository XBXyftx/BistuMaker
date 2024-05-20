<template>
  <el-card class="page-container">
    <el-table :data="messagesPageList" style="width: 100%" ref="exportTableRef">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="名字" prop="username"></el-table-column>
      <el-table-column label="信息" prop="message"></el-table-column>
      <el-table-column label="ip" prop="ip"></el-table-column>
      <el-table-column label="浏览器" prop="browser"></el-table-column>
      <el-table-column label="操作系统" prop="os"></el-table-column>
      <el-table-column label="时间" prop="createTime"></el-table-column>
      <el-table-column label="操作" width="100" prop="id">
        <template #default="{ row }">
          <!--          <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>-->
          <el-button :icon="Delete" circle plain type="danger" @click="delete1(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>



    <el-pagination layout="prev, pager, next"
                   :total="pageSize"
                   :default-page-size="10"
                   :current-page="currentPage"
                   @current-change="handleCurrentChange"
    />
  </el-card>
</template>


<script setup>
import {ref} from "vue"
import {messageDeleteService,messageAllInfoService} from '@/api/message.js'
import {Delete} from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {notificationsDeleteService} from "@/api/notifications.js";
const messagesAllInfo = ref([])
const getAllInfo = async () => {
  const res = await messageAllInfoService()
  messagesAllInfo.value = res.data
  pageSize.value=res.data.length
  if (messagesAllInfo.value.length<=10){
    messagesPageList.value=messagesAllInfo.value
  }
  // console.log(messagesAllInfo.value)
}
 getAllInfo()

const delete1 = (row) => {
  console.log(row.id)
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
        // let result = await userDeleteService(row.id);
        let result = await messageDeleteService(row.id);
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        await getAllInfo();
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '用户取消了删除',
        })
      })
}

let messagesPageList = ref([])
const pageSize=ref()
const currentPage=ref(1)
const  handleCurrentChange = (i)=>{
  // imageAlbumAllList.value=null
  currentPage.value=i
  messagesPageList.value=messagesAllInfo.value.slice((i-1)*10,i*2)
  console.log(messagesPageList.value)
}

</script>


<style scoped>

</style>