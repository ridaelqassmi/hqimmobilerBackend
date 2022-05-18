<template>
<div class="container-parent is-fullhd">
  <div class="container1 " >
    <div class="logo-container">
      <p class="text-h3">HQimmobilier</p>
    </div>
     <b-field position="is-centered"  class="input1 px-0  mx-0 " >
      <b-input placeholder="Search..." 
                type="search"
                
              style="width:80%" icon="magnify">
            </b-input>
              <p class="control">
                <b-button type="is-primary" label="Search" />
            </p>
     </b-field>        
    <div class="button-container">
      <b-button tag="router-link" to="/createPost" type="is-primary" expanded outlined>make a post</b-button>
      <b-button tag="router-link" to="/createPost" type="is-primary" expanded outlined>rent</b-button>
      <b-button tag="router-link" to="/createPost" type="is-primary" expanded outlined>buy</b-button>
      
   
    </div>
  </div>
</div>
  
</template>

<style scoped>
.container1{
  background: rgb(207, 198, 198);
  height:500px;
  justify-content: center;
  display: flex;
  align-items: center;
  flex-direction: column;

}
.input1{
  /*border:solid rgb(82, 81, 81) 1px;*/
  /*border-radius: 25px;*/
 
   width:80%;
 
  


}
.columns{
  display: flex;

}
.button-container{
  display: flex;
  justify-content: space-evenly;
  
   width:80%;
   gap:10px;

  align-items: center;
}

</style>