<template>
    <div class="d-flex align-center justify-center">
        <p>YOu are logged out , see you soon</p>
    </div>
</template>


<script>
import {mapMutations} from 'vuex';
export default {
    setup() {
        
    },
    methods:{
         ...mapMutations(["logOut"]),
    },
    mounted(){
         this.logOut();
    }
}
</script>

<style scoped>
.r{
    height: 100vh;
}
</style>