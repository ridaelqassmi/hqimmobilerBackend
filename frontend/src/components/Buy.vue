<template>
  <div>
    <v-expand-transition>
    <v-row>
      <v-col cols="12" class="my-0">
        <v-text-field
          label="Title"
          background-color="white"
          filled
          color="black"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="12">
        <p class="h6 py-0 white--text">Description</p>
        <hr />
        <v-textarea
          background-color="white"
          color="black"
          class="rounded-lg"
          value=""
        ></v-textarea>
      </v-col>
      <v-col cols="12">
        <v-select
          filled
          background-color="white"
          :items="items"
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
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="12" class="my-0">
        <v-text-field
          label="prix"
          background-color="white"
          filled
          color="black"
          type="number"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="12">
        <p class="h6 py-0 white--text">Adresse</p>
        <hr />
        <v-autocomplete
          :items="items"
          
          
          color="white"
          hide-no-data
          hide-selected
          item-text="Description"
          item-value="API"
          label="Cities"
         
          return-object
          background-color="white"
          height="30"
        ></v-autocomplete>
        <l-map style="height: 350px" class="map" app :zoom="zoom" :center="center" @click="AddMarker">
      <l-tile-layer :url="url" :attribution="attribution" ></l-tile-layer>
      <l-marker v-if="markerLatLng" :lat-lng="markerLatLng"  >
        <l-icon
            :icon-size="dynamicSize"
          :icon-anchor="dynamicAnchor"
          
          :icon-url="iconUrl"
        />
        
      </l-marker>
      <v-locatecontrol/>
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
          <v-container >
            <v-row >
            <v-col cols="1" class="ml-auto"><v-btn  fab @click="deleteImg(url,i)"><v-icon>mdi-delete</v-icon></v-btn></v-col>
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
        <v-btn block class="white--text purple">submit</v-btn>
      </v-col>
    </v-row>
    </v-expand-transition>
    
  </div>
</template>

<script>
import { LMap, LTileLayer,LMarker,LIcon } from "vue2-leaflet";
import Vue2LeafletLocatecontrol from 'vue2-leaflet-locatecontrol'
export default {
  components: {
    LMap,
    LTileLayer,
     'v-locatecontrol': Vue2LeafletLocatecontrol,
     LMarker,
     LIcon

  },
  data() {
    return {
      model: 0,
      items: ["Foo", "Bar", "Fizz", "Buzz"],
      file: "",
      urls: [],
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 8,
      center: [47.31322, -1.319482],
      markerLatLng:0,
      iconSize:32,
      iconUrl:require('../assets/location-pin.png'),
      selectedFiles:[]
    };
  },
   computed: {
    dynamicSize() {
      return [this.iconSize, this.iconSize * 1.15];
    },
    dynamicAnchor() {
      return [this.iconSize / 2, this.iconSize * 1.15];
    }},
  methods: {
    onFileChange(e) {
      const file = e.target.files;
      console.log(file);

      for (let i = 0; i < file.length; i++) {
        this.urls.push(URL.createObjectURL(file[i]));
        this.selectedFiles.push(file[i]);
        
      }console.log(this.selectedFiles);
    },
    AddMarker(e){
      
        this.markerLatLng = [e.latlng.lat,e.latlng.lng];
        console.log(this.markerLatLng)
    },
    deleteImg(url,i){
      this.urls = this.urls.filter(u=>u != url);
      console.log(i+"before and the new  file is ");
      this.selectedFiles.splice(i, 1);
      console.log(this.selectedFiles);
      
    }
  },
  
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
.map{
  z-index: 0;
}
</style>
