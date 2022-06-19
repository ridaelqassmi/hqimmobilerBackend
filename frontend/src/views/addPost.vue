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
                <FirstStep />
              </v-card>
              <v-card
                v-if="n == 2"
                elevation="0"
                class="mb-12"
                
              >
                <SecondStep />
              </v-card>

              <v-btn color="primary" @click="nextStep(n)"> suivant </v-btn>
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
export default {
components:{FirstStep,SecondStep},
  data() {
    return {
      e1: 1,
      steps: 3,
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