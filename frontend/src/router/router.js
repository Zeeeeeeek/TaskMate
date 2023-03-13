import {createRouter, createWebHistory} from "vue-router";
import Home from "@/views/Home.vue";
import Auth from "@/views/Auth.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";

const routes = [
    {path: '/', component: Home, alias: '/home', name: 'home'},
    {path: '/login', component: Login, name: 'login'},
    {path: '/register', component: Register, name: 'register'}
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router