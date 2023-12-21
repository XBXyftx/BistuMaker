import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

//、/ 引入编辑器组件
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
    Prism,
});


//pinia
import {createPinia} from "pinia";
import {createPersistedState} from "pinia-plugin-persistedstate";
const pinia = createPinia()
const persist = createPersistedState();

pinia.use(persist)
const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
app.use(VueMarkdownEditor);
app.use(pinia)
