
<template>

  <div>
    <v-row dense class="d-flex align-center">
      <v-col cols="2">
            <v-avatar size="62" class="mr-2" color="blue">
            <span class="white--text text-h5 text-uppercase font-weight-bold  ">
              {{
              getNameShortCut()
            }}
            </span>
          </v-avatar>
         
      </v-col>
      <v-col cols="2 ">
        <p class="grey--text font-weight-bold text-body-2 pt-0 my-0">{{createdAt}}</p>
        <p class="black--text font-weight-bold py-2">{{ firstName }} {{ lastName }}</p>

      </v-col>
      <v-col cols="7">
         <v-rating
         readonly
                  
                  :value="rating"
                  color="amber"
                  dense
                  half-increments
                  size="24"
                  
                >
                </v-rating>
                <p class="text-body-2 pt-2">{{ commentContent }}. </p>
      </v-col>
    </v-row>
  </div>
</template>

<script>

import {mapState} from 'vuex';
export default {
  data(){
    return{
     

    }
  },
 
  setup() {},
  props: ["userId", "firstName", "lastName", "commentContent","id","rating","createdAt"],
  emits:['deleteComment'],
  methods: {
    getNameShortCut() {
      return this.firstName.charAt(0) + this.lastName.charAt(0);
    },
    deleteComment(id){
      this.$emit('deleteComment',id);
    },


 timeSince(x) {
   
   let intervals = [
  { label: 'year', seconds: 31536000 },
  { label: 'month', seconds: 2592000 },
  { label: 'day', seconds: 86400 },
  { label: 'hour', seconds: 3600 },
  { label: 'minute', seconds: 60 },
  { label: 'second', seconds: 1 }];
  let date =  new Date(x);
  const seconds =  Math.floor((Date.now() - date.getTime()) / 1000);
  const interval =  intervals.find(i => i.seconds < seconds);
  console.log(interval);
  const count = Math.floor(seconds / interval.seconds);
  return `${count} ${interval.label}${count !== 1 ? 's' : ''} ago`;
}
  },
  computed:{
    ...mapState(["AuthentifiedUserDetails"]),

  }
};



</script>

<style scoped>


</style>


