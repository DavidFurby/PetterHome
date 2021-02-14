<template>
  <nb-container v-if="hasAnimals">
    <AppHeader
      screen="Add Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddPetForm
      :user="user"
      :animals="animals"
      :navigation="navigation"
      :numbers="numbers"
    />
  </nb-container>
  <nb-container v-else class="spinner-container">
    <nb-spinner color="blue" />
  </nb-container>
</template>

<script>
import AddPetForm from "../components/AddPetForm";

export default {
  components: { AddPetForm },

  props: {
    navigation: {
      type: Object,
    },
  },
  created() {
    this.$store.dispatch("animals/fetchAnimals");
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    numbers() {
      const numbers = [];
      for (let i = 0; i <= 100; i++) {
        numbers[i] = i;
      }
      return numbers;
    },
    animals() {
      let animals = this.$store.state.animals.items;
      return animals;
    },
    hasAnimals() {
      return this.animals && this.animals.length > 0;
    },
  },
};
</script>
