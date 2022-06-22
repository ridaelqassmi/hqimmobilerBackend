<template>
  <v-row dense class=" my-8">
   
  
   

    <EditProfile v-if="showEditProfile" />
    <OwnPosts v-if="showMyPost"/>
    
  </v-row>
</template>

<script>
import EditProfile from "@/components/EditProfile.vue";
import OwnPosts from '@/components/OwnPosts.vue';
import {mapMutations} from 'vuex';
export default {
  components: { EditProfile,OwnPosts },
  data: () => ({
    showEditProfile: false,
    showMyPost: false,
    showMyFavourite: false,
    showMyMessage: false,
     headers: [
        {
          text: 'Dessert ',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        { text: 'Calories', value: 'calories' },
        { text: 'Fat ', value: 'fat' },
        { text: 'Carbs ', value: 'carbs' },
        { text: 'Protein ', value: 'protein' },
        { text: 'Actions', value: 'actions', sortable: false },
      ],
    items: [
      {
        id:0,
        icon: "mdi-account",
        text: "Profile",
      },
      {
        id:1,
        icon: "mdi-post",
        text: "mon publication",
      },
      {
        id:3,
        icon: "mdi-inbox",
        text: "inbox",
      },
      {
        id:2,
        icon: "mdi-heart",
        text: "my favourite",
      },
      {
        id:5,
        icon: "mdi-logout",
        text: "log out",
      },
    ],
    
    model: 1,
  }),
  methods: {
    ...mapMutations(["logOut"]),
    //active tabs
    toggleActiveTab(id) {
      //based on this id we could make a lot of choices
      //if the id =0 means affiche Edit page
      //if the id=1 means afficher my publication
      //if the id=2 means afficher les bost que j'aime
      //if the id=3 means afficher my message inbox
      //if the id=4 means logout

      switch (id) {
        case 0:
          this.togglePage(true, false, false, false);
          break;
        case 1:
          this.togglePage(false, false, false, true);
          break;
        case 2:
          this.togglePage(false, true, false, false);
          break;
        case 3:
          this.togglePage(false, false, true, false);
          break;
        case 5:
          this.logOut();
          break;
      }
    },
    togglePage(showEditProfile, showMyFavourite, showMyMessage, showMyPost) {
      this.showEditProfile = showEditProfile;
      this.showMyPost = showMyPost;
      this.showMyFavourite = showMyFavourite;
      this.showMyMessage = showMyMessage;
    },
    
  },
};
</script>
<style scoped>
.rcard {
  /*box-shadow: 0px 1px 0px 3px #a9a9a9 !important;*/
  /*border: solid 1px #a9a9a9;*/
}
.border {
  background: rgb(203 203 203);
  color: white;
}
</style>
