<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Invites" />
      <RequestCard
        v-if="ifInvites"
        :invites="invites"
        :acceptRequest="acceptRequest"
      />
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
  data() {
    return {
      user: {
        type: Object,
      },
    };
  },
  props: {
    requestData: {
      type: Array,
      default: () => [],
    },
    navigation: {
      type: Object,
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
  async created() {
    this.user = await this.navigation.getParam("user", "undefined");
    const userId = this.user.id;
    await this.$store.dispatch("invites/fetchInvites", userId);
  },
  computed: {
    invites() {
      return this.$store.state.invites.invites;
    },
    ifInvites() {
      return Object.keys(this.invites).length > 0;
    },
  },
};
</script>