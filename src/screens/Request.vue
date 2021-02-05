<template>
  <nb-container>
    <AppHeader screen="Invites" />
    <scroll-view>
      <RequestCard v-if="ifUser" :acceptRequest="acceptRequest" :user="user" />
      <nb-text v-else>No Invites available</nb-text>
    </scroll-view>
  </nb-container>
</template>
<script>
import RequestCard from "../components/RequestCard";
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
  },
  methods: {
    acceptRequest(inviteId) {
      let params = {};
      params.inviteId = inviteId;
      params.userId = this.user.id;
      this.$store
        .dispatch("invites/acceptInvite", params)
        .then((res) => {
          Toast.show({
            text: "Invite was accepted",
            buttonText: "ok",
            type: "success",
            duration: 3000,
          });
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
  },
};
</script>