import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userAuthetified:false,
  },
  getters: {
    getAuthetifiedId(state){
      return state.userAuthetified;
    }
  },
  mutations: {
    changeAuthetifiedId(state,payload){
      state.userAuthetified = payload;
    }

  },
  actions: {
    changeAuthetifiedId(context,payload){
      context.commit('changeAuthentifiedId',payload)
    }
  },
  modules: {
  }
})
