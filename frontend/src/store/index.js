//import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
//import axios from 'axios';

Vue.use(Vuex)

export default new Vuex.Store({
plugins: [createPersistedState()],
  state: {
    userAuthentified:false,
    AuthentifiedUserDetails:{},
  } ,
  mutations:{
    toggleAuthentified:(state)=>{
      if(localStorage.getItem("Autorization") !=null){
        console.log("is not null");
        state.userAuthentified = true;
      }else{
        state.userAuthentified =false;
      }
      
    }
    ,
    setAuthentifiedUserDetails:(state,user)=>{
      state.AuthentifiedUserDetails = user;
    }
  
  },
  getters:{
   
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
