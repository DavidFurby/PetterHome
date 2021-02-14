<template>
  <nb-content>
    <nb-card v-for="user in sharedWith" :key="user.id">
      <nb-card-item>
        <nb-body full info>
          <nb-text>{{ user.username }} </nb-text>
          <nb-text>{{ user.email }} </nb-text>
        </nb-body>
        <nb-button :on-press="() => deleteReceivedUser(user.id)" danger
          ><nb-text class="text">Delete</nb-text></nb-button
        >
      </nb-card-item>
    </nb-card>
  </nb-content>
</template>

<script>
export default {
  props: {
    sharedWith: {
      type: Array,
      default: () => [],
    },
    deleteReceivedUser: {
      type: Function,
    },
  },
};
</script>

<style>
.text {
  font-family: ArchitectsDaughter;
}
</style>