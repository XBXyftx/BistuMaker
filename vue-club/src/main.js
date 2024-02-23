import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/font/font.less';
//、/ 引入编辑器组件
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
    Prism,
});
// //1 预览组件以及样式
// import VMdPreview from '@kangc/v-md-editor/lib/preview';
// import '@kangc/v-md-editor/lib/style/preview.css';
// // VuePress主题以及样式（这里也可以选择github主题）--VuePress主题代码呈黑色背景，github呈白色背景
// // import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
// import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

//pinia
import {createPinia} from "pinia";
import {createPersistedState} from "pinia-plugin-persistedstate";
const pinia = createPinia()
const persist = createPersistedState();

pinia.use(persist)
const app = createApp(App)
// app.use(router)
app.use(ElementPlus)

app.use(VueMarkdownEditor);
// app.use(VMdPreview)
app.use(pinia)


import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';
VMdPreview.use(githubTheme, {
    Hljs: hljs,
});

// 导入打字机
import vuetyped from 'vue3typed'
// 设置Vue3的全局方法实例
// 代替Vue2的 Vue.use的全局方法
// const app = createApp(App)
// 挂载打字机的全局方法 .use(vuetyped) 请无视.use(store).use(router)



app.use(vuetyped)



app.use(VMdPreview);
app.use(router)



import UndrawUi from 'undraw-ui'
import 'undraw-ui/dist/style.css'
app.use(UndrawUi)

// Vue.prototype.baseUrl = "https://www.example.com/api"
//定义全局变量
import {baseURL} from "@/utils/baseURL.js";

app.config.globalProperties.$baseURL = baseURL

app.mount('#app')
