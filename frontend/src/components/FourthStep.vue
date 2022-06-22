
<template>
  <div>
    <v-row>
      <v-col cols="12"><p>Caractéristiques générales</p></v-col>
      <v-col class="d-flex">
        <div
          v-for="item in items"
          :key="item.id"
          @click="toggleCheck(item.id)"
          :class="item.checked?'checked':''"
          class="d-flex flex-column justify-center align-center re"
        >
          <v-img
            :src="require('@/assets/features/' + item.imageSrc)"
            height="50px"
            width="50px"
            
          >
          </v-img>
          <p>{{ item.name }}</p>
        </div>
      </v-col>
    </v-row>
    <v-btn color="primary"   class="mt-5" @click="createPost()"> save </v-btn>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  setup() {},
  data() {
    return {
      items: [],
      selectedFeatures:[],
    }
  },
  methods:{
      toggleCheck(id){
        

            this.items.forEach((element ,index)=> {
            
             
            
                if(element.id == id){
                  
                    this.items[index].checked = !this.items[index].checked;
                    console.log(this.items[index]);  
                }  
                
                
                
            });
      },
      createPost(){
        
        this.$emit("createPost",[1,2]);
      },
      getFeatures(){
        axios.get('api/features').then((res)=>this.items = res.data).then(()=>{
          
          this.items.forEach(element=>{
            element.checked=false;
          })
        })
      }
  },
  mounted(){
    this.getFeatures();
  },
  watch:{
    items(){
      this.items.forEach(element =>{
          if(element.checked == true){
            this.selectedFeatures.push(element.id);
          }
        })
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
    background: red;
    border: solid #d9d7d7 0.1px;
}
</style>

