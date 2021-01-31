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
  data() {
    return {
      pet: {
        type: Object
      }
    }
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
    animal() {
      return this.pet.animal || {};
    },
    isPetPageLoaded() {
      return Object.keys(this.pet).length > 0
    },
  },
  created() {
    this.pet = this.navigation.getParam("pet", "undefined");
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
