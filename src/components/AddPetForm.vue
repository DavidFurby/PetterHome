<template>
  <nb-container
    ><nb-form>
      <nb-picker
        mode="dropdown"
        iosHeader="Select animal"
        placeholder="Animal"
        placeholderStyle="{ color: '#bfc6ea' }"
        :iosIcon="getIosIcon()"
        :selectedValue="animalSelection"
        :onValueChange="onAnimalChange"
      >
        <item
          v-for="animal in animals"
          :key="animal.id"
          :label="animal.animal"
          :value="animal.id"
        />
      </nb-picker>
      <nb-picker
        mode="dropdown"
        iosHeader="Select breed"
        placeholder="Breed"
        placeholderStyle="{ color: '#bfc6ea' }"
        :iosIcon="getIosIcon()"
        :selectedValue="breedSelection"
        :onValueChange="onBreedChange"
      >
        <item
          v-for="animal in animals"
          :key="animal.id"
          :label="animal.breeds"
          :value="animal.id"
        /> </nb-picker
      ><nb-item stackedLabel><nb-input placeholder="Name"/></nb-item>

      <nb-item stackedLabel><nb-input placeholder="Height"/></nb-item>
      <nb-item stackedLabel>
        <nb-input placeholder="Weight"/>
      </nb-item >
      <nb-list-item >
          <nb-text>Medication ?</nb-text>
        <nb-body>
        </nb-body>
        <nb-checkbox :onPress="setMedication" :checked="medication" />
      </nb-list-item>
      <nb-item v-if="medication"
        ><nb-input placeholder="Medication"></nb-item>
      <nb-item v-if="medication"
        ><nb-input placeholder="Dosage amount"
      /></nb-item>
      <DogForm v-if="ifDog"/>
          <nb-button block :on-press="addPet">
      <nb-text>Add Pet</nb-text>
    </nb-button>
    </nb-form>
  </nb-container>
</template>
<script>
import React from "react";
import { Picker, Icon } from "native-base";
import animalMock from "../data/animalMock.json";
import DogForm from "../components/DogForm";
export default {
  components: { Item: Picker.Item, DogForm },
  data() {
    return {
      animalSelection: null,
      breedSelection: null,
      animals: animalMock,
      medication: false,
    };
  },

  methods: {
    onAnimalChange(animalValue) {
      this.animalSelection = animalValue;
    },
    onBreedChange(breedValue) {
      this.breedSelection = breedValue;
    },
    getIosIcon() {
      return <Icon name="ios-arrow-down-outline" />;
    },
    setMedication() {
      return (this.medication = !this.medication);
    },
    addPet() {
      this.$v.form.$touch();
      if (this.$v.form.$invalid) {
        this.$store
          .dispatch("auth/newPet")
          .then(() => this.navigateToMain())
          .catch(() => {
            Toast.show({
              text: "Something went wrong",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      }
      alert(JSON.stringify(this.form));
    },
    navigateToMain() {
      this.navigation.navigate("Main", {
        message: "Succesfully added new pet!",
      });
    },
  },
  computed: {
    ifDog() {
      if (this.animalSelection === 1) {
        return true;
      }
    },
  },
};
</script>