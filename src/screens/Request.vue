<template>
  <nb-container>
    <AppHeader screen="Invites" />
    <scroll-view>
      <RequestCard
        v-if="ifInvites"
        :acceptRequest="acceptRequest"
        :declineInvite="declineInvite"
        :user="user"
        :invites="invites"
      />
    </scroll-view>
  </nb-container>
</template>
<script>
import RequestCard from "../components/RequestCard";
import { Toast } from "native-base";
export default {
  components: {
    RequestCard,
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

    ifUser() {
      return (this.user = {});
    },
    invites() {
      const invites = this.$store.state.invites.invites;
      return invites;
    },
    ifInvites() {
      return Object.keys(this.invites).length > 0;
    },
  },
  methods: {
    acceptRequest(inviteId) {
      let params = {};
      params.inviteId = inviteId;
      params.userId = this.user.id;
      this.$store
        .dispatch("invites/acceptInvite", params)
        .then(() => {
          Toast.show({
            text: "Invite was accepted",
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
          this.goBack();
        })
        .catch(() => {
          Toast.show({
            text: "Invite could not be accepted",
            buttonText: "ok",
            type: "danger",
            duration: 3000,
          });
        });
    },
    declineInvite(inviteId) {
      this.$store
        .dispatch("invites/declineInvite", inviteId)
        .then((res) => {
          Toast.show({
            text: "Invite was declined",
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
          this.goBack();
        })
        .catch(() => {
          Toast.show({
            text: "Invite could not be declined",
            buttonText: "ok",
            type: "danger",
            duration: 3000,
          });
        });
    },
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>