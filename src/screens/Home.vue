<template>
  <nb-container>
    <nb-header><nb-title>Home</nb-title></nb-header>
    <nb-content>
      <nb-card>
        <nb-card-item
          button
          v-for="pet in pets"
          v-bind:key="pet._id"
          :on-press="goToPetScreen"
        >
          <nb-body full info>
            <nb-text>{{ pet.petName }} </nb-text>
            <nb-text>{{ pet.animal }} </nb-text>
            <nb-text>{{ pet.age }} </nb-text>
            <nb-text>{{ pet.gender }} </nb-text>
            <nb-text>{{ pet.breed }} </nb-text>
          </nb-body>
          <nb-button :on-press="goToPetSchema"
            ><nb-text>Calender</nb-text></nb-button
          >
        </nb-card-item>
      </nb-card>
      <nb-button block :on-press="goToAddPetScreen">
        <nb-text>Add Animal</nb-text>
      </nb-button>
    </nb-content>
  </nb-container>
</template>
<script>
export default {
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
     pets() {
      let petData = this.$store.state.pets;
     console.log(petData);
     return petData;
    },
  },
   created() {
    this.$store.dispatch("fetchPets");
  },
  methods: {
    goToPetScreen() {
      this.navigation.navigate("Pet");
    },
    goToAddPetScreen() {
      this.navigation.navigate("AddPet");
    },
    goToPetSchema() {
      this.navigation.navigate("Schema");
    },
  },
};
</script>
<style scoped>
</style>