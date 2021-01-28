<template>
  <nb-container>
    <scroll-view>
      <AppHeader screen="Request Page" />
      <RequestCard :requests="requests" :acceptRequest="acceptRequest"/>
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
  methods: {
    acceptRequest() {
      alert("accepted");
    },
  },
};
</script>