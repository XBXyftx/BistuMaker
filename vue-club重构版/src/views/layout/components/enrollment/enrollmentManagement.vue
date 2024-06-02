<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>报名表</span>
        <br>
        <el-button @click="exportToExcel">导出xlsx</el-button>
      </div>
    </template>
    <el-table :data="enrollmentListPage" style="width: 100%" ref="exportTableRef">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="名字" prop="name"></el-table-column>
      <el-table-column label="简介" prop="introduction"></el-table-column>
      <el-table-column label="电话" prop="phoneNumber"></el-table-column>
      <el-table-column label="邮箱" prop="email"></el-table-column>
      <el-table-column label="级别" prop="department"></el-table-column>
      <el-table-column label="加入社团原因" prop="reason"></el-table-column>
      <el-table-column label="加入社团原因" prop="customReason"></el-table-column>
      <el-table-column label="意向部门" prop="desiredPosition"></el-table-column>
      <el-table-column label="学号" prop="studentId"></el-table-column>
      <el-table-column label="时间" prop="signupTime"></el-table-column>
<!--      <el-table-column label="操作" width="100" prop="id">-->
<!--        <template #default="{ row }">-->
<!--          <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>-->
<!--          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>



    <el-pagination layout="prev, pager, next"
                   :total="enrollmentList.length"
                   @current-change="handleCurrentChange"
    />
  </el-card>
</template>


<script setup>
[]
import {ref} from "vue"
import {enrollmentAllInfoService} from "@/api/enrollment.js"
const enrollmentList = ref([])
const enrollmentListPage = ref([])
const getEnrollmentList = async () => {
  const  ref = await enrollmentAllInfoService()
  enrollmentList.value = ref.data
  handleCurrentChange(1);
  for (let enrollment of enrollmentList.value) {
    // 在此处处理每个 enrollment 对象
    xlsxList.value.push([enrollment.name,enrollment.introduction,enrollment.phoneNumber,enrollment.email,enrollment.department,enrollment.reason,enrollment.customReason,enrollment.desiredPosition,enrollment.studentId,enrollment.signupTime])
  }

}

const  handleCurrentChange = (i)=>{
  enrollmentListPage.value=enrollmentList.value.slice((i-1)*10,i*10)

}
getEnrollmentList()
const exportTableRef = ref(null);
import * as XLSX from 'xlsx'



const xlsxList = ref(
    [
        ['名字', '简介', '电话', '邮箱', '级别', '加入社团原因', '加入社团原因', '意向部门', '学号', '时间'],

    ])


const exportToExcel = () => {
  // 创建一个工作簿
  const workbook = XLSX.utils.book_new();

  // 创建一个工作表
  const worksheet = XLSX.utils.aoa_to_sheet( xlsxList.value);


  // 将工作表添加到工作簿
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

  // 生成Excel文件
  XLSX.writeFile(workbook, 'data.xlsx');
};

</script>


<style scoped>

</style>