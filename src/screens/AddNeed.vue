<template>
  <nb-container>
    <AppHeader
      screen="Add Need"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddNeedForm
      v-if="ifAvailableUsers && ifFirstAvailableUser"
      :username="username"
      :petId="petId"
      :availableUsers="availableUsers"
      :ifFirstAvailableUser="ifFirstAvailableUser"
    />
    <nb-container v-else class="spinner-container">
      <nb-spinner color="blue" />
    </nb-container>
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
  data() {
    return {
      username: {
        type: String,
        default: "",
      },
      petId: {
        type: String,
        default: "",
      },
      availableUsers: {
        type: Array,
        default: () => [],
      },
      firstAvailableUser: {
        type: String,
        default: "",
      },
    };
  },
  async created() {
    const petId = await this.navigation.getParam("petId", "undefined");
    const user = await this.navigation.getParam("user", "undefined");
    const sharedWithUsers = await this.navigation.getParam(
      "sharedWith",
      "undefined"
    );
    let arr = [];
    arr.push(user.username);
    sharedWithUsers.map((user) => {
      arr.push(user.username);
    });
    this.availableUsers = arr;
    const firstUser = arr[0];
    console.log(firstUser, "first");
    this.petId = petId;
    this.username = user.username;
    this.firstAvailableUser = firstUser;
    console.log(this.firstAvailableUser, "firstAvailableUser");
  },
  
  methods: {
    goBack() {
      this.navigation.goBack();
    },
    ifAvailableUsers() {
      return Object.keys(this.availableUsers).length > 0;
    },
  },
  computed: {
    ifFirstAvailableUser() {
      console.log(
        typeof this.firstAvailableUser !== "object"
          ? this.firstAvailableUser
          : null
      );
      return typeof this.firstAvailableUser !== "object"
        ? this.firstAvailableUser
        : null;
    },
  },
};
</script>