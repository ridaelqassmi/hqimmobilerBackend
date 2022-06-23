<template>
     <v-col class="mx-auto">
      <h1 class="text-h5 font-weight-bold text-capitalize">edit profile</h1>
      <v-divider></v-divider>
      <v-container>
        <v-row dense>
          <v-col class="py-1" cols="6">
            <v-text-field
              label="Prenom"
              placeholder="Placeholder"
              outlined
              v-model="firstName"
              value="rida"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field
              label="Nom"
              placeholder="Nom"
              outlined
              v-model="lastName"
              value="Elqassmi"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              label="Number"
              placeholder="le numero de telephone"
              outlined
              type="number"
              v-model="phoneNumber"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
              <v-text-field
              label="old password"
              placeholder="le numero de telephone"
              outlined
              type="password"
              v-model="phoneNumber"
            ></v-text-field>
            <v-text-field
              label="new password"
              placeholder="le numero de telephone"
              outlined
              type="password"
              v-model="phoneNumber"
            ></v-text-field>
          </v-col>
          <v-col offset-lg="10" cols="2"
>
        <v-btn color="blue darken-4 white--text" width="100%">submit</v-btn>

          </v-col>
        </v-row>
      </v-container>
    </v-col>
</template>

<script>
export default {
    setup() {
        
    },
    data(){
      return{
        phoneNumber:""
      }
    }
}
</script>