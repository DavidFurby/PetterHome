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
      :firstAvailableUser="ifFirstAvailableUser"
      :goBack="goBack"
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
    const availableUsers = await this.navigation.getParam(
      "availableUsers",
      "undefined"
    );
    const firstAvailableUser = await this.navigation.getParam(
      "firstAvailableUser",
      "undefined"
    );
    this.petId = petId;
    this.username = user.username;
    this.availableUsers = availableUsers;
    this.firstAvailableUser = firstAvailableUser;
    console.log(this.firstAvailableUser, "availableUser");
  },

  methods: {
    goBack() {
      this.navigation.navigate("Main");
    },
    ifAvailableUsers() {
      return Object.keys(this.availableUsers).length > 0;
    },
  },
  computed: {
    ifFirstAvailableUser() {
      return typeof this.firstAvailableUser !== "object"
        ? this.firstAvailableUser
        : null;
    },
  },
};
</script>