<script setup>
import { ref } from "vue";
import ChatItem from "@/views/home/answer/ChatItem.vue";

// 数据
const MAN = "man";
const ROB = "rob";

const dialog = ref([
  { from: ROB, content: "hello" },
]);
//显示状态true和false
let show = ref(false);


// const inputMessage = ref("");

// const outputMessage = "";
//问答数据对象
let answer = [
  {
    id: 7,
    question: "原神新版本有什么亮点吗？",
    answer: "新版本啊，哈哈，我要告诉你，这个真的是...充满了惊喜和新奇的内容哦！"
  },
  {
    id: 8,
    question: "原神中最喜欢的角色是谁？",
    answer: "最喜欢的角色啊，嘿嘿，我来点小废话，你知道吗，这个角色可真是...让人无法忘怀的魅力存在啊。"
  },
  {
    id: 9,
    question: "原神有哪些独特的游戏机制？",
    answer: "独特的游戏机制啊，哇，我要给你说说这个...让人感叹不已的设计理念！"
  },
  {
    id: 10,
    question: "原神的音乐如何？",
    answer: "音乐啊，哈哈，废话来了，这个...简直就是游戏中的一大亮点，给人一种身临其境的感觉啊。"
  },
  {
    id: 11,
    question: "原神中有哪些隐藏任务值得一试？",
    answer: "隐藏任务啊，嗯，有个小故事，这个...是我在游戏中探索时意外发现的一些令人惊喜的小秘密！"
  },
  {
    id: 12,
    question: "原神的美术风格如何吸引你？",
    answer: "美术风格啊，哦，我来加点废话，这个...简直就是游戏中的一幅幅艺术品，让人仿佛置身于梦幻世界一般。"
  },
];
// 方法
function submit(answer,question) {
  if(show.value===false){
    show.value = true;
    const messageMAN = { content: question , from: MAN };
    dialog.value.push(messageMAN);
    setTimeout(function() {
      const messageBOT = { content: answer , from: ROB };
      dialog.value.push(messageBOT);
      show.value=false;
    }, 1000);
    // answer = answer.filter(item => item.id === 1);

    show.value=false;

  }else{

  }


}
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
});
//根据设备设置宽度
const with1 = ref('75%');
if (isMobile.value) {
  with1.value = '100%';
}

</script>

<template>
  <div class="chat" v-bind:style="{ width: with1}" >
    <h1>机器人为你服务</h1>

    <div class="dialog">
      <TransitionGroup name="list" tag="ul">
      <ChatItem
          v-for="item in dialog"
          :content="item.content"
          :from="item.from"
      ></ChatItem>
      </TransitionGroup>
    </div>
    <TransitionGroup name="list" tag="ul">
    <div v-for="item in answer" :key="item.id"  class="flex-container">
      <button class="centered-button" @click="submit(item.answer,item.question)">{{item.question}}</button>
    </div>
    </TransitionGroup>
  </div>
</template>

<style scoped lang="scss">
.flex-container {
  //display: flex;
  display: inline-block;
  flex-wrap: nowrap; /* 防止换行 */
  justify-content: space-between; /* 在子元素间均匀分配间距 */
}
.centered-button {
  margin-left: 20px;
  margin-top: 5px;
  //display: inline-block;
  font-size: 16px;
  padding: 10px 20px;
  text-align: center;
  color: white;
  background-color: rgba(117, 113, 117, 0.5);
  border: none;
  cursor: pointer;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.centered-button:hover {
  background-color: royalblue;
}

.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(30px);
}


.chat {
  //居中
  margin: 0 auto;
  width: 80%;
  transition: all 0.2s ease;
  position: relative;

  h1 {
    font-size: 18px;
    text-align: center;
    margin-bottom: 10px;
    //color: whitesmoke;
  }

  .dialog {
    //width: 100%;
    //height: 80%;
    //min-height: 500px;
    //margin-bottom: 10px;
    //background-color: whitesmoke;
    //border-radius: 10px;
    //padding: 10px;
  }

  //.input {
  //  width: 100%;
  //  min-height: 50px;
  //  border-radius: 10px 10px 20px 20px;
  //  padding: 5px;
  //
  //  input {
  //    width: 85%;
  //    min-height: 40px;
  //    padding: 5px;
  //    font-size: 16px;
  //    border: none;
  //    outline: none;
  //    margin-right: 3%;
  //    border-radius: 5px;
  //    word-break: break-word;
  //    white-space: pre-line;
  //    background-color: whitesmoke;
  //  }
  //
  //}
}
</style>
