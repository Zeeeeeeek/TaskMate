import {createRouter, createWebHistory} from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
const routes = [
    {path: '/', component: Home, alias: '/home', name: 'home'},
    {path: '/login', component: Login, name: 'login'},
    {path: '/register', component: Register, name: 'register'}
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from) => {
    if(to.name !== 'login' && to.name !== 'register' && !localStorage.getItem('token')) {
        return {name: 'login'}
    }
})
export default router