<template>
  <div>
    <p class="font-weight-bold body-2 text-capitalize mb-0">type of post</p>
    <v-select :items="items" label="type " dense outlined required></v-select>
    <v-row dense>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">title</p>
        <v-text-field outlined dense> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">surfacie</p>
        <v-text-field type="number" outlined dense> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">prix</p>
        <v-text-field type="number" outlined dense> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6" v-if="rent">
        <p class="font-weight-bold body-2 text-capitalize mb-0">durée</p>
        <v-select
          :items="items"
          label="type "
          dense
          outlined
          required
        ></v-select>
      </v-col>
          <v-col cols="12" :lg="rent?12:6" >
        <p class="font-weight-bold body-2 text-capitalize mb-0">categorié</p>
        <v-select
          :items="items"
          label="type "
          dense
          outlined
          required
        ></v-select>
      </v-col>
   
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">nombre de salle de bain</p>
     <v-text-field type="number" outlined dense> </v-text-field>
      </v-col>
       <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">date de availabilty</p>
    <input type="date" name="" id="" class="dateInput">
      </v-col>
         <v-col cols="12" lg="12">
        <p class="font-weight-bold body-2 text-capitalize mb-0">description</p>
      <v-textarea
          outlined
          
         dense
         height="80px"
   
          
        ></v-textarea>
      </v-col>
      
      

      
    </v-row>
  </div>
</template>
<script>
export default {
    setup() {
        
    },
    data(){
        return{
            rent:false
        }
        
    }
}
</script>
<style scoped>
.dateInput{
 width: 100%;
    border: solid 1px #b7a6a6;
    padding: 6px;
    border-radius: 3px;
    color: #b7a6a6;
    margin-bottom: 20px;
}

</style>