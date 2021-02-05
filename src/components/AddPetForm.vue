<template>
  <nb-container>
    <scroll-view>
      <nb-form>
          <nb-picker
            note
            mode="dropdown"
            :style="{ width: 120 }"
            :selectedValue="selectedAnimal"
            :onValueChange="onAnimalChange"
          >
            <item
              v-for="(animal, animalIndex) in animals"
              :key="animalIndex"
              :label="animal.animal"
              :value="animal.animal"
            />
          </nb-picker>

              <nb-picker
            note
            mode="dropdown"
            :style="{ width: 120 }"
            :selectedValue="selectedBreed"
            :onValueChange="onBreedChange"
          >
            <item
              v-for="(breed, breedIndex) in animals[currentAnimal].breeds"
              :key="breedIndex"
              :label="breed"
              :value="breed"
            />
          </nb-picker> 
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

    <nb-picker
          note
          mode="dropdown"
          :style="{ width: 120 }"
          :selectedValue="selectedGender"
          :onValueChange="onGenderChange"
        >
          <item label="Male" value="MALE" />
          <item label="Female" value="FEMALE" />
        </nb-picker>

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
      currentAnimal: 0,
      petForm: {
        petName: "",
        petAge: "",
        gender: "MALE",
        weight: "",
        height: "",
      },
      animalForm: {
        animal: this.animals[0].animal,
        breed: this.animals[0].breeds[0],
      },
    };
  },
  props: {
    user: {
      type: Object,
    },
    animals: {
      type: Array,
    },
    navigation: {
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
    onAnimalChange(animal, index) {
      this.animalForm.animal = animal;
      this.currentAnimal = index;
    },
    onBreedChange(breed) {
      this.animalForm.breed = breed;
    },
    onGenderChange(gender) {
      this.petForm.gender = gender;
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
      let animal = {};
      animal.animal = animalForm.animal;
      animal.breed = animalForm.breed;

      petForm.animal = animal;
      let userId = this.user.id;
      let params = { petForm, userId };

      if (!this.$v.petForm.$invalid) {
        this.$store
          .dispatch("pets/addPetToUser", params)
          .then(() => this.navigateToMain());
      } else {
        Toast.show({
          text: "Pet could not be added",
          buttonText: "ok",
          type: "danger",
          duration: 3000,
        });
      }
    },
    navigateToMain() {
      this.navigation.navigate("Main");
    },
  },

  computed: {
    selectedAnimal() {
      return this.animalForm.animal;
    },
    selectedBreed() {
      return this.animalForm.breed;
    },
    selectedGender() {
      return this.petForm.gender;
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
};
</script>