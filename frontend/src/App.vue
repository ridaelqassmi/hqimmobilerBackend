<template>
  <v-app>
    <v-navigation-drawer
      app
      v-model="drawer"
      class="pa-0 ma-0"
      disable-resize-watcher
    >
      <v-list dense>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          class="pa-2 rounded-lg mt-0"
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon
              color="#4A4A4A"
              class="font-weight-bold"
              v-text="item.icon"
            ></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title
              color="#4A4A4A"
              class="font-weight-bold text-capitalized"
              v-text="item.text"
            ></v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!--if the user is authentified-->
<div v-if="userAuthentified">
<v-list-item
          v-for="(item, i) in authItems"
          :key="i"
         
          class="pa-2 rounded-lg mt-0"
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon
              color="#4A4A4A"
              class="font-weight-bold"
              v-text="item.icon"
            ></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title
              color="#4A4A4A"
              class="font-weight-bold text-capitalized"
              v-text="item.text"
            ></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
</div>
        

      </v-list>
    </v-navigation-drawer>
    <v-app-bar app elevation="0" color="white">
      <v-app-bar-nav-icon
        class="pa-0 zindex"
        color="grey"
        @click="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-row>
        <v-col cols="6">
          <v-toolbar-title
            class="
              grey--text
              text--darken-2 text-xs-body-1
              d-flex
              font-italic
              align-center
            "
          >
            <span class="font-weight-bold">HQ</span>immobilier</v-toolbar-title
          >
        </v-col>
        <v-col cols="2" class="d-sm-none d-md-flex hidden-sm-and-down">
          <v-btn
            to="/createPost"
            class="
              light-blue
              accent-4
              white--text
              rounded-pill
              px-4
              font-weight-meduim
              elevation-0
              text-capitalize
            "
            >ajouter une post
          </v-btn>
        </v-col>
        <v-spacer></v-spacer>

        <v-col>
          <v-btn
            class="elevation-0 transparent"
            @click="showRegistreInComponent = !showRegistreInComponent"
            v-if="!userAuthentified"
          >
            <v-icon>mdi-login</v-icon>
            <p class="px-2 align-self-center mb-0">Se connecter</p>
          </v-btn>

          <HeaderProfile v-if="userAuthentified" :key="i" />
        </v-col>
      </v-row>
    </v-app-bar>
    <!--test-->

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
import HeaderProfile from "./components/HeaderProfile.vue";


import RegistreInComponent from "@/components/RegistreInComponent.vue";
import { mapState, mapMutations } from "vuex";
import axios from "axios";

export default {
  components: { RegistreInComponent, HeaderProfile },
  name: "App",

  data: () => ({
    drawer: false,
    showLoginComponent: false,
    showRegistreInComponent: false,
    i: 1,
    items: [
      { text: "Home", icon: "mdi-home", to: "/" },
      { text: "posts", icon: "mdi-account", to: "/posts" },
      { text: "ajouter un announce", icon: "mdi-pencil", to: "/createPost" },
      
    ],
    authItems:[
       
      {
        to:'/user/edit/',
        icon: "mdi-account",
        text: "Profile",
      },
      {
        to:'/myposts',
        icon: "mdi-post",
        text: "mon publication",
      },
      {
        to:'/inbox',
        icon: "mdi-inbox",
        text: "inbox",
      },
      {
        id:2,
        icon: "mdi-heart",
        text: "my favourite",
      },
      {
        to:'/user/loggedOut',
        icon: "mdi-logout",
        text: "log out",
      },
    ]

    
  }),
  computed: {
    ...mapState(["userAuthentified", "AuthentifiedUserDetails"]),
  },

  watch: {
    // Creating function
    // for input component
    AuthentifiedUserDetails: function (val) {
      if (val == true) {
        axios.get("api/authentifiedUser").then((res) => {
          this.setAuthentifiedUserDetails(res.data);
        });
      }
    },
  },

  methods: {
    ...mapMutations(["getUserDetails"]),
    getAuthentifiedUser() {
      axios.get("api/authentifiedUser").then((res) => console.log(res.data));
    },

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
.zindex {
  z-index: 1000 !important;
}
</style>
<style lang="scss" >
@import url("https://fonts.googleapis.com/css? family=Oxygen:300,400,700&display=swap");

@import url("https://fonts.googleapis.com/css2?family=IBM+Plex+Serif&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;1,100;1,200;1,300&display=swap");

@import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;1,100;1,300;1,400&display=swap");

$font-family1: "IBM Plex Serif";
$font-family: "Oxygen";

$font-family: "Roboto";
.v-application {
  [class*="text-"] {
    color: #36405a;
    font-family: $font-family, sans-serif !important;
  }
  font-family: $font-family, sans-serif !important;
  [class*="text-h5"] {
    color: #36405a;
    font-family: $font-family1, sans-serif !important;
  }
}
</style>