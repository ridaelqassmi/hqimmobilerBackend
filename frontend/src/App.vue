<template>
  <v-app >
    <v-navigation-drawer app
      v-model="drawer"
      
      class="blue darken-4"
      
     
    >
        <v-list dense>
       
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            class="   pa-2 rounded-lg mt-0 "
            :to="item.to"
            
          >
            <v-list-item-icon >
              <v-icon class="white--text"  v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title class="white--text" v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        
      </v-list>
    </v-navigation-drawer>
    <v-app-bar app  elevation="1">
        <v-app-bar-nav-icon class="pa-0 " @click="drawer=!drawer"></v-app-bar-nav-icon>

         <v-toolbar-title class="grey--text text--darken-2 font-weight-bold Heading "> HQimmobilier</v-toolbar-title>
         <v-spacer></v-spacer>
         
          <v-form class="	hidden-sm-and-down">
            <v-btn class="pl-8 pr-8 ma-3 elevation-1 info">Sing in </v-btn>
            <v-btn class="pl-8 pr-8 ma-3 elevation-1 primary " >signUp</v-btn>
          </v-form>
         
         

    </v-app-bar>
   
    

    
   

    
   

    <v-main class="main">
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    drawer:false,
     items: [
        { text: 'Home', icon: 'mdi-home' ,to:'/home'},
        { text: 'Rent', icon: 'mdi-account',to:'/rent' },
        { text: 'Buy', icon: 'mdi-flag' ,to:'buy'},
      ],

  }),
};
</script>

<style scoped>
.main{
  background: #014982;

}
</style>
