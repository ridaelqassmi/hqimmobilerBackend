<template>
  <div class="parent">
   
    <v-container class="background-- is-fullhd" color="#003BDE" fluid>
      <v-row dense class="align-center" >
        <v-col class="align-center">
          <p class="text-body-2 font-weight-bold mt-4 white--text" >
             <router-link class="text-body-2 font-weight-bold mt-4 white--text" :to="'/'" 
            > Home ></router-link
          > 
          
          <router-link :to="{name:'buy',query:{categorie:Post.categories.id}}" class="text-body-2 font-weight-bold mt-4 dark white--text"> {{ Post.cities.cityName }} ></router-link>
            {{ Post.categories.categorieName }}
          </p>
          <h1 class="text-h7 price-color font-weight-bold">
            {{ Post.price }} DH
            <span class="white--text text-body-2 font-weight-bold"
              >| {{ Post.adress }}</span
            >
          </h1>
          <h1 class="text-h5 white--text font-weight-bold">
            {{ Post.title }}
            <v-icon @click="toggleFavourite()" class="mx-5"
              >mdi-heart-outline</v-icon
            >
          </h1>
          <p class="white--text">
            {{ Post.numberRoom }} Chambre | {{Post.salleBain}} salle de bain | {{Post.areaSize}} m <sup>2</sup> | Etat: {{Post.etat.name}}
          </p>
        </v-col>

        <v-col cols="6" xs="0" class="hidden-xs-only">
          <v-row class="px-5 d-flex justify-end">
            <v-btn
              color="#f8e71c"
              class="black--text text-title font-weight-meduim text-capitalize"
              >Envoyer un message</v-btn
            >
          </v-row>
        </v-col>
      </v-row>
      <b-carousel
        :autoplay="false"
        indicator-custom
        :indicator-inside="false"
        :overlay="gallery"
        @click="switchGallery(true)"
        :items-to-show="2"
      >
        <b-carousel-item v-for="(item, i) in images2.length" :key="i">
          <a class="image">
            <img
              :src="require('../assets/' + getImgUrl(i))"
              ratio=""
              class="myimage"
            />
          </a>
        </b-carousel-item>
        <span
          v-if="gallery"
          @click="switchGallery(false)"
          class="modal-close is-large"
        />
        <template #indicators="props">
          <figure class="al image" :draggable="false">
            <b-image
              :draggable="false"
              :src="require('../assets/' + getImgUrl(props.i))"
              :title="props.i"
              class="image-2"
            />
          </figure>
        </template>
      </b-carousel>
    </v-container>
    <v-container fluid class="is-fullhd">
      <v-row>
        <v-col
          cols="12"
          md="12"
          lg="12"
          xl="12"
          sm="12"
          xs="12 "
          class="text-center"
        >
          created by
          <router-link class="pa-2" :to="'/user/' + Post.user.id"
            >{{ Post.user.firstName }} {{ Post.user.lastName }}</router-link
          >
          at <span>{{ Post.date | formatDate }}</span>
        </v-col>
        <v-col cols="12" md="8" lg="8" xl="8" sm="12" xs="12">
          <v-expansion-panels class="mb-6 pa-0 elevation-0">
            <v-expansion-panel>
              <v-expansion-panel-header
                class="text-center"
                expand-icon="mdi-menu-down"
              >
                description
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <p class="black--text text--lighten-2 description">
                  {{ Post.description }}
                </p>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>
                full details
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <p><strong>location : </strong> {{ Post.location }}</p>
                <p>
                  <strong>Numbers of Rooms : </strong> {{ Post.numberRoom }}
                </p>
                <p>
                  <strong>nombre de salle de bain : </strong> {{ Post.salleBain }}
                </p>
                <p>
                  <strong>surfacie : </strong> {{ Post.areaSize }} m<sup>2</sup>
                </p>
                <p><strong>ville : </strong> {{ Post.cities.cityName }}</p>
                <p>
                  <strong>categorie : </strong>
                  {{ Post.categories.categorieName }}
                </p>
                <p>
                  <strong>posted by : </strong> {{ Post.user.firstName }}
                  {{ Post.user.lastName }}
                </p>
                <h1
                  class="
                    text-h5
                    blue--text
                    font-weight-bold
                    text--darken-2 text-center
                  "
                >
                  Features
                </h1>
                <hr />
                <div class="d-flex flex-wrap justify-space-around">
                  <div
                    class="d-flex flex-column align-center mx-11 my-4"
                    v-for="i in 10"
                    :key="i"
                  >
                    <img
                      src="https://www.rent.com/_next/static/media/washerDryerInUnit.572249f0.png"
                      class="my-2"
                      width="100px"
                      alt=""
                    />
                    <span class="text-center">waching machine</span>
                  </div>
                </div>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>

          <!--here it goes reviews  -->
         <ReviewSystem />

          <!--here it ends-->
        </v-col>
        <v-col
          class="py-5 px-6 hidden-sm-only fixed f flat"
          cols="12"
          md="4"
          lg="4"
          xl="4"
          sm="12"
          xs="12"
        >
          <v-row class="d-flex justfiy-center">
            <v-col
              class="white elevation-2 lighten-2 fixed"
              cols="12"
              md="10"
              lg="10"
              xl="10"
              sm="12"
              xs="12"
            >
              <h1
                class="
                  text-h5
                  blue--text
                  font-weight-bold
                  text--darken-2 text-center
                "
              >
                Contact
              </h1>
              <hr />
              <b-field label="Message">
                <b-input maxlength="200" type="textarea"></b-input>
              </b-field>
              <b-field
                label="Email"
              
              >
                <b-input type="email"  maxlength="30"> </b-input>
              </b-field>

              <b-field label="Phone Number">
                <b-input placeholder="Number" type="number"> </b-input>
              </b-field>
              <b-field>
                <b-button expanded class="is-primary"> send </b-button>
              </b-field>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
