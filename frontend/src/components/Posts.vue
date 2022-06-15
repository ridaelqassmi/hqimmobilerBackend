<template>
    <v-row class="mx-6" justify="center" tile>
      <v-col v-for="post in posts" :key="post.id" cols="11" md="3" lg="3" sm="12" xs="12">
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
</template>

<script>
export default {
  
    props :['posts']

}
</script>
