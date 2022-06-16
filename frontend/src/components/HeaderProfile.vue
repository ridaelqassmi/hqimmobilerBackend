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
      :to="{ name: 'UserProfile', params: { userId: AuthentifiedUserDetails.id }}"
     
    >
      <v-icon>mdi-account</v-icon>
      {{ AuthentifiedUserDetails.firstName }}
      {{ AuthentifiedUserDetails.lastName }}
      <v-icon>mdi-menu-down</v-icon>
      
      </v-btn
    >
</div>
    
  

</template>

<script>
import axios from "axios";
import { mapMutations, mapState } from "vuex";
export default {
  methods: {
    ...mapMutations(["setAuthentifiedUserDetails"]),

    
      
  },
  computed: {
    ...mapState(["AuthentifiedUserDetails"]),
  },
  created() {
      if(!this.AuthentifiedUserDetails){
          axios.get("api/authentifiedUser").then((res) => {
        this.setAuthentifiedUserDetails(res.data);
    });
      }
    

  },
};
</script>