<template>
  <div class="parent">
    <v-container full-height>
      <v-row dense class="mx-sm-5 d">
        <v-col cols="12" lg="6" md="6" sm="10">
          <input
            type="text"
            name=""
            id=""
            class="dateInput"
            placeholder="title"
            v-model="title"
          />
        </v-col>
        <v-col md="2" sm="2" lg="2">
          <v-btn
            class="py-5 blue darken-2 white--text text-capitalize"
            width="100%"
            >search</v-btn
          >
        </v-col>
        <v-col cols="12" md="2" sm="12" lg="2">
          <v-btn
            aria-expanded
            width="100%"
            class="py-5 indigo darken-1 white--text text-capitalize"
            @click="dialog = true"
            >advanced filters <v-icon class="ml-2">mdi-filter</v-icon></v-btn
          >
        </v-col>
      </v-row>
    </v-container>

    <!-------------------------->
    <v-dialog v-model="dialog" max-width="600px" class="transparent ma-0">
      <v-row dense class="mx-auto px-5 pt-5 white">
        <v-col cols="12" class="red lighten-2" v-if="ErrorArray.length != 0">
          <p v-for="error in ErrorArray" :key="error">
            {{ error }}
          </p>
        </v-col>
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
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            type of post
          </p>
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
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="prixMin"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">prix max</p>
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="prixMax"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            surfacie min
          </p>
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="surfacieMin"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            surfacie max
          </p>
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="surfacieMax"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            chambre min
          </p>
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="chambreMin"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            chambre max
          </p>
          <v-text-field
            type="number"
            min="0"
            oninput="validity.valid||(value='');"
            v-model="chambreMax"
            outlined
            dense
          >
          </v-text-field>
        </v-col>

        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">sort By</p>
          <v-select
            :items="SortItem"
            item-value="name"
            item-text="name"
            v-model="selectedSort"
            dense
            outlined
            required
          ></v-select>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">Order</p>
          <v-select
            :items="OrderItem"
            item-value="id"
            item-text="name"
            v-model="selectedOrder"
            dense
            outlined
            required
          ></v-select>
        </v-col>
      </v-row>

      <v-card-actions class="white">
        <v-row> </v-row>
        <v-col offset="7">
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
        </v-col>
        <v-col>
          <v-btn class="blue darken-1 white--text" @click="validate()">
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
      OrderItem: [
        { id: 1, name: "asc" },
        { id: 2, name: "desc" },
      ],
      SortItem:[{id:1,name:"price"},{id:2,name:"date"}],
      title: "",
      etatItems: {},

      categoriesItem: [],
      typeItems: [
        { id: 1, typeName: "vente" },
        { id: 2, typeName: "location" },
      ],
      RegionItems: [],
      CityItems: [],
      selectedType: 0,
      SelectedCity: "",
      filters: {
        areaMax: 0,
        areaMin: 0,
        categorieId: 0,
        cityId: 0,
        field: "date",
        order: 2,
        page: 0,
        priceMax: 0,
        priceMin: 0,
        roomMax: 0,
        roomMin: 0,
        title: "",
      },
      Defaultfilters:{
        
        areaMax: 0,
        areaMin: 0,
        categorieId: 0,
        cityId: 0,
        field: "date",
        order: 2,
        page: 0,
        priceMax: 0,
        priceMin: 0,
        roomMax: 0,
        roomMin: 0,
        title: "",
      
      },
     
      SelectedRegion: "",
      selectedCategorie: "",
      ErrorArray: [],
      prixMin: "",
      prixMax: "",
      surfacieMin: "",
      surfacieMax: "",
      chambreMin: "",
      chambreMax: "",
      selectedSort: "",
      selectedOrder: "",
    };
  },
  methods: {
    checkifMinIslessthanMax(min, fieldnameMin, max, fieldnameMax) {
      if (min != "" && max != "") {
        if (min > max) {
          this.ErrorArray.push(
            fieldnameMin + "doit étre inferieur a " + fieldnameMax
          );
        }
      }
    },
    validate() {
      this.ErrorArray = [];
      this.checkifMinIslessthanMax(
        this.prixMin,
        " prix min",
        this.prixMax,
        " prix max"
      );
      this.checkifMinIslessthanMax(
        this.chambreMin,
        "chambre min",
        this.chambreMax,
        "chambre max"
      );
      this.checkifMinIslessthanMax(
        this.surfacieMin,
        "chambre min",
        this.surfacieMax,
        "chambre max"
      );
      if (this.ErrorArray.length != 0) {
        return;
      }
      console.log("filter before")
      console.log(filters);
      let filters = this.Defaultfilters;
      this.dialog = false;
      console.log("filter after")
      console.log(filters);
      if(this.chambreMin !=="" && this.chambreMax !==""){
        filters.chambreMin = this.chambreMin;
        filters.chambreMax = this.chambreMax;

      }
      console.log("the value of prix max "+this.prixMax);
      if(this.prixMin !==" " && this.prixMax !==" "){
        console.log("is not an emptystring")
        filters.priceMin =this.prixMin;
        filters.priceMax =this.prixMax;
      }
      if(this.chambreMin!==" " && this.chambreMax!==" "){
        filters.roomMin = this.chambreMin;
        filters.roomMax =this.chambreMax;
      }
      if(this.surfacieMin!==" " && this.surfacieMax!==" "){
        filters.areaMin = this.surfacieMin;
        filters.areaMax =this.surfacieMax;

      }
      if(this.SelectedCity !== " "){
        filters.cityId= this.SelectedCity;
      }
      if(this.selectedCategorie!==""){
        filters.categorieId = this.selectedCategorie;

      }
      if(this.selectedSort !==""){
        filters.field = this.selectedSort
      }
      if(this.selectedOrder !==""){
        filters.order = this.selectedOrder;
      }
      if(this.title!==""){
        filters.title = this.title;
      }
      console.log("just before submited");
      console.log(filters);
      this.$emit("getDataByFilter",filters);
    },

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
        .then((res) => (this.categoriesItem = res.data));
        
    },
    getAllDurations() {
      axios.get("/api/etat").then((res) => (this.etatItems = res.data));
    },
    getAllRegions() {
      axios
        .get("/api/regions")
        .then((res) => (this.RegionItems = res.data))
        .then(() => console.log(this.RegionItems));
    },
    getAllCititesByRegion(id) {
      axios
        .get("/api/cities/region/" + id)
        .then((res) => (this.CityItems = res.data));
    },
    checkInRoute() {
      
      if (this.$route.query.type != null) {
        
        this.selectedType = parseInt(this.$route.query.type);
      }
    },
    getPostByFilter() {
      axios({
        url: "api/rent/filter",
        method: "post",
        headers: {
          "content-type": "application/json",
        },
        data: JSON.stringify(this.filters),
      }).then((res) => (this.posts = res.data));
    },
  },
  mounted() {
    this.getAllCategories();
    this.getAllDurations();
    this.getAllRegions();
    this.checkInRoute();
  },
  watch: {
    SelectedRegion(val) {
      
      this.getAllCititesByRegion(val);
    },
      
      selectedCategorie(val){
        this.selectedCategorie = val;
      },

      
      prixMin(val){
        this.prixMin = val;
      },
      prixMax(val){
        this.prixMax = val;
      },
      surfacieMin(val){
        this.surfacieMin = val;
      },
      surfacieMax(val){
        this.surfacieMax = val;
      },
      chambreMin(val){
        this.chambreMin =val;
      },
      chambreMax(val){
        this.chambreMax = val;
      },
      selectedSort(val){
        this.selectedSort = val;
      },
      selectedOrder(val){
        this.selectedOrder=val;
      },

  },
};
</script>

<style scoped>
.parent {
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