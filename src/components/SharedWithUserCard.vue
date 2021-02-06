<template>
  <nb-content>
      <nb-card v-for="user in sharedWith" :key="user.id">
        <nb-card-item button>
          <nb-body full info>
            <nb-text>{{ user.username }} </nb-text>
            <nb-text>{{ user.email }} </nb-text>
          </nb-body>
          <nb-button><nb-text>Delete</nb-text></nb-button>
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
  },
};
</script>

<style>
</style>