import Vue from "vue";

import ReviewSystem from '@/components/ReviewSystem.vue';
Vue.filter("formatDate", function (value) {
  if (value) {
    return moment(String(value)).format("MMMM Do YYYY, h:mm:ss a ");
  }
});

export default {
  components: {ReviewSystem },
  data() {
    return {
      gallery: false,
      favourite: false,
      Post: "",
      images: [
        {
          src: "https://photos.zillowstatic.com/fp/8ce5f240390cb38c12b128fba32324cd-cc_ft_768.webp",
        },
        {
          src: "https://photos.zillowstatic.com/fp/7a1835b81ca6fa0160c2ae7f4fe57f2b-cc_ft_768.webp",
        },
        {
          src: "https://photos.zillowstatic.com/fp/8513f1fc765cdb74b68cfde0656268c6-cc_ft_768.webp",
        },
        {
          src: "https://photos.zillowstatic.com/fp/a2c1e3504feb791c726a5aec88e36b36-cc_ft_768.webp",
        },
        {
          src: "https://photos.zillowstatic.com/fp/7a120bbadd0b015940fe4809b7df6311-cc_ft_768.webp",
        },
        {
          src: "https://photos.zillowstatic.com/fp/8513f1fc765cdb74b68cfde0656268c6-cc_ft_768.webp",
        },
      ],
      images2: [],
    };
  },
  created() {
    axios
      .get("api/rent/" + this.$route.params.postId)
      .then((res) => (this.Post = res.data))
      .then(() => {
        this.images2 = this.Post.images;
      });
  },
  methods: {
    getImgUrl(value) {
      return this.images2[value].imagePath;
    },
    switchGallery(value) {
      this.gallery = value;
      if (value) {
        return document.documentElement.classList.add("is-clipped");
      } else {
        return document.documentElement.classList.remove("is-clipped");
      }
    },
    toggleFavourite() {},
    
  },
  
};
</script>
<style scoped>
.background-- {
  background: #003bde;
  background: #5474cd;
}
.price-color {
  color: #f8e71c;
}
.image-container {
  display: flex;
  background: white;
  gap: 10px;
  overflow-x: auto;
  scrollbar-width: thin;
  scrollbar-color: #6969dd #e0e0e0;
}
.image-container::-webkit-scrollbar-track {
  background-color: rgb(169, 169, 169);
}
.image-container::-webkit-scrollbar {
  width: 10px;
}
.image-container::-webkit-scrollbar-thumb {
  background: rgb(25, 0, 255);
}

.myimage {
  max-height: 700px !important;
}
.image-2 {
  max-height: 100px !important;
}
.image-pagination > a {
  border-radius: 50%;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.is-active .al img {
  border: 1px solid #fff;
  filter: grayscale(0%);
}
.al img {
  border: 1px solid transparent;
  filter: grayscale(100%);
}

.description {
  text-indent: 50px;
  text-align: justify;
}

@media screen and (min-width: 992px) {
  .f {
    position: sticky;
    top: 80px;
  }
}
.parent {
  background: #e7e7e7;
}

.tube{
  width: 100%;
 
  background: rgb(209, 205, 205);
  height: 22px;
  border-radius: 10px;

}
.filledTube{
  width: 80%;
  background: #1e78dd;
  height: 100%;
  border-radius: 10px;
}
</style>