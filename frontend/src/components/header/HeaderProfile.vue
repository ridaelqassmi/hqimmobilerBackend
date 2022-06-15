<template>
  <div>
    <v-btn
      class="
        black--text
        elevation-0
        transparent
        text-capitalize
        font-weight-bold
      "
      @click="getDetail()"
    >
      <v-icon>mdi-account</v-icon>
      {{ AuthentifiedUserDetails.firstName }}
      {{ AuthentifiedUserDetails.lastName }}</v-btn
    >
    {{ AuthentifiedUserDetails }}
  </div>
</template>

<script>
import axios from "axios";
import { mapMutations, mapState } from "vuex";
export default {
  methods: {
    ...mapMutations(["setAuthentifiedUserDetails"]),

      getDetail(){
           axios.get("api/authentifiedUser",).then((res) => {
     console.log(res.data);
    });
      }
  },
  computed: {
    ...mapState(["AuthentifiedUserDetails"]),
  },
  created() {
    axios.get("api/authentifiedUser").then((res) => {
      this.setAuthentifiedUserDetails(res.data);
    });

  },
};
</script>