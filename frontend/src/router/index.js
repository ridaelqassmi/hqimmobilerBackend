import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RentView from '../views/Rent.vue'
import HouseView from '../views/houses.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/rent',
    name: 'rent',
    component: RentView
  
  },
  {
    path: '/buy',
    name: 'buy',
    component: HouseView
  
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
