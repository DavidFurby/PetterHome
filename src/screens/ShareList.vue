<template>
  <nb-container>
    <AppHeader :screen="header" />
    <scroll-view>
      <SharedWithUserCard v-if="isSharedWithLoaded" :sharedWith="sharedWith" />
      <nb-text v-else>You haven't shared this pet with anyone yet</nb-text>
    </scroll-view>
    <nb-content>
      <nb-button block :on-press="setShare">
        <nb-text>Share Pet</nb-text>
      </nb-button>
      <nb-content v-if="share">
        <nb-text>Add sharing account</nb-text>
        <nb-item
          ><nb-form
            ><nb-input placeholder="username" v-model="userForm.username" />
          </nb-form>
        </nb-item>
        <nb-button :on-press="() => sharePet()"
          ><nb-text>Send invite</nb-text>
        </nb-button>
      </nb-content>
    </nb-content>
  </nb-container>
</template>

<script>
import { Toast } from "native-base";
import SharedWithUserCard from "../components/SharedWithUserCard";
export default {
  components: {
    SharedWithUserCard,
  },
  data() {
    return {
      header: "Share list",
      share: false,
      userForm: {
        username: "",
      },
      petId: {
        type: String,
        default: "",
      },
      userId: {
        type: String,
        default: "",
      },
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  computed: {
    isSharedWithLoaded() {
      return Object.keys(this.sharedWith).length > 0;
    },
    sharedWith() {
      return this.$store.state.sharedWith.sharedWithUsers;
    },
    hasUsers() {
      return Object.keys(this.sharedWith).length > 0;
    },
  },
  async created() {
    const petId = this.navigation.getParam("petId", "undefined");
    const userId = this.navigation.getParam("userId", "undefined");
    let params = {};
    params.petId = petId;
    params.userId = userId;
    await this.$store.dispatch("sharedWith/fetchSharedWithUsers", params);
    this.userId = userId;
    this.petId = petId;
  },

  methods: {
    setShare() {
      return (this.share = !this.share);
    },
    sharePet() {
      let userForm = this.userForm;
      let username = userForm.username;
      const petId = this.petId;
      const userId = this.userId;
      let params = { userId, petId, username };
      this.$store
        .dispatch("invites/sendInvite", params)
        .then((res) => {
          if (
            res == "Invite for this pet has already been sent to this user!"
          ) {
            Toast.show({
              text: res,
              buttonText: "ok",
              type: "danger",
              duration: 3000,
            });
          }
          if (res == "This pet has already been received by that user!") {
            Toast.show({
              text: res,
              buttonText: "ok",
              type: "danger",
              duration: 3000,
            });
          }
          if (res == "Invite has been sent!") {
            Toast.show({
              text: res,
              buttonText: "ok",
              type: "success",
              duration: 3000,
            });
          }
        })
        .catch(() => {
          Toast.show({
            text: "Invite couldn't send",
            buttonText: "ok",
            type: "danger",
            duration: 3000,
          });
        });
    },
  },
};
</script>

<style>
</style>