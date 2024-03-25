<script setup>

import {ref} from "vue"
import {enrollmentAllInfoService} from "@/api/enrollment.js"

const enrollmentList = ref([])

const getEnrollmentList = async () => {
  const  ref = await enrollmentAllInfoService()
  enrollmentList.value = ref.data
  console.log(ref)
}
getEnrollmentList()
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>报名表</span>
      </div>
    </template>
    <el-table :data="enrollmentList" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="标题" prop="name"></el-table-column>

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








  </el-card>
</template>

<style scoped>

</style>