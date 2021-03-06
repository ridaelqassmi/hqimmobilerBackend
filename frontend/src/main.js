import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import 'leaflet/dist/leaflet.css';
import store from './store'
import './axios';

import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import Notifications from 'vue-notification'

import VeeValidate from 'vee-validate';

Vue.use(VeeValidate);

Vue.use(Buefy)
Vue.use(Notifications)

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
