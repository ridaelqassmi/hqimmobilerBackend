<template>
    <div class="">
      <v-row justify="center">
        <v-col cols="12" sm="12" lg="8" md="8">
            <v-tabs v-model="tab"   width="150px"  slider-color="blue"  centered grow >
    <v-tab class="d">send</v-tab> 
    <v-tab class="d" >receved</v-tab></v-tabs>
        </v-col>
        
        <v-col cols="11" lg="8" md="8" sm="11" class="white messageDIv" v-for="messageEnvoye in messageEnvoyer" :key="messageEnvoye">
          <div class="white messageDIv">
             <p class="pa-0 ma-0 text-h6  grey--text text--darken-4 font-weight-bold">{{messageEnvoye.recever.firstName}} {{messageEnvoye.recever.lastName}}</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2">mdi-phone</v-icon>  0654101197</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2">mdi-email</v-icon> ridanewhh@gmail.com</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2" >mdi-post</v-icon> {{messageEnvoye.post.title}}</p>
             
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2">mdi-message</v-icon> {{messageEnvoye.content}} </p>
          </div>
          <div class="mt-2 d-flex justify-end ">
            <v-btn class="blue darken-2 white--text" :to="'rent/'+messageEnvoye.post.id">see post</v-btn>
          </div>
          
        </v-col>
        
               
         
                
             

        

      </v-row>
    
    

    </div>

 

    
  

</template>

<script>
import axios from "axios";
export default {
  setup() {},
  data() {
    return {
      tab: 0,
      messageEnvoyer: [],
      recevedMessages: [],
    };
  },
  methods: {
    getMessageSend() {
      axios
        .get("/api/message/send")
        .then((res) => (this.messageEnvoyer = res.data));
    },
    getMessageCommingToMe() {
      axios
        .get("/api/message/receved")
        .then((res) => (this.recevedMessages = res.data));
    },
  },
  mounted() {
    this.getMessageSend();
    this.getMessageCommingToMe();
  },
};
</script>

<style scoped>
.d {
  background: #f0f2f5 !important;
}
.messageDIv{
    border-radius: 7px;
    background: #009688 !important;
    background: white !important;
}
</style>
