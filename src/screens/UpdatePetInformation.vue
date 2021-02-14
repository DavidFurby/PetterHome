<template>
  <nb-container>
    <AppHeader
      screen="Update Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <nb-content v-if="hasAnimals && hasPet">
      <UpdatePetForm
        :pet="hasPet"
        :user="user"
        :animals="animals"
        :navigation="navigation"
        :numbers="numbers"
      />
      
    </nb-content>
    <nb-content v-else class="spinner-container">
      <nb-spinner color="blue" />
    </nb-content>
  </nb-container>
</template>

<script>
import UpdatePetForm from "../components/UpdatePetForm";
export default {
  components: {
    UpdatePetForm,
  },
  data() {
    return {
      pet: {
        type: Object,
      },
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  created() {
    const pet = this.navigation.getParam("pet", "undefined");
    this.pet = pet;
    this.$store.dispatch("animals/fetchAnimals");
  },

  computed: {
    hasPet() {
      return this.pet;
    },
    user() {
      return this.$store.state.auth.user;
    },
    animals() {
      let animals = this.$store.state.animals.items;
      return animals;
    },
    hasAnimals() {
      return this.animals && this.animals.length > 0;
    },
    numbers() {
      const numbers = [];
      for (let i = 0; i <= 100; i++) {
        numbers[i] = i;
      }
      return numbers;
    },
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>

<style>
</style>