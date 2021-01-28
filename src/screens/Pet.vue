<template>
  <nb-container>
    <AppHeader
      :screen="pet.petName"
      leftButton="return"
      rightButton="delete"
      :rightButtonFunction="deleteFromApp"
      :leftButtonFunction="goBack"
    />
    <nb-content padder>
      <nb-card>
        <nb-card-item>
          <nb-body>
            <nb-text>Name: {{ pet.petName }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Animal: {{ pet.animal }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Breed: {{ pet.breed }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Age: {{ pet.age }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Weight: {{ pet.weight }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Height: {{ pet.height }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item>
          <nb-body>
            <nb-text>Birthday: {{ pet.birthday }}</nb-text></nb-body
          ></nb-card-item
        >
        <nb-card-item
          ><nb-body><nb-text>Needs</nb-text> </nb-body></nb-card-item
        >
      </nb-card>
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
      let pet = this.$store.state.pet;
      return pet;
    },
  },
  created() {
    const params = this.navigation.getParam("params", "undefined");
    console.log(params); 
    this.$store.dispatch("pets/fetchPetById", petId);
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
