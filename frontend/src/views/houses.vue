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
    <div border="top" class="border-top">
      <p class="mx-10 black--text">
        {{ (20*(correntPage-1)+ numberOfElements) }} of {{ totalElements }}
      </p>
    </div>

    <v-row class="mx-6">
      <v-col v-for="post in posts" :key="post.id" cols="12" md="3">
        <v-card class="">
          <v-img
            class="white--text align-end"
            height="200px"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <v-card-title>{{ post.title }}</v-card-title>
          </v-img>

          <v-card-subtitle class="pb-0">
            {{ post.cities.cityName }}
          </v-card-subtitle>

          <v-card-text class="text--primary">
            <div>{{ post.numberRoom }} rooms area:{{ post.areaSize }} m²</div>
            <v-chip class="black--text" @click="getPostByCategorie(post.categories.id)">
              {{ post.categories.categorieName }}
            </v-chip>

            <div>{{ post.price }}</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" text > Share </v-btn>
              <router-link  style="routerlink" :to="{ name: 'showPOST', params: { postId: post.id }}"> <v-btn color="orange" text >Explore</v-btn></router-link>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

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
const BASE_URL = "http://localhost:8080/api/";
export default {
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
          BASE_URL +
            "rent/categorie/" +
            this.categoriesId +
            "/page/" +
            this.correntPage
        );
      } else {
        this.makeGetRequestToApi(BASE_URL + "rent/" + page);
      }
    },
    getPostByCategorie(id) {
        this.categoriesId = id;
      if (id ==0) {
        this.correntPage = 0;
        return this.getPage(this.correntPage+1);
      }
      this.correntPage = 0;
      this.makeGetRequestToApi(
        BASE_URL + "rent/categorie/" + id + "/page/" + this.correntPage
      );
    },
  },
  mounted() {
    this.makeGetRequestToApi(BASE_URL + "rent");
    axios
      .get(BASE_URL + "categories")
      .then((res) => (this.categories = res.data))
      .then(() => {
        let object  = {id:0,categorieName:"All"}
        this.categories.unshift(object)
      });
  },
};
</script>

<style scoped>
.headingCustom {
  font-variation-settings: "wght" 900;
}
a{
  text-decoration: none !important;
}
</style>