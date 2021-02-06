<template>
  <nb-container>
    <AppHeader
      screen="Add Need"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddNeedForm :user="user" :petId="petId" :availableUsers="availableUsers" />
  </nb-container>
</template>
<script>
import { Picker } from "native-base";
import AddNeedForm from "../components/AddNeedForm";
export default {
  components: { Item: Picker.Item, AddNeedForm },

  props: {
    navigation: {
      type: Object,
    },
  },
  data: () => {
    return {
      selected: "key0",
      user: {
        type: String,
      },
      petId: {
        type: String,
      },
      availableUsers: {
        type: Array,
        default: () => [],
      },
    };
  },
  async created() {
    this.user = await this.navigation.getParam("user", "undefined");
    this.petId = await this.navigation.getParam("petId", "undefined");
    let sharedWithUsers = await this.navigation.getParam(
      "sharedWith",
      "undefined"
    );
    let arr = [];
    arr.push(this.user.username);
    sharedWithUsers.map((user) => {
      arr.push(user.username);
    });
    console.log(arr, "arr");
    this.availableUsers = arr;
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
  },
};
</script>