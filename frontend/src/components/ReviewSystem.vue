<template>
  <div>
    <v-row>
      <v-col cols="8">
        <h1 class="text-h4 mt-10 font-weight-bold">Ratings & Reviews</h1>
        <h1 class="text-overline font-weight-bold">Review this property</h1>
        <span class="text-body-2">Share details of your own experience</span>
      </v-col>
      <v-spacer> </v-spacer>
      <v-col>
        <v-btn
          color="#003BDE"
          class="mt-14 white--text text-overline rounded-lg"
          @click="showReviewModal"
          >write A review</v-btn
        >
      </v-col>
      <v-col cols="12">
        <v-row>
          <v-col class="white mt-10 mx-5">
            <p class="text-h6 font-weight-bold">Star Rating System</p>
            <v-row class="d-flex flex-row align-center">
              <v-col cols="3" class="d-flex align-center flex-column">
                <p class="text-center text-h2 font-weight-bold">
                  {{ caluculeRatingAvg() }}
                </p>
                <v-rating
                  :value="caluculeRatingAvg()"
                  color="amber"
                  dense
                  half-increments
                  size="24"
                  readonly
                >
                </v-rating>
                <p class="pt-2">Average Rating</p>
              </v-col>
              <v-col cols="6">
                <div class="tube mb-3" v-for="i in 5" :key="i">
                  <div class="filledTube"></div>
                </div>
              </v-col>
              <v-col>
                <v-rating
                  readonly
                  v-for="i in 5"
                  :key="i"
                  class="mb-2"
                  v-model="Ratingvalue"
                  :value="2.5"
                  color="amber"
                  dense
                  half-increments
                  size="22"
                >
                </v-rating>
              </v-col>
              <v-col cols="12 mt-0 pt-0"
                ><p class="text-h6 font-weight-bold">Reviews</p></v-col
              >

              <v-col v-if="comments.length != 0">
                <CommentsDetail
                  v-for="comment in comments"
                  :key="comment.id"
                  :userId="comment.user.id"
                  :firstName="comment.user.firstName"
                  :lastName="comment.user.lastName"
                  :createdAt="timeSince(comment.createdAt)"
                  :commentContent="comment.content"
                  :id="comment.id"
                  @deleteComment="deleteComment"
                  :rating="comment.rating"
                />
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <!-- Modal for adding a review -->
    <v-row justify="center">
      <v-dialog v-model="reviewModal" persistent max-width="600px">
        <v-card color="#f0f2f5" class="lighten-3 px-5">
          <v-card-title class="text-h5 font-weight-bold text-center">
            <v-row justify="center" class="py-5"> Rate your overall experience </v-row>
          </v-card-title>
          <v-card-text class="elevation-2 white px-2 pt-5">
            <div class="ma-5">
              <v-row align="center" class="mx-0">
                <v-rating
                  v-model="Ratingvalue"
                  :value="0"
                  color="amber"
                  dense
                  half-increments
                  size="24"
                >
                </v-rating>
                <v-spacer></v-spacer>
                <h1
                  class="
                    text-h6
                    px-5
                    yellow--text
                    text--darken-3
                    font-weight-bold
                  "
                >
                  {{ Ratingvalue }}
                </h1>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <b-field label="Message">
                    <b-input
                      maxlength="200"
                      v-model="messageContent"
                      type="textarea"
                    ></b-input>
                  </b-field>
                </v-col>
              </v-row>
            </div>
          </v-card-text>
          <v-card-actions class="py-2">
            <v-spacer></v-spacer>
            <v-btn class="" text @click="reviewModal = false">cancel</v-btn>
            <v-btn class="is-info" text @click="submitReview()"
              >submit review</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import CommentsDetail from "@/components/CommentsDetail.vue";
import axios from "axios";
import {mapMutations,mapState} from 'vuex';
export default {
  components: { CommentsDetail },
  setup() {},
  data() {
    return {
      reviewModal: false,
      Ratingvalue: 0,
      messageContent: "",
      AverageRatingvalue: 0,
      comments: [],
      intervals: [
        { label: "year", seconds: 31536000 },
        { label: "month", seconds: 2592000 },
        { label: "day", seconds: 86400 },
        { label: "hour", seconds: 3600 },
        { label: "minute", seconds: 60 },
        { label: "second", seconds: 1 },
      ],
    };
  },
  computed:{
    ...mapState(["showAuthModal"])
  },
 
  methods: {
    ...mapMutations(["toggleAuthModal"]),
    showReviewModal(){
      this.toggleAuhModal;
      this.$store.commit('toggleAuthModal')
      if(this.$store.state.showAuthModal==false){
        this.reviewModal = true;
      }


    },
    timeSince(x) {
      let date = new Date(x);
      const seconds = Math.floor((Date.now() - date.getTime()) / 1000);
      const interval = this.intervals.find((i) => i.seconds < seconds);
      
      if (typeof interval == "undefined") {
        return "1 s ago";
      } else {
        const count = Math.floor(seconds / interval.seconds);
        return `${count} ${interval.label}${count !== 1 ? "s" : ""} ago`;
      }
    },
    async submitReview() {
      //let commentDto = {message:this.messageContent,postid:this.$route.params.postId};
      
      let response = await axios.post("/api/post/comment", {
        message: this.messageContent,
        postid: this.$route.params.postId,
        rating: this.Ratingvalue,
      });
      
      if (response.status == 200) {
        this.comments = [response.data, ...this.comments];
        
        this.reviewModal = false;
      } else {
        console.log("you have to deal with this ");
      }
    },
    async getAllComment() {
      let response = await axios.get(
        "/api/post/" + this.$route.params.postId + "/comment"
      );
      if (response.status == 200) {
        this.comments = response.data;
        console.log(this.comments);
      }
    },
    async deleteComment(id) {
      let response = await axios.delete("api/post/comment/" + id);
      if (response.status == 200) {
        
        this.comments = this.comments.filter((comment) => comment.id != id);
      }
    },
    caluculeRatingAvg() {
      let somme = 0;

      this.comments.forEach((element) => {
        somme = element.rating + somme;
      });
      let s = " " + somme / this.comments.length;
      if (this.comments.length == 0) {
        return 0;
      } else {
        return s.substring(0, 4);
      }
    },
  },
  mounted() {
    this.getAllComment();
  },
};
</script>

<style scoped>
.tube {
  width: 100%;

  background: rgb(209, 205, 205);
  height: 22px;
  border-radius: 10px;
}
.filledTube {
  width: 80%;
  background: #1e78dd;
  height: 100%;
  border-radius: 10px;
}
</style>