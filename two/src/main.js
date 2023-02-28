import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import qs from 'qs'

import intro from 'intro.js' // introjs库
import 'intro.js/introjs.css' // introjs默认css样式
// introjs还提供了多种主题，可以通过以下方式引入
import 'intro.js/themes/introjs-modern.css' // introjs主题


import 'element-plus/theme-chalk/display.css';
import  './assets/icon/iconfont.js'
import { ArrowDown } from '@element-plus/icons-vue'

import './assets/icon/iconfont.css'
let app = createApp(App)
axios.defaults.baseURL = "http://localhost:8081/"
app.config.globalProperties.$axios = axios
app.config.globalProperties.$qs = qs
app.use(ElementPlus)

app.use(router).mount('#app')


