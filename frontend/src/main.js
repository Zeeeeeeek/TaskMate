import { createApp } from 'vue'
import App from './App.vue'

import './assets/main.css'
import Home from "@/views/Home.vue";
import Auth from "@/views/Auth.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    { path: '/', component: Home, alias: '/home', name: 'home' },
    { path: '/auth', component:Auth, name: 'auth'},
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})


createApp(App)
    .use(router)
    .mount('#app')
