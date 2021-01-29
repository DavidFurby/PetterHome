<template>
  <nb-container v-if="isPetPageLoaded">
    <AppHeader
      :screen="pet.petName"
      leftButton="return"
      rightButton="delete"
      :rightButtonFunction="deleteFromApp"
      :leftButtonFunction="goBack"
    />
    <PetPage
      :animal="animal"
      :pet="pet"
      :deleteFromApp="deleteFromApp"
      :goBack="goBack"
    />
  </nb-container>
</template>
<script>
import { ActionSheet } from "native-base";
import PetPage from ".././components/PetPage";
export default {
  components: {
    PetPage,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      btnOptions: ["Delete", "Cancel"],
      optionCancelIndex: 1,
      optionDestructiveIndex: 0,
    };
  },
  computed: {
    pet() {
      let pet = this.$store.state.pets.pet;
      return pet;
    },
    animal() {
      return this.pet.animal || {};
    },
    isPetPageLoaded() {
      return Object.keys(this.pet).length > 0
    },
  },
  created() {
    const userId = this.navigation.getParam("userId", "undefined");
    const petId = this.navigation.getParam("petId", "undefined");

    let params = {};
    params.userId = userId;
    params.petId = petId;
    this.$store.dispatch("pets/fetchPetById", params);
  },
  methods: {
    deleteFromApp() {
      this.displayActionsheet();
      //this.$store.dispatch('pets/deletePetsById', this.petId)
    },
    displayActionsheet() {
      ActionSheet.show(
        {
          options: this.btnOptions,
          cancelButtonIndex: this.optionCancelIndex,
          destructiveButtonIndex: this.optionDestructiveIndex,
          title: "Are you sure you want to delete?",
        },
        (buttonIndex) => {
          this.clicked = this.btnOptions[buttonIndex];
        }
      );
    },
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>
