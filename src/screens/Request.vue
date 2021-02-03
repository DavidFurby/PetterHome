<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Request Page" />
      <RequestCard
        v-if="hasInvites"
        :invites="invites"
        :requests="requests"
        :acceptRequest="acceptRequest"
      />
    </scroll-view>
  </nb-container>
</template>
<script>
import requestMock from "../data/requestMock.json";
import RequestCard from "../components/RequestCard";
export default {
  components: {
    RequestCard,
  },
  data: {
    requests: requestMock,
  },
  props: {
    requestData: {
      type: Array,
      default: () => [],
    },
  },
  created() {
    this.$store.dispatch("auth/fetchCurrentUser");
    this.$store.dispatch("invites/fetchInvites");
  },
  methods: {
    acceptRequest() {
      alert("accepted");
    },
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    invites() {
      return this.$store.state.invites.invites;
    },
    hasInvites() {
      return this.invites && this.user.length > 0;
    },
  },
};
</script>