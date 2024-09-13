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
                   :default-page-size="2"
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

const messagesAllInfo = ref([]);
const messagesPageList = ref([]);
const totalItems = ref(0); // 总记录数
const pageSize = ref(10);
const currentPage = ref(1);
const getAllInfo = async () => {
  const res = await messageAllInfoService()
  messagesAllInfo.value = res.data
  totalItems.value = res.data.length;
  // 初始化分页数据
  updatePageData(currentPage.value);

}

getAllInfo();

const delete1 = (row) => {

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
        await messageDeleteService(row.id);
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

const handleCurrentChange = (newPage) => {
  currentPage.value = newPage;
  updatePageData(newPage);
};

const updatePageData = (page) => {
  const start = (page - 1) * pageSize.value;
  const end = page * pageSize.value;
  messagesPageList.value = messagesAllInfo.value.slice(start, end);
};

</script>


<style scoped>

</style>