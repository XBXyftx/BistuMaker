<template>

  <TopNavBar></TopNavBar>
  <div  v-if="isMobile===false">
    <div class="demo" >
      <div class="container a-container" ref="aContainer"

      >
        <form @submit.prevent="submitForm('a')" class="form" id="a-form">
          <el-form ref="form" label-width="120px" :model="formData" :rules="formRules" class="questionnaire-form">
            <br>
            <br>
            <br>
            <br>
            <br>
            <!-- 学生信息部分 -->
            <el-form-item label="姓名" prop="name">
              <el-input v-model="formData.name" placeholder="请输入你的姓名"></el-input>
            </el-form-item>

            <el-form-item label="学号" prop="studentId">
              <el-input v-model="formData.studentId" placeholder="请输入你的学号"></el-input>
            </el-form-item>

            <el-form-item label="级别" prop="department">
              <el-input v-model="formData.department" placeholder="请输入你的年级信息(如计科2102)"></el-input>
            </el-form-item>

            <el-form-item label="手机号码" prop="phoneNumber">
              <el-input v-model="formData.phoneNumber" placeholder="请输入你的手机号码"></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="formData.email" placeholder="请输入你的邮箱"></el-input>
            </el-form-item>

            <!-- 参加社团原因部分 -->
            <el-form-item label="参加社团的原因" prop="reason">
              <el-select v-model="formData.reason" placeholder="请选择或输入原因">
                <el-option
                    v-for="reason in reasons"
                    :key="reason.value"
                    :label="reason.label"
                    :value="reason.value">
                </el-option>
              </el-select>
            </el-form-item>


            <!-- 自定义原因 -->
            <el-form-item label="其他原因（选填）" prop="customReason">
              <el-input v-model="formData.customReason" type="textarea" autosize placeholder="如有其他原因，请详细说明"></el-input>
            </el-form-item>

            <!-- 报名信息部分 -->
            <el-form-item label="意向职位" prop="desiredPosition">
              <el-input v-model="formData.desiredPosition" placeholder="请输入意向职位"></el-input>
            </el-form-item>

            <el-form-item label="自我介绍" prop="introduction">
              <el-input v-model="formData.introduction" type="textarea" autosize placeholder="请简要介绍一下自己"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="onSubmit">提交</el-button>

            </el-form-item>
            <br>
            <br>
            <br>
          </el-form>
        </form>
      </div>

      <div class="container b-container" ref="bContainer">
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

      <div class="switch" id="switch-cnt">
        <div class="switch_circle"></div>
        <div class="switch_circle switch_circle-t"></div>
        <div class="switch_container" id="switch-c1" :class="{ 'is-hidden': isC2Visible }">
          <h2 class="switch_title title" style="letter-spacing: 0;">👈Welcome！</h2>
          <p class="switch_description description">是管理员喔？由此登录</p>
          <button class="switch_button button switch-btn" @click="toggleForm">切换报名</button>
        </div>

        <div class="switch_container" id="switch-c2" :class="{ 'is-hidden': !isC2Visible }">
          <h2 class="switch_title title" style="letter-spacing: 0;">Hello Friend！👉</h2>
          <p class="switch_description description">是新生嘛？点此报名</p>
          <button class="switch_button button switch-btn" @click="toggleForm">切换管理员</button>
        </div>
      </div>
    </div>
  </div>

  <div v-else>
    <div class="switch_container_moblic" id="switch-c2"  >
      <h2 class="switch_title title" style="letter-spacing: 0;">Hello Friend！👇</h2>
      <p class="switch_description description">&nbsp&nbsp&nbsp是新生嘛？点此报名</p>
