<template>
  <div>
    <v-row class="mt-lg-5">
      <v-col cols="12" lg="8" offset-lg="2">
        <v-stepper v-model="e1" elevation="0" color="transparent">
          <v-stepper-header class="elevation-0 transparent">
            <template v-for="n in steps">
              <v-stepper-step :key="`${n}-step`" :complete="e1 > n" :step="n">
                Step {{ n }}
              </v-stepper-step>

              <v-divider v-if="n !== steps" :key="n"></v-divider>
            </template>
          </v-stepper-header>
        
          
        <v-container class="mb-5 mx-5 mt-5" v-if="errorsArray.length != 0">
          <v-row justify="center">
            <v-col
              cols="12"
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
          <v-stepper-items>
            <v-stepper-content
              v-for="n in steps"
              :key="`${n}-content`"
              :step="n"
            >
              <v-card
                v-if="n == 1"
                class="mb-12"
                
                
                elevation="0"
              >
                <FirstStep @verfiyInputForFirstStep="verfiyInputForFirstStep" />
              </v-card>
              <v-card
                v-if="n == 2"
                elevation="0"
                class="mb-12"
                
              >
                <SecondStep  @verfiyInputForSecondStep="verfiyInputForSecondStep"/>
              </v-card>
               <v-card
                v-if="n == 3"
                elevation="0"
                class="mb-12"
                
              >
                <ThridStep  @checkThirdStep="checkThirdStep"/>
              </v-card>

               <v-card
                v-if="n == 4"
                elevation="0"
                class="mb-12"
                
              >
                <FourthStep  @createPost="createPost"/>
              </v-card>


             <!-- <v-btn color="primary" @click="nextStep(n)"> suivant </v-btn>-->
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import FirstStep from '@/components/FirstStep.vue'
import SecondStep from '@/components/SecondStep.vue'
import ThridStep from '@/components/ThirdStep.vue'
import FourthStep from '@/components/FourthStep.vue'
import axios from 'axios'
export default {
components:{FirstStep,SecondStep,ThridStep,FourthStep},
  data() {
    return {
      e1: 1,
      steps: 4,
      errorsArray:[],
      postData:{},
      
      selectedFiles:[],
      thumbnailFile:{}
    };
  },
  

  watch: {
    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
  },

  methods: {
    /*verfication functions  */
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
    checkfroInt(field,fieldname){
      if( !Number.isInteger(parseInt(field)) ){
        this.errorsArray.push(fieldname +" doit etre un nombre entier");
      }

    },
    checkIfNumber(field,fieldname){
      if(!(typeof parseFloat(field) =='number')){
        this.errorsArray.push(fieldname+" doit etre un nombre");
      }
    },
    checkifSelected(field,fieldname){
      if(field == ""){
        this.errorsArray.push(fieldname +" doit etre selectionné");
      }
    },
    checkifdateIsValid(field,fieldname){
      let date = new Date();
      console.log(date.getTime());
      console.log("date we have is "+new Date(field).getTime());

      if(!(date.getTime() < new Date(field).getTime())){
        this.errorsArray.push(fieldname+" not valid ");
      }
    },
    checkforAlphanemericAndVirgule(field,fieldname){
      if(!(/^[a-zA-Z0-9,]/.test(field))){
        this.errorsArray.push(fieldname+" your code does't much the request patters a-zA-Z0-9,")
      }

    },
    /* end of verfication functions */
    verfiyInputForFirstStep(selectedType,title,surfacie,prix,selectedDuree,selectedCategorie,salleBain,valableUntil,description,etat,numberRoom){
      
      /*verify */
      this.errorsArray=[];
      if(selectedType !=1 && selectedType !=2){
        this.errorsArray.push("tu doit selectionné le type de l'announce");
      }
      this.required(title,"titre");
      this.alphaNumericCheck(title,"titre");
      this.checkIfNumber(surfacie,"surfacie");
      this.checkfroInt(prix,"prix");
      if(selectedType==2){
        this.checkifSelected(selectedDuree,"Duree");
      }
      this.checkifSelected(selectedCategorie,"categorie");
      this.checkfroInt(salleBain,"salle de bain");
     
      this.checkifdateIsValid(valableUntil,"valalbe jusqu'a");
      this.checkforSize(description,"description",20,300);
      this.checkifSelected(etat,"Etat");
      this.checkfroInt(numberRoom,"nombre de chambre ");


      console.log(this.postData.firstname);
      if(this.errorsArray != 0){
        return;
      }

      this.postData.type = selectedType;
      this.postData.title=title;
      this.postData.surfacie=surfacie;
      this.postData.prix=prix;
      this.postData.categorie = selectedCategorie;
      this.postData.salleBain = salleBain;
      this.postData.dateAvailabilty=valableUntil;
      
      this.postData.duree = selectedDuree;
      this.postData.description = description;
      this.postData.etat = etat;
      this.postData.numberRoom =numberRoom;
      console.log(this.postData);
      this.nextStep(1)

    },
    verfiyInputForSecondStep(SelectedRegion,selectedCity,address,markerLatLng){
      this.errorsArray =[];
        console.log(SelectedRegion,selectedCity,address,markerLatLng);
        this.checkifSelected(SelectedRegion,"region");
        this.checkifSelected(selectedCity,"ville");
        this.checkforAlphanemericAndVirgule(address,"address");
        this.checkforSize(address,"address ",20,100);
        if(markerLatLng[0]=="" || markerLatLng[1]==""){
          this.errorsArray.push("tu doit ajouter un marker")
        }
        console.log(selectedCity);
        if(this.errorsArray.length !=0){
          return
        }
        this.postData.region = SelectedRegion;
        this.postData.address=address;
        this.postData.ville=selectedCity;
        this.postData.lat = markerLatLng[0];
        this.postData.lng = markerLatLng[1];
        console.log(this.postData);
        this.nextStep(2);
    },
    checkThirdStep(selectedFiles,thumbnailFile){
      this.errorsArray=[];
      if(!(selectedFiles.length <16 && selectedFiles.length>=4)){
        this.errorsArray.push("tu doit selectionné au minimun 4 et au maximum 10")
      }
      console.log(selectedFiles);
      console.log(thumbnailFile);


      let ArrayOfAcceptedExstenstions = ["png","jpeg","jpg","webp"];
      this.validate(thumbnailFile,ArrayOfAcceptedExstenstions);
      selectedFiles.forEach(file=>{
        this.validate(file,ArrayOfAcceptedExstenstions);
      })
      if(this.errorsArray.length !=0){
        return;
      }
     
      this.selectedFiles = selectedFiles;
      this.thumbnailFile = thumbnailFile;

   
      this.nextStep(3);

     
    

    },
    validate(file,ArrayOfAcceptedExstenstions){

      
      let ext = file.name.split(".");
      if(!ArrayOfAcceptedExstenstions.includes(ext[1])){
        this.errorsArray.push("not supported format "+ ext);
      }
    },

    createPost(){
     
     
     
     let f = new FormData();
     
     for(let i=0;i<this.selectedFiles.length;i++){
       console.log("i am here")
       f.append("images",this.selectedFiles[i]);
     }
     f.append("thumbnail",this.thumbnailFile);
      f.append("post",JSON.stringify(this.postData));

      axios({
        url:"api/post/create",
        method:"post",
        headers:{'Content-type':'multipart/form-data'},
        data:f
      }).then((res)=>{
        this.$router.push("rent/"+res.data.id);
      })
    },

    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 1;
      } else {
        this.e1 = n + 1;
      }
    },
  },
};
</script>