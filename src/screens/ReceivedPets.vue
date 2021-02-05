<template>
  <view>
    <AppHeader :screen="header" />
    <ReceivedPetCard />
  </view>
</template>
<script>
import ReceivedPetCard from "../components/ReceivedPetCard";
export default {
  data() {
    return {
      header: "Received Pets"
    }
  },
  components: {
    ReceivedPetCard,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
};
</script>