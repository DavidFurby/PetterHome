<template>
  <nb-container v-if="hasAnimals">
    <AppHeader
      screen="Add Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddPetForm :user="user" :animals="animals" />
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
  data() {
    return {
      selected: "key0",
      user: null,
    };
  },
  created() {
    this.user = this.navigation.getParam("user", "undefined");
    this.$store.dispatch("animals/fetchAnimals");
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
  computed: {
    animals() {
      let animals = this.$store.state.animals.animals;
      return animals;
    },
    hasAnimals() {
      return this.animals && this.animals.length > 0;
    },
  },
};
</script>