<template>
    <v-col class="mx-8">
      <h1 class="text-h5 font-weight-bold text-capitalize">my posts</h1>
      <v-divider></v-divider>
      
        <v-row >
          <v-col offset-lg="10" cols="2">
            <v-btn class="blue darken-4 font-weight-bold white--text" width="100%">add post</v-btn>
          </v-col>
          <v-col>
             <v-simple-table>
        <thead>
          <tr>
            
            <th class="text-center  font-weight-bold  text-title" v-for="header in Headers" :key="header">{{header}}</th>
            
          </tr>
        </thead>

        <tbody>
          <tr v-for="post in Posts" :key="post.id">
            
            <td >{{post.title}}</td>
            <td>{{post.type}}</td>
            <td>{{post.date}}</td>
            <td class="text-right">
              {{post.state}}
            </td>
             <td class="text-right">
              {{post.state}}
            </td>
          </tr>

        </tbody>
      </v-simple-table>
            

          </v-col>
        </v-row>
        
    </v-col>
    
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            Posts:[],
            Headers:['titre','type','date de publication','state','action']
        }
    },
    setup() {
        
    },
    methods:{
        async getMyOwnPosts(){
            try{
              let response = await axios.get("/api/authentifiedUser/posts"); 
              this.Posts = response.data;
            }catch(error){
                console.log(error);
            }
             
        }
    },
    mounted(){
        this.getMyOwnPosts();

    }
}
</script>

<style scoped>
td{
    text-align: center !important;
    text-transform: capitalize;
}

</style>