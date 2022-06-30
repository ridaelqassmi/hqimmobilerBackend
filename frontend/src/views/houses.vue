<template>
  <div class="py-16">
    <h1
      class="
        grey--text
        font-weight-bold
        text-capitalize text-h2 text-center
        headingCustom
      "
    >
      all posts
    </h1>

    
  
    <!-------ship ------------->
    <v-row class="justify-center mx-auto">
      <v-col cols="12" sm="12" md="8">
        <v-sheet class="py-4 transparent px-1 rounded-lg">
          <v-chip-group active-class=" red lighten-2">
            <v-chip
              class="black--text"
              v-for="categorie in categories"
              :key="categorie.id"
              @click="getPostByCategorie(categorie.id)"
            >
              {{ categorie.categorieName }}
            </v-chip>
          </v-chip-group>
        </v-sheet>
      </v-col>
    </v-row>
    <!-------endship----------->

    <!-----Filters--------------->

    <RentPostFilterComponent  @getDataByFilter="checkIfFilterIsApplied"/>

    <!--end Filters---->
   

    <Posts :posts="posts" />

    <!------pagination section ------>
    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width" @click="getPage(correntPage)">
              <v-pagination
                v-model="correntPage"
                class="my-4"
                circle
                :length="numberPages"
              >
              </v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Posts from "@/components/Posts.vue";
import RentPostFilterComponent from "@/components/RentPostFilterComponent.vue";

export default {
  components: { RentPostFilterComponent, Posts },

  data() {
    return {
      filtersIsApplied:false,
      posts: [],
      correntPage: 0,
      numberPages: 0,
      lastPage: false,
      numberOfElements: 0,
      totalElements: 0,
      categories: [],
      categoriesId: 0,
      filters: {
        type:"",
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

    };
  },
  methods: {
   makeGetRequestToApi(url) {
      axios.get(url).then((res) => {
        this.posts = res.data.content;
        this.correntPage = res.data.number;
        this.numberPages = res.data.totalPages;
        this.totalElements = res.data.totalElements;
        this.numberOfElements = res.data.numberOfElements;
      });
    },
    getPage(page) {
      /*
      if (this.categoriesId != 0) {
        this.makeGetRequestToApi(
          "api/rent/categorie/" +
            this.categoriesId +
            "/page/" +
            this.correntPage
        );
      } else {
        this.makeGetRequestToApi("api/rent/page/" + (page - 1));
      }*/

      if(!this.filtersIsApplied ){
        this.getAllPosts(page)
        
      }else{
        this.filters.page = page;
            if(this.filters.type == 1){
          this.getDataByFilterBuy(this.filters);
        }else if(this.filters.type == 2){
          this.getDataByFilterRent(this.filters)
        }
      }
    },
    getPostByCategorie(id) {
      this.categoriesId = id;
      if (id == 0) {
        this.filtersIsApplied = false;
        this.getAllPosts(0)
      }
      this.correntPage = 0;
      this.makeGetRequestToApi(
        "api/rent/categorie/" + id + "/page/" + this.correntPage
      );
    },

    checkIfFilterIsApplied(filters){
     if(filters.type == 1){
       this.getDataByFilterBuy(filters);
     }else if(filters.type == 2){
       this.getDataByFilterRent(filters)
     }
     else if(this.$route.query.type == 1 ){
        this.filtersIsApplied = true;
        this.filters.type = 1;
        this.getDataByFilterBuy(filters)
      }
      else if(this.$route.query.type == 2){
        
        this.filtersIsApplied = true;
        this.filters.type = 2;
        this.getDataByFilterRent(filters);
      }else{
        this.getAllPosts(0);
      }


      

    },
    
    getDataByFilterRent(filters){
      console.log(filters);
      //filters.page = 0;
      axios({
        url: "api/rent/filter",
        method: "post",
        headers: {
          "content-type": "application/json",
        },
        data: JSON.stringify(filters),
      }).then((res) => {
        this.posts = res.data.content;
        this.correntPage = res.data.number;
        this.numberPages = res.data.totalPages;
        this.totalElements = res.data.totalElements;
        this.numberOfElements = res.data.numberOfElements;
      });
    },
     getDataByFilterBuy(filters){
      console.log(filters);
      //filters.page = 0;
      axios({
        url: "api/buy/filter",
        method: "post",
        headers: {
          "content-type": "application/json",
        },
        data: JSON.stringify(filters),
      }).then((res) => {
        this.posts = res.data.content;
        this.correntPage = res.data.number;
        this.numberPages = res.data.totalPages;
        this.totalElements = res.data.totalElements;
        this.numberOfElements = res.data.numberOfElements;
      });
    },
    getAllPosts(page){
      axios.get("api/posts/page/"+page).then(res=>{
        this.posts = res.data.content;
        this.correntPage = res.data.number;
        this.numberPages = res.data.totalPages;
        this.totalElements = res.data.totalElements;
        this.numberOfElements = res.data.numberOfElements;
      })
    }

  
    /*getPostByFilter() {
      axios
        .post("api/rent/filter", {
          filterDto: JSON.stringify(this.filters),
        })
        .then((res) => (this.posts = res.data));
    },*/
  },
  mounted() {
    this.checkIfFilterIsApplied(this.filters);
   
    //this.makeGetRequestToApi("api/rent");
    axios
      .get("api/categories")
      .then((res) => (this.categories = res.data))
      .then(() => {
        let object = { id: 0, categorieName: "All" };
        this.categories.unshift(object);
      });

    this.getDataByFilter(this.filters);
    
  },
};
</script>

<style scoped>
.headingCustom {
  font-variation-settings: "wght" 900;
}
a {
  text-decoration: none !important;
}
</style>