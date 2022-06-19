<template>
  <div class="parent d-flex aling-center">
    <v-btn
      class="black--text elevation-0 transparent"
      
      dark
      :to="{ name: 'UserProfile', params: { userId: 2 } }"
    >
      <v-icon >mdi-account</v-icon> <p class="mb-0 d-none d-sm-flex px-2">
      {{ getAuthUser.firstName }} {{ getAuthUser.lastName }}
    </p>
    </v-btn>
   
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters, mapMutations, mapState } from "vuex";
export default {
  methods: {
    ...mapMutations(["setAuthentifiedUserDetails"]),
  },
  computed: {
    ...mapState(["AuthentifiedUserDetails"]),
    ...mapGetters(["getAuthUser"]),
  },
  created() {
    if (!this.AuthentifiedUserDetails) {
      axios.get("api/authentifiedUser").then((res) => {
        this.setAuthentifiedUserDetails(res.data);
      });
    }
  },
};
</script>

<style scoped>
.parent {
  align-items: stretch;
  justify-content: flex-end;
}
p {
  align-self: center;
}
</style>