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
      </nb-form>
      <nb-form>
        <InputWithError
          :error="$v.petForm.petName.$dirty && !$v.petForm.petName.required"
          msg="A name must be given for the pet"
        >
          <nb-item stackedLabel
            ><nb-input
              :placeholder="petForm.petName"
              v-model="petForm.petName"
              :on-blur="() => $v.petForm.petName.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input
            :placeholder="petForm.petAge.toString()"
            v-model="petForm.petAge"
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
          ><nb-input
            v-model="petForm.height"
            :placeholder="petForm.height.toString()"
        /></nb-item>

        <nb-item stackedLabel>
          <nb-input
            v-model="petForm.weight"
            :placeholder="petForm.weight.toString()"
          />
        </nb-item>
      </nb-form>

      <nb-button block :on-press="updatePet">
        <nb-text>Update Pet</nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>

<script>
import { Picker } from "native-base";

import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item },

  data() {
    return {
      breedSelection: "",
      currentAnimal: 0,
      petForm: {
        petName: this.pet.petName,
        petAge: this.pet.petAge,
        gender: this.pet.gender,
        weight: this.pet.weight,
        height: this.pet.height,
      },
      animalForm: {
        animal: this.pet.animal.animal,
        breed: this.pet.animal.breed,
      },
    };
  },
  props: {
    user: {
      type: Object,
      default: () => {},
    },
    pet: {
      type: Object,
      default: () => {},
    },
    animals: {
      type: Array,
      default: () => [],
    },
    navigation: {
      type: Object,
      default: () => [],
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
    setMedication() {
      this.petForm.medication = !this.petForm.medication;
    },
    updatePet() {
      this.$v.petForm.$touch();
      this.$v.animalForm.$touch();
      let petForm = this.petForm;
      let animalForm = this.animalForm;
      let animal = {};
      animal.animal = animalForm.animal;
      animal.breed = animalForm.breed;

      petForm.animal = animal;
      console.log(petForm);
      let params= {}
      params.petForm = petForm;
      console.log(this.pet.id)
      params.petId = this.pet.id;
      if (!this.$v.petForm.$invalid) {
        this.$store.dispatch("pets/updateUserPet", params).then(() => {
          Toast.show({
            text: "The pets information was updated succesfully",
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
          this.navigation.navigate("Main");
        });
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
      this.navigation.goBack();
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
  },
};
</script>

<style>
</style>