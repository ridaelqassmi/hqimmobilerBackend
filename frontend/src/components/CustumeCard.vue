<template>
     <div class="costumizedCard">
                <v-btn dark class=" transparent"  depressed height="80px" append>
                  <v-icon x-large dark >
                  {{iconName}}
                </v-icon>
                </v-btn>
                
                <p class="white--text text-body-1 font-weight-bold">{{text}}</p>
              </div>
</template>

<script>
export default {
    setup() {
        
    },
    props:["iconName","text","url"],
}
</script>