<script setup>


const props = defineProps({
  from:String,
  content:String
});

// 获取图片的动态路径
// const getSrc = (name) =>
//   new URL(`../assets/${name}`, import.meta.url).href;
// const avatar = props.from === "ROB" ? "rob.jpg" : "girl.png";
//





</script>

<template>
  <div class="chatItem" :class="props.position">
    <img :src="'src/assets/' + props.from + '.png'" alt="avatar" class="avatar" />
    <div class="message">
<!--      <div class="ballText">{{ props.content }}</div>-->
      <vuetyped :strings="[content]"  :smart-backspace="true" :typeSpeed="0">
        <div class="typing" />
      </vuetyped>

    </div>
  </div>
</template>

<style scoped lang="scss">







.chatItem {
  position: relative;
  //padding: 10px 20px;
  border-radius: 10px;
  background: #d7d7d7;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  margin-bottom: 10px;
  width: 100%;
  img {
    width: 38px;
    height: 38px;
    border-radius: 8px;

  }

  .message {

    margin-top: 8px;
    width: 100%;
    .chatContent {
      max-width: 75%;
      word-break: break-word;
      white-space: pre-line;
      font-size: 16px;
    }
  }

}
</style>
