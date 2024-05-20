<template>
  <div class="messages" :style="{ width: isMobile ? '90%' : '40%' }">
    <div style="text-align: center; margin-bottom: 20px; font-size: 20px;margin-top: 30px">
      欢迎留言，分享心声！
    </div>


    <h1>留言板</h1>

    <div class="form">
      <input type="text" v-model="message.username" placeholder="用户名">
      <textarea v-model="message.message" placeholder="留言内容"></textarea>
      <button @click="submitMessage" class="submitBtn">留言</button>
    </div>

    <div id="messageBoard">
      <div class="message" v-for="(msg, index) in messages" :key="index">
        <div class="message-info">
          <div class="info">
            <img src="@/assets/logo.jpg" alt="头像" :style="{width : isMobile ? '60px' : '100px'}">
            <a>{{ msg.username }}</a>
          </div>
          <span v-if="isMobile!==true">发布于:<br>{{ msg.createTime }}</span>
        </div>
        <div class="content">
          {{ msg.message }}
        </div>
        <br>
        <span v-if="isMobile===true">{{ msg.createTime }}</span>
      </div>
    </div>

  </div>
</template>


<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { messageAddService, messageAllInfoService } from '@/api/message.js';
import * as sysTool from "@/utils/tool.js";
import { ElMessage } from "element-plus";

const isMobile = ref(window.innerWidth < 768);
const messages = ref([]);
let message = ref({
  ip: '',
  area: '未知',
  browser: '',
  os: '',
  username: '',
  message: '',
  create_time: ''
});

const getMessages = async () => {
  const res = await messageAllInfoService();
  messages.value = res.data;
}
getMessages();

const submitMessage = async () => {
  if (message.value.message === '') {
    ElMessage("请输入内容");
    return;
  }
  if (message.value.message.length > 200) {
    ElMessage("输入的太长了");
    return;
  }
  if (message.value.username === '') {
    message.value.username = '匿名';
  }
  message.value.browser = sysTool.GetCurrentBrowser();
  message.value.os = sysTool.GetOs();
  await messageAddService(message.value);
  await getMessages();
}

onMounted(() => {
  window.addEventListener("resize", () => {
    isMobile.value = window.innerWidth < 768;
  });
});

onUnmounted(() => {
  window.removeEventListener("resize", () => {});
});
</script>

<style scoped>

* {
  box-sizing: border-box;
  text-shadow: 0px 0px 20px #ffffff;
}

.messages {

  height: auto;
  margin: 50px auto;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background: rgba(255, 255, 255, 0.8);
  border-radius: 20px;
  box-shadow: 0 10px 50px rgba(0, 0, 0, 0.1);
}

.form {
  width: 100%;
  margin-bottom: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

input,
textarea {
  width: 90%;
  margin-bottom: 20px;
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 10px;
  font-size: 1rem;
  font-family: 'Poppins', sans-serif;
}

input:focus,
textarea:focus {
  border-color: #764ba2;
  outline: none;
  box-shadow: 0 0 10px rgba(118, 75, 162, 0.5);
}

.submitBtn {
  padding: 10px 30px;
  font-size: 1.2rem;
  font-family: 'Poppins', sans-serif;
  color: #fff;
  background: linear-gradient(90deg, rgba(144, 150, 255, 0.9) 0%, #c3c6fc 100%);
  border: none;
  border-radius: 50px;
  cursor: pointer;
  transition: background 0.3s ease;
}

.submitBtn:hover {
  background: linear-gradient(90deg, rgba(144, 150, 255, 0.7) 0%, #a3a6fc 100%);
}

#messageBoard {
  width: 100%;
}

.message {
  text-shadow: 0px 0px 20px #ffffff;
  width: 100%;
  margin: 20px 0;
  padding: 15px;
  background-image: linear-gradient(90deg, rgba(144, 150, 255, 0.9) 0%, #c3c6fc 100%);
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  animation: messageFadeIn 0.5s ease forwards;
  display: flex;
  flex-direction: column;
}

.message-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.info {
  display: flex;
  align-items: center;
}

.info img {
  border-radius: 50%;
  border: 3px solid #fff;
}

a {
  margin-left: 15px;
  font-size: 1.5rem;

  color: #333;
  text-decoration: none;
}

.content {
  font-size: 1.3rem;

  color: #555;
  white-space: pre-wrap;
  word-wrap: break-word;
}

@keyframes messageFadeIn {
  0% {
    opacity: 0;
    transform: translateY(20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.message-info span {
  font-size: 1.2rem;
  color: #777;
}
</style>
