<template>
  <nb-container v-if="isNeedsLoaded">
    <AppHeader :screen="head" />
    <nb-content
      ><nb-card v-for="(need, needIndex) in needs" :key="needIndex"
        ><nb-card-item
          ><nb-label>{{ need.type }} </nb-label>
          <nb-label> Notified: {{ need.notified }}</nb-label>
        </nb-card-item>
        <nb-button><nb-text>Add new schedule</nb-text></nb-button>
        <nb-card
          v-for="(schedule, scheduleIndex) in need.schedule"
          :key="scheduleIndex"
          ><nb-card-item
            ><nb-label
              >{{ schedule.time }} assigned to
              {{ schedule.assignedTo }}</nb-label
            >
          </nb-card-item>
        </nb-card>
      </nb-card>
      <nb-button :on-press="() => goToAddNeedScreen(pet.id)"
        ><nb-text>Add new Need</nb-text></nb-button
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
        default: () => {},
      },
      user: {
        type: Object,
        default: () => {},
      },
      head: "schedules",
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
    schedule() {},
    isNeedsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
    sharedWith() {
      return this.$store.state.sharedWith.sharedWithUsers;
    },
  },
  async created() {
    this.pet = this.navigation.getParam("pet", "undefined");
    this.user = this.navigation.getParam("user", "undefined");
    let params = {};
    params.userId = this.user.id;
    params.petId = this.pet.id;
    await this.$store.dispatch("sharedWith/fetchSharedWithUsers", params);
  },

  methods: {
    changeTime() {
      alert("change Time");
    },
    changeAssignment() {
      alert("change assigned user");
    },
    goToAddNeedScreen(petId) {
      const user = this.user;
      const sharedWith = this.sharedWith;
      console.log(petId);
      this.navigation.navigate("AddNeed", {
        user: user,
        petId: petId,
        sharedWith: sharedWith,
      });
    },
  },
};
</script>

<style></style>
