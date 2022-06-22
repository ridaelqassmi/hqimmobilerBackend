//import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios';

Vue.use(Vuex)


function parseJwt(token) {
  token = token.substring(7, token.length);
  var base64Url = token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );

  return JSON.parse(jsonPayload);
}
export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    userAuthentified: false,
    AuthentifiedUserDetails: {},
    showAuthModal:false,
    
  },
  mutations: {
    toggleAuthentified: (state) => {



      if (localStorage.getItem("Autorization") != null && parseJwt(localStorage.getItem("Autorization")).exp > (Date.now() / 1000)) {
        state.userAuthentified = true;
        
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Autorization");
      } else {
        
        state.userAuthentified = false;
      }

    },
    toggleAuthModal:(state)=>{
      console.log("i am being called")
      if(state.userAuthentified){
        state.showAuthModal = false;
      }
      else{
        state.showAuthModal = true;
      }
    }
    ,
    logOut:(state)=>{
     
    
      state.userAuthentified = false;
      state.AuthentifiedUserDetails={},
      axios.defaults.headers.common['Authorization']="";
    },
    setAuthentifiedUserDetails: (state, user) => {
      state.AuthentifiedUserDetails = user;
    },


  }
  ,
  getters: {
    getAuthUser(state) {
   
      if (localStorage.getItem("Autorization") != null && parseJwt(localStorage.getItem("Autorization")).exp > (Date.now() / 1000)) {
      
        return state.AuthentifiedUserDetails;
      } else {
     
        return null;
      }

    }

  },

})

/*
if you want to call state
this.$store.state.authModalShow;


==mutation is a way to change state in the store

if you want to call mutation 
this.$store.commit('toggleAuthModal');

016-Mapping Mutation 
extract the function from store object 
in the components we can create code like this 
import {mapMutation } from 'veux';
export default{
  name:'header',
  methods :{
    ...mapMutations(['toggleAuthModal']),
  }
}


////////// Getters /////////////////////////////
as computed properties for your store ;
accessible in all components

export default{
  computed:{
    authModalShow(){
      return this.$store.getters.authModalShow;
    }
  }
}


020-Mapping getters and state proprites

import {mapMutations,mapGetters} from 'vuex';
export default {
  name:'Auth',
  computed:{
    ...mapGetters(['authModalShow']),
  }
}

if your getter function does not calcule something 
you dont have to do it instead of this
import {mapMutations,mapGetters} from 'vuex';
export default {
  name:'Auth',
  computed:{
    ...mapGetters(['authModalShow']),
  }
}
write somethig like this
import {mapMutations,mapState } from 'vuex';
export default {
  name:'Auth',
  computed:{
    ...mapState(['authModalShow']),
  }
}
getter vs mapping state


*/
