<script setup>
import { ref,watch } from 'vue'
import useUserInfoStore from '@/stores/userInfo.js'
const userInfoStore = useUserInfoStore();

const password = ref({
  password: '',
  rePassword: ''
})
// const userInfo = ref({...userInfoStore.info,...password})

let userInfo = ref(
    {
      id: userInfoStore.info.id,
      username: userInfoStore.info.username,
      password: '',
      role: userInfoStore.info.role,
    }
)


//修改个人信息
import {userInfoUpdateService} from '@/api/user.js'
import {ElMessage} from 'element-plus'

const updateUserInfo = async ()=>{
  if (password.value.password!==password.value.rePassword){
    ElMessage.error('两次密码不一致')
  }
  //八位以上
  // if (password.password.length<8 && password.rePassword<8){
  //   ElMessage.error('密码长度不符合要求')
  // }

  userInfo.value.password=password.value.password
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
        <el-form :model="userInfo"   label-width="100px" size="large">
          <el-form-item label="登录名称">
            <el-input v-model="userInfo.username" disabled></el-input>
          </el-form-item>

          <h2>重置密码</h2>
          <el-form-item label="密码" prop="password">
            <el-input v-model="password.password"></el-input>
          </el-form-item>
          <el-form-item label="再次输入" prop="password1" show-password>
            <el-input v-model="password.rePassword"></el-input>
          </el-form-item>



          <el-form-item>
            <el-button type="primary" @click="updateUserInfo">提交修改</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>