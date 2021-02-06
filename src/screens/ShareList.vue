<template>
  <nb-container>
    <AppHeader :screen="header" :sharedWith="sharedWith" />
    <scroll-view>
      <SharedWithUserCard v-if="hasUsers" />
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
            ><nb-input
              placeholder="username"
              v-model="userForm.username"
              :on-blur="() => $v.userForm.username.$touch()"
            />
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
      pet: {
        type: Object,
        default: () => {},
      },
      user: {
        type: Object,
        default: () => {},
      },
      share: false,
      userForm: {
        username: "",
      },
    };
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  async created() {
    this.pet = this.navigation.getParam("pet", "undefined");
    this.user = this.navigation.getParam("user", "undefined");

    let params = {};
    params.userId = this.user.id;
    params.petId = this.pet.id;
    console.log(params);
    await this.$store.dispatch("sharedWith/fetchSharedWithUsers", params);
  },

  methods: {
    setShare() {
      return (this.share = !this.share);
    },
    sharePet() {
      if (this.user.username != this.userForm.username) {
        let userForm = this.userForm;
        let username = userForm.username;
        const petId = this.pet.id;
        const userId = this.user.id;
        let params = { userId, petId, username };
        this.$store
          .dispatch("invites/sendInvite", params)
          .then((res) => {
            if ((res = "Invite for this pet has already been sent!")) {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            } else if (
              (res = "This pet has already been received by that user!")
            ) {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            } else {
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
      } else {
        Toast.show({
          text: "Can't invite yourself",
          buttonText: "ok",
          type: "danger",
          duration: 3000,
        });
      }
    },
  },
  computed: {
    isPetsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
    sharedWith() {
      console.log(this.$store.state);
      return this.$store.state.sharedWith.sharedWithUsers;
    },
    hasUsers() {
      return Object.keys(this.sharedWith).length > 0;
    },
  },
};
</script>

<style>
</style>