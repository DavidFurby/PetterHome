<template>
  <view>
    <AppHeader screen="ReceivedPets" />

    <ReceivedPetCard v-if="ifReceivedPets" />
    <nb-text v-else>No pets have been received yet available</nb-text>
  </view>
</template>
<script>
import ReceivedPetCard from "../components/ReceivedPetCard";
export default {
  components: {
    ReceivedPetCard,
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
};
</script>