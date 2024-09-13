<template>


  <div style="background: linear-gradient(135deg, #5C258D, #4389A2);">
    <TopNavBar></TopNavBar>

    <h1 >博文</h1>
    <blogList></blogList>

    <Footer></Footer>
  </div>



</template>

<script setup>
import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import Footer from "@/components/Footer/Footer.vue";
import BlogList from "@/views/blog/blogList.vue";

</script>

<style scoped>

h1{
  text-align:center;
  color:#fff;
  font-size: 50px;
  padding-top: 20px;
  margin-top: 0;
}
</style>