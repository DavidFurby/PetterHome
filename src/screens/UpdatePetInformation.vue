<template>
  <nb-container>
    <AppHeader
      screen="Add Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <nb-content v-if="hasAnimals && hasPet">
      <UpdatePetForm
        :pet="hasPet"
        :user="user"
        :animals="animals"
        :navigation="navigation"
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
      console.log(this.pet);
      return this.pet;
    },
    user() {
      return this.$store.state.auth.user;
    },
    animals() {
      let animals = this.$store.state.animals.items;
      console.log(animals);
      return animals;
    },
    hasAnimals() {
      return this.animals && this.animals.length > 0;
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