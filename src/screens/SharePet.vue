<template>
  <nb-container>
    <nb-header><nb-title>Share Pet</nb-title></nb-header>

    <nb-button :on-press="goToRecieve"><nb-text>Recieve</nb-text> </nb-button>
    <nb-card v-for="animal in animals" :key="animal.id">
      <nb-card-item button :on-press="goToShareList">
        <nb-body full info>
          <nb-text>{{ animal.name }} </nb-text>
          <nb-text> {{ animal.age }} år </nb-text>
          <nb-text>{{ animal.breed }} </nb-text>
        </nb-body>
      </nb-card-item>
    </nb-card>
  </nb-container>
</template>
<script>
export default {
  props: {
    animals: {
      type: Array,
      default: () => [],
    },
    navigation: {
      type: Object,
    },
  },
  methods: {
    goToShareList() {
      this.navigation.navigate("ShareList");
    },
    goToRecieve() {
      this.navigation.navigate("Recieve");
    },
  },
};
</script>