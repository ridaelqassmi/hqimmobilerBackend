<template>
  <v-app>
    <v-navigation-drawer app v-model="drawer" class="blue darken-4">
      <v-list dense class="ml-2">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          class="pa-2 rounded-lg mt-0"
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon
              class="white--text font-weight-bold"
              v-text="item.icon"
            ></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title
              class="white--text font-weight-bold text-capitalized"
              v-text="item.text"
            ></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar app elevation="0" color="#003BDE">
      <v-app-bar-nav-icon
        class="pa-0"
        color="white"
        @click="drawer = !drawer"
      ></v-app-bar-nav-icon>

      <v-toolbar-title
        class="white--text text--darken-2 font-weight-bold Heading"
      >
        HQimmobilier</v-toolbar-title
      >
      <v-spacer></v-spacer>

      <v-form class="hidden-sm-and-down">
        <v-btn to="/createPost"
          class="white--text white--text elevation-0 transparent text-capitalize font-weight-bold"
          
          >ajouter un post
        </v-btn>
        
        <v-btn
          class="white--text white--text elevation-0 transparent text-capitalize font-weight-bold"
          @click="showLoginComponent = !showLoginComponent"
          >Sing in
        </v-btn>
        <v-btn
          class="white--text white--text elevation-0 transparent text-capitalize font-weight-bold"
          @click="showRegistreInComponent = !showRegistreInComponent"
          >signUp</v-btn
        >
      </v-form>
    </v-app-bar>

    <v-main class="main">
      <router-view />
      <login-component
        v-on:toggle-showModal="toggleLoginModel"
        v-if="showLoginComponent"
      />
      <RegistreInComponent
        v-on:toggle-showModal="toggleRegistrationModel"
        v-if="showRegistreInComponent"
      />
    </v-main>
  </v-app>
</template>

<script>
import LoginComponent from "./components/auth/LoginComponent.vue";
import RegistreInComponent from "./components/auth/RegistreInComponent.vue";

export default {
  components: { LoginComponent, RegistreInComponent },
  name: "App",

  data: () => ({
    drawer: false,
    showLoginComponent: false,
    showRegistreInComponent: false,
    items: [
      { text: "Home", icon: "mdi-home", to: "/" },
      { text: "Rent", icon: "mdi-account", to: "/rent" },
      { text: "Buy", icon: "mdi-flag", to: "buy" },
      { text: "user", icon: "mdi-account", to: "/user" },
    ],
  }),
  methods: {
    toggleRegistrationModel() {
      this.showRegistreInComponent = !this.showRegistreInComponent;
    },
    toggleLoginModel() {
      this.showLoginComponent = !this.showLoginComponent;
    },
  },
};
</script>

<style >
.main {
  /*background: #014982;*/
  background: #f0f2f5;
}
html {
  scrollbar-width: none;
  -ms-overflow-style: none;
  overflow-y: scroll;
}
html::-webkit-scrollbar {
  width: 0;
  height: 0;
}
* {
  padding: 0;
  margin: 0;
}
</style>
<style lang="scss" >
@import url("https://fonts.googleapis.com/css? family=Oxygen:300,400,700&display=swap");

@import url("https://fonts.googleapis.com/css2?family=IBM+Plex+Serif&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;1,100;1,200;1,300&display=swap");

$font-family: "IBM Plex Serif";

.v-application {
  [class*="text-"] {
    color: #36405a;
    font-family: $font-family, sans-serif !important;
  }
  font-family: $font-family, sans-serif !important;
}
</style>