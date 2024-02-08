<template>
      <div class="notice" @click="toggleDetails">

        <el-icon style="float: right;margin-top: 20px" v-if="!showDetails"><ArrowUpBold /></el-icon>
        <el-icon style="float: right;margin-top: 20px" v-else><ArrowDownBold /></el-icon>


        <h3><el-icon><Tickets /></el-icon> 公告</h3>


        <transition name="fade" v-for="item in noticeList">
        <div class="details" v-if="showDetails">
          <p style=" color: #fff;font-size: 1.5rem;margin: 10px   ">
          {{item.title}}
          </p>
          <div v-html="item.content">
          </div>
        </div>
        </transition>


      </div>
</template>

<script setup>
import { ref } from 'vue';
import {ArrowDownBold, ArrowUp, ArrowUpBold, Tickets} from "@element-plus/icons-vue";
import {notificationsGetIsReadService} from "@/api/notifications.js";
const showDetails = ref(false);
const toggleDetails = () => {
  showDetails.value = !showDetails.value;
}
const noticeList = ref([]);
const getData =async () => {
 const result =await notificationsGetIsReadService()
  console.log(result.data)
  noticeList.value = result.data;
}

getData()


</script>

<style scoped>
.notice {
  border: 1px solid rgba(204, 204, 204, 0.32);
  background: linear-gradient(45deg, #ff6666, #ff9999);
  padding: 1px 20px 20px;
  cursor: pointer;
  width: 80%;
  border-radius: 15px;
  margin: 10px auto 0;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h3 {
  color: #fff;
  margin-bottom: 1px;

}

.details {
  margin-top: 15px;
  padding: 15px;
  border: 1px solid #ddd;
  background: linear-gradient(45deg, #ff6666, #ff9999);
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 过渡动画的类名定义 */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.1s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}
</style>
