<template>
  <nb-container>
    <AppHeader :screen="schemaScreen" />
    <nb-content
      ><nb-card v-for="need in petTemp[0].needs" :key="need.id"
        ><nb-card-item
          ><nb-text>{{ need.type }}</nb-text
          ><nb-body /><nb-card
            ><nb-card-item v-for="time in need.time" :key="time.id"
              ><nb-text
                >at
                <nb-button :on-press="changeTime">
                  <nb-text> {{ time.clock }}</nb-text></nb-button
                >
                assigned to
                <nb-button :on-press="changeAssignment"
                  ><nb-text>{{ time.assigned }}</nb-text></nb-button
                ></nb-text
              ></nb-card-item
            ></nb-card
          >
        </nb-card-item></nb-card
      >
    </nb-content>
  </nb-container>
</template>

<script>
import petMock from "../data/userMock.json";
export default {
  data() {
    return {
      petTemp: petMock[0].pets,
      schemaScreen: petMock[0].petName + " schedule",
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    pet() {
      return this.store.state.pets;
    },
  },
  created() {
    const petId = this.navigation.getParam("petId", "undefined");
    //this.$store.dispatch("pets/fetchPetById", petId);
  },

  methods: {
    changeTime() {
      alert("change Time");
    },
    changeAssignment() {
      alert("change assigned user");
    },
  },
};
</script>

<style></style>
