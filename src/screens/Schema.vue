<template>
  <nb-container v-if="isNeedsLoaded">
    <AppHeader :screen="pet.petName" />
    <nb-content
      ><nb-card v-for="need in needs" :key="need.id"
        ><nb-card-item
          ><nb-text>{{ need.type }}</nb-text>
          <nb-card-item v-for="schedule in schedules(need)" :key="schedule.id"
              ><nb-text>
                at
                <nb-button :on-press="changeTime">
                  <nb-text> {{ schedule.time }}</nb-text></nb-button
                >
                assigned to
                <nb-button
                  ><nb-text>{{ schedule.assignedTo }}</nb-text>
                  </nb-button>
                </nb-text>
              </nb-card-item>
            </nb-card>
      <nb-button :on-press="goToAddScheduleScreen"
        ><nb-text>Add new Schedule</nb-text></nb-button
      >
    </nb-content>
  </nb-container>
</template>

<script>
import AddSchema from "./AddSchema";
export default {
  components: {
    AddSchema,
  },
  data() {
    return {
      pet: {
        type: Object,
      },
      user: {
        type: Object,
      },
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    needs() {
      let needs = this.pet.needs;
      return needs;
    },

    isNeedsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
  },
  created() {
    this.pet = this.navigation.getParam("pet", "undefined");
    this.user = this.navigation.getParam("user", "undefined");
  },

  methods: {
        schedules(need) {
      return need.schedule;
    },
    changeTime() {
      alert("change Time");
    },
    changeAssignment() {
      alert("change assigned user");
    },
    goToAddScheduleScreen() {
      user = this.user;
      pet = this.pet;
      this.navigation.navigate("AddSchema", { user: user, pet: pet });
    },
  },
};
</script>

<style></style>
