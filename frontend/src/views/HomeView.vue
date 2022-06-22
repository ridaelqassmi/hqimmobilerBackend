<template>
  <v-container fill-height fluid class="is-fullhd mx-5">
    <v-row align="center" class="part1">
      <v-col cols="12" md="6" sm="4">
        <h1 class="text-h3 font-weight-bold my-5 font-italic">HQ immobilier</h1>
        <p class="text-body-2">
          bienvenue sur notre site, vous avez besoin d'aide pour choisir votre
          maison ? je suppose que vous êtes au bon endroit, notre site est doté
          de multiples fonctionnalités qui, nous l'espérons, vous seront utiles
        </p>
        <v-btn
          class="
            elevation-2
            blue
            darken-4
            my-5
            pa-5
            font-weight-bold
            white--text
            text-button
          "
          >Explore les posts</v-btn
        >
      </v-col>
      <v-col cols="12" sm="8" md="6" lg="6" xl="6">
        <v-img src="../assets/image1.jpg" class="rounded-lg"></v-img>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <h1 class="text-h5 my-5 text-center font-weight-bold border solo">
          chercher une post
        </h1>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" lg="9" sm="12" xs="12" xl="8">
              <v-text-field
                
                label="search"
                outlined
                class="width:150px"
                clearable
                append-icon="mdi-magnify"
                multiple
                @click:append="Search()"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row justify-lg="center" justify-md="center" justify-sm="center">
            <v-col cols="12" sm="4" md="4" lg="3" xl="2" xs="8" justify-center>
              <CustumeCard
                iconName="mdi-pencil"
                text="creé votre post"
                url="/createPost"
              />
            </v-col>
            <v-col cols="12" sm="4" md="4" lg="3" xl="2" xs="8">
              <CustumeCard
                iconName="mdi-key"
                text="location "
                url="/posts?type=2 "
              />
            </v-col>
            <v-col cols="12" sm="4" md="4" lg="3" xl="2" xs="8">
              <CustumeCard iconName="mdi-home" text="vonte " url="/posts?type=1 " />
            </v-col>
          </v-row>
        </v-container>
      </v-col>
    </v-row>
    <v-row>
      <v-container>
        <p class="text-h5 my-5 text-center font-weight-bold border solo">
          popular sites
        </p>
        <v-row>
          <VilleCard
            v-for="(ville, i) in villes"
            :key="i"
            :imagePath="ville.imagePath"
            :cityName="ville.cityName"
            :url="ville.url"
          />
        </v-row>
      </v-container>
    </v-row>
  </v-container>
</template>

<style scoped>
.part1 {
  min-height: 90vh;
}
.costumizedCard {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;

  padding: 20px;
  height: 200px;
  border-radius: 10px;
  background: #0226ebad;
  background: #2b42c1;

  box-shadow: inset 3px 0px 0px 3px #f3a2a2;
}

/*ville card style */
</style>

<script>
import CustumeCard from "@/components/CustumeCard.vue";
import VilleCard from "@/components/VilleCard.vue";
export default {
  components: { CustumeCard, VilleCard },
  setup() {},
  data() {
    return {
      villes: [
        { cityName: "Marrakech", imagePath: "marrakech.png", url: "" },
        { cityName: "CasaBlanca", imagePath: "casablanca.png", url: "" },
        { cityName: "fes", imagePath: "fes.png", url: "" },
        { cityName: "Rabat", imagePath: "rabat.png", url: "" },
        { cityName: "tangier", imagePath: "tangier.png", url: "" },
        { cityName: "beni mellal", imagePath: "beni-mellal.jpg", url: "" },
      ],
    };
  },
  methods: {
    Search() {
      console.log("i am cliked");
    },
  },
};
</script>