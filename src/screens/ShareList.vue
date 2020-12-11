<template>
  <nb-component>
    <nb-header><nb-title>Share List</nb-title></nb-header>
    <nb-text>{{ pet.name }} share page</nb-text>
    <nb-card v-for="user in users" :key="user.id">
      <nb-card-item button :on-press="goToPetScreen">
        <nb-body full info>
          <nb-text>{{ user.name }} </nb-text>
          <nb-button><nb-text>Delete</nb-text></nb-button>
        </nb-body>
      </nb-card-item>
    </nb-card>
    <nb-button><nb-text>Add new user</nb-text></nb-button>
  </nb-component>
</template>

<script>
export default {
  props: {
    pet: {
      type: Array,
      default: () => {},
    },
    users: {
      type: Array,
      default: () => {},
    },
  },
};
</script>

<style>
</style>