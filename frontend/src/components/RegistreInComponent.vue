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
        <v-container class="mb-5" v-if="errorsArray.length != 0">
          <v-row justify="center">
            <v-col
              cols="11"
              v-if="errorsArray.length != 0"
              class="red lighten-1 errorStyle"
            >
              <p
                class="pa-0 font-weight-bold ma-0 text-body-2 white--text"
                v-for="err in errorsArray"
                :key="err"
              >
                {{ err }}
              </p>
            </v-col>
          </v-row>
        </v-container>

        <v-card-text>
          <v-row v-if="RegistreForm">
            <v-col cols="12" sm="12" md="6">
              <b-field>
                <b-input
                  name="FirstName"
                  placeholder="first Name"
                  type="text"
                  v-model="RFirstName"
                  key="loginfeirfpa"
                >
                </b-input>
              </b-field>
            </v-col>
            <v-col cols="12" sm="12" md="6">
              <b-field>
                <b-input
                  v-validate="'required|alpha'"
                  name="LastName"
                  placeholder="Last Name"
                  key="login-pa"
                  type="text"
                  v-model="RLastName"
                  required
                  validation-message="Only letters are allowed"
                  pattern="[a-zA-Z]*"
                >
                </b-input>
              </b-field>
            </v-col>
            <v-col cols="12" sm="12" md="12">
              <b-field key="iam">
                <b-input
                  placeholder="Email"
                  name="Email"
                  v-model="REmail"
                  type="email"
                ></b-input>
              </b-field>
            </v-col>
            <v-col cols="12" sm="12" md="12">
              <b-field>
                <b-input
                  v-model="RPhoneNumber"
                  placeholder="Phone number"
                  type="number"
                >
                </b-input>
              </b-field>
            </v-col>
            <v-col cols="12" sm="12" md="12">
              <b-field>
                <b-input
                  type="password"
                  placeholder="Password"
                  password-reveal
                  v-model="RPassword"
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
                  v-model="RCPassword"
                >
                </b-input>
              </b-field>
            </v-col>
          </v-row>
          <v-row
            ><v-col v-if="error" cols="12" :class="color">
              <p class="text-center white--text font-weight-bold mb-0">
                {{ error }}
              </p>
            </v-col></v-row
          >
          <v-row v-if="!RegistreForm">
            <v-col cols="12" sm="12" md="12">
              <b-field>
                
                 <b-input
                  placeholder="Email"
                  name="Email"
                  v-model="loginEmail"
                  
                ></b-input>
              </b-field>
            </v-col>
            <v-col cols="12" sm="12" md="12">
              <b-field key="login-password">
                <b-input
                  v-model="loginPassword"
                  type="password"
                  placeholder="Password "
                  password-reveal
                >
                </b-input>
              </b-field>
            </v-col>
          </v-row>
        </v-card-text>

        <div class="columns">
          <div class="column" v-if="!RegistreForm">
            <div class="buttons mx-6 column">
              <b-button type="is-link" expanded @click="login()"
                >log in</b-button
              >
            </div>
          </div>
          <div class="column" v-if="RegistreForm">
            <div class="buttons mx-6 column">
              <b-button type="is-link" expanded @click="Registre()"
                >Registre in
              </b-button>
            </div>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      dialog: true,
      RegistreForm: true,
      loginEmail: "",
      loginPassword: "",
      error: "",
      color: "",
      RFirstName: "",
      RLastName: "",
      REmail: "",
      RPhoneNumber: "",
      RPassword: "",
      RCPassword: "",
      seccus: "green lighten-2",
      errorColor: "red lighten-2",
      errorsArray: [],
    };
  },
 

  methods: {
    ...mapMutations(["toggleAuthentified", "setAuthentifiedUserDetails"]),

    toggleShowModel() {
      this.$emit("toggle-showModal");
    },
    ToggleRegistreForm(value) {
      this.RegistreForm = value;
    },
    Registre() {
      this.errorsArray = [];
      this.required(this.RFirstName, "first Name");
      this.checkforSize(this.RFirstName, "first Name", 3, 15);
      this.checkforAlphabits(this.RFirstName, "firstName");
      this.required(this.RLastName, "last name");
      this.checkforSize(this.RLastName, "last name", 3, 15);
      this.checkforAlphabits(this.RLastName, "last name");
      this.emailValidation(this.REmail);
      this.phoneNumberValidation(this.RPhoneNumber);
      this.verifyPassword(this.RPassword);
      if (this.errorsArray.length != 0) {
        return;
      }
      axios
        .post("/register", {
          email: this.REmail,
          firstName: this.RFirstName,
          lastName: this.RLastName,
          password: this.RPassword,
          phoneNumber :this.RPhoneNumber,
        })
        .then(() => {
          this.message("you account created seccussfuly ", this.seccus);
          setTimeout(() => {
            this.RegistreForm = false;
          }, 500);
        })
        .catch((e) => {
          if (e.response.status != 200) {
            this.message("failed to create account", this.errorColor);
          }
        });
    },

    required(field, fieldname) {
      if (field == "") {
        this.errorsArray.push(fieldname + " input is required ");
      }
    },
    checkforSize(field, fieldname, min, max) {
      if (field.length <= min || field.length >= max) {
        this.errorsArray.push(
          fieldname + " min size is " + min + " and max size is " + max
        );
      }
    },
    checkforAlphabits(field, fieldname) {
      if (!/^[a-zA-Z]+$/.test(field)) {
        this.errorsArray.push(fieldname + " should contain only caracters");
      }
    },
    emailValidation(field) {
      if (!/\S+@\S+\.\S+/.test(field)) {
        this.errorsArray.push("email Not valid ex:yourname@gmail.com");
      }
    },
    phoneNumberValidation(field) {
      if (!/(06|07)[0-9]{8}/.test(field)) {
        this.errorsArray.push("number phone  Not valid");
      }
    },
    verifyPassword(field) {
      this.checkforSize(field, "password", 8, 20);
      this.alphaNumericCheck(field, "password");
      if (this.RPassword != this.RCPassword) {
        this.errorsArray.push("password and confirmed password dont match");
      }
    },
    alphaNumericCheck(field, fieldname) {
      if (!/[a-zA-Z0-9]*/.test()) {
        this.errorsArray.push(
          fieldname + ": only numbers and alphabits are allowed"
        );
      }
    },
    login() {
      this.errorsArray = [];

      this.required(this.loginEmail, "email");
      this.emailValidation(this.loginEmail);
      this.alphaNumericCheck(this.password, "password");
      
      if (this.errorsArray.length  != 0) {
        return;
      }
      axios
        .post("/login", {
          email: this.loginEmail,
          password: this.loginPassword,
        })
        .then((res) => {
          this.message("connected in seccussfuly ", "green lighten-2");
          localStorage.setItem("Autorization", "Bearer " + res.data.token);
          this.setAuthentifiedUserDetails(res.data.user);
          this.toggleAuthentified();

          setTimeout(() => {
            this.toggleShowModel();
          }, 3000);
        })

        .catch((e) => {
          if (e.response.status == 403) {
            this.message(
              "your password or email is wrong try again",
              "red lighten-2"
            );
          }
        });
    },
    message(msg, color) {
      this.error = msg;
      this.color = color;
      setTimeout(() => {
        this.error = "";
      }, 5000);
    },
  },
};
</script>

<style>
.Costume-color {
  background: #4761f2 !important;
}
.errorStyle {
  border-radius: 5px;
}
</style>