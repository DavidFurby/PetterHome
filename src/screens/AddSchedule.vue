<template>
  <nb-container>
    <AppHeader
      screen="Add Schedule"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddScheduleForm
      v-if="ifAvailableUsers && ifFirstAvailableUser"
      :petId="petId"
      :needId="needId"
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
import AddScheduleForm from "../components/AddScheduleForm";
export default {
  components: { Item: Picker.Item, AddScheduleForm },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      userId: {
        type: String,
        default: "",
      },
      petId: {
        type: String,
        default: "",
      },
      needId: {
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
    const user = await this.navigation.getParam("user", "undefined");
    const petId = await this.navigation.getParam("petId", "undefined");
    const needId = await this.navigation.getParam("needId", "undefined");
    const availableUsers = await this.navigation.getParam(
      "availableUsers",
      "undefined"
    );
    const firstAvailableUser = await this.navigation.getParam(
      "firstAvailableUser",
      "undefined"
    );

    this.petId = petId;
    this.userId = user.id;
    this.needId = needId;
    this.availableUsers = availableUsers;
    this.firstAvailableUser = firstAvailableUser;
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
      return typeof this.firstAvailableUser !== "object"
        ? this.firstAvailableUser
        : null;
    },
  },
};
</script>