<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <nb-card>
          <nb-card-item>
            <nb-text :style="{ width: 200 }">Animal</nb-text>
            <nb-picker
              note
              mode="dropdown"
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
          </nb-card-item>

          <nb-card-item>
            <nb-text :style="{ width: 200 }">Breed</nb-text>
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
          </nb-card-item>
        </nb-card>
      </nb-form>
      <nb-form>
        <nb-card>
          <nb-card-item>
            <nb-text :style="{ width: 200 }">Name</nb-text>
            <InputWithError
              :error="$v.petForm.petName.$dirty && !$v.petForm.petName.required"
              msg="A name must be given for the pet"
            >
              <nb-item stackedLabel
                ><nb-input
                  :style="{ width: 100 }"
                  :placeholder="petForm.petName"
                  v-model="petForm.petName"
                  :on-blur="() => $v.petForm.petName.$touch()"
              /></nb-item>
            </InputWithError>
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 200 }">Age</nb-text>
            <nb-picker
              note
              mode="dropdown"
              :style="{ width: 120 }"
              :selectedValue="selectedAge"
              :onValueChange="onAgeChange"
            >
              <item
                v-for="(number, numberIndex) in numbers"
                v-bind:key="numberIndex"
                :label="number.toString()"
                :value="number.toString()"
              />
            </nb-picker>
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 200 }">Gender</nb-text>
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
          </nb-card-item>
          <nb-card-item>
            <nb-item>
              <nb-text :style="{ width: 200 }">Height</nb-text>
              <nb-picker
                note
                mode="dropdown"
                :style="{ width: 120 }"
                :selectedValue="selectedHeight"
                :onValueChange="onHeightChange"
              >
                <item
                  v-for="(number, numberIndex) in numbers"
                  v-bind:key="numberIndex"
                  :label="number.toString()"
                  :value="number.toString()"
                />
              </nb-picker>
            </nb-item>
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 200 }">Weight</nb-text>
            <nb-picker
              note
              mode="dropdown"
              :style="{ width: 120 }"
              :selectedValue="selectedWeight"
              :onValueChange="onWeightChange"
            >
              <item
                v-for="(number, numberIndex) in numbers"
                v-bind:key="numberIndex"
                :label="number.toString()"
                :value="number.toString()"
              />
            </nb-picker>
          </nb-card-item>
        </nb-card>
      </nb-form>
      <nb-button block info :on-press="updatePet">
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
      default: () => {},
    },
    numbers: {
      type: Array,
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
      this.animalForm.breed = this.animals[index].breeds[0]
      this.currentAnimal = index;
    },
    onBreedChange(breed) {
      this.animalForm.breed = breed;
    },
    onGenderChange(gender) {
      this.petForm.gender = gender;
    },

    onHeightChange(height) {
      this.petForm.height = height.toString();
    },
    onWeightChange(weight) {
      this.petForm.weight = weight.toString();
    },
    onAgeChange(age) {
      console.log(age); 
      this.petForm.petAge = age.toString();
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
      let params = {};
      params.petForm = petForm;
      params.petId = this.pet.id;
      console.log(params.petForm, "test")
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
    selectedHeight() {
      return this.pet.height;
    },
    selectedWeight() {
      return this.pet.weight;
    },
    selectedAge() {
      return this.pet.petAge; 
    },
  },
};
</script>

<style>
</style>