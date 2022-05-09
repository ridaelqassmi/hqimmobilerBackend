<template>
  <v-row justify="center" absolute>
    <v-dialog v-model="dialog"  max-height="800px" max-width="500px">
      <v-card>
        <v-card-title class="justify-center pa-8" >
          <span class="text-h6 white--text rounded-sm  indigo darken-2  elevation-4 Costume-color px-8 py-1 "> Log In</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row >
              
             
              <v-col cols="12" sm="12" md="12" class="py-0">
                <v-text-field class="mb-0"
                  label="Email"
                  placeholder="Placeholder"
                  outlined
                ></v-text-field>
              
             
              
              </v-col> <v-col cols="12" sm="12" md="12" class="py-0">
                <v-text-field class="mb-0"
                  label="password"
                  
                  outlined
                  type="password"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="toggleShowModel()">
            Close
          </v-btn>
          <v-btn color="blue darken-1" text @click="toggleShowModel()">
              log In
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      dialog: true,
    };
  },
  methods: {
    toggleShowModel() {
      this.$emit("toggle-showModal");
    },
  },
};
</script>

<style>
.Costume-color{
    background: #4761f2 !important;

}
</style>