<script setup>

import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";

import footer1 from "@/components/Footer/footer.vue";
import Content from "@/components/Content/content.vue";
import ContentActivity from "@/views/activity/ContentActivity.vue";
import ContentContainer from "@/views/activity/contentContainer.vue";


</script>
<template>
  <TopNavBar>
  </TopNavBar>

  <ContentActivity></ContentActivity>



  <footer1>
  </footer1>

</template>
<style scoped>
.content-container {
  margin-top: 50px;
  display: flex;
  align-items: center; /* 保持垂直居中 */
  justify-content: center; /* 添加这一行以实现水平居中 */
  width: 100%; /* 或者给一个固定宽度，并且需要设置其父元素的定位为相对定位 */
  max-width: 90%; /* 示例：最大宽度为800px */
  //margin: 0 auto; /* 如果父元素不是绝对定位，则可使用这种方式进行水平居中 */
  margin-left: 128px;
  margin-right: 128px;
}

.text-content {
  //flex: 1; /* 文字部分占据剩余空间 */
  //padding-right: 20px; /* 可根据需求设置间距 */
  width: 50%;
  margin-right: 20px;
}

.image-content {
  //flex: 0 0 auto; /* 图片宽度自适应，不参与flex-grow */
  //margin-left: 20px;
  //text-align: right; /* 可选：使图片右对齐（如果需要） */
}
/* 当屏幕宽度小于或等于600px时，让文字和图片分别占据整行 */
@media (max-width: 600px) {
  .text-content,
  .image-content {
    width: 100%;
    order: 1; /* 文字内容的顺序优先级设置为1 */
  }

  .text-content {
    order: 2; /* 图片内容的顺序优先级设置为2，确保它在文字下方显示 */
  }
}
</style>
