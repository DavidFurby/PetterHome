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
    <nb-button class="button" block :on-press="() => goToUpdateInformation(pet)"
      ><nb-text class="text"
        >Update information
        <nb-icon
          :style="{ fontSize: 15, color: 'white' }"
          name="build" /></nb-text
    ></nb-button>
  </nb-container>
  <nb-container v-else class="spinner-container">
    <nb-spinner color="blue" />
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
                {
                  if (res == "pet has been deleted successfully") {
                    Toast.show({
                      text: res,
                      buttonText: "ok",
                      type: "success",
                      duration: 3000,
                    });
                    this.goBack();
                  }
                }
              })
              .catch((err) => {
                console.log(err);
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
    goToUpdateInformation(pet) {
      this.navigation.navigate("UpdatePet", { pet: pet });
    },
  },
};
</script>
<style scoped>
.button {
  background-color: #edccaf;
}
.text {
  color: white;
  font-family: ArchitectsDaughter;
}
</style>