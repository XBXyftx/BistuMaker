<template>
  <TopNavBar>
  </TopNavBar>

  <content></content>


  <Footer>
  </Footer>
</template>


<script setup>
import {ref} from 'vue'
import TopNavBar from "@/components/TopNavBar/TopNavBar.vue";
import Footer from "@/components/Footer/Footer.vue";


import 'vue-waterfall-plugin-next/dist/style.css'

import Content from "@/views/phoneAlbum/content/content.vue";

</script>

<style scoped>
.main{
  background: linear-gradient(135deg, #5C258D, #4389A2);
}

</style>