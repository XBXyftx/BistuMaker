<template>
  <div class="login">
    <el-form ref="form" size="large" autocomplete="off"   :model="registerData" :rules="rules">
      <el-form-item>
        <h1>登录</h1>
      </el-form-item>

      <el-form-item prop="username">
        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
      </el-form-item>

      <!-- 登录按钮 -->
      <el-form-item>
        <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
      </el-form-item>

    </el-form>

  </div>

</template>

<script setup>
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
// 引入用户登录服务
import {userLoginService} from "@/api/user.js";
// 引入token存储和路由钩子
import {useTokenStore} from '@/stores/token.js'
import {useRouter} from "vue-router";
// 引入Element Plus的用户和锁图标
import {User} from "@element-plus/icons-vue";
const router = useRouter()
const tokenStore = useTokenStore();

// 定义登录表单数据和规则
const registerData = ref({
  username: '',
  password: ''
})
const rules ={
  username:[
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min:3,max:15,message: '用户名长度不符合要求', trigger: 'blur'}
  ],
  password:[
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min:5,max:16,message: '密码长度不符合要求', trigger: 'blur'}
  ],

}

// 定义登录方法
const login =async ()=>{
  // 调用登录服务
  let result =  await userLoginService(registerData.value);
  // 展示登录结果消息
  ElMessage.success(result.msg ? result.msg : '登录成功')
  // 存储获取到的token
  tokenStore.setToken(result.data)
  // 跳转到管理员首页
  router.push('/admin')
}

</script>


<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-form {
  width: 100%;
  max-width: 400px;
  margin: 20px;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 5px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.login-form-item {
  margin-bottom: 20px;
}

.el-input {
  width: 100%;
}

.el-button {
  width: 100%;
}


</style>