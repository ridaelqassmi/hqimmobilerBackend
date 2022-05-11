<template>
  <div>
    
    <v-container class="background--" color="#003BDE" fluid>
        
      <v-row dense class="align-center " justify="center">
        <v-col cols="12" md="11" class="align-center">
          <p class="text-body-2 font-weight-bold mt-4  white--text">Home> {{Post.cities.cityName}} > {{Post.categories.categorieName}} </p>
          <h1 class="text-h7 price-color  font-weight-bold">
            {{ Post.price }} DH
            <span class="white--text  text-body-2 font-weight-bold">| {{ Post.location }}</span>
          </h1>
          <h1 class="text-h5 white--text font-weight-bold ">{{Post.title}}</h1>
          <p class="white--text">{{Post.numberRoom}} Rooms | 2 Bath | 100 m</p>
        </v-col>
      </v-row>
      <div class="hidden-xs">
        <div class="image-container">
          <img
            v-for="(image,i) in images"
            height="500px"
            :key="i"
            :id="`slide-${i}`"
            :src="image.src"
            alt=""
          />
        </div>
      </div>
    </v-container>
    <v-container fluid>
      <v-row>
        <v-col cols="12" xs="12" sm="12" md="8" lg="6">
          <p class="grey--text text-h6 text--darken-4">full description<v-divider></v-divider> </p>
        <p class="black--text text--lighten-2"> Reason to believe présente à ces clients Appartement tout confort NEUF à Marchane avec place de parking et sécurisée
Espace très lumineux, dans un immeuble neuf situé à quelques dizaines de mètre du centre ville, du port et des commerces. Emplacement très recherché. 3 chambres , cuisine ,salle de bain tout confort.
A proximité immédiate de toutes les commodités. Vous rentrez, prenez l'ascenseur, direction l'appartement et vous êtes au calme en profitant d'un espace à vivre neuf et qualitatif. N'hésitez plus, poussez la porte de ce petit bijou.Nous vous invitons à nous contacter directement.
Nous sommes disponibles pour vous accompagner dans votre recherche d'achat ou d'investissement ainsi que dans votre projet de vente: Évaluation gratuite et sans engagement.
Ref Mar 0101/228</p>
        </v-col>

      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      Post: "",
      images:[
          {src:"https://photos.zillowstatic.com/fp/8ce5f240390cb38c12b128fba32324cd-cc_ft_768.webp"},
      {src:"https://photos.zillowstatic.com/fp/7a1835b81ca6fa0160c2ae7f4fe57f2b-cc_ft_768.webp"},
      {src:"https://photos.zillowstatic.com/fp/8513f1fc765cdb74b68cfde0656268c6-cc_ft_768.webp"},
      {src:"https://photos.zillowstatic.com/fp/a2c1e3504feb791c726a5aec88e36b36-cc_ft_768.webp"},
      {src:"https://photos.zillowstatic.com/fp/7a120bbadd0b015940fe4809b7df6311-cc_ft_768.webp"},
      {src:"https://photos.zillowstatic.com/fp/8513f1fc765cdb74b68cfde0656268c6-cc_ft_768.webp"},
      ],
    };
  },
  mounted() {
    axios
      .get('api/rent/'+this.$route.params.postId)
      .then((res) => (this.Post = res.data));
  },
};
</script>
<style scoped>
.background-- {
  background: #003bde;
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

.image-pagination > a {
  border-radius: 50%;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>