<template>
<div class="parent">
 <v-container full-height>
    <v-row dense class="mx-sm-5 d"  >
 <v-col cols="12" lg="6" md="6" sm="10">
       
         <input
          type="text"
          name=""
          
          id=""
          class="dateInput"
          placeholder="title"
        /> 
      </v-col> 
      <v-col md="2" sm="2" lg="2">
        <v-btn class="py-5 blue darken-2 white--text text-capitalize"  width="100%">search</v-btn>
      </v-col> 
      <v-col cols="12" md="2" sm="12" lg="2">
        <v-btn aria-expanded width="100%" class="py-5 indigo darken-1 white--text text-capitalize" @click="dialog =true">advanced filters <v-icon class="ml-2">mdi-filter</v-icon></v-btn>
      </v-col>
      
   
</v-row>
 </v-container>


    <!-------------------------->
    <v-dialog v-model="dialog"  max-width="600px"
      class="transparent  ma-0">
    
      

       <v-row dense class="mx-auto px-5 pt-5 white">
         
          <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">Region</p>
        <v-select
         
           :items="RegionItems"
          item-text="name"
          item-value="id"
          v-model="SelectedRegion"
          dense
          outlined
          required
        ></v-select>
      </v-col>
        <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">ville</p>
        <v-select
          :items="CityItems"
          item-text="cityName"
          item-value="id"
          v-model="SelectedCity"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="6">
            <p class="font-weight-bold body-2 text-capitalize mb-0">type of post</p>
    <v-select
      :items="typeItems"
      v-model="selectedType"
      item-text="typeName"
      item-value="id"
      dense
      outlined
      required
    ></v-select>
         </v-col>
        <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">categorié</p>
        <v-select
          :items="categoriesItem"
          item-text="categorieName"
          item-value="id"
          dense
          outlined
          required
          v-model="selectedCategorie"
        ></v-select>
      </v-col>
         <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">prix min</p>
        <v-text-field type="number" v-model="surfacie" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">prix max</p>
        <v-text-field type="number" v-model="prix" outlined dense>
        </v-text-field>
      </v-col>
         <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">surfacie min</p>
        <v-text-field type="number" v-model="surfacie" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">surfacie max</p>
        <v-text-field type="number" v-model="prix" outlined dense>
        </v-text-field>
      </v-col>
           <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">chambre min</p>
        <v-text-field type="number" v-model="surfacie" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">chambre max</p>
        <v-text-field type="number" v-model="prix" outlined dense>
        </v-text-field>
      </v-col>

          <v-col  cols="12" lg="6" >
        <p class="font-weight-bold body-2 text-capitalize mb-0">sort By</p>
        <v-select
          :items="DureeItem"
          item-value="id"
          item-text="name"
          v-model="selectedDuree"
          dense
          outlined
          required
        ></v-select>
      </v-col>
       <v-col  cols="12" lg="6" >
        <p class="font-weight-bold body-2 text-capitalize mb-0">Order</p>
        <v-select
          :items="DureeItem"
          item-value="id"
          item-text="name"
          v-model="selectedDuree"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      
        </v-row>




















        <v-card-actions class="white">
          <v-row>

          </v-row>
          <v-col offset="7">
 <v-btn color="blue darken-1"  text @click="dialog = false">
            Close
          </v-btn>
          </v-col>
          <v-col>  <v-btn  class="blue darken-1 white--text"  @click="dialog = false">
            Save
          </v-btn>

          </v-col>
        
         
        </v-card-actions>
     
    </v-dialog>


  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      rida: 0,
     
      dialog: false,
      orderData: ["PRICE", "DATE CROISSANCE", "Date decroissance"],
      title: "",
      etatItems:{},
      SelectedRegion:"",
      categoriesItem:[],
      typeItems: [
        { id: 1, typeName: "vente" },
        { id: 2, typeName: "location" },
      ],
      RegionItems:[],
      CityItems:[],
      selectedType:0,
      SelectedCity:""
    };
  },
  methods: {
    searchByTitle() {
      axios
        .get("api/rent/search/by", {
          params: {
            title: this.title,
          },
        })
        .then((res) => console.log(res.data));
    },
       getAllCategories() {
      axios
        .get("/api/categories")
        .then((res) => (this.categoriesItem = res.data))
        .then(() => console.log(this.categoriesItem));
    },
    getAllDurations() {
      axios.get("/api/etat").then((res) => (this.etatItems = res.data));
    },
    getAllRegions(){
      axios.get("/api/regions").then((res)=>this.RegionItems = res.data).then(()=>console.log(this.RegionItems));
    },
    getAllCititesByRegion(id){
      axios.get("/api/cities/region/"+id).then(res=> this.CityItems = res.data);
    },
    checkInRoute(){
      console.log(this.$route.query)
      if(this.$route.query.type != null ){
        console.log('this is a buying search pos');
        this.selectedType = parseInt(this.$route.query.type)
      }
    }
    
    
  
  },
   mounted() {
    this.getAllCategories();
    this.getAllDurations();
    this.getAllRegions();
    this.checkInRoute();
    
  },
  watch:{
    SelectedRegion(val){
      console.log(val);
      this.getAllCititesByRegion(val);
    },
  }
};
</script>

<style scoped>
.parent{
  width: 100vw;
}


.dateInput {
  width: 100%;
  border: solid 1px #b7a6a6;
  padding: 6px;
  border-radius: 3px;
  color: #b7a6a6;
  margin-bottom: 20px;
}
</style>