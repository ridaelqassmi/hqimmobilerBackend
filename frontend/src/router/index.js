import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
//import RentView from '../views/Rent.vue'
import HouseView from '../views/houses.vue'
//import CreatePost from '../views/CreatePost.vue'
import ShowPostVue from '../views/ShowPostVue.vue'
import UserProfile from '../views/UserProfile.vue'
import addPost from '@/views/addPost.vue'
import OwnPosts from '@/components/OwnPosts.vue'
//import PostsView from '@/views/PostsView.vue'
import EditProfile from '@/components/EditProfile.vue'
import LogOut from '@/components/LogOut.vue'
import store from '@/store/index.js';
import Error from '@/components/ErrorComponent.vue';
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/createPost',
    name: 'createPost',
    component: addPost
  
  },
 /* {
    path: '/buy',
    name: 'buy',
    component: HouseView
  
  },
  */
 
  {
    path: '/rent/:postId',
    name: 'showPOST',
    component: ShowPostVue
  
  }
  ,{
    path: '/user/c/:userId',
    name: 'UserProfile',
    component: UserProfile
  
  },{
    path:'/posts',
    name:'post',
    component:HouseView
  },{
    path:'/myposts',
    name:'myPosts',
    component:OwnPosts
  },
  {
    path:'/user/edit/',
    name:'editUser',
    component:EditProfile
  },
  {
    path:'/user/loggedOut',
    name:'logout',
    component:LogOut
  },
  {
    path:'/notAuthentified',
    name:'notAuthentified',
    component:Error
  }
  


]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next) => {
  if (to.name === 'home' || to.name==='showPOST' || to.name==="buy"|| to.name==="post"|| to.name==="notAuthentified" ) {
    next() 
  
  } else if(store.state.userAuthentified){
    next()
  }else{
    next({path:'/notAuthentified'})
  }
  
  
})


export default router
