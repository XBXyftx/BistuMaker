<script setup>
import {
  Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'

import {
  notificationsAddService,
  notificationsAllInfoService,
  notificationsDeleteService, notificationsUpdateIsReadService,
} from "@/api/notifications.js"
import {ElMessage} from "element-plus";
//控制添加用户弹窗
let dialogVisible = ref(false)
//定义数据模型
const notificationsData = ref({
  title:'',
  content:'',
  isRead:true
})
const notificationsList = ref([
])

//回显用户数据
const getNotificationsList = async () => {
  let result = await notificationsAllInfoService()
  console.log(result.data)
  notificationsList.value = result.data
}
getNotificationsList()


const addNotifications = async () => {
  console.log(notificationsData.value)
  if (notificationsData.value.isRead===true){
    notificationsData.value.isRead=1
  }else{
    notificationsData.value.isRead=0
  }
  let result = await notificationsAddService(notificationsData.value);
  ElMessage.success(result.msg? result.msg : '添加成功')

  notificationsData.value.title=''
  notificationsData.value.content=''
  await getNotificationsList()
  dialogVisible.value=false
}

//删除用户
import {ElMessageBox} from 'element-plus'
const deleteCategory = (row) => {
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
        let result = await notificationsDeleteService(row.id);
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        await getNotificationsList()
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '用户取消了删除',
        })
      })
}

const changeStatus = async (id) =>{
  console.log("提交之前的id"+id)
  const result = await notificationsUpdateIsReadService(id)
  ElMessage.success(result.msg? result.msg : '修改成功')
}



</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>用户管理</span>
        <div class="extra">
          <el-button type="primary" @click="dialogVisible= true"> 添加新的通知</el-button>
        </div>
      </div>
    </template>
    <el-table :data="notificationsList" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="标题" prop="title"></el-table-column>
      <el-table-column label="内容" prop="content"></el-table-column>
      <el-table-column label="时间" prop="createTime"></el-table-column>
      <el-table-column prop="isRead" label="状态" width="100">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.isRead"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="changeStatus(scope.row.id)"
          />
        </template>
      </el-table-column>

      <el-table-column label="操作" width="100" prop="id">
        <template #default="{ row }">
<!--          <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>-->
          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>


    <!-- 添加通知弹窗 -->
    <el-dialog v-model="dialogVisible" title="添加通知" width="30%">
      <el-form :model="notificationsData"  label-width="100px" style="padding-right: 30px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="notificationsData.title" ></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="notificationsData.content" ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="isRead">
<!--          <el-input v-model="notificationsData.isRead" ></el-input>-->
          <el-switch v-model="notificationsData.isRead" />
        </el-form-item>




      </el-form>
      <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="addNotifications()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>









  </el-card>
<!--  {{notificationsList}}-->
</template>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>