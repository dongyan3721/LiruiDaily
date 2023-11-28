import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import axios from 'axios'


axios.defaults.baseURL="http://localhost:8080/api/"
let app = createApp(App);
app.use(router);
app.config.globalProperties.$axios = axios
app.mount('#app')
