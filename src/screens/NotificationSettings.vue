<template>
  <nb-container>
    <nb-header><nb-title>Notification Settings</nb-title></nb-header>
    <NotifcationSettingsForm
      v-for="pet in petData"
      :key="pet.id"
      :pet="pet"
      :selected="selected"
    />
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
      selected: "key1",
    };
  },

  computed: {
    pets() {
      let petData = this.$store.state.pets;
      return petData;
    },
  },

  methods: {
    onValueChange: function (value) {
      this.selected = value;
    },
  },
};
</script>