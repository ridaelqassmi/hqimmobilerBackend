<template>
  <div class="mt-lg-40 white">
      <v-container class="red">
          hello
      </v-container>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  setup() {},
  data() {
    return {
      filters: {
        area: [0, 0],
        asc: true,
        categoriesList: [],
        cities: 0,
        features: [],
        nbRooms: [0, 0],
        orderByDate: true,
        orderByPrice: true,
        page: 0,
        prices: [0, 0],
        title: "",
      },
      posts:[]
    }
  },
  methods:{
      getPostByFilter(){
      axios({
        url:"api/rent/filter",
        method:"post",
        headers:{
          'content-type': 'application/json'
        },
        data:JSON.stringify(this.filters)
      }).then((res)=>this.posts = res.data);
    }
  }
};
</script>