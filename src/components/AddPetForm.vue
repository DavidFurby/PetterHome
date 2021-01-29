<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <InputWithError
          :error="$v.animalForm.animal.$dirty && !$v.animalForm.animal.required"
          msg="Must select an animal"
        >
          <nb-item stackedLabel
            ><nb-input
              placeholder="Animal"
              v-model="animalForm.animal"
              :on-blur="() => $v.animalForm.animal.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input placeholder="Breed" v-model="animalForm.breed"
        /></nb-item>
      </nb-form>

      <nb-form>
        <InputWithError
          :error="$v.petForm.petName.$dirty && !$v.petForm.petName.required"
          msg="A name must be given for the pet"
        >
          <nb-item stackedLabel
            ><nb-input
              placeholder="Name"
              v-model="petForm.petName"
              :on-blur="() => $v.petForm.petName.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input placeholder="Age" v-model="petForm.petAge"
        /></nb-item>

        <InputWithError
          :error="$v.petForm.gender.$dirty && !$v.petForm.gender.required"
          msg="A gender must be given for the pet"
        >
          <nb-item stackedLabel
            ><nb-input placeholder="Gender" v-model="petForm.gender"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input v-model="petForm.height" placeholder="Height"
        /></nb-item>

        <nb-item stackedLabel>
          <nb-input v-model="petForm.weight" placeholder="Weight" />
        </nb-item>
        <!-- <nb-list-item>
          <nb-text>Medication ?</nb-text>
          <nb-body> </nb-body>
          <nb-checkbox
            :on-press="setMedication"
            :checked="petForm.medication"
          />
        </nb-list-item>
        <Medication
          v-if="ifMedication"
          :medicationName="$v.petForm.medicationName"
          :dosage="$v.petForm.dosage"
        />
        <DogForm v-if="ifDog" />
        <nb-button block :on-press="addPet">
          <nb-text>Add Pet</nb-text> 
        </nb-button>-->
      </nb-form>
      <nb-form>
        <nb-item stackedLabel
          ><nb-input placeholder="Need" v-model="needForm.type"
        /></nb-item>

        <nb-item stackedLabel
          ><nb-input placeholder="Notified" v-model="needForm.notified"
        /></nb-item>
      </nb-form>
      <nb-form>
        <nb-item stackedLabel
          ><nb-input
            placeholder="Set Scheduled time for need"
            v-model="scheduleForm.time"
        /></nb-item>
      </nb-form>
      <nb-button block :on-press="addPet">
        <nb-text>Add Pet</nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>
<script>
import React from "react";
import { Picker, Icon } from "native-base";
import DogForm from "./DogForm";
import Medication from "./Medication";
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item, DogForm, Medication },
  data() {
    return {
      breedSelection: "",
      petForm: {
        petName: "",
        petAge: "",
        gender: "",
        weight: "",
        height: "",
      },
      animalForm: {
        animal: "",
        breed: "",
      },
      needForm: {
        type: "",
        notified: "",
      },
      scheduleForm: {
        time: "",
        assignedTo: "user",
      },
    };
  },
  props: {
    user: {
      type: Object,
    },
  },
  validations: {
    petForm: {
      petName: {
        required,
      },
      gender: {
        required,
      },
    },
    animalForm: {
      animal: {
        required,
      },
    },
  },
  methods: {
    onAnimalChange(animalValue) {
      this.animalForm.animal = animalValue;
    },
    onBreedChange(breedValue) {
      this.breedSelection = breedValue;
    },
    getIosIcon() {
      return <Icon name="ios-arrow-down-outline" />;
    },
    setMedication() {
      this.petForm.medication = !this.petForm.medication;
    },
    addPet() {
      this.$v.petForm.$touch();
      this.$v.animalForm.$touch();
      let petForm = this.petForm;
      let animalForm = this.animalForm;
      let needForm = this.needForm;
      let scheduleForm = this.scheduleForm;
      let animal = {};
      let need = {};
      let schedule = {};
      animal.animal = animalForm.animal;
      animal.breed = animalForm.breed;
      need.type = needForm.type;
      need.notified = needForm.notified;
      schedule.time = scheduleForm.time;
      schedule.assignedTo = scheduleForm.assignedTo;
      need.schedule = schedule;

      petForm.animal = animal;
      petForm.need = need;
      let userId = this.user;
      let params = { petForm, userId };
      if (!this.$v.petForm.$invalid) {
        this.$store
          .dispatch("pets/addPetToUser", params)
          .then(() => this.navigateToMain())
          .catch(() => {
            Toast.show({
              text: "Something went wrong",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      } else {
        return alert("something went wrong");
      }
    },
    navigateToMain() {
      this.navigation.navigate("Main", {
        message: "Succesfully added new pet!",
      });
    },
  },

  computed: {
    animals() {
      return this.$store.state.animals.items;
    },

    ifMedication() {
      return this.petForm.medication;
    },
    ifDog() {
      if (this.animalSelection === 1) {
        return true;
      }
    },
  },
  created() {
    this.$store
      .dispatch("animals/fetchAnimals")
      .then((animals) => alert(JSON.stringify(animals)));
  },
};
</script>