<template>
  <div class="shell1">
    <header>
      <div class="img" :style="imgStyle">
        <carousel-main></carousel-main>
      </div>
    </header>
  </div>
</template>

<script>
import CarouselMain from "@/views/home/carouselMain/carouselMain.vue";

export default {
  components: {CarouselMain},
  data() {
    return {
      imgStyle: {
        transform: 'scale(1)',
        opacity: 1
      }
    };
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll() {
      const scrollValue = window.scrollY;
      const maxScroll = document.documentElement.scrollHeight - window.innerHeight;
      const scrollPercentage = scrollValue / maxScroll;
      const clipValue = 100 - scrollPercentage * 30;

      this.imgStyle = {
        transform: `scale(${1 + scrollPercentage * 0.9})`,
        clipPath: `polygon(0 0, 100% 0, 100% ${clipValue}%, 0 100%)`,
        opacity: 1 - scrollPercentage * 0.1
      };
    }
  }
};
</script>

<style  scoped>
.shell1 {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  overflow-x:hidden;
}
.img {
  width: 100%;
  height: 95vh;
  position: relative;
  overflow: hidden;

  background-size: cover;
  transition: transform 0.5s ease, opacity 0.5s ease;
}
</style>
