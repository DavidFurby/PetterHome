<template>
  <nb-container v-if="isNeedsLoaded">
    <AppHeader :screen="schemaScreen" />
    <nb-content
      ><nb-card v-for="need in needs" :key="need.id"
        ><nb-card-item
          ><nb-text>{{ need.type }}</nb-text
          ><nb-body /><nb-card
            ><nb-card-item v-for="schedule in need.schedules" :key="schedule.id"
              ><nb-text
                >at
                <nb-button :on-press="changeTime">
                  <nb-text> {{ schedule.time }}</nb-text></nb-button
                >
                assigned to
                <nb-button
                  ><nb-text>{{ schedule.assignedTo }}</nb-text></nb-button
                ></nb-text
              ></nb-card-item
            ></nb-card
          >
        </nb-card-item>
      </nb-card>
      <nb-button><nb-text>Add new Schedule</nb-text></nb-button>
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
      console.log(needs);
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
    changeTime() {
      alert("change Time");
    },
    changeAssignment() {
      alert("change assigned user");
    },
    goToAddNeedScreen() {
      user = this.user;
      pet = this.pet;
      this.navigation.navigate("addSchedule", { user: user, pet: { pet } });
    },
  },
};
</script>

<style></style>
