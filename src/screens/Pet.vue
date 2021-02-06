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
import { Toast } from "native-base";

export default {
  components: {
    PetPage,
  },
  data() {
    return {
      pet: {
        type: Object,
      },
      user: {
        type: Object,
      },
    };
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
      return Object.keys(this.pet).length > 0;
    },
  },
  created() {
    this.pet = this.navigation.getParam("pet", "undefined");
    this.user = this.navigation.getParam("user", "undefined");
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
          if (this.clicked == this.btnOptions[0]) {
            let params = {};
            params.petId = this.pet.id;
            params.userId = this.user.id;
            this.$store
              .dispatch("pets/deletePetFromUser", params)
              .then(() => {
                this.navigation.goBack();
              })
              .catch(() => {
                Toast.show({
                  text: "Pet could not be deleted",
                  buttonText: "ok",
                  type: "danger",
                  duration: 3000,
                });
              });
          }
        }
      );
    },
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>
