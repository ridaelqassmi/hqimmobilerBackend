<template>
  <v-container fluid  class="py-10 is-fullhd is-primary  background-container">
    <v-row justify-lg="center"  justify-md="center" >
      <v-col  cols="12" md="9" sm="8" xs="12">
        <p class="text-h3 font-weight-bold white--text py-8 text-center">
          {{ postType }}
        </p>

        <v-row>
          <v-col cols="6">
            <v-btn
              @click="toggleShowBuy()"
              :class="{ 'indigo  white--text': buy }"
              block
              class="text-button font-weight-bold"
              >sell</v-btn
            >
          </v-col>
          <v-col cols="6">
            <v-btn
            
              @click="toggleShowRent()"
              :class="{ 'indigo   white--text': rent }"
              block
              >Rent</v-btn
            >
          </v-col>
        </v-row>
        <rent-components v-if="rent" />
        <buy v-if="buy" />
      </v-col>
      <hr />
    </v-row>
  </v-container>
</template>
<script>
import Buy from "../components/createPostComponents/Buy.vue";
import RentComponents from "../components/createPostComponents/RentComponents.vue";

export default {
  components: { Buy, RentComponents },

  data() {
    return {
      rent: false,
      buy: true,
      postType: "Selling Post",
    };
  },

  methods: {
    toggleShowRent() {
      this.rent = !this.rent;
      this.buy = false;
      this.postType = "Renting Post";
    },
    toggleShowBuy() {
      this.buy = !this.buy;
      this.rent = false;
      this.postType = "Selling Post";
    },
  },
};
</script>

<style scoped>
.background-container{
  background: #5CBBF6 !important;
}
</style>