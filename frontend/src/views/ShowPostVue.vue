<template>
  <div>
    {{ Post }}
    <v-container class="background-- is-fullhd" color="#003BDE" fluid>
      <v-row dense class="align-center" justify="center">
        <v-col class="align-center">
          <p class="text-body-2 font-weight-bold mt-4 white--text">
            Home> {{ Post.cities.cityName }} >
            {{ Post.categories.categorieName }}
          </p>
          <h1 class="text-h7 price-color font-weight-bold">
            {{ Post.price }} DH
            <span class="white--text text-body-2 font-weight-bold"
              >| {{ Post.location }}</span
            >
          </h1>
          <h1 class="text-h5 white--text font-weight-bold">{{ Post.title }}</h1>
          <p class="white--text">
            {{ Post.numberRoom }} Rooms | 2 Bath | 100 m
          </p>
        </v-col>

        <v-col cols="6" xs="0" class="hidden-xs-only">
          <v-row justify="end" class="px-5">
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
          <v-expansion-panels class="mb-6 elevation-0">
            <v-expansion-panel>
              <v-expansion-panel-header
                class="text-center"
                expand-icon="mdi-menu-down"
              >
                description
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <p class="black--text text--lighten-2 description">
                  {{Post.description}}
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
                  <strong>AREA : </strong> {{ Post.areaSize }} m<sup>2</sup>
                </p>
                <p><strong>city : </strong> {{ Post.cities.cityName }}</p>
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
          <v-row>
            <v-col cols="8">
              <h1 class="text-h4 mt-10 font-weight-bold">Ratings & Reviews</h1>
              <h1 class="text-overline font-weight-bold">
                Review this property
              </h1>
              <span class="text-body-2"
                >Share details of your own experience</span
              >
            </v-col>
            <v-spacer> </v-spacer>
            <v-col>
              <v-btn
                color="#003BDE"
                class="mt-14 white--text text-overline rounded-lg"
                @click="reviewModal = true"
                >write A review</v-btn
              >
            </v-col>
          </v-row>

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
          <v-row justify="center">
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
                type="is-danger"
                message="This email is invalid"
              >
                <b-input type="email" value="john@" maxlength="30"> </b-input>
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
    <v-row justify="center">
      <v-dialog v-model="reviewModal" persistent max-width="600px">
        <v-card color="#f0f2f5" class="lighten-3 px-5">
          <v-card-title class="text-h5 font-weight-bold text-center">
            <v-row justify="center my-5"> Rate your overall experience </v-row>
          </v-card-title>
          <v-card-text class="elevation-2 white px-2 pt-5">
            <div class="ma-5">
              <v-row align="center" class="mx-0">
                <v-rating
                  v-model="Ratingvalue"
                  :value="0"
                  color="amber"
                  dense
                  half-increments
                  size="24"
                >
                </v-rating>
                <v-spacer></v-spacer>
                <h1
                  class="
                    text-h6
                    px-5
                    yellow--text
                    text--darken-3
                    font-weight-bold
                  "
                >
                  {{ Ratingvalue }}
                </h1>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <b-field label="Message">
                    <b-input maxlength="200" type="textarea"></b-input>
                  </b-field>
                </v-col>
              </v-row>
            </div>
          </v-card-text>
          <v-card-actions class="py-2">
            <v-spacer></v-spacer>
            <v-btn class="" text @click="reviewModal = false">cancel</v-btn>
            <v-btn class="is-info" text>submit review</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
import Vue from "vue";
Vue.filter("formatDate", function (value) {
  if (value) {
    return moment(String(value)).format("MMMM Do YYYY, h:mm:ss a ");
  }
});

export default {
  data() {
    return {
      gallery: false,
      reviewModal: false,
      Ratingvalue: 0,
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
</style>