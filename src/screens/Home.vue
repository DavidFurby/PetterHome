<template>
  <nb-container>
    <AppHeader root :screen="title" />
    <nb-text class="text-color-primary">{{
      JSON.stringify(notification.data)
    }}</nb-text>
    <scroll-view>
      <PetCard
        v-for="pet in pets"
        :key="pet.id"
        :pet="pet"
        :navigateToPet="goToPetScreen"
        :navigateToPetSchema="goToPetSchema"
      />
    </scroll-view>
    <nb-button block :on-press="goToAddPetScreen" :pets="pets">
      <nb-text>Add Pet</nb-text>
    </nb-button>
  </nb-container>
</template>
<script>
import PetCard from "../components/PetCard";

export default {
  components: {
    PetCard,
  },
  props: {
    navigation: {
      type: Object,
    },
  },
  data() {
    return {
      notification: {},
      title: "Home Screen",
    };
  },
  created() {
    this.registerForPushNotificationsAsync();
    this._notificationSubscription = Notification.addListener(
      this._handleNotification
    );
  },
  methods: {
    registerForPushNotifications: async () => {
      const { status: existingStatus } = await Permissions.getAsync(
        Permissions.NOTIFICATIONS
      );
      let finalStatus = existingStatus;

      // only ask if permissions have not already been determined, because
      // iOS won't necessarily prompt the user a second time.
      if (existingStatus !== "granted") {
        // Android remote notification permissions are granted during the app
        // install, so this will only ask on iOS
        const { status } = await Permissions.askAsync(
          Permissions.NOTIFICATIONS
        );
        finalStatus = status;
      }

      // Stop here if the user did not grant permissions
      if (finalStatus !== "granted") {
        return;
      }

      // Get the token that uniquely identifies this device
      Notifications.getExpoPushTokenAsync().then((token) => {
        console.log(token);
      });
    },

    _handleNotification(notification) {
      console.log(notification, "notification");
      this.notifications = notification;
    },
    goToPetScreen(pet) {
      this.navigation.navigate("Pet", { pet: pet });
    },
    goToAddPetScreen() {
      let user = this.user;
      this.navigation.navigate("AddPet", { user: user });
    },
    goToPetSchema(petData) {
      let user = this.user;
      let pet = petData;
      this.navigation.navigate("Schema", { pet: pet, user: user });
    },
  },
  async created() {
    const userId = this.user.id;
    console.log("object");
    console.log(userId);
    await this.$store.dispatch("receivedPets/fetchReceivedPets", userId);
    await this.$store.dispatch("users/fetchCurrentUser", userId);
    await this.$store.dispatch("invites/fetchInvites", userId);
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
    pets() {
      return this.user.pets;
    },
    hasUser() {
      return this.user && this.user.length > 0;
    },
  },
};
</script>
<style scoped>
</style>