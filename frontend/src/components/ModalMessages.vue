<template>
    <div>
        <v-dialog v-model="dialog" max-height="800px" max-width="500px">
            <div class="white  pt-2 px-5 lighten-1 " >
                <h1 class="text-center text-h6 font-weight-bold " :class="type == 'success' ? 'good' : 'error'">{{type}}</h1>


                <p class="white font-weight-bold text-body-2 ma-0 pa-0 grey--text text--darken-2 py-5 text-center">{{message}}</p>
                <v-row dense class="ma-0 d-flex justify-end" >
                  
                    
                    <v-col cols="3" >
                        <v-btn  texte width="100%"  depressed  class="blue--text" @click="closeModal()">close</v-btn>
                    </v-col>
                </v-row>
                
            </div>
    </v-dialog>
    </div>
</template>

<script>
import {mapMutations} from 'vuex';

export default {
    props : ["type","message"],
    setup() {
        
    },
    data(){
        return{
            dialog:true
        }

    },
    computed:{
        show(){
            return this.$store.state.MessageModalData;
        }
    },
    methods:{
        ...mapMutations(["DisableShowModalData"]),
        closeModal(){
            this.$emit("closeMessageModel");

        }
    }
}
</script>


<style scoped>
.error{
   background: #dd6666;
    border-radius: 3px; 
    color: white !important;
}
.good{
    background: rgb(95, 225, 95);
     border-radius: 3px; 
    color: white !important;
}
</style>