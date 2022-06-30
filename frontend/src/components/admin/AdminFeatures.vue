<template>
  <div class="ma-11 white pa-5 mainContent">
    <div class="white">
      <h1 class="text-center text-h6">gestion de features</h1>
    </div>

    <v-row class="pt-5">
      <v-col cols="3" offset="9">
        <v-btn
          class="blue lighten-1 px-5 font-weight-bold white--text"
          width="100%"
          @click="showAddModal = true"
          >add features</v-btn
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
            <tr v-for="feature in features" :key="feature.id">
              <td class="text-center tds">{{ feature.id }}</td>
              <td class="text-center tds text-lowercase">
                {{ feature.name }}
              </td>
              <td class="text-center imageParent justfiy-center">
                <v-img
                  :src="require('@/assets/' + feature.imageSrc)"
                  alt=""
                  height="100px"
                  width="100px"
                  style="image mx-auto"
                />
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
                    @click="getFeaturesById(feature.id)"
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
                  ><v-icon
                    small
                    @click="deleteFeature(feature.id)"
                    color="white"
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
            <v-row>
              <v-col cols="12">
                <h1 class="text-capitalize text-center font-weight-bold my-2">
                  add feature
                </h1>
              </v-col>
              <v-col cols="12">
                <p class="font-weight-bold body-2 text-capitalize mb-0">
                  features name
                </p>
                <v-text-field outlined dense v-model="newFeaturesName">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-img
                  v-for="url in thumbnailUrl"
                  :key="url"
                  :src="url"
                  width="300px"
                ></v-img>
                <p class="font-weight-bold body-2 text-capitalize mt-5 mb-3">
                  selectionné un image
                </p>

                <label for="file1"
                  ><v-icon>mdi-camera</v-icon>Selectionné un seul image</label
                >

                <input
                  type="file"
                  id="file1"
                  ref="file1"
                  @change="onFileChange2"
                  accept=".jpg,.jpeg,.png"
                />
              </v-col>
            </v-row>
          </v-col>

          <v-col class="d-flex justify-end">
            <v-btn color="blue darken-1" text @click="showAddModal = false">
              Close
            </v-btn>

            <v-btn class="blue darken-1 px-7 white--text" @click="addFeature()">
              Save
            </v-btn>
          </v-col>
        </v-card>
      </v-dialog>

      <!--edit modal-->
      <v-dialog v-model="showUpdateModal" width="600px" class="transparent">
        <v-card>
          <v-col cols="12">
            <v-row>
              <v-col cols="12">
                <h1 class="text-capitalize text-center font-weight-bold my-2">
                  edit feature
                </h1>
              </v-col>
              <v-col cols="12">
                <p class="font-weight-bold body-2 text-capitalize mb-0">
                  features name
                </p>
                <v-text-field outlined dense v-model="selectedFeature.name">
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-img
                  v-for="url in thumbnailUrl"
                  :key="url"
                  :src="url"
                  width="300px"
                ></v-img>
                <p class="font-weight-bold body-2 text-capitalize mt-5 mb-3">
                  selectionné un image
                </p>

                <label for="file1"
                  ><v-icon>mdi-camera</v-icon>Selectionné un seul image</label
                >

                <input
                  type="file"
                  id="file1"
                  ref="file1"
                  @change="onFileChange2"
                  accept=".jpg,.jpeg,.png"
                />
              </v-col>
            </v-row>
          </v-col>
          <v-col class="d-flex justify-end">
            <v-btn color="blue darken-1" text @click="showUpdateModal = false">
              Close
            </v-btn>

            <v-btn
              class="blue darken-1 px-7 white--text"
              @click="updateCategorie(selectedFeature.id)"
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
      Headers: ["id", "name", "image ", "action"],

      features: [],
      showAddModal: false,
      dialog: false,
      newFeaturesName: "",
      selectedFeature: {},
      showUpdateModal: false,
      thumbnailUrl: "",
      thumbnailFile: "",
    };
  },
  methods: {
    getAllFeatures() {
      axios
        .get("/api/features")
        .then((res) => (this.features = res.data))
        .then(() => {
          console.log(this.features);
        });
    },
    addFeature() {
      let formdata = new FormData();
      formdata.append("file", this.thumbnailFile),
        formdata.append("name", this.newFeaturesName);
      axios({
        url: "/api/admin/features",
        method: "post",
        data: formdata,
      }).then((res) => {
        this.features = [res.data, ...this.features];
        this.showAddModal = false;
      });
    },
    getFeaturesById(id) {
      axios
        .get("/api/admin/features/" + id)
        .then((res) => (this.selectedFeature = res.data))
        .then(() => {
          this.showUpdateModal = true;
        });
    },
    updateCategorie(id) {
        let formdata = new FormData();
        formdata.append("id",id);
        formdata.append("file",this.thumbnailFile)
        formdata.append("name",this.selectedFeature.name)
      axios({
          url:"/api/admin/features",
          method:"put",
          data:formdata
        })
        .then(() => {
          this.getAllFeatures();
          this.showUpdateModal = false;
          this.thumbnailUrl ="",
          this.thumbnailFile =""
        });
    },
    deleteFeature(id) {
      axios.delete("/api/admin/features/" + id).then(() => {
        this.getAllFeatures();
      });
    },
    onFileChange2(e) {
      const file = e.target.files;
      this.thumbnailUrl = [];

      this.thumbnailUrl.push(URL.createObjectURL(file[0]));
      this.thumbnailFile = file[0];
    },
  },

  mounted() {
    this.getAllFeatures();
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

.image {
  object-fit: contain !important;
  height: 100px !important;
  width: 100px !important;
  display: block;
  position: absolute;



}

#file,
#file1 {
  display: none;
}
label {
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  align-items: center;
  height: 100px;
  background: #f0f2f5;
  border: solid #d1c6c6 0.1px;
  border-radius: 5px;
  flex-direction: column;
}
#preview {
  margin: 10px 0 10px 0;
}
#preview img {
  height: 100px;
  width: 100px;
}
</style>