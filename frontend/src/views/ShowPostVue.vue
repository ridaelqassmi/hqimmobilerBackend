<template>
    <div>{{Post.title}}</div>
</template>

<script>
import axios from 'axios';
const BASE_URL = "http://localhost:8080/api/rent"
export default {

    data(){
        return{
            Post:"",
        }
    }
    ,
    mounted(){
        axios.get(BASE_URL+"/"+this.$route.params.postId).then((res)=> this.Post = res.data)
       
    }
}
</script>