<template>
  <v-row justify="center" absolute>
    <v-dialog v-model="dialog" max-height="800px" max-width="500px">
      <v-card class="overflow-hidden">
        <div class="columns is-mobile pt-4 pa-4">
          <div class="column">
            <b-button
              :class="{
                'is-primary': !RegistreForm,
                ' outlined': RegistreForm,
              }"
              expanded
              @click="ToggleRegistreForm(false)"
              >Log in</b-button
            >
          </div>
          <div class="buttons"></div>
          <div class="column">
            <div class="buttons">
              <b-button
                :class="{ 'is-primary': RegistreForm, outlined: !RegistreForm }"
                expanded
                @click="ToggleRegistreForm(true)"
                >register in</b-button
              >
            </div>
          </div>
        </div>

        <v-card-text>
          <v-container>
            <v-row v-if="RegistreForm">
              <v-col cols="12" sm="12" md="6">
                <b-field>
                  <b-input
                    placeholder="first Name"
                    type="text"
                    required
                    key="loginfeirfpa"
                    validation-message="Only letters are allowed"
                    pattern="[a-zA-Z]*"
                  >
                  </b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="6">
                <b-field>
                  <b-input
                    placeholder="Last Name"
                    key="login-pa"
                    type="text"
                    required
                    validation-message="Only letters are allowed"
                    pattern="[a-zA-Z]*"
                  >
                  </b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <b-field key="iam">
                  <b-input placeholder="Email" type="email"></b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <b-field>
                  <b-input
                    placeholder="Phone number"
                    type="number"
                    min="10"
                    max="20"
                  >
                  </b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <b-field>
                  <b-input
                    type="password"
                    placeholder="Password "
                    password-reveal
                  >
                  </b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <b-field>
                  <b-input
                    type="password"
                    placeholder="confirme password  "
                    password-reveal
                  >
                  </b-input>
                </b-field>
              </v-col>
            </v-row>
            <v-row v-if="!RegistreForm">
              <v-col cols="12" sm="12" md="12">
                <b-field key="login-emailjeifjeij">
                  <b-input placeholder="Email" v-model="RegistreEmail" type="email"></b-input>
                </b-field>
              </v-col>
              <v-col cols="12" sm="12" md="12">
                <b-field key="login-password">
                  <b-input
                  v-model="RegistrePassword"
                    type="password"
                    placeholder="Password "
                    password-reveal
                  >
                  </b-input>
                </b-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <div class="columns">
          <div class="column">
            <div class="buttons mx-6 column">
              <b-button type="is-link" expanded @click="RegistreIn()"
                >register in</b-button
              >
            </div>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from 'axios';
import {mapMutations} from 'vuex';
export default {
  
  data() {
    return {
      dialog: true,
      RegistreForm: false,
      RegistreEmail:"",
      RegistrePassword:""
    };
  }
  ,
  methods: {
    ...mapMutations(["toggleAuthentified"]),

     parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
},
    

    toggleShowModel() {
      this.$emit("toggle-showModal");
    },
    ToggleRegistreForm(value) {
      this.RegistreForm = value;
    },
    RegistreIn(){
      axios.post("/login",{
        email:this.RegistreEmail,
        password:this.RegistrePassword
      }).then(res => {
        localStorage.setItem("Autorization","Bearer " +res.data);
        
        
      }).then(()=>{
        this.toggleAuthentified()
        this.toggleShowModel()
        console.log(this.parseJwt(localStorage.getItem("Autorization")));
        
        
      });
    }
  },
};
</script>

<style>
.Costume-color {
  background: #4761f2 !important;
}
</style>