<template>
      <div class="notice" @click="toggleDetails(item.id)" v-for="item in noticeList">
        <el-icon style="float: right;margin-top: 20px" v-if="!isRead[item.id]"><ArrowUpBold /></el-icon>
        <el-icon style="float: right;margin-top: 20px" v-else><ArrowDownBold /></el-icon>
        <div style="display: inline-flex; align-items: center;">
        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" aria-hidden="true" role="img" class="flex-shrink-0" width="30" height="30" viewBox="0 0 32 32" style="color: rgb(31, 41, 55);"><path fill="currentColor" d="M21 6.07C21 4.37 22.38 3 24.07 3s3.06 1.37 3.07 3.07v6.225a3.722 3.722 0 0 1 0 7.19v6.235a3.07 3.07 0 0 1-6.14 0v-.232l-5.93-2.918a7.725 7.725 0 0 0-1.931-.648a8.166 8.166 0 0 0-1.459-.132l-.004-.01H10v6.018c0 .67-.54 1.21-1.21 1.21H6.216c-.67 0-1.21-.54-1.21-1.21V21.78h-1.28C2.772 21.78 2 21.03 2 20.11v-8.43c0-.93.77-1.68 1.727-1.68h8.045a7.844 7.844 0 0 0 3.3-.78L21 6.303V6.07ZM7.006 21.78v5.228H8V21.78h-.994ZM19.55 9.303l-3.384 1.665l-.007.004c-.992.478-2.06.798-3.159.942v7.719l.098.257c1.066.15 2.106.47 3.074.945l3.378 1.662V9.303Z"
        ></path></svg>
          &nbsp;&nbsp;&nbsp;&nbsp;
        <div style=" font-size: 1.4rem;margin: 10px;color:rgb(0,0,0);padding-top: 15px  ">
          {{item.title}}
        </div>

        </div>
        <transition name="fade">
        <div class="details" v-if="isRead[item.id]">
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
const toggleDetails = (id) => {

  showDetails.value = !showDetails.value;
  isRead.value[id] = !isRead.value[id];
}
const noticeList = ref([]);
//给我创建个哈希表，用来key为数字，value为Boolean
const isRead = ref({});
const getData =async () => {
 const result =await notificationsGetIsReadService()
  // console.log(result.data)
  //将result.data.id依次遍历到isRead中
  result.data.forEach((item) => {
    isRead.value[item.id] = false;
  })
  // console.log(isRead.value)
  noticeList.value = result.data;
}

getData()


</script>
<style scoped>
/* General styling for SVG elements */
svg {
  margin-top: 15px;
  vertical-align: text-bottom;
}

/* Notice container styling */
.notice {
  border: 1px solid rgba(204, 204, 204, 0.32);
  background: linear-gradient(45deg, #e7e7e7, #ffe5e5);
  padding: 20px; /* Combined padding for consistency */
  cursor: pointer;
  width: 80%;
  border-radius: 15px;
  margin: 1px auto; /* Simplified margin */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease; /* Added transition for hover effect */
}

/* Heading styling */
h3 {
  color: #000000;
  margin-bottom: 10px; /* Adjusted margin for better spacing */
}

/* Details section styling */
.details {
  margin-top: 15px;
  padding: 15px;
  border: 1px solid #ddd;
  background: linear-gradient(45deg, #e7e7e7, #ffe5e5);
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Transition animations for fade effects */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.1s ease; /* Increased duration for smoother transition */
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

/* Focus and hover states for notice container */
.notice:focus {
  outline: none;
}

.notice:hover {
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15); /* Enhanced shadow on hover */
}
</style>