<!--      <button class="switch_button button switch-btn" @click="toggleForm">切换报名</button>-->
    </div>



    <div>
      <form @submit.prevent="submitForm('a')" class="form" id="a-form">
        <el-form ref="form" label-width="120px" :model="formData" :rules="formRules" class="questionnaire-form">
          <h1>请填写信息</h1>
          <!-- 学生信息部分 -->
          <el-form-item label="姓名" prop="name">
            <el-input v-model="formData.name" placeholder="请输入你的姓名"></el-input>
          </el-form-item>

          <el-form-item label="学号" prop="studentId">
            <el-input v-model="formData.studentId" placeholder="请输入你的学号"></el-input>
          </el-form-item>

          <el-form-item label="级别" prop="department">
            <el-input v-model="formData.department" placeholder="请输入你的年级信息(如计科2102)"></el-input>
          </el-form-item>

          <el-form-item label="手机号码" prop="phoneNumber">
            <el-input v-model="formData.phoneNumber" placeholder="请输入你的手机号码"></el-input>
          </el-form-item>

          <el-form-item label="邮箱" prop="email">
            <el-input v-model="formData.email" placeholder="请输入你的邮箱"></el-input>
          </el-form-item>

          <!-- 参加社团原因部分 -->
          <el-form-item label="参加社团的原因" prop="reason">
            <el-select v-model="formData.reason" placeholder="请选择或输入原因">
              <el-option
                  v-for="reason in reasons"
                  :key="reason.value"
                  :label="reason.label"
                  :value="reason.value">
              </el-option>
            </el-select>
          </el-form-item>


          <!-- 自定义原因 -->
          <el-form-item label="其他原因（选填）" prop="customReason">
            <el-input v-model="formData.customReason" type="textarea" autosize placeholder="如有其他原因，请详细说明"></el-input>
          </el-form-item>

          <!-- 报名信息部分 -->
          <el-form-item label="意向职位" prop="desiredPosition">
            <el-input v-model="formData.desiredPosition" placeholder="请输入意向职位"></el-input>
          </el-form-item>

          <el-form-item label="自我介绍" prop="introduction">
            <el-input v-model="formData.introduction" type="textarea" autosize placeholder="请简要介绍一下自己"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
          </el-form-item>
        </el-form>
      </form>
    </div>
  </div>


  <Footer></Footer>
</template>

<script>
import {enrollmentAddService} from "@/api/enrollment.js";
import {ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElMessage} from 'element-plus';
import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import footer1 from "@/components/Footer/Footer.vue";
import {userLoginService} from "@/api/user.js";
import {useTokenStore} from '@/stores/token.js'
import {useRouter} from "vue-router";
import {User} from "@element-plus/icons-vue";
import Footer from "@/components/Footer/Footer.vue";

export default {
  setup() {
    const router = useRouter()

  },
  components: {TopNavBar,Footer},
  data() {

    return {
      isMobile:window.innerWidth < 768,
      formData: {
        name: '',
        studentId: '',
        department: '',
        phoneNumber: '',
        reason: '',
        customReason: '',
        desiredPosition: '',
        introduction: '',
        email:''
      },
      isC2Visible: false,
      reasons:[
        { value: '兴趣爱好', label: '兴趣爱好' },
        { value: '提升技能', label: '提升技能' },
        { value: '社交拓展', label: '社交拓展' },
      ],
      formRules: {
        // 添加表单验证规则，这里仅示例
        name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
        studentId: [{ required: true, message: '学号不能为空', trigger: 'blur' }],

        department: [{ required: true, message: '年级不能为空', trigger: 'blur' }],
        introduction: [{ required: true, message: '自我介绍不能为空', trigger: 'blur' }],
      },
      registerData:{
        username: '',
        password: ''
      },
      rules:{
        username:[
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min:3,max:15,message: '用户名长度不符合要求', trigger: 'blur'}
        ],
        password:[
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min:5,max:16,message: '密码长度不符合要求', trigger: 'blur'}
        ],
      },


    };
  },
  methods: {
    submitForm(formType) {
      // Logic for form submission
      console.log('Form submitted:', formType, this.formData);
    },

    async login(){
      //调用接口,完成登录
      console.log('用户数据 ' + this.registerData )
      let result =  await userLoginService(this.registerData);
      // console.log(result)
      ElMessage.success(result.msg ? result.msg : '登录成功')
      //把得到的token存储到pinia中
      console.log(result.data)

      const tokenStore = useTokenStore();
      tokenStore.setToken(result.data)
      // tokenStore.setToken(result.data)


      //跳转到首页 路由完成跳转
      this.$router.push('/admin')
    },

    async onSubmit () {
      console.log(this.formData)
      if (this.formData.name!=='' && this.formData.phoneNumber!=='' && this.formData.department!=='' && this.formData.phoneNumber!=='' && this.formData.introduction!==''){
        // 提交表单数据逻辑
        let result = await enrollmentAddService(this.formData)
        ElMessage.success(result.msg? result.msg : '提交成功');

        this.formData.name='';
        this.formData.phoneNumber='';
        this.formData.department='';
        this.formData.phoneNumber='';
        this.formData.introduction='';
        this.formData.customReason='';
        this.formData.reason='';
        this.formData.desiredPosition='';
        this.formData.email='';


        // console.log(formData);
      }else {
        ElMessage.error('请填写完整信息')
      }
    },


    toggleForm() {
      // Toggle between sign in and sign up forms
      this.isC2Visible = !this.isC2Visible;
    },
    validatePhoneNumber(rule, value, callback) {
      // 这里可以添加手机号码的验证逻辑
      if (!/^\d{11}$/.test(value)) {
        return callback(new Error('请输入正确的手机号码'));
      }
  callback();
    }
  },
  mounted() {
    //监听窗口大小变化
    window.addEventListener('resize', () => {
      this.isMobile = window.innerWidth < 768;
      console.log(this.isMobile)
    });



    // Add event listeners after component mounted
    const allButtons = document.querySelectorAll(".submit");
    const switchBtn = document.querySelectorAll(".switch-btn");
    const switchCtn = document.querySelector("#switch-cnt");
    const switchCircle = document.querySelectorAll(".switch_circle");
    const switchC1 = document.querySelector("#switch-c1");
    const switchC2 = document.querySelector("#switch-c2");
    const aContainer = this.$refs.aContainer;
    const bContainer = this.$refs.bContainer;

    const getButtons = (e) => e.preventDefault();
    const changeForm = () => {
      switchCtn.classList.add("is-gx");
      setTimeout(function () {
        switchCtn.classList.remove("is-gx");
      }, 1500);
      switchCtn.classList.toggle("is-txr");
      switchCircle[0].classList.toggle("is-txr");
      switchCircle[1].classList.toggle("is-txr");
      switchC1.classList.toggle("is-hidden");
      switchC2.classList.toggle("is-hidden");
      aContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-z");
    };

    for (let i = 0; i < allButtons.length; i++) {
      allButtons[i].addEventListener("click", getButtons);
    }
    for (let i = 0; i < switchBtn.length; i++) {
      switchBtn[i].addEventListener("click", changeForm);
    }
  }
};
</script>

