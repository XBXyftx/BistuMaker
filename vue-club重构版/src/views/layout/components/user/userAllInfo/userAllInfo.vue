<script setup>
import {
  Edit,
  Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const userList = ref([
])

import {userAllInfoService, userDeleteService, userInfoUpdateService, userRegisterService} from "@/api/user.js";
import {ElMessage} from "element-plus";
//控制添加用户弹窗
const dialogVisible = ref(false)
const dialogVisible1 = ref(false)
const radio1 = ref('admin')
//定义数据模型
const registerData = ref({
  username: '',
  password: '',
  rePassword: '',
  role:'user'
})
//校验密码的函数
const checkRePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次确认密码'))
  } else if (value !== registerData.value.password) {
    callback(new Error('请确保两次输入的密码一样'))
  } else {
    callback()
  }
}

//定义表单校验规则
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
  ],
  rePassword: [
    { validator: checkRePassword, trigger: 'blur' }
  ]
}

//回显用户数据
const getUserList = async () => {
  let result = await userAllInfoService()
  console.log(result)
  userList.value = result.data
}
getUserList()
// console.log("用户信息+all:"+userList.value)

const addUser = async () => {
  // let result = await  userRegisterService(categoryModel.value);
  // ElMessage.success(result.msg ? result.msg : '添加成功')
  // await getUserList()
  // dialogVisible.value = false;
  // console.log('用户为+'+registerData.value.role)
  if(registerData.value.password === registerData.value.rePassword){
    let  data = {
      username:registerData.value.username,
      password:registerData.value.password,
      role:registerData.value.role
    }
    let result = await  userRegisterService(data);
    ElMessage.success(result.msg? result.msg : '添加成功')
    registerData.value.username=''
    registerData.value.role=''
    registerData.value.rePassword=''
    registerData.value.password=''
    await getUserList()
    dialogVisible.value = false;
  }else{
    ElMessage.error('两次输入的密码不一致')
  }
}

//删除用户
import {ElMessageBox} from 'element-plus'
const deleteCategory = (row) => {
  //提示用户  确认框
  console.log(row)

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
        let result = await userDeleteService(row.id);
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        //刷新列表
        await getUserList()
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '用户取消了删除',
        })
      })
}



// const registerData1 = ref({
//   username: '',
//   password: '',
//   rePassword: '',
//   role:''
// })
//展示编辑弹窗
const showDialog = (row) => {
  // dialogVisible.value = true; title.value = '编辑分类'
  // // 数据拷贝
  // categoryModel.value.categoryName = row.categoryName;
  // categoryModel.value.categoryAlias = row.categoryAlias;
  // //扩展id属性,将来需要传递给后台,完成分类的修改
  // categoryModel.value.id = row.id
  registerData.value.username = row.username;
  registerData.value.password = row.password;
  registerData.value.role = row.role;
  dialogVisible1.value = true;
}
const updateUser = async () => {
  let  data = {
    username:registerData.value.username,
    password:registerData.value.password,
    role:registerData.value.role
  }
  //调用接口
  let result = await userInfoUpdateService(data);

  ElMessage.success(result.msg? result.msg : '修改成功');

  //修改pinia中的个人信息
  // userInfoStore.setInfo(registerData.value)
  dialogVisible1.value = false;
}


</script>
<template>

<!--  {{userList[1]}}-->
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>用户管理</span>
        <div class="extra">
          <el-button type="primary" @click="dialogVisible= true"> 添加用户</el-button>
        </div>
      </div>
    </template>
    <el-table :data="userList" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="权限" prop="role"></el-table-column>
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
    <el-dialog v-model="dialogVisible" title="添加用户" width="30%">
      <el-form :model="registerData" :rules="rules" label-width="100px" style="padding-right: 30px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="registerData.username" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerData.password" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="确定密码" prop="rePassword">
          <el-input v-model="registerData.rePassword" minlength="1" maxlength="10"></el-input>
        </el-form-item>

        <el-form-item label="修改权限" prop="password1">
          <div class="mb-2 flex items-center text-sm">
            <el-radio-group v-model="registerData.role" class="ml-4">
              <el-radio label="admin" size="large">管理员</el-radio>
              <el-radio label="user" size="large">普通用户</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="addUser()"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>


    <!-- 添加分类弹窗 -->
    <el-dialog v-model="dialogVisible1" title="添加用户" width="30%">
      <el-form :model="registerData" :rules="rules" label-width="100px" style="padding-right: 30px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="registerData.username" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerData.password" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="确定密码" prop="rePassword">
          <el-input v-model="registerData.rePassword" minlength="1" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="修改权限" prop="password1">
          <div class="mb-2 flex items-center text-sm">
            <el-radio-group v-model="registerData.role" class="ml-4">
              <el-radio label="admin" size="large">管理员</el-radio>
              <el-radio label="user" size="large">普通用户</el-radio>
            </el-radio-group>
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
            <el-button @click="dialogVisible1 = false">取消</el-button>
            <el-button type="primary" @click=""> 确认 </el-button>
        </span>
      </template>
    </el-dialog>







  </el-card>
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