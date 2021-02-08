<template>
  <nb-container v-if="isPetPageLoaded">
    <AppHeader
      :screen="pet.petName"
      leftButton="return"
      rightButton="delete"
      :rightButtonFunction="deleteFromApp"
      :leftButtonFunction="goBack"
    />
    <PetPage :animal="animal" :pet="pet" />
  </nb-container>
  <nb-text v-else>Pet can't be loaded yet</nb-text>
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
    pet() {
      return this.$store.state.pets.item;
    },
    animal() {
      return this.pet.animal || {};
    },
    isPetPageLoaded() {
      return Object.keys(this.pet).length > 0;
    },
  },
  created() {
    const petId = this.navigation.getParam("petId", "undefined");
    let params = {};
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
          if (this.clicked == this.btnOptions[0]) {
            const petId = this.pet.id;
            this.$store
              .dispatch("pets/deletePetFromUser", petId)
              .then((res) => {
                console.log(res);
                {
                  if (res == "Pet has been deleted! :(") {
                    Toast.show({
                      text: res,
                      buttonText: "ok",
                      type: "success",
                      duration: 3000,
                    });
                    this.navigation.goBack();
                  }
                }
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
