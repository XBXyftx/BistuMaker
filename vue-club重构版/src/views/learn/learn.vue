<script setup>


import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import footer1 from "@/components/Footer/footer.vue";

</script>

<template>
  <TopNavBar>
  </TopNavBar>


  <div class="shell">
    <label for="button1" class="button" style="--i:0">校外</label>
    <input type="radio" id="button1" name="page" checked>
    <div class="page">
      <div class="page_content">
        <h1>参加前沿科技大会</h1>
        <p>鸿蒙技术开发</p>
        <span>
                    再看山顶，云下百尺，一处小院里铺着地砖摆着家具，小到碗筷靠垫，大到衣柜被褥，无不齐全。藕衣人不知从哪踱出来，进了前厅往八仙椅上一坐，打开那摞账本，从倒数第三册里抽出一页，轻轻抖动，纸的夹缝里漏出一颗围棋黑子，落地清脆，“瑭啷”一声。
                </span>
      </div>
    </div>


    <label for="button2" class="button" style="--i:1">校内</label>
    <input type="radio" id="button2" name="page">
    <div class="page">
      <div class="page_content">
        <h1>学习硬件技术</h1>
        <p>激光切割机</p>
        <span>绩眉头一挑，道：“生意场，你死我活的地方，得罪几个人再寻常不过。只是除了司岁台谁又知道我们谁是谁？种稻子的叫技术天师，打铁的算豪放大姐，我充其量一个季老板，再恨我的人能有多恨呢。”
                </span>
      </div>
    </div>


    <label for="button3" class="button" style="--i:2">假期</label>
    <input type="radio" id="button3" name="page">
    <div class="page">
      <div class="page_content">
        <h1>假期长期任务</h1>
        <p>跟进社团指导老师</p>
        <span>
                    他随即想到那三张老脸。人的寿命之于岁兽不过蚍蜉，然而蚍蜉亦有渡海之志，年轻的雄心一轮滚过一轮，老脸复老脸，大炎早已由人类话事，司岁台一个当差的小子都敢同他大声说话。
                </span>
      </div>
    </div>
  </div>


  <footer1>
  </footer1>
</template>

<style scoped>


.shell {
  position: relative;
  width: 100%;
  min-width: 1000px;
  min-height: 670px;
  height: 600px;
  overflow: hidden;
}
.page{
  transform: translateX(-110%);
  position: absolute;
  width: 50%;
  height: 100%;
  transition: .5s;
  background-size: cover;
}
.button{
  display: block;
  width: 10%;
  color: #fff;
  font-size: 30px;
  border-bottom: #fff solid 4px;
  text-align: center;
  position: absolute;
  bottom: 10px;
  margin: 50px;
  left: calc(50% + (var(--i)*17%));
  z-index: 999;
  transition: .3s;
}
.page_content{
  box-sizing: border-box;
  height: 100%;
  padding: 50px;
  color: #FFF;
  position: absolute;
  left: 100%;
  display: none;
  animation-name: fade;
  animation-duration: .7s;
  animation-iteration-count: 1;
  opacity: 1;
}
@keyframes fade{
  0%{
    opacity: 0;
  }
  50%{
    opacity: 0;
    transform: translateY(-50px);
  }
  100%{
    opacity: 1;
    transform: translateY(0px);
  }
}
input{
  display: none;
}
input:checked+.page{
  transform: translateX(0px);
  transition: .4s;
}
input:checked+.page .page_content{
  width: 100%;
  display: block;
}
.page:nth-child(3){
  background-image: url('https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/HeadPic.jpg');
}
.page:nth-child(3) .page_content{
  background-color: #eee0e5;
}

.page:nth-child(6){
  background-image: url('https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/HeadPic.jpg');
}
.page:nth-child(6) .page_content{
  background-color: #ffe7ba;
}

.page:nth-child(9){
  background-image: url('https://kaguranotamashii-1308648145.cos.ap-beijing.myqcloud.com/HeadPic.jpg');
}
.page:nth-child(9) .page_content{
  background-color: #eee8aa;
}
.page h1{
  font-size: 70px;
}
.page p{
  font-size: 30px;
  padding: 10px;
  background-color: rgba(0, 0, 0, .3);
  border-radius: 10px;
  margin: 30px 0;
}
.page span{
  font-size: 26px;
  letter-spacing: 2px;
  color: rgb(56, 56, 56);
}
label:hover{
  cursor: pointer;
  border-bottom: solid 4px rgba(0, 0, 0, .3);
}



.nav{
  width: 100%;
  height: 80px;
  position: relative;
  top: 0;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.5);
  background-color: #fff;
  z-index: 300;
}
.nav ul{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  right: 5%;
}
.nav ul .nav-box{
  position: absolute;
  bottom: 0;
  left: 0;
  width: calc((100% / 6)*1);
  height: 10px;
  border-radius: 2px;
  transition: .5s;
}
.nav ul li{
  width: 100%;
  text-align: center;
}
.nav ul li a{
  color: rgb(70, 100, 180);
  font: 100 25px '优设标题黑';
  display: block;
  width: 100%;
  height: 100%;
  line-height: 70px;
  font-weight: bold;
}
.nav ul li:nth-child(3):hover~.nav-box{
  left: calc(100% / 6 *2);
  background-color: #99ffff;
}
.nav ul li:nth-child(4):hover~.nav-box{
  left: calc(100% / 6 *3);
  background-color: #ffb1b1;
}
.nav ul li:nth-child(5):hover~.nav-box{
  left: calc(100% / 6 *4);
  background-color: #8da1f8;
}
.nav ul li:nth-child(6):hover~.nav-box{
  left: calc(100% / 6 *5);
  background-color: #7df88e;
}



.footer {
  padding: 95px 0;
  text-align: center;
  background-color: #9999cc;
}

.footer a {
  color: #fff;
  display: inline-block;
}
</style>