<!-- Footer.vue -->

<template>
  <footer>
    <div class="container">
      <div class="sec aboutus">
        <h2>关于我们</h2>
        <p> 创客空间是北京信息科技大学的科技社团，致力于促进创新与技术的交流与合作。通过举办工作坊、比赛和分享会等活动，为学生提供了一个广阔的学术与创意平台，激发了创造力，培养了团队协作精神，推动了科技创新。</p>
        <ul class="sci">
<!--          <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>-->
<!--          <li><a href="#"><i class="fa fa-twitter"  aria-hidden="true"></i></a></li>-->
<!--          <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>-->
<!--          <li><a href="#"><i class="fa fa-youtube-play" aria-hidden="true"></i></a></li>-->
        </ul>
      </div>

      <div class="sec quickLinks">
        <h2>链接</h2>
        <ul>
          <li><a href="#">关于</a></li>
<!--          <li><a href="#">FAQ</a></li>-->
<!--          <li><a href="#">Privacy Policy</a></li>-->
<!--          <li><a href="#">Help</a></li>-->
<!--          <li><a href="#">Terms & Consitions</a></li>-->
<!--          <li><a href="#">Contact</a></li>-->
        </ul>
      </div>

      <div class="sec contact">
        <h2>联系方式</h2>
        <ul class="info">
          <li>
            <span><i class="fa fa-map-marker" aria-hidden="true"></i></span>
            <span>北京市昌平区太行路55号</span>
          </li>
          <li>
            <span><i class="fa fa-phone" aria-hidden="true"></i></span>
            <p><a href="tel:12345678900">+1 234 567 8900</a><br>
</p>
          </li>
          <li>
            <span><i class="fa fa-envelope" aria-hidden="true"></i></span>
            <p><a href="mailto:knowmore@email.com">邮箱</a></p>
          </li>
        </ul>
      </div>
    </div>
  </footer>

</template>

<script setup>

</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppoms', sans-serif;
}

body{
  justify-content: flex-end;
  align-items: center;
  min-height: 100vh;
  flex-direction: column;
  background: #000000;
}

footer{
  position: relative;
  width: 100%;
  height: auto;
  padding: 50px 100px;
  background: #cccccc;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

footer .container{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  flex-direction: row;
}

footer .container .sec{
  margin-right: 30px;
}

footer .container .sec.aboutus{
  width: 40%;
}

footer .container h2{
  position: relative;
  color: #000000;
  font-weight: 500;
  margin-bottom: 15px;
}

footer .container h2::before{
  content: "";
  position: absolute;
  bottom: -5px;
  left: 0;
  width: 50px;
  height: 2px;
  background: #3c92f3;
}

footer p{
  color: #000000;
}

.sci{
  margin-top: 20px;
  display: flex;
}

.sci li{
  list-style: none;
}

.sci li a{
  display: inline-block;
  width: 40px;
  height: 40px;
  background: #222;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  text-decoration: none;
  border-radius: 4px;
}

.sci li a:hover{
  background: #f00;
}

.sci li a .fa{
  color: #fff;
  font-size: 20px;
}

.quickLinks{
  position: relative;
  width: 25%;
}

.quickLinks ul li{
  list-style: none;
}

.quickLinks ul li a{
  color: #999;
  text-decoration: none;
  margin-bottom: 10px;
  display: inline-block;
}

.quickLinks ul li a:hover{
  color: #fff;
}

.contact{
  width: calc(35% - 60px);
  margin-right: 0 !important;
}

.contact .info{
  position: relative;
}

.contact .info li{
  display: flex;
  margin-bottom: 16px;
}

.contact .info li span:nth-child(1){
  color: #fff;
  font-size: 20px;
  margin-right: 10px;
}

.contact .info li span{
  color: #999;
}

.contact .info li a{
  color: #999;
  text-decoration: none;
}

.contact .info li a:hover{
  color: #fff;
}

.copyrightText{
  width: 100%;
  background: #181818;
  padding: 8px 100px;
  text-align: center;
  color: #999;
}

@media (max-width: 768px){
  footer{
    padding: 40px;
  }
  footer .container{
    flex-direction: column;
  }
  footer .container .sec{
    margin-right: 0;
    margin-bottom: 40px;
  }
  footer .container .sec.aboutus, .quickLinks, .contact{
    width: 100%;
  }
  .copyrightText{
    padding: 8px 40px;
  }
}

footer{
  position: absolute;


}
</style>
