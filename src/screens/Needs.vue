<template>
  <nb-container v-if="isNeedsLoaded">
    <AppHeader :screen="pet.petName" />
    <nb-content
      ><nb-card v-for="(need, needIndex) in needs" :key="needIndex"
        ><nb-card-item
          ><nb-label>{{ need.type }} </nb-label>
          <nb-label> Notified: {{ need.notified }}</nb-label>
        </nb-card-item>
        <nb-button :on-press="() => goToAddScheduleScreen(pet.id, need.id)"
          ><nb-text>Add new schedule</nb-text></nb-button
        >
        <nb-card
          v-for="(schedule, scheduleIndex) in need.schedules"
          :key="scheduleIndex"
          ><nb-card-item
            ><nb-label
              >{{ schedule.time }} assigned to
              {{ schedule.assignedUser }}</nb-label
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
import AddNeed from "./AddNeed";
export default {
  components: {
    AddNeed,
  },

  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pet() {
      return this.$store.state.pets.pet;
    },
    needs() {
      let needs = this.pet.needs;
      return needs;
    },
    isNeedsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
    sharedWith() {
      return this.$store.state.sharedWith.sharedWithUsers;
    },
  },
  async created() {
    const petId = this.navigation.getParam("petId", "undefined");
    const userId = this.navigation.getParam("userId", "undefined");
    let params = {};
    params.userId = userId;
    params.petId = petId;
    this.$store.dispatch("pets/fetchPetById", params);
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
      const sharedWith = this.sharedWith;
      const user = this.user;
      this.navigation.navigate("AddNeed", {
        user: user,
        petId: petId,
        sharedWith: sharedWith,
      });
    },
    goToAddScheduleScreen(petId, needId) {
      const sharedWith = this.sharedWith;
      const user = this.user;
      this.navigation.navigate("AddSchedule", {
        user: user,
        petId: petId,
        needId: needId,
        sharedWith: sharedWith,
      });
    },
  },
};
</script>

<style></style>
