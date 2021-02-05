<template>
  <nb-container>
    <AppHeader :screen="header" />
    <scroll-view>
      <nb-content v-if="hasUsers">
        <nb-card v-for="user in sharedWith" :key="user.id">
          <nb-card-item button>
            <nb-body full info>
              <nb-text>{{ user.username }} </nb-text>
              <nb-text>{{ user.email }} </nb-text>
            </nb-body>
            <nb-button><nb-text>Delete</nb-text></nb-button>
          </nb-card-item>
        </nb-card>
      </nb-content>
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

export default {
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
    await this.$store.dispatch("sharedWith/fetchSharedWithUsers", params);
  },

  methods: {
    setShare() {
      return (this.share = !this.share);
    },
    sharePet() {
      let userForm = this.userForm;
      let username = userForm.username;
      const petId = this.pet.id;
      const userId = this.user.id;
      let params = { userId, petId, username };
      this.$store
        .dispatch("invites/sendInvite", params)
        .then(() =>
          Toast.show({
            text: "Invite was sent successfully",
            buttonText: "ok",
            type: "success",
            duration: 3000,
          })
        )
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
  computed: {
    isPetsLoaded() {
      return Object.keys(this.pet).length > 0;
    },
    sharedWith() {
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