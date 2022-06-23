<template>
  <div>
    <v-row dense>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">Region</p>
        <v-select
         
           :items="RegionItems"
          item-text="name"
          item-value="id"
          v-model="SelectedRegion"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">ville</p>
        <v-select
          :items="CityItems"
          item-text="cityName"
          item-value="id"
          v-model="SelectedCity"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="12">
        <p class="font-weight-bold body-2 text-capitalize mb-0">address</p>
        <v-text-field outlined v-model="address" dense> </v-text-field>
      </v-col>
      <v-col>
        <l-map 
          style="height: 350px"
          class="map"
          app
          :zoom="zoom"
          :center="center"
          @click="AddMarker"
          
          v-if="changed"
        >
          <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
          <l-marker v-if="markerLatLng" :lat-lng="markerLatLng">
            <l-icon
              :icon-size="dynamicSize"
              :icon-anchor="dynamicAnchor"
              :icon-url="iconUrl"
            />
          </l-marker>

          <v-locatecontrol />
        </l-map>
      </v-col>
    </v-row>
    <v-btn color="primary" class="mt-5" @click="nextStep()"> suivant </v-btn>
  </div>
</template>

<script>
import axios from 'axios';
import { LMap, LTileLayer, LMarker, LIcon } from "vue2-leaflet";
import Vue2LeafletLocatecontrol from "vue2-leaflet-locatecontrol";

export default {
  setup() {},
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LIcon,
    "v-locatecontrol": Vue2LeafletLocatecontrol,
  },
  data() {
    return {
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom:12,
      center: [33.59920, -7.62000],
      markerLatLng: 0,
      iconSize: 32,
      iconUrl: require("../assets/location-pin.png"),
      CityItems:[],
      RegionItems:[],
      SelectedRegion:"",
      SelectedCity:"",
      changed:true,
      address:""
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
  methods:{
    nextStep(){
      
        this.$emit("verfiyInputForSecondStep",this.SelectedRegion,this.SelectedCity,this.address,this.markerLatLng);
      },
      AddMarker(e) {
      this.markerLatLng = [e.latlng.lat, e.latlng.lng];
      
    },
    /*getAllCitites(){
      axios.get('/api/cities').then((res) => this.CityItems = res.data).then(()=> console.log(this.CityItems));
    },*/
    getAllRegions(){
      axios.get("/api/regions").then((res)=>this.RegionItems = res.data);
    },
    getAllCititesByRegion(id){
      axios.get("/api/cities/region/"+id).then(res=> this.CityItems = res.data);
    },
    centerUpdated (center) {
      this.center = center;
    },
  },
  mounted(){
    //this.getAllCitites();
    this.getAllRegions();
  },
  watch:{
    SelectedRegion(val){
      console.log(val);
      this.getAllCititesByRegion(val);
    },
    SelectedCity(val){
      this.changed=false;
      let selected = this.CityItems.filter(item => item.id == val);
      console.log(selected[0].cityName);
      this.center[1]=selected[0].lng;
      this.center[0]=selected[0].lat;
      this.centerUpdated(this.center);
      this.zoom=8;
      
      
      setTimeout(() => {
        this.changed =true;
      }, 1000);
      
    }
  }
};
</script>


<style scoped>
.map {
  z-index: 0;
}
</style>