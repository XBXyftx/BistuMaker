<script setup>
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
// 注册表单
 const registerData = ref({
   username: '',
   password: ''
 })

// 校验规则
// const checkRePassword = (rule, value, callback) => {
//   if (value === '') {
//     callback(new Error('请再次输入密码'))
//   } else if (value!== registerData.password) {
//     callback(new Error('两次输入的密码不一致'))
//   } else {
//     callback()
//   }
// }

//定义表单规则
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

import {userLoginService} from "@/api/user.js";
import {useTokenStore} from '@/stores/token.js'
import {useRouter} from "vue-router";
import {User} from "@element-plus/icons-vue";
const router = useRouter()
const tokenStore = useTokenStore();
const login =async ()=>{
  //调用接口,完成登录
  // console.log('用户数据 ' + registerData.value.username)
  let result =  await userLoginService(registerData.value);
  // console.log(result)
  ElMessage.success(result.msg ? result.msg : '登录成功')
  //把得到的token存储到pinia中
  console.log(result.data)
  tokenStore.setToken(result.data)
  //跳转到首页 路由完成跳转
  router.push('/admin')
}

</script>

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


      <!--    <el-form class="login-form" ref="form" :model="registerData"  :rules="rules"-->
<!--             label-width="80px" style="width:80%">-->
<!--      <el-form-item>-->
<!--        <h1>登录</h1>-->
<!--      </el-form-item>-->


<!--      <el-form-item prop="username">-->
<!--        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>-->
<!--      </el-form-item>-->

<!--      <el-form-item prop="password">-->
<!--        <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>-->
<!--      </el-form-item>-->

<!--      <el-form-item>-->
<!--        <el-button class="button" type="primary" @click="login()" auto-insert-space>登录</el-button>-->
<!--      </el-form-item>-->

<!--    </el-form>-->


  </div>

</template>

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