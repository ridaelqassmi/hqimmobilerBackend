<template>
  <div>
    <v-row>
      <v-col cols="12"><p>Caractéristiques générales</p></v-col>
      <v-col class="d-flex">
        <div
          v-for="item in items"
          :key="item.name"
          @click="toggleCheck(item.id)"
          :class="item.checked?'checked':''"
          class="d-flex flex-column justify-center align-center re"
        >
          <v-img
            :src="require('@/assets/' + item.path)"
            height="50px"
            width="50px"
            
          >
          </v-img>
          <p>{{ item.name }}</p>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      items: [
        { id:1,name: "Garage", path: "features/Garage.png",checked:false },
        { id:2,name: "jardin", path: "features/jardin.png",checked:false },
      ],
    };
  },
  methods:{
      toggleCheck(id){
            this.items.forEach(element => {
                if(element.id == id){
                    element.checked = !element.checked;
                }       
            });
      }
  }
};
</script>

<style scoped>
.re {
 width: 100px;
    border-radius: 50%;
    
    margin: 10px;
    height: 100px;
}
.checked{
    background: rgb(215, 215, 226);
    border: solid #d9d7d7 0.1px;
}
</style>

