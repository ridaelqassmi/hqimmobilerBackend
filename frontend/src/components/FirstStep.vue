<template>
  <div>
    <p class="font-weight-bold body-2 text-capitalize mb-0">type of post</p>
    <v-select
      :items="typeItems"
      v-model="selectedType"
      item-text="typeName"
      item-value="id"
      dense
      outlined
      required
    ></v-select>
    <v-row dense>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">title</p>
        <v-text-field outlined dense v-model="title"> </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">surfacie</p>
        <v-text-field type="number" v-model="surfacie" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">prix</p>
        <v-text-field type="number" v-model="prix" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6" v-if="rent">
        <p class="font-weight-bold body-2 text-capitalize mb-0">durée</p>
        <v-select
          :items="DureeItem"
          item-value="id"
          item-text="name"
          v-model="selectedDuree"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" :lg="rent ? 12 : 6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">categorié</p>
        <v-select
          :items="categoriesItem"
          item-text="categorieName"
          item-value="id"
          dense
          outlined
          required
          v-model="selectedCategorie"
        ></v-select>
      </v-col>

      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">
          nombre de salle de bain
        </p>
        <v-text-field type="number" v-model="salleBain" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">
          nombre de chambre
        </p>
        <v-text-field type="number" v-model="numberRoom" outlined dense>
        </v-text-field>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">
          etat de properity
        </p>
        <v-select
          :items="etatItems"
          v-model="etat"
          item-text="name"
          item-value="id"
          dense
          outlined
          required
        ></v-select>
      </v-col>
      <v-col cols="12" lg="6">
        <p class="font-weight-bold body-2 text-capitalize mb-0">
          date de availabilty
        </p>
        <input
          type="date"
          name=""
          v-model="valableUntil"
          id=""
          class="dateInput"
        />
      </v-col>
      <v-col cols="12" lg="12">
        <p class="font-weight-bold body-2 text-capitalize mb-0">description</p>
        <v-textarea
          outlined
          v-model="description"
          dense
          height="80px"
        ></v-textarea>
      </v-col>
    </v-row>

    <v-btn color="primary" @click="nextStep()"> suivant </v-btn>
  </div>
</template>
<script>
import axios from "axios";

export default {
  setup() {},
  data() {
    return {
      rent: false,
      typeItems: [
        { id: 1, typeName: "vente" },
        { id: 2, typeName: "location" },
      ],
      categoriesItem: [],
      selectedType: "",
      DureeItem: [
        { id: 1, name: "jour" },
        { id: 2, name: "mois" },
        { id: 3, name: "années" },
      ],
      salleBain: "",
      selectedCategorie: "",
      title: "",
      surfacie: "",
      selectedDuree: "",
      prix: "",
      valableUntil: "",
      description: "",
      numberRoom: "",
      etatItems: [],
      etat: "",
    };
  },
  methods: {
    nextStep() {
      this.$emit(
        "verfiyInputForFirstStep",
        this.selectedType,
        this.title,
        this.surfacie,
        this.prix,
        this.selectedDuree,
        this.selectedCategorie,
        this.salleBain,
        this.valableUntil,
        this.description,
        this.etat,
        this.numberRoom
      );
    },

    getAllCategories() {
      axios
        .get("/api/categories")
        .then((res) => (this.categoriesItem = res.data))
        .then(() => console.log(this.categoriesItem));
    },
    getAllDurations() {
      axios.get("/api/etat").then((res) => (this.etatItems = res.data));
    },
  },
  mounted() {
    this.getAllCategories();
    this.getAllDurations();
  },
  watch: {
    selectedType(val) {
      if (val == 1) {
        this.rent = false;
      } else if (val == 2) {
        this.rent = true;
      }
    },
  },
};
</script>
<style scoped>
.dateInput {
  width: 100%;
  border: solid 1px #b7a6a6;
  padding: 6px;
  border-radius: 3px;
  color: #b7a6a6;
  margin-bottom: 20px;
}
</style>