<style scoped>
* {
  list-style: none;
  padding: 0;
  margin: 0;
  text-decoration: none;
  box-sizing: border-box;
  user-select: none;
}

body {
  font-family: Arial, sans-serif; /* Adding a default font family */
  background-color: #ecf0f3;
}

.demo {
  position: relative;
  width: 100%;
  height: 100vh;
  padding: 25px;
  background-color: #ecf0f3;
  overflow: hidden;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 600px;
  height: 100%;
  padding: 25px;
  background-color: #ecf0f3;
  transition: all 1.25s ease;
}

.form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  padding-right: 20px;
}

.form_input {
  width: 350px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  background-color: #ecf0f3;
  transition: all 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}

.form_input:focus {
  box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}

.form_span {
  margin-top: 30px;
  margin-bottom: 12px;
}

.form_link {
  color: #181818;
  font-size: 15px;
  margin-top: 25px;
  border-bottom: 1px solid #a0a5a8;
  line-height: 2;
  cursor: pointer;
}

.title {
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
  letter-spacing: 10px;
}

.description {
  font-size: 14px;
  letter-spacing: 0.25px;
  text-align: center;
  line-height: 1.6;
}

.button {
  width: 180px;
  height: 50px;
  border-radius: 25px;
  margin-top: 50px;
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1.15px;
  background-color: #4B70E2;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
  cursor: pointer;
  transition: all 0.25s ease;
}

.button:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
}

.button:active {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
}

.a-container {
  z-index: 100;
  left: calc(100% - 800px);
  transition: all 1.25s ease;
}

.b-container {
  left: calc(100% - 800px);
  z-index: 0;
  margin-left: 120px;
  transition: all 1.25s ease;
}

.switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 400px;
  padding: 50px;
  z-index: 200;
  background-color: #ecf0f3;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #d1d9e6;
  transition: all 1.25s ease;
  overflow: hidden;
}

.switch_circle {
  position: absolute;
  width: 500px;
  height: 500px;
  border-radius: 50%;
  background-color: #ecf0f3;
  box-shadow: inset 8px 8px 12px #b8bec7, inset -8px -8px 12px #fff;
  bottom: -60%;
  left: -60%;
  transition: all 1.25s ease;
}

.switch_circle-t {
  top: -30%;
  left: 60%;
  width: 300px;
  height: 300px;
}

.switch_container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: absolute;
  width: 400px;
  padding: 50px 55px;
  transition: all 1.25s ease;
}

.switch_container_mobile {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  padding: 50px 55px;
  transition: all 1.25s ease;
}

.switch_button {
  cursor: pointer;
  transition: all 0.25s ease;
}

.switch_button:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
}

.switch_button:active,
.switch_button:focus {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
}

.is-txr {
  left: calc(100% - 400px);
  transform-origin: left;
}

.is-txl {
  left: 0;
  transform-origin: right;
}

.is-z {
  z-index: 200;
}

.is-hidden {
  visibility: hidden;
  opacity: 0;
  position: absolute;
}

.is-gx {
  animation: is-gx 1.25s;
}

@keyframes is-gx {
  0%, 10%, 100% {
    width: 400px;
  }
  30%, 50% {
    width: 500px;
  }
}
</style>
