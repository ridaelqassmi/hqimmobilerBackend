<template>
    <div class="">
      <v-row justify="center">
        <v-col cols="12" sm="12" lg="8" md="8">
            <v-tabs v-model="tab"   width="150px"  slider-color="blue"  centered grow >
    <v-tab class="d">send</v-tab> 
    <v-tab class="d" >receved</v-tab></v-tabs>
        </v-col>
        <div v-if="tab == 1" class="tab1">

           <v-col cols="11" lg="8" md="8" sm="11" class="white messageDIv mx-auto" v-for="messageEnvoye in recevedMessages" :key="messageEnvoye">
          <div class="white messageDIv">
             <p class="pa-0 ma-0 text-h6  grey--text text--darken-4 font-weight-bold">{{messageEnvoye.sender.firstName}} {{messageEnvoye.sender.lastName}}</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2">mdi-phone</v-icon>  {{messageEnvoye.sender.phone}}</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2">mdi-email</v-icon> {{messageEnvoye.sender.email}}</p>
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2" >mdi-post</v-icon> {{messageEnvoye.post.title}}</p>
             
          

             <p class="grey  lighten-2 pa-4  mt-5 dd"> {{messageEnvoye.content}}</p>
          </div>
          <div class="mt-2 d-flex justify-end ">
            <v-btn class="blue darken-2 white--text" :to="'rent/'+messageEnvoye.post.id">see post</v-btn>
          </div>
          
        </v-col>

        </div>



        <div v-if="tab == 0" class="tab1">

           <v-col cols="11" lg="8" md="8" sm="11" class="white  mx-auto" v-for="messageEnvoye in messageEnvoyer" :key="messageEnvoye">
          <div class="white messageDIv">
             <p class="pa-0 ma-0 text-h6  grey--text text--darken-4 font-weight-bold">{{messageEnvoye.recever.firstName}} {{messageEnvoye.recever.lastName}}</p>
   
             <p class="pa-0 ma-0 grey--text text--darken-4 text-body-2 mt-2 "><v-icon color="blue darken-3" class="pr-2" >mdi-post</v-icon> {{messageEnvoye.post.title}}</p>
             
          

             <p class="grey  lighten-2 pa-4  mt-5 dd"> {{messageEnvoye.content}}</p>

              <div class="mt-2 d-flex justify-end ">
            <v-btn class="blue darken-2 white--text" :to="'rent/'+messageEnvoye.post.id">see post</v-btn>
          </div>
          </div>
         
          
        </v-col>

        </div>
       
        
       
        
               
         
                
             

        

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
.d{
  background: #f0f2f5 !important;
}
.messageDIv{
    border-radius: 7px;
    background: #009688 !important;
    background: white !important;
}
.dd{
  border: solid black 1px !important;
  background: red !important;
}

.tab1{
  width:100%;

}
</style>
