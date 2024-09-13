<template>
    <ul>
      <li>
        <h2>HELLO</h2>
        <p>
          你好👋！欢迎来到北信科创客空间后台管理界面，我是21级计科学生神乐之魂，负责本社官网的主要开发工作。很高兴你们能看到这段话，前台界面以及后期的维护工作由计科22级同学彗星犬同学完成，后端和后台主要由我完成，也辛苦彗星犬同学了。
        </p>
        <p>
          我的编码能力尚有欠缺，可能会存在一些bug和不足之处。尽管我已经尽力确保代码的质量，但在这个学习和成长的过程中，难免会遇到一些挑战和困难。如果您在使用过程中遇到任何问题或发现任何bug，我们将不胜感激地接受您的反馈，并尽快加以修复和改进。您可以通过邮件、即时通讯工具或其他方便的方式与我们联系，我们将尽最大努力确保我们的项目能够稳健、顺畅地运行。再次感谢您的支持与理解！
        </p>

        <p class="it">
          <a href="https://github.com/Kaguranotamashii/club_demo.git" target="_blank">前 端</a>
          ：
          <span>Vue3 + Pinia + Element-plus + Vue-Router</span>
        </p>
        <p class="it">
          <a href="https://github.com/Kaguranotamashii/club_demo.git" target="_blank">后 端</a>
          ：
          <span>SpringBoot3 + Mysql + Mybatis</span>
        </p>
        <p>该后台诞生于2024年3月</p>
      </li>
      <li>
        <h2>关于我们</h2>
        <p>
          <div class="box" style="background-color:#92d6ff">
            <h3>神乐之魂</h3>
            <p>
              <br>微信:Kagura_Tamashii
              <br>QQ:1092983146
            </p>
          </div>
          <div class="box" style="background-color:#fdf8d6">
            <h3>三速彗星犬</h3>
            <p>
              <br>微信:LongWayNoHome
              <br>QQ:1335527061
            </p>
          </div>
        </p>
      </li>
    </ul>
</template>

<script setup>
</script>

<style scoped lang="scss">

ul{
  list-style: none;
}
ul li {
  box-shadow: #000000 0 0 7px;
  background: #FFFFFF;
  padding: 10px 25px;
  margin-bottom: 30px;

  h2 {
    margin-bottom: 10px;
    font-size: 20px;
    letter-spacing: 3px;
    line-height: 24px;
    border-left: 5px solid #73b899;
    text-indent: 10px;
  }
  p {
    text-indent: 2em;
    color: #444;
    font-size: 14px;
    letter-spacing: 4px;
    line-height: 30px;
    a {
      color: #409eff;
      &:hover {
        text-decoration: underline;
      }
    }
  }
  p.it {
    span:nth-child(2) {
      color: #bd4147;
      font-weight: 700;
    }
  }

  .QR-code {
    display: flex;
    justify-content: end;
    img {
      width: 150px;
      height: 150px;
      margin-left: 20px;
      box-shadow: 0 0 4px #aaa;
      border-radius: 10px;
    }
  }
}

h3 {
  margin-top: 0;
  color: #444;
}

.box {
  display: inline-block;
  border: 1px solid #ccc;
  padding: 20px;
  margin-right: 30px;
  margin-top: 10px;
  vertical-align: top;
  box-shadow: #9a9a9a 0 0 5px;
}
</style>