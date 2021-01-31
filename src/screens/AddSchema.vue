<template>
  <nb-container>
    <AppHeader
      screen="Add Schema"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddScheduleForm :user="user" :pet="pet" />
  </nb-container>
</template>
<script>
import { Picker } from "native-base";
import AddScheduleForm from "../components/AddScheduleForm";
export default {
  components: { Item: Picker.Item, AddScheduleForm },

  props: {
    navigation: {
      type: Object,
    },
  },
  data: function () {
    return {
      selected: "key0",
      user: null,
    };
  },
  created() {
    this.user = this.navigation.getParam("user", "undefined");
    this.pet = this.navigation.getParam("pet", "undefined");
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>