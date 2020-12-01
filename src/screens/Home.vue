<template>
  <nb-container>
    <nb-content padder>
      <nb-card v-for="animal in animals" :key="animal.id">
        <nb-card-item button :on-press="goToPetScreen">
          <nb-body full info>
            <nb-text>{{ animal.name }} </nb-text>
            <nb-text> {{animal.age}} år </nb-text>
            <nb-text>{{animal.breed}} </nb-text>
          </nb-body>
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
    animals: {
      type: Array,
      default: () => {},
    },
  },
  methods: {
    goToPetScreen() {
      alert("Pet");
      this.navigation.navigate("Pet");
    },
    goToAddPetScreen() {
      alert("AddPet");
      this.navigation.navigate("AddPet");
    },
  },
};
</script>
<style scoped>
</style>