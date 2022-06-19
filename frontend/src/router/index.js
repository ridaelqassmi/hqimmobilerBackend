import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
//import RentView from '../views/Rent.vue'
import HouseView from '../views/houses.vue'
import CreatePost from '../views/CreatePost.vue'
import ShowPostVue from '../views/ShowPostVue.vue'
import UserProfile from '../views/UserProfile.vue'
import addPost from '@/views/addPost.vue'

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
    component: addPost
  
  },
  {
    path: '/buy',
    name: 'buy',
    component: HouseView
  
  },
  
  {
    path: '/createPost',
    name: 'createPost',
    component: CreatePost
  
  },
  {
    path: '/rent/:postId',
    name: 'showPOST',
    component: ShowPostVue
  
  }
  ,{
    path: '/user/:userId',
    name: 'UserProfile',
    component: UserProfile
  
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
