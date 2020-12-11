<template>
  <nb-container>
    <nb-header><nb-title>Home</nb-title></nb-header>
    <nb-content>
      <nb-card v-for="animal in animals" :key="animal.id">
        <nb-card-item button :on-press="goToPetScreen">
          <nb-body full info>
            <nb-text>{{ animal.name }} </nb-text>
            <nb-text>{{ animal.animal }} </nb-text>
            <nb-text>{{ animal.age }} </nb-text>
            <nb-text>{{ animal.gender }} </nb-text>
            <nb-text>{{ animal.breed }} </nb-text>
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
import mock from "../data/mock.json";
export default {
  data() {
    return {
      animals: [
        {
          id: 1,
          name: "Vine snake (unidentified)",
          animal: "Oxybelis sp.",
          age: "16-618-3835",
          gender: "Male",
          breed: "Bryaceae",
        },
        {
          id: 2,
          name: "Red-legged pademelon",
          animal: "Thylogale stigmatica",
          age: "39-666-8557",
          gender: "Female",
          breed: "Brassicaceae",
        },
        {
          id: 3,
          name: "Canada goose",
          animal: "Branta canadensis",
          age: "44-145-2412",
          gender: "Female",
          breed: "Fabaceae",
        },
        {
          id: 4,
          name: "Andean goose",
          animal: "Chloephaga melanoptera",
          age: "10-572-8866",
          gender: "Male",
          breed: "Asteraceae",
        },
        {
          id: 5,
          name: "Chilean flamingo",
          animal: "Phoenicopterus chilensis",
          age: "36-123-3492",
          gender: "Male",
          breed: "Gyalectaceae",
        },
        {
          id: 6,
          name: "Boar, wild",
          animal: "Sus scrofa",
          age: "37-922-6438",
          gender: "Female",
          breed: "Cyperaceae",
        },
        {
          id: 7,
          name: "Radiated tortoise",
          animal: "Geochelone radiata",
          age: "80-513-0237",
          gender: "Male",
          breed: "Poaceae",
        },
        {
          id: 8,
          name: "Little brown bat",
          animal: "Myotis lucifugus",
          age: "58-086-8792",
          gender: "Male",
          breed: "Rosaceae",
        },
        {
          id: 9,
          name: "Chimpanzee",
          animal: "Pan troglodytes",
          age: "96-942-5915",
          gender: "Male",
          breed: "Caryophyllaceae",
        },
        {
          id: 10,
          name: "Javan gold-spotted mongoose",
          animal: "Herpestes javanicus",
          age: "54-194-4940",
          gender: "Male",
          breed: "Lamiaceae",
        },
      ],
    };
  },
  props: {
    navigation: {
      type: Object,
    },
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