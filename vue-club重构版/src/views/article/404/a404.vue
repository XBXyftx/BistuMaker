<template>
  <div class="err404">
    <h4>您访问的页面不见了！</h4>
    <h5>
      <router-link to="/" class="go-home">去首页</router-link>
    </h5>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>

<style scoped>
/* 确保 body 或者父容器没有其他影响居中的样式 */
body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* 确保整个视窗高度都能看到居中内容 */
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, sans-serif; /* 添加通用字体设置以美化样式 */
}

.err404 {
  text-align: center; /* 确保文本元素居中 */
  max-width: 500px; /* 可自定义最大宽度，以便在不同屏幕尺寸下有更好的展示效果 */
  margin: 0 auto; /* 去掉无用的重复值 */
  padding: 40px; /* 添加内边距以增加视觉美感 */
  padding-top: 200px;
  padding-bottom: 300px;
  background-color: #f8f9fa; /* 添加背景颜色以区别于背景 */
  border-radius: 8px; /* 添加圆角以美化样式 */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}

.err404 h4 {
  font-size: 24px;
  color: #191919;
  padding-bottom: 20px; /* 调整下间距以适应整体布局 */
  margin: 0; /* 清除默认的顶部和底部外边距 */
}

.err404 .go-home {
  width: 130px;
  height: 38px;
  background: #c9141d;
  line-height: 38px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  display: inline-block;
  border-radius: 2px;
  cursor: pointer; /* 添加指针样式表示可点击 */
  transition: background-color 0.3s ease; /* 添加过渡动画 */
}

.err404 .go-home:hover {
  background-color: #dd1b22; /* 鼠标悬停时改变背景颜色 */
}
</style>