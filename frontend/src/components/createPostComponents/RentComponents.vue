<template>
  <div>
    <v-row>
      <v-col cols="12" class="my-0">
        <v-text-field
          label="Title"
          background-color="white"
          filled
          color="black"
          clearable
          v-model="title"
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="12">
        <p class="h6 py-0 white--text">Description</p>
        <hr />
        <v-textarea
          background-color="white"
          color="black"
          class="rounded-lg"
          v-model="description"
          value=""
        ></v-textarea>
      </v-col>
      <v-col cols="12">
        <v-select
          filled
          background-color="white"
          :items="categorie"
           item-text="categorieName"
          item-value="id"
          v-model="selectedCategorie"
          color="black"
          :menu-props="{ buttom: true, offsetY: true }"
          label="Categories"
        ></v-select>
      </v-col>
      <v-col cols="6" class="my-0">
        <v-text-field
          label="chambre"
          background-color="white"
          filled
          color="black"
          v-model="chambre"
          type="number"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="6" class="my-0">
        <v-text-field
          label="surfacie"
          background-color="white"
          filled
          color="black"
          type="number"
          v-model="area"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="6" class="my-0">
        <v-text-field
          label="prix"
          background-color="white"
          filled
          color="black"
          type="number"
          v-model="prix"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="6" class="my-0">
        <v-select
          filled
          background-color="white"
          :items="items"
          color="black"
          v-model="duree"
          :menu-props="{ buttom: true, offsetY: true }"
          label="durée "
        ></v-select>
      </v-col>
      <v-col cols="12">
        <p class="white--text">appareils électroménagers</p>
        <hr />
        <v-row dense>
          <v-col cols="6" v-for="appliance in appliances" :key="appliance">
            <v-checkbox
              v-model="ex4"
              :label="appliance"
              color="white"
              :value="appliance"
              hide-details
              class="white--text"
              multiple
              dark
            ></v-checkbox>
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="12">
        <p class="h6 py-0 white--text">Adresse</p>
        <hr />
        <v-autocomplete
          :items="CityItems"
          v-model="Selectedcity"
          color="white"
          hide-no-data
          hide-selected
          item-text="cityName"
          item-value="CityItems.id"
          label="Cities"
          return-object
          background-color="white"
          height="30"
        ></v-autocomplete>
        <l-map
          style="height: 350px"
          class="map"
          app
          :zoom="zoom"
          :center="center"
          @click="AddMarker"
        >
          <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
          <l-marker v-if="markerLatLng" :lat-lng="markerLatLng">
            <l-icon
              :icon-size="dynamicSize"
              :icon-anchor="dynamicAnchor"
              :icon-url="iconUrl"
            />
          </l-marker>
          <LGeoJson :geojson="myGeoJson"/>
          <v-locatecontrol />
        </l-map>
      </v-col>

      <v-col>
        <p class="h6 py-0 white--text">media</p>
        <hr />

        <v-carousel v-if="urls.length != 0">
          <v-carousel-item
            v-for="(url, i) in urls"
            v-bind:src="url"
            contain
            :key="i"
          >
            <v-container fill-height>
              <v-row justify="center">
                <v-col cols="1"
                  ><v-btn fab @click="deleteImg(url, i)"
                    ><v-icon>mdi-delete</v-icon></v-btn
                  ></v-col
                >
              </v-row>
            </v-container>
          </v-carousel-item>
        </v-carousel>

        <label for="file"
          ><v-icon>mdi-camera</v-icon>Select your pictures</label
        >

        <input
          type="file"
          id="file"
          multiple
          ref="file"
          @change="onFileChange"
        />
      </v-col>
      <v-col cols="12">
        <v-btn block class="white--text purple" @click="submitHandler()"
          >submit</v-btn
        >
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import { LMap, LTileLayer, LMarker, LIcon,LGeoJson } from "vue2-leaflet";
import Vue2LeafletLocatecontrol from "vue2-leaflet-locatecontrol";
import myGeoJson from "../../citiesGeojson.js";
export default {
  components: {
    LMap,
    LTileLayer,
    LGeoJson,
    "v-locatecontrol": Vue2LeafletLocatecontrol,
    LMarker,
    LIcon,
  },
  data() {
    return {
      myGeoJson : myGeoJson,
      model: 0,
      items: ["Foo", "Bar", "Fizz", "Buzz"],
      file: "",
      urls: [],
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 8,
      center: [47.31322, -1.319482],
      markerLatLng: 0,
      iconSize: 32,
      iconUrl: require("../../assets/location-pin.png"),
      selectedFiles: [],
      appliances: ["freezer", "air conditioning", "footoy", "etc"],
      Selectedcity: "",
      title: "",
      description: "",
      categorie: "",
      area: 0,
      chambre: 0,
      prix: 0,
      duree: "",
      adresse: "",
      ex4: "",
      selectedCategorie:"",
      CityItems:{},
    };
  },
  computed: {
    dynamicSize() {
      return [this.iconSize, this.iconSize * 1.15];
    },
    dynamicAnchor() {
      return [this.iconSize / 2, this.iconSize * 1.15];
    },
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files;
      console.log(file);

      for (let i = 0; i < file.length; i++) {
        this.urls.push(URL.createObjectURL(file[i]));
        this.selectedFiles.push(file[i]);
      }
      console.log(this.selectedFiles);
    },
    AddMarker(e) {
      this.markerLatLng = [e.latlng.lat, e.latlng.lng];
      console.log(this.markerLatLng);
    },
    deleteImg(url, i) {
      this.urls = this.urls.filter((u) => u != url);
      console.log(i + "before and the new  file is ");
      this.selectedFiles.splice(i, 1);
      console.log(this.selectedFiles);
    },
    submitHandler() {
      /*construct the data  */
      /*selected file is the object which contain the photo selected  */
      let formdata = new FormData();
      
      formdata.append("idCategorie",this.selectedCategorie);
      formdata.append("idCity",this.Selectedcity.id);
   
       for(let i=0;i<this.selectedFiles.length;i++){
         formdata.append("files",this.selectedFiles[i]);
       }
      let rentingPost = {duree:"two weeks",price:1500,numberRoom:this.chambre,description:this.description,title:this.title,location:"no where",hasAppliance:false,areaSize:180};
      formdata.append('rentingPost',JSON.stringify(rentingPost));
      axios({
        url:"api/rent",
        method:"post",
        headers:{'Content-type':'multipart/form-data'},
        data:formdata
      })
    },
  },
  created(){
    /*get data from database for the user to choose from */
    /*getting the categories*/
    axios.get('/api/categories').then(res=> this.categorie = res.data); 
    /*getting the cities*/
    axios.get("api/cities").then(res=>this.CityItems = res.data); 
  }
};
</script>

<style scoped>
#file {
  display: none;
}
label {
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  align-items: center;
  height: 100px;
  background: white;
  flex-direction: column;
}
#preview {
  margin: 10px 0 10px 0;
}
#preview img {
  height: 100px;
  width: 100px;
}
.map {
  z-index: 0;
}
</style>
