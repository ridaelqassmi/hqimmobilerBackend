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
    isAdmin :false

  },
  mutations: {
    toggleAuthentified: (state) => {

      if (localStorage.getItem("Autorization") != null && parseJwt(localStorage.getItem("Autorization")).exp > (Date.now() / 1000)) {
        if(parseJwt(localStorage.getItem("Autorization")).roles.includes("ADMIN")){
          state.isAdmin = true;
        }
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
      state.isAdmin = false;
      state.AuthentifiedUserDetails={},
      axios.defaults.headers.common['Authorization']="";
      localStorage.removeItem("Autorization");
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

