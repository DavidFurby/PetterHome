<template>
  <nb-container>
    <AppHeader
      screen="Pet"
      leftButton="return"
      rightButton="delete"
      :rightButtonFunction="deleteFromApp"
      :leftButtonFunction="goBack"
    />
    <nb-content padder>
      <nb-text>{{ pet.petName }}</nb-text>
      <nb-text>Animal: {{ pet.animal }}</nb-text>
      <nb-text>Breed: {{ pet.breed }}</nb-text>
      <nb-text>Weight: {{ pet.weight }}</nb-text>
      <nb-text>Height:{{ pet.height }}</nb-text>
      <nb-text>Birthday:{{ pet.birth }}</nb-text>
      <nb-text v-for="needs in pet" v-bind:key="needs.id"
        >{{ needs.type }}
        <nb-text v-for="time in needs" :key="time.id"
          >{{ time.clock }} assigned To {{ time.assigned }}</nb-text
        >
      </nb-text>
    </nb-content>
  </nb-container>
</template>
<script>
import { ActionSheet } from "native-base";
export default {
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
      return this.$store.state.pets;
    },
  },
  created() {
    const petIdTemp = this.navigation.getParam("petId", "undefined");
    this.petId = petIdTemp;
    alert(this.petId);
    //this.$store.dispatch('pets/fetchPetsById', this.petId)
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