<template>
  <nb-container>
    <AppHeader screen="Notifications" />
    <scroll-view>
      <NotificationSettingsForm
        :pets="petData"
      />
    </scroll-view>
  </nb-container>
</template>
<script>
import petMock from "../data/petMock.json";
import NotificationSettingsForm from "../components/NotificationSettingsForm";
export default {
  components: {
    NotificationSettingsForm,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      petData: petMock,

    };
  },
  computed: {
    pets() {
      let petData = this.$store.state.pets;
      return petData;
    },
  },
};
</script>