<template>
  <nb-container>
    <AppHeader
      screen="Add Schedule"
      leftButton="return"
      :leftButtonFunction="goBack"
    />
    <AddScheduleForm
      v-if="ifAvailableUsers"
      :petId="petId"
      :needId="needId"
      :availableUsers="availableUsers"
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
    };
  },
  async created() {
    const user = await this.navigation.getParam("user", "undefined");
    const petId = await this.navigation.getParam("petId", "undefined");
    const needId = await this.navigation.getParam("needId", "undefined");
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
    this.petId = petId;
    this.userId = user.id;
    this.needId = needId;
  },
  methods: {
    goBack() {
      this.navigation.goBack();
    },
    ifAvailableUsers() {
      return Object.keys(this.availableUsers).length > 0;
    },
  },
};
</script>