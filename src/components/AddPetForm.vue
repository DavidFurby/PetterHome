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
      ><nb-item><nb-input placeholder="Name"/></nb-item>

      <nb-item><nb-input placeholder="Height"/></nb-item>
      <nb-item>
        <nb-input placeholder="Weight"/>
      </nb-item>
      <nb-list-item :onPress="setMedication">
        <nb-body>
          <nb-text>Medication ?</nb-text>
        </nb-body>
        <nb-checkbox :checked="medication" />
      </nb-list-item>
      <nb-item v-if="medication"
        ><nb-input placeholder="Medication"></nb-item>
      <nb-item v-if="medication"
        ><nb-input placeholder="Dosage amount"
      /></nb-item>
      <DogForm v-if="ifDog"/>
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
    onAnimalChange(value) {
      this.animalSelection = value;
    },
    onBreedChange(value) {
      this.breedSelection = value;
    },
    getIosIcon() {
      return <Icon name="ios-arrow-down-outline" />;
    },
    setMedication() {
      return (this.medication = !this.medication);
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