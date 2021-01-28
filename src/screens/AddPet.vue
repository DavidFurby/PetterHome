<template>
  <nb-container>
    <AppHeader
      screen="Add Pet"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddPetForm :user="user" />
  </nb-container>
</template>
<script>
import { Picker, Toast } from "native-base";
import AddPetForm from "../components/AddPetForm";
export default {
  components: { Item: Picker.Item, AddPetForm },

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
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>