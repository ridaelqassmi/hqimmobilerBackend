<template>
  <v-col class="mx-auto">
    <h1 class="text-h5 font-weight-bold text-capitalize">my posts</h1>
    <v-divider></v-divider>
    

    <v-row>
      <v-col cols="3" offset="9">
        <v-btn
          class="blue lighten-1 px-5 font-weight-bold white--text"
          width="100%"
          to="/createPost"
          >add post</v-btn
        >
      </v-col>
      <v-col cols="12">
        <v-simple-table>
          <thead>
            <tr>
              <th
                class="text-center font-weight-bold text-title"
                v-for="header in Headers"
                :key="header"
              >
                {{ header }}
              </th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="post in Posts" :key="post.id">
              <td class="text-center tds">{{ post.title }}</td>
              <td class="text-center tds text-lowercase">{{ post.type }}</td>
              <td class="text-center tds">{{ timeSince(post.date) }}</td>
              <td class="text-center tds">
                {{ post.state === false ? "pending" : "published" }}
              </td>
              <td class="text-center pt-2">
                <v-btn
                  depressed
                  max-height="30px"
                  max-width="30px"
                  min-height="20px"
                  min-width="20px"
                  fab
                  class="pa-0 ma-0 green"
                  ><v-icon
                    class="ma-0 pa-0"
                    color="white"
                    @click="getPostToEdit(post.id)"
                    small
                    >mdi-pencil</v-icon
                  ></v-btn
                >
                <v-btn
                  depressed
                  max-height="30px"
                  max-width="30px"
                  min-height="20px"
                  min-width="20px"
                  fab
                  class="pa-0 ma-0 red"
                  ><v-icon small @click="deletePost(post.id)" color="white">mdi-delete</v-icon></v-btn
                >
                <v-btn
                  depressed
                  max-height="30px"
                  max-width="30px"
                  min-height="20px"
                  min-width="20px"
                  fab
                  :to="'/rent/'+post.id"
                  class="pa-0 ma-0 blue lighten-1"
                  ><v-icon small color="white">mdi-eye</v-icon></v-btn
                >
              </td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-col>
    </v-row>

    <v-dialog
      v-model="showEditModal"
      max-width="600px"
      class="transparent ma-0"
    >
      <v-row dense class="mx-auto px-5 pt-5 white">
        <v-col cols="12">
          <h1 class="text-capitalize text-center font-weight-bold my-2">
            edite post
          </h1>
          
        </v-col>
         <v-col
              cols="12"
              v-if="errorsArray.length != 0"
              class="red lighten-1 errorStyle"
            >
              <p
                class="pa-0 font-weight-bold ma-0 text-body-2 white--text"
                v-for="err in errorsArray"
                :key="err"
              >
                {{ err }}
              </p>
            </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">title</p>
          <v-text-field outlined v-model="postToEdit.title" dense>
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">categorié</p>
          <v-select
            :items="categoriesItem"
            item-text="categorieName"
            item-value="id"
            dense
            outlined
            required
            v-model="postToEdit.categories"
          ></v-select>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">surfacie</p>
          <v-text-field
            type="number"
            v-model="postToEdit.areaSize"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">prix</p>
          <v-text-field type="number" v-model="postToEdit.price" outlined dense>
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            nombre de chambre
          </p>
          <v-text-field
            type="number"
            v-model="postToEdit.numberRoom"
            outlined
            dense
          >
          </v-text-field>
        </v-col>
        <v-col cols="12" lg="6">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            nombre de salle de bain
          </p>
          <v-text-field
            type="number"
            v-model="postToEdit.salleBain"
            outlined
            dense
          >
          </v-text-field>
        </v-col>

        <v-col cols="12" lg="12">
          <p class="font-weight-bold body-2 text-capitalize mb-0">
            description
          </p>
          <v-textarea
            outlined
            v-model="postToEdit.description"
            dense
            height="80px"
          ></v-textarea>
        </v-col>
      </v-row>

      <v-card-actions class="white">
        <v-row> </v-row>
        <v-col offset="7">
          <v-btn color="blue darken-1" text @click="showEditModal = false">
            Close
          </v-btn>
        </v-col>
        <v-col>
          <v-btn class="blue darken-1 white--text" @click="validate()">
            Save
          </v-btn>
        </v-col>
      </v-card-actions>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      Posts: [],
      Headers: ["titre", "type", "date de publication", "state", "action"],
      intervals: [
        { label: "year", seconds: 31536000 },
        { label: "month", seconds: 2592000 },
        { label: "day", seconds: 86400 },
        { label: "hour", seconds: 3600 },
        { label: "minute", seconds: 60 },
        { label: "second", seconds: 1 },
      ],
      idPostToEdit: "",
      showEditModal: false,
      postToEdit: {},
      title: "",
      categoriesItem: [],
      description: "",
      closeItem: [
        { id: 0, name: "closed" },
        { id: 0, name: "still " },
      ],

      selectedCategorie: "",
      surfacie: "",
      numberRoom: "",
      prix: "",
      closedDeal: "",
      errorsArray:[]
    };
  },
  setup() {},
  methods: {
    async getMyOwnPosts() {
      try {
        let response = await axios.get("/api/authentifiedUser/posts");
        this.Posts = response.data;
        this.Posts.date = this.formateDate();
      } catch (error) {
        console.log(error);
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
    formateDate() {
      let date = new Date(this.Posts.date);
      return date.getMinutes();
    },

    getPostToEdit(id) {
      console.log(id);
      axios.get("/api/rent/" + id).then((res) => (this.postToEdit = res.data));
      console.log(this.postToEdit);
      this.getAllCategories();
      this.showEditModal = true;
    },
    getAllCategories() {
      axios
        .get("/api/categories")
        .then((res) => (this.categoriesItem = res.data));
    },
    required(field, fieldname) {
      if (field == "") {
        this.errorsArray.push(fieldname + " input is required ");
      }
    },
    checkforSize(field, fieldname, min, max) {
      if (field.length <= min || field.length >= max) {
        this.errorsArray.push(
          fieldname + " min size is " + min + " and max size is " + max
        );
      }
    },
    checkforAlphabits(field, fieldname) {
      if (!/^[a-zA-Z]+$/.test(field)) {
        this.errorsArray.push(fieldname + " should contain only caracters");
      }
    },
   
    alphaNumericCheck(field, fieldname) {
      if (!/[a-zA-Z0-9]*/.test()) {
        this.errorsArray.push(
          fieldname + ": only numbers and alphabits are allowed"
        );
      }
    },
    checkfroInt(field,fieldname){
      if( !Number.isInteger(parseInt(field)) ){
        this.errorsArray.push(fieldname +" doit etre un nombre entier");
      }

    },
    checkIfNumber(field,fieldname){
      if(!(typeof parseFloat(field) =='number')){
        this.errorsArray.push(fieldname+" doit etre un nombre");
      }
    },
    checkifSelected(field,fieldname){
      if(field == ""){
        this.errorsArray.push(fieldname +" doit etre selectionné");
      }
    },
   
    
    validate() {
      this.errorsArray = [];
      console.log(this.postToEdit.categories);
      this.required(this.postToEdit.title,"titre");
      this.alphaNumericCheck(this.postToEdit.title,"titre");
      this.checkIfNumber(this.postToEdit.areaSize,"surfacie");
      this.checkfroInt(this.postToEdit.price,"prix");
    
      this.checkifSelected(this.postToEdit.categories,"categorie");
      this.checkfroInt(this.postToEdit.salleBain,"salle de bain");
     
      this.checkforSize(this.postToEdit.description,"description",20,300);
      
      this.checkfroInt(this.postToEdit.numberRoom,"nombre de chambre ");
      if(this.errorsArray.length != 0){
        return
      }
      let categories = this.postToEdit.categories ;
      if(this.postToEdit.categories.id){
        categories = this.postToEdit.categories.id;
      }
      axios
        .put("api/post/update", {
          id: this.postToEdit.id,
          title: this.postToEdit.title,
          numberRoom: this.postToEdit.numberRoom,
          categorieId: categories,
          price:this.postToEdit.price,
          area:this.postToEdit.areaSize,
          description:this.postToEdit.description,
          salleBain:this.postToEdit.salleBain
        })
        .then((res) => console.log(res.data)).then(()=>{
          this.showEditModal = false;
          this.getMyOwnPosts();
        });
    },
    deletePost(id){
      axios.delete("api/post/delete",{params :{
        id:id
      }}).then((res) => console.log(res)).then(()=> {
        this.getMyOwnPosts();
      })
    }
  },
  mounted() {
    this.getMyOwnPosts();
  },
  watch: {
    idPostToEdit(val) {
      this.idPostToEdit = val;
    },
  },
};
</script>

<style scoped>
.tds {
  text-align: center !important;
  text-transform: capitalize;
  padding: 10px 0 !important;
}
</style>