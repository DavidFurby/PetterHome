<template>
  <nb-container>
    <nb-header><nb-title>Notification Settings</nb-title></nb-header>
    <scroll-view>
      <NotificationSettingsForm
        :pets="petData"
        :notificationOn="notificationOn"
        :setCheck="setCheck"
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
      setCheck: false,
    };
  },
  methods: {
    notificationOn() {
      this.setCheck = !this.setCheck;
    },
  },
  computed: {
    pets() {
      let petData = this.$store.state.pets;
      return petData;
    },
  },
};
</script>