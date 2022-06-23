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

    <RentPostFilterComponent  @getDataByFilter="getDataByFilter"/>

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
      posts: [],
      correntPage: 0,
      numberPages: 0,
      lastPage: false,
      numberOfElements: 0,
      totalElements: 0,
      categories: [],
      categoriesId: 0,

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
      console.log();
      if (this.categoriesId != 0) {
        this.makeGetRequestToApi(
          "api/rent/categorie/" +
            this.categoriesId +
            "/page/" +
            this.correntPage
        );
      } else {
        this.makeGetRequestToApi("api/rent/page/" + (page - 1));
      }
    },
    getPostByCategorie(id) {
      this.categoriesId = id;
      if (id == 0) {
        this.correntPage = 0;
        return this.getPage(this.correntPage + 1);
      }
      this.correntPage = 0;
      this.makeGetRequestToApi(
        "api/rent/categorie/" + id + "/page/" + this.correntPage
      );
    },
    getDataByFilter(filters){
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
    
    this.makeGetRequestToApi("api/rent");
    axios
      .get("api/categories")
      .then((res) => (this.categories = res.data))
      .then(() => {
        let object = { id: 0, categorieName: "All" };
        this.categories.unshift(object);
      });
    
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