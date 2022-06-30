<template>
  <div class="ma-11 white pa-5 mainContent">
    <div class="white">
      <h1 class="text-center text-h6">gestion de categories</h1>
    </div>

    <v-row class="pt-5">
      <v-col cols="3" offset="9">
        <v-btn
          class="blue lighten-1 px-5 font-weight-bold white--text"
          width="100%"
          @click="showAddModal = true"
          >add categories</v-btn
        >
      </v-col>

      <v-col cols="12">
        <v-simple-table class="rounded-lg pt-5">
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
            <tr v-for="categorie in categories" :key="categorie.id">
              <td class="text-center tds">{{ categorie.id }}</td>
              <td class="text-center tds text-lowercase">
                {{ categorie.categorieName }}
              </td>

              <td class="text-center pt-2">
                <v-btn
                  depressed
                  max-height="30px"
                  max-width="30px"
                  min-height="20px"
                  min-width="20px"
                  fab
                  class="pa-0 ma-0 green mx-2"
                  ><v-icon
                    class="ma-0 pa-0"
                    color="white"
                    @click="getCategorieById(categorie.id)"
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
                  class="pa-0 ma-0 mx-2 red"
                  ><v-icon small @click="deleteCategorie(categorie.id)" color="white"
                    >mdi-delete</v-icon
                  ></v-btn
                >
              </td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-col>

      <!--add modal -->
      <v-dialog v-model="showAddModal" width="600px" class="transparent">
        <v-card>
          <v-col cols="12">
            <h1 class="text-capitalize text-center font-weight-bold my-2">
              edite post
            </h1>
          </v-col>
          <v-col cols="12">
            <p class="font-weight-bold body-2 text-capitalize mb-0">
              categories name
            </p>
            <v-text-field outlined dense v-model="newCategorieName">
            </v-text-field>
          </v-col>
          <v-col class="d-flex justify-end">
            <v-btn color="blue darken-1" text @click="showAddModal = false">
              Close
            </v-btn>

            <v-btn
              class="blue darken-1 px-7 white--text"
              @click="addCategorie()"
            >
              Save
            </v-btn>
          </v-col>
        </v-card>
      </v-dialog>

      <!--edit modal-->
      <v-dialog v-model="showUpdateModal" width="600px" class="transparent">
        <v-card>
          <v-col cols="12">
            <h1 class="text-capitalize text-center font-weight-bold my-2">
              edite post
            </h1>
          </v-col>
          <v-col cols="12">
            <p class="font-weight-bold body-2 text-capitalize mb-0">
              categories name
            </p>
            <v-text-field
              outlined
              dense
              v-model="selectedCategorie.categorieName"
            >
            </v-text-field>
          </v-col>
          <v-col class="d-flex justify-end">
            <v-btn color="blue darken-1" text @click="showUpdateModal = false">
              Close
            </v-btn>

            <v-btn
              class="blue darken-1 px-7 white--text"
              @click="updateCategorie(selectedCategorie.id)"
            >
              Save
            </v-btn>
          </v-col>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
export default {
  setup() {},
  data() {
    return {
      Headers: ["id", "categories name", "actions"],

      categories: [],
      showAddModal: false,
      dialog: false,
      newCategorieName: "",
      selectedCategorie: {},
      showUpdateModal: false,
    };
  },
  methods: {
    getAllCategories() {
      
      axios.get("/api/categories").then((res) => (this.categories = res.data));
    },
    addCategorie() {
      axios
        .post("/api/categories", {
          categorieName: this.newCategorieName,
        })
        .then((res) => {
          this.categories = [res.data, ...this.categories];
        });
    },
    getCategorieById(id) {
      axios
        .get("/api/categories/" + id)
        .then((res) => (this.selectedCategorie = res.data)).then(()=>{
            this.showUpdateModal = true
        });
    },
    updateCategorie(id) {
      axios
        .put("api/categories/" + id, {
          categorieName: this.selectedCategorie.categorieName,
          id: this.selectedCategorie.id,
        })
        .then(() => {
          this.getAllCategories();
          this.showUpdateModal = false
        });
    },
    deleteCategorie(id){
        axios.delete("/api/categories/"+id).then(()=>{
            this.getAllCategories();
        })
    }
  },

    mounted() {
      this.getAllCategories();
    },
  
};
</script>
<style scoped>
.mainContent {
  border: solid #e5dada 1px !important;
  border-radius: 5px;
}
.tds {
  text-align: center !important;
  text-transform: capitalize;
  padding: 10px 0 !important;
}
thead {
  background: #06283d;
  padding-top: 10px;
}
th {
  padding: 10px 10px !important;
  color: white !important;
}
html {
  overflow: hidden !important;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

html::-webkit-scrollbar {
  width: 0;
  height: 0;
}

.v-dialog .v-dialog--active {
  overflow: hidden;
}
</style>