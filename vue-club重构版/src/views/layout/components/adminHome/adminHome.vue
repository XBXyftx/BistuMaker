<template>
  <ul>
    <li>
      <h2>HELLO</h2>
      <p>
        你好👋！欢迎来到北信科创客空间后台管理界面，我是21级计科学生神乐之魂。很高兴你们能看到这句话，前台界面由邓婷同学完成，后端和后台主要由我完成，也辛苦邓婷同学了。我的编码能力尚有欠缺，可能会存在一些bug和不足之处。尽管我已经尽力确保代码的质量，但在这个学习和成长的过程中，难免会遇到一些挑战和困难。如果您在使用过程中遇到任何问题或发现任何bug，我将不胜感激地接受您的反馈，并尽快加以修复和改进。您可以通过邮件、即时通讯工具或其他方便的方式与我联系，我将尽最大努力确保我们的项目能够稳健、顺畅地运行。再次感谢您的支持与理解！”

      </p>

      <p class="it">
        <a href="https://github.com/Kaguranotamashii/club_demo.git" target="_blank">前 端</a>
        <span>Vue3 + Pinia + Element-plus + Vue-Router</span>
      </p>
      <p class="it">
        <a href="https://github.com/Kaguranotamashii/club_demo.git" target="_blank">后 端</a>
        ：
        <span>SpringBoot3 + Mysql + Mybatis</span>
      </p>
      <p>该后台诞生于2023年3月</p>
    </li>
    <li>
      <h2>关于我</h2>
      <p>
        <br>
        微信:Kagura_Tamashii<br>
        QQ:1092983146
      </p>
    </li>
  </ul>
</template>

<script setup></script>

<style scoped lang="scss">
ul li {
  margin-bottom: 10px;
  padding: 25px 20px;
  background-color: #fff;
  border-radius: 10px;
  h2 {
    margin-bottom: 15px;
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
      background-color: rgba(27, 31, 35, 0.05);
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
</style>