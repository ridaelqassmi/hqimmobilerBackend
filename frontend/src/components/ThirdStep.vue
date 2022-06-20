<template>
  <div>
    <v-row>
        <v-col cols="12">
            <v-img v-for="url in thumbnailUrl" :key="url" :src="url" width="300px"></v-img>
            <p class="font-weight-bold body-2 text-capitalize mt-5 mb-3">selectionné un thumbnail un image representative de votre propriety </p>
            
            <label for="file1"
          ><v-icon>mdi-camera</v-icon>Selectionné un seul image</label
        >

        <input
          type="file"
          id="file1"
          
          ref="file1"
          @change="onFileChange2"
        />
        </v-col>
      <v-col>
        

        <v-carousel v-if="urls.length != 0">
          <v-carousel-item
            v-for="(url, i) in urls"
            v-bind:src="url"
            contain
            :key="i"
          >
            <v-container fill-height>
              <v-row justify="center">
                <v-col cols="1"
                  ><v-btn fab @click="deleteImg(url, i)"
                    ><v-icon>mdi-delete</v-icon></v-btn
                  ></v-col
                >
              </v-row>
            </v-container>
          </v-carousel-item>
        </v-carousel>
        <p class="font-weight-bold body-2 text-capitalize mt-5 mb-3">select multiple images de votre proprietie </p>
        <label for="file"
          ><v-icon>mdi-image-plus</v-icon>Select your pictures</label
        >

        <input
          type="file"
          id="file"
          multiple
          ref="file"
          @change="onFileChange"
        />
      </v-col>
    </v-row>
    <v-btn color="primary"   class="mt-5" @click="nextStep(3)"> suivant </v-btn>
  </div>
</template>

<script>
export default {
  setup() {},
  data() {
    return {
      urls: [],
      selectedFiles: [],
      thumbnailUrl:[],
      thumbnailFile:{}
    };
  },
  methods:{
     nextStep(n){
        this.$emit("nextStep",n);
      },
      onFileChange(e) {
      const file = e.target.files;
      console.log(file);

      for (let i = 0; i < file.length; i++) {
        this.urls.push(URL.createObjectURL(file[i]));
        this.selectedFiles.push(file[i]);
      }
      console.log(this.selectedFiles);
    },
     deleteImg(url, i) {
      this.urls = this.urls.filter((u) => u != url);
      console.log(i + "before and the new  file is ");
      this.selectedFiles.splice(i, 1);
      console.log(this.selectedFiles);
    },
    onFileChange2(e){
        const file = e.target.files;
        this.thumbnailUrl = [];
        console.log(file[0]);
        
        this.thumbnailUrl.push(URL.createObjectURL(file[0]));
        this.thumbnailFile = file[0];
        console.log(this.thumbnailUrl);
    }
  }
};
</script>

<style scoped>
#file,#file1 {
  display: none;
}
label {
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  align-items: center;
  height: 100px;
  background: #f0f2f5;
  border: solid #d1c6c6 0.1px;
  border-radius: 5px;
  flex-direction: column;
}
#preview {
  margin: 10px 0 10px 0;
}
#preview img {
  height: 100px;
  width: 100px;
}
</style>