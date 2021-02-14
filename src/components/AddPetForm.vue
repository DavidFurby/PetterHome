<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <nb-card>
          <nb-card-item>
            <nb-text :style="{ width: 220 }">Animal</nb-text>
            <nb-picker
              note
              mode="dropdown"
              :style="{ width: 10 }"
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
            <nb-text :style="{ width: 220 }">Breed</nb-text>
            <nb-picker
              note
              mode="dropdown"
              :style="{ width: 10 }"
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
            <nb-text :style="{ width: 220 }">Name</nb-text>
            <InputWithError
              :error="$v.petForm.petName.$dirty && !$v.petForm.petName.required"
              msg="A name must be given for the pet"
            >
              <nb-item
                ><nb-input
                  placeholder="Name"
                  v-model="petForm.petName"
                  :on-blur="() => $v.petForm.petName.$touch()"
              /></nb-item>
            </InputWithError>
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 220 }">Age</nb-text>
            <nb-picker
              note
              mode="dropdown"
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
            <nb-text :style="{ width: 220 }">Gender</nb-text>
            <nb-picker
              note
              mode="dropdown"
              :selectedValue="selectedGender"
              :onValueChange="onGenderChange"
            >
              <item label="Male" value="MALE" />
              <item label="Female" value="FEMALE" />
            </nb-picker>
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 220 }">Height (cm)</nb-text>
            <nb-picker
              note
              mode="dropdown"
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
          </nb-card-item>
          <nb-card-item>
            <nb-text :style="{ width: 220 }">Weight (kg)</nb-text>
            <nb-picker
              note
              mode="dropdown"
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

      <nb-button block success :on-press="addPet">
        <nb-text class="text">Add Pet</nb-text>
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
      currentAnimal: 0,
      petForm: {
        petName: "",
        petAge: 0,
        gender: "MALE",
        weight: 0,
        height: 0,
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
      this.animalForm.breed = this.animals[index].breeds[0];
      this.currentAnimal = index;
    },
    onBreedChange(breed) {
      this.animalForm.breed = breed;
    },
    onGenderChange(gender) {
      this.petForm.gender = gender;
    },
    onAgeChange(age) {
      this.petForm.petAge = age;
    },
    onHeightChange(height) {
      this.petForm.height = height;
    },
    onWeightChange(weight) {
      this.petForm.weight = weight;
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

      if (!this.$v.petForm.$invalid) {
        this.$store.dispatch("pets/addPetToUser", petForm).then(() => {
          Toast.show({
            text: "Pet was added succesfully",
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
    selectedAge() {
      return this.petForm.petAge;
    },
    selectedHeight() {
      return this.petForm.height;
    },
    selectedWeight() {
      return this.petForm.weight;
    },
  },
};
</script>
<style scoped>
.text {
  font-family: ArchitectsDaughter;
}
</style>