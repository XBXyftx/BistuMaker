<script setup>
import img1 from '@/assets/title-2.png'
import img2 from '@/assets/title-1.png'
import {getCurrentInstance, ref, watch} from "vue";
//判断是否是移动端
const isMobile = ref(window.innerWidth < 768);
const Height = ref('100rpx');
//监听窗口大小变化
window.addEventListener('resize', () => {
  isMobile.value = window.innerWidth < 768;
  Height.value = isMobile.value ? '200px' : '30rem';
});

import animate from 'animate.css'

const scroll_ = number => {

  window.scrollTo({
    top: innerHeight || document.documentElement.clientHeight || document.body.clientHeight,
    behavior: "smooth"
  });
  console.log(1231)
  // scrollTo(0,innerHeight||document.documentElement.clientHeight||document.body.clientHeight)
}
</script>

<template>
  <div class="StarField">
    <canvas ref="canvas"></canvas>
    <div class="text">
      <img :src="img1" alt="" class="img1 animate__animated animate__zoomIn"
           :class="isMobile?'img1IsMobile':''"
      >
      <img :src="img2" alt="" class="img2 animate__animated animate__zoomIn"
           :class="isMobile?'img2IsMobile':''"
      >
      <div class="click animate__animated animate__fadeInDown animate__delay-1s animate__slow">
        <a @click="scroll_(123)">
          Go to know more⬇️
        </a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      STAR_COLOR: '#fff',
      STAR_SIZE: 3,
      STAR_MIN_SCALE: 0.2,
      OVERFLOW_THRESHOLD: 50,
      STAR_COUNT: 0, // 初始化为0，将在mounted中计算
      canvas: null, // 用于存放canvas元素引用
      context: null,
      scale: 1,
      width: 0,
      height: 0,
      stars: [],
      pointerX: null,
      pointerY: null,
      velocity: {x: 0, y: 0, tx: 0, ty: 0, z: 0.0009},
      touchInput: false,
    };
  },
  mounted() {
    this.STAR_COUNT = (window.innerWidth + window.innerHeight) / 9;
    this.canvas = this.$refs.canvas;
    this.context = this.canvas.getContext('2d');
    this.resize();
    this.generate();
    this.step();
    window.addEventListener('resize', this.resize);
    this.canvas.addEventListener('mousemove', this.onMouseMove);
    this.canvas.addEventListener('touchmove', this.onTouchMove);
    this.canvas.addEventListener('touchend', this.onMouseLeave);
    document.addEventListener('mouseleave', this.onMouseLeave);
  },
  beforeUnmount() {
    window.removeEventListener('resize', this.resize);
    this.canvas.removeEventListener('mousemove', this.onMouseMove);
    this.canvas.removeEventListener('touchmove', this.onTouchMove);
    this.canvas.removeEventListener('touchend', this.onMouseLeave);
    document.removeEventListener('mouseleave', this.onMouseLeave);
  },
  methods: {
    generate() {
      for (let i = 0; i < this.STAR_COUNT; i++) {
        this.stars.push({
          x: 0,
          y: 0,
          z: this.STAR_MIN_SCALE + Math.random() * (1 - this.STAR_MIN_SCALE),
        });
      }
    },
    placeStar(star) {
      star.x = Math.random() * this.width;
      star.y = Math.random() * this.height;
    },
    recycleStar(star) {
      let direction = 'z';
      let vx = Math.abs(this.velocity.x);
      let vy = Math.abs(this.velocity.y);
      if (vx > 1 || vy > 1) {
        let axis;
        if (vx > vy) {
          axis = Math.random() < vx / (vx + vy) ? 'h' : 'v';
        } else {
          axis = Math.random() < vy / (vx + vy) ? 'v' : 'h';
        }
        if (axis === 'h') {
          direction = this.velocity.x > 0 ? 'l' : 'r';
        } else {
          direction = this.velocity.y > 0 ? 't' : 'b';
        }
      }
      star.z = this.STAR_MIN_SCALE + Math.random() * (1 - this.STAR_MIN_SCALE);
      if (direction === 'z') {
        star.z = 0.1;
        star.x = Math.random() * this.width;
        star.y = Math.random() * this.height;
      } else if (direction === 'l') {
        star.x = -this.OVERFLOW_THRESHOLD;
        star.y = this.height * Math.random();
      } else if (direction === 'r') {
        star.x = this.width + this.OVERFLOW_THRESHOLD;
        star.y = this.height * Math.random();
      } else if (direction === 't') {
        star.x = this.width * Math.random();
        star.y = -this.OVERFLOW_THRESHOLD;
      } else if (direction === 'b') {
        star.x = this.width * Math.random();
        star.y = this.height + this.OVERFLOW_THRESHOLD;
      }
    },
    resize() {
      this.scale = window.devicePixelRatio || 1;
      this.width = window.innerWidth * this.scale;
      this.height = window.innerHeight * this.scale;
      this.canvas.width = this.width;
      this.canvas.height = this.height;
      this.stars.forEach(this.placeStar);
    },
    step() {
      this.context.clearRect(0, 0, this.width, this.height);
      this.update();
      this.render();
      requestAnimationFrame(this.step);
    },
    update() {
      this.velocity.tx *= 0.96;
      this.velocity.ty *= 0.96;
      this.velocity.x += (this.velocity.tx - this.velocity.x) * 0.8;
      this.velocity.y += (this.velocity.ty - this.velocity.y) * 0.8;
      this.stars.forEach((star) => {
        star.x += this.velocity.x * star.z;
        star.y += this.velocity.y * star.z;
        star.x += (star.x - this.width / 2) * this.velocity.z * star.z;
        star.y += (star.y - this.height / 2) * this.velocity.z * star.z;
        star.z += this.velocity.z;
        if (
            star.x < -this.OVERFLOW_THRESHOLD ||
            star.x > this.width + this.OVERFLOW_THRESHOLD ||
            star.y < -this.OVERFLOW_THRESHOLD ||
            star.y > this.height + this.OVERFLOW_THRESHOLD
        ) {
          this.recycleStar(star);
        }
      });
    },
    render() {
      this.stars.forEach((star) => {
        this.context.beginPath();
        this.context.lineCap = 'round';
        this.context.lineWidth = this.STAR_SIZE * star.z * this.scale;
        this.context.globalAlpha = 0.5 + 0.5 * Math.random();
        this.context.strokeStyle = this.STAR_COLOR;
        this.context.beginPath();
        this.context.moveTo(star.x, star.y);
        let tailX = this.velocity.x * 2;
        let tailY = this.velocity.y * 2;
        if (Math.abs(tailX) < 0.1) tailX = 0.5;
        if (Math.abs(tailY) < 0.1) tailY = 0.5;
        this.context.lineTo(star.x + tailX, star.y + tailY);
        this.context.stroke();
      });
    },
    movePointer(x, y) {
      if (typeof this.pointerX === 'number' && typeof this.pointerY === 'number') {
        let ox = x - this.pointerX;
        let oy = y - this.pointerY;
        this.velocity.tx = this.velocity.tx + (ox / 8) * this.scale * (this.touchInput ? 1 : -1);
        this.velocity.ty = this.velocity.ty + (oy / 8) * this.scale * (this.touchInput ? 1 : -1);
      }
      this.pointerX = x;
      this.pointerY = y;
    },
    onMouseMove(event) {
      this.touchInput = false;
      this.movePointer(event.clientX, event.clientY);
    },
    onTouchMove(event) {
      this.touchInput = true;
      this.movePointer(event.touches[0].clientX, event.touches[0].clientY);
      event.preventDefault();
    },
    onMouseLeave() {
      this.pointerX = null;
      this.pointerY = null;
    },
  },

};
</script>

<style  scoped>

.StarField {
  width: 100%;
  height: 100vh;
  background-image: linear-gradient(159deg, #360033 0%,#0b8793 100%);
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3);
}
canvas {
  display: block;
  width: 100%;
  height: 100%;
  position:absolute;
}
.click {
  text-align: center;
  margin-top: 10px;
  font-size: 20px;
  color: #fff;
  padding: 10px;
  border-radius: 10px;
  background: linear-gradient(100deg, #7F7FD5, #4286f4);
  width: 200px;
  top: 82%;
  position: absolute;
  left: 42%;
}
.img1 {
  width: 60vw;
  height: auto;
  top: 21%;
  left: 40%;
  position: absolute;
  transform: translateX(-50%);
  letter-spacing: 4px;
  font-size: 20px;
}

.img1IsMobile {
  width: 105vw;
  height: auto;
  top: 25%;
  left: 50%;
}

.img2 {
  width: 60vw;
  height: auto;
  top: 41%;
  position: absolute;
  left: 60%;
  transform: translateX(-50%);
  color: #fff;
  letter-spacing: 4px;
  font-size: 20px;
}

.img2IsMobile {
  width: 105vw;
  height: auto;
  top: 40%;
  left: 50%;
}
</style>


