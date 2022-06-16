<template>
     <v-col  class="  parent" cols="12" md="12" xl="6" lg="6" :class="{'backgroundImage': 'url(' + require(`@/assets/villes/${imagePath}`) + ' )'}">
            
<div class="villeCard" >
              <p class="white--text text-h5 zindex2 font-weight-bold">{{cityName}}</p>
              <v-btn class="rounded-lg zindex1 darken-2">explore</v-btn>
              <img :src="require(`@/assets/villes/${imagePath}`)" class="image" alt="">
            </div>
          </v-col>

</template>

<script>
export default {
    setup() {
        
    },
    props:["cityName","imagePath","url"],
  
    
}
</script>

<style scoped>
p{
    background: #877c7ca1;
    padding: 0 10px;
}
.parent{
    position: relative;
    max-height: 500px;
    border-radius: 18px;
}

.image{
    
    z-index: 0;
    object-fit: cover;
    width: 100%;
    height: 100%;
    border-radius: 10px;
}
.villeCard{
  height: 400px;
  width:100% ;
 
  background-size: cover;
  display: flex;
  flex-direction:column ;
  justify-content: center;
  align-items: center;
}
.zindex1,.zindex2{
    z-index: 1;
    position: absolute;
}
.zindex1{
    top:60%
}

</style>