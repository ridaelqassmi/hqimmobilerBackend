<template>
  <v-container  fluid class="pa-0 is-fullhd" >
    <div class="hero">
      <v-container fill-height class="is-fullhd">
        <v-row justify="center" dense class="pa-0" >
        <v-col justify-center cols="12" md="8" lg="8" sm="8" xm="12" class="pa-4 v-colCostume">
          <v-row dense>
            <v-col cols="12" xm="12" class="mb-0 pa-2 ">
              <v-text-field class="mb-0 pa-0"
                  label="search"
                  placeholder="search"
                  outlined
                  dark
                  color="white"
                  prepend-inner-icon="mdi-magnify"
                ></v-text-field></v-col>
          <v-col cols="12" md="4"><v-btn block color="#014982" to="/createPost" class="white--text ">Post a post</v-btn></v-col>
          <v-col cols="12" md="4"><v-btn block color="#014982" to="/rent" class="white--text">rent post</v-btn></v-col>
          <v-col cols="12" md="4"> <v-btn block color="#014982"  to="sell" class="white--text">buy post</v-btn></v-col>
          <v-col  cols="12" md="4" ><div class="buttons">
            <b-button tag="router-link"
                to="/create" type="is-primary" expanded>
                motherfucker
            </b-button>
        </div></v-col>
            
          </v-row>
         

        </v-col>
      </v-row>
      </v-container>
      
    </div>
  </v-container>
</template>

<script>
  

  export default {
    name: 'Home',

    components: {
      
    },
  }
</script>
<style scoped>
.hero{
  background: url('https://images.unsplash.com/photo-1516522046618-1630709a6831?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80');
  height: 600px;
  background: no-repeat contain;
  
}
.v-colCostume{
  background :rgba(0, 0, 0, 0.384);
 
}
</style>