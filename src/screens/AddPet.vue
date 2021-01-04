<template>
  <nb-container>
    <AppHeader
      screen="Add Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddPetForm />
    <nb-button block :on-press="addPet">
      <nb-text>Add Pet</nb-text>
    </nb-button>
  </nb-container>
</template>
<script>
import { Picker } from "native-base";
import animalMock from "../data/animalMock.json";
import AddPetForm from "../components/AddPetForm";
export default {
  components: { Item: Picker.Item, AddPetForm },

  props: {
    animals: {
      type: Array,
      default: () => [],
    },
    navigation: {
      type: Object,
    },
  },
  data: function () {
    return {
      selected: "key0",
    };
  },
  methods: {
    onValueChange: function (value) {
      this.selected = value;
    },
    goBack() {
      this.navigation.goBack();
    },
    addPet() {
      alert("adding pet")
    }
  },
};
</script>