<script setup>
import { ref,watch } from 'vue'
import useUserInfoStore from '@/stores/userInfo.js'
const userInfoStore = useUserInfoStore();

const password = {
  password: '123',
  rePassword: '123'
}
const userInfo = ref({...userInfoStore.info,...password})
console.log(userInfo.value)
const radio1 = ref('')
radio1.value = userInfo.value.role
watch(radio1,()=>{
  console.log(radio1.value)
})


// const registerData = ref({
//   username: '',
//   password: '',
//   rePassword: '',
//   role:''
// })
// registerData.value.username=userInfo.username
// registerData.value.role=userInfo.role
//

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



//修改个人信息
import {userInfoUpdateService} from '@/api/user.js'
import {ElMessage} from 'element-plus'
const updateUserInfo = async ()=>{
  //调用接口
  let result = await userInfoUpdateService(userInfo.value);
  ElMessage.success(result.msg? result.msg : '修改成功');

  //修改pinia中的个人信息
  userInfoStore.setInfo(userInfo.value)
}
</script>
<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>基本资料</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
          <el-form-item label="登录名称">
            <el-input v-model="userInfo.username" disabled></el-input>
          </el-form-item>
          <h2>重置密码</h2>
          <el-form-item label="密码" prop="passwortd">
            <el-input v-model="userInfo.password"></el-input>
          </el-form-item>
          <el-form-item label="再次输入" prop="password1" show-password>
            <el-input v-model="userInfo.rePassword"></el-input>
          </el-form-item>
          <h2>修改角色</h2>
          <el-form-item label="修改权限" prop="password1">
<!--            <el-input v-model="userInfo.role"></el-input>-->
            <div class="mb-2 flex items-center text-sm" v-if="radio1=='admin'">
              <el-radio-group v-model="radio1" class="ml-4">
                <el-radio label="admin" size="large">管理员</el-radio>
                <el-radio label="user" size="large">普通用户</el-radio>
              </el-radio-group>
            </div>

            <div class="mb-2 flex items-center text-sm" v-else disabled>
              <el-radio-group v-model="radio1" class="ml-4">
                <el-radio label="admin" size="large">管理员</el-radio>
                <el-radio label="user" size="large">普通用户</el-radio>
              </el-radio-group>
            </div>

          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="updateUserInfo">提交修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>