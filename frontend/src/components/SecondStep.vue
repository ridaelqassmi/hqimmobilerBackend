<template>
  <div>
    <v-row dense>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">province</p>
        <v-select
          :items="items"
          label="ville "
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">ville</p>
        <v-select
          :items="items"
          label="ville "
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="12">
        <p class="font-weight-bold body-2 text-capitalize mb-0">address</p>
        <v-text-field outlined dense> </v-text-field>
      </v-col>
      <v-col>
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

          <v-locatecontrol />
        </l-map>
      </v-col>
    </v-row>
  </div>
</template>

<script>
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
      zoom: 8,
      center: [47.31322, -1.319482],
      markerLatLng: 0,
      iconSize: 32,
      iconUrl: require("@/assets/location-pin.png"),
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
      AddMarker(e) {
      this.markerLatLng = [e.latlng.lat, e.latlng.lng];
      console.log(this.markerLatLng);
    },
  }
};
</script>