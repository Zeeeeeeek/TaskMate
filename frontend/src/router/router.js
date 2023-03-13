import {createRouter, createWebHistory} from "vue-router";
import Home from "@/views/Home.vue";
import Auth from "@/views/Auth.vue";

const routes = [
    {path: '/', component: Home, alias: '/home', name: 'home'},
    {path: '/auth', component: Auth, name: 'auth'},
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router