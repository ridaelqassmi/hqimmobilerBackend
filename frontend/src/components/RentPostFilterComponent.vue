<template>
  <v-row justify="center" dense>
    <v-col cols="12" lg="9" md="9" sm="9" xs="9">
      <div>
        <v-row class="mx-1">
          <v-col cols="12" lg="4" sm="4" xs="12" md="4">
            <b-field expanded>
              <b-input
                placeholder="Search by title..."
                type="search"
                icon="magnify"
                v-model="title"
                icon-clickable
                @icon-click="searchIconClick"
                @input="searchByTitle()"
              >
              </b-input>
            </b-field>
          </v-col>

          <v-col cols="12" lg="3" sm="3" xs="12" md="3">
            <b-field>
              <b-autocomplete
                v-model="name"
                :data="filteredDataArray"
                placeholder="start typing your city.."
                icon="magnify"
                clearable
                @select="(option) => (selected = option)"
              >
                <template #empty>No results found</template>
              </b-autocomplete>
            </b-field>
          </v-col>
          <v-col cols="12" lg="3" md="3" sm="3" xs="12">
            <b-field>
              <b-select placeholder="order by" expanded>
                <option
                  v-for="(option, i) in orderData"
                  :value="option"
                  :key="i"
                >
                  {{ option }}
                </option>
              </b-select>
            </b-field>
          </v-col>
          <v-col cols="12" lg="2" md="2" sm="2" xs="12">
            <p class="control">
              <b-button
                @click="dialog = true"
                label="filters"
                type="is-primary"
              />
            </p>
          </v-col>
        </v-row>
      </div>
    </v-col>

    <!-------------------------->
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title class="d-flex justify-center">
          <h1
            class="
              font-weight-bold
              grey--text
              px-8
              text-capitalize
              rounded-pill
            "
          >
            filters
          </h1>
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col>
              <b-field label="Regions">
                <b-select expanded placeholder="Select a subject">
                  <option value="1">Option 1</option>
                  <option value="2">Option 2</option>
                </b-select>
              </b-field>
            </v-col>
            <v-col>
              <b-field label="Province">
                <b-select expanded placeholder="Select a subject">
                  <option value="1">Option 1</option>
                  <option value="2">Option 2</option>
                </b-select>
              </b-field>
            </v-col>
            <v-col cols="12">
              <b-field label="type de bien">
                <b-select expanded placeholder="Select a subject">
                  <option value="1">Option 1</option>
                  <option value="2">Option 2</option>
                </b-select>
              </b-field>
            </v-col>
            <v-col cols="12"
              ><b-field label="Budget">
                <v-container
                  ><b-slider
                    indicator
                    v-model="numbers"
                    :min="0"
                    :max="5000"
                    :step="100"
                    ticks
                  >
                  </b-slider
                ></v-container>
              </b-field>
            </v-col>
            <v-col cols="12"
              ><b-field label="surface">
                <v-container
                  ><b-slider
                    indicator
                    v-model="numbers"
                    :min="0"
                    :max="5000"
                    :step="100"
                    ticks
                  >
                  </b-slider
                ></v-container>
              </b-field>
            </v-col>
            <v-col cols="12"
              ><b-field label="chambre">
                <v-container
                  ><b-slider
                    indicator
                    v-model="numbers"
                    :min="0"
                    :max="5000"
                    :step="100"
                    ticks
                  >
                  </b-slider
                ></v-container>
              </b-field>
            </v-col>
            <v-col>
              <b-field label="caractéristique">
                <v-container class="is-fullhd" fluid>
                  <b-checkbox
                    class="px-5 py-5"
                    v-for="i in 11"
                    :key="i"
                    v-model="checkboxGroup"
                    native-value="Silver"
                  >
                    atay
                  </b-checkbox>
                </v-container>
              </b-field>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Close
          </v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      rida: 0,
      numbers: [0, 5000],
      checkboxGroup: ["fruit", "way"],
      dialog: false,
      orderData: ["PRICE", "DATE CROISSANCE", "Date decroissance"],
      title: "",
    };
  },
  methods: {
    searchByTitle() {
      axios
        .get("api/rent/search/by", {
          params: {
            title: this.title,
          },
        })
        .then((res) => console.log(res.data));
    },
  },
};
</script>