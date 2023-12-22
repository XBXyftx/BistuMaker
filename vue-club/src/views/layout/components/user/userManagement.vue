<script setup>
import userInfo from '@/views/layout/components/user/userInfo/userInfo.vue'
import { ref } from 'vue'
import UserAllInfo from "@/views/layout/components/user/userAllInfo/userAllInfo.vue";
// const editableTabs = ref(
//     [{
//       title: 'Tab 1',
//       name: '1',
//       content: '<user-info></user-info>'
//     }, {
//       title: 'Tab 2',
//       name: '2',
//       content: 'Tab 2 content'
//     }]
// )
</script>
<template>

  <el-tabs type="border-card" >
    <el-tab-pane label="用户管理">
      <user-info></user-info>
    </el-tab-pane>
    <el-tab-pane label="全体用户管理">
    <user-all-info></user-all-info>
    </el-tab-pane>
  </el-tabs>

</template>

<style   scoped>

</style>