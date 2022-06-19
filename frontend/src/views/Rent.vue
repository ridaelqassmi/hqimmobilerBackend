<template>
  <div class="about">
    <h1>This is an rent page</h1>
    <v-btn @click="refrechs()" to="/rent?city=beniMallal">home</v-btn>
    
  </div>
</template>

<script>
export default {
  setup() {
    
  },
  data(){
    return{
         urlParams : new URLSearchParams(window.location.search)
    }
  },
  methods:{
    refrechs(){
   this.urlParams =new URLSearchParams(window.location.search)
   console.log(this.urlParams)
       console.log(this.urlParams.has('city')); // true
    console.log(this.urlParams.get('city')); // "MyParam"
    }
  },
  created(){
    this.refrechs()
  }
 

}
</script>
