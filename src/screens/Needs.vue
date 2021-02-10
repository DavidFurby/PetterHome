<template>
  <nb-container>
    <AppHeader screen="Needs" />
    <nb-content v-if="isNeedsLoaded">
      <NeedPage
        v-for="(need, needIndex) in needs"
        :key="needIndex"
        :need="need"
        :goToAddScheduleScreen="goToAddScheduleScreen"
        :deleteNeed="deleteNeed"
        :deleteSchedule="deleteSchedule"
        :petId="pet.id"
      />
      <nb-button block :on-press="() => goToAddNeedScreen(pet.id)"
        ><nb-text>Add new Need</nb-text></nb-button
      >
    </nb-content>
  </nb-container>
</template>

<script>
import AddNeed from "./AddNeed";
import NeedPage from "../components/NeedPage";
import { Toast } from "native-base";
export default {
  components: {
    AddNeed,
    NeedPage,
  },
  data() {
    return {
      firstAvailableUser: {
        type: String,
        default: "",
      },
      availableUsers: {
        type: Array,
        default: [],
      },
      userId: {
        type: Array,
      },
    };
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
      return this.$store.state.pets.item;
    },
    needs() {
      let needs = this.pet.needs;
      return needs;
    },
    isNeedsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
    sharedWithUsers() {
      return this.$store.state.sharedWith.sharedWithUsers;
    },
  },
  async created() {
    const petId = this.navigation.getParam("petId", "undefined");
    let params = {};
    params.petId = petId;
    this.$store.dispatch("pets/fetchPetById", params);
    await this.$store.dispatch("sharedWith/fetchSharedWithUsers", params);
    console.log("object");
    const sharedWithUsers = this.sharedWithUsers;
    let arr = [];
    arr.push(this.user.username);
    sharedWithUsers.map((user) => {
      arr.push(user.username);
    });
    const firstUser = arr[0];
    this.availableUsers = arr;
    this.firstAvailableUser = firstUser;
    console.log(firstUser, "test");
  },

  methods: {
    goToAddNeedScreen(petId) {
      const sharedWithUsers = this.sharedWithUsers;
      const firstAvailableUser = this.firstAvailableUser;
      const availableUsers = this.availableUsers;
      const user = this.user;
      this.navigation.navigate("AddNeed", {
        user: user,
        petId: petId,
        sharedWithUsers: sharedWithUsers,
        firstAvailableUser: firstAvailableUser,
        availableUsers: availableUsers,
      });
    },
    goToAddScheduleScreen(petId, needId) {
      const sharedWithUsers = this.sharedWithUsers;
      const firstAvailableUser = this.firstAvailableUser;
      const availableUsers = this.availableUsers;
      const user = this.user;
      this.navigation.navigate("AddSchedule", {
        user: user,
        petId: petId,
        needId: needId,
        sharedWithUsers: sharedWithUsers,
        firstAvailableUser: firstAvailableUser,
        availableUsers: availableUsers,
      });
    },
    deleteNeed(petId, needId) {
      params = {};
      params.petId = petId;
      params.needId = needId;
      this.$store.dispatch("pets/deleteNeed", params).then((res) => {
        if (res == "need was deleted") {
          Toast.show({
            text: res,
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
        } else {
          Toast.show({
            text: "need has been deleted successfully",
            buttonText: "ok",
            type: "danger",
            duration: 3000,
          });
        }
      });
    },
    deleteSchedule(petId, needId, scheduleId) {
      params = {};
      params.petId = petId;
      params.needId = needId;
      params.scheduleId = scheduleId;
      this.$store.dispatch("pets/deleteSchedule", params).then((res) => {
        if (res == "schedule has been deleted successfully") {
          Toast.show({
            text: res,
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
        } else {
          Toast.show({
            text: "schedule could not be deleted",
            buttonText: "ok",
            type: "danger",
            duration: 3000,
          });
        }
      });
    },
  },
};
</script>

<style></style>
