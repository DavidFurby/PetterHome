<template>
  <nb-container>
    <nb-content padder>
      <nb-text>{{ user.name }}</nb-text>
      <nb-text>Email: {{ user.email }}</nb-text>
      <nb-text>Account ID: {{ user.id }}</nb-text>
      <nb-text>Phone Number: {{ user.phone }}</nb-text>
      <nb-text>Region: {{ user.region }}</nb-text>
      <nb-button><nb-text>Change password</nb-text></nb-button>
      <nb-button><nb-text>Log out</nb-text></nb-button>
    </nb-content>
  </nb-container>
</template>

<script>
export default {
  props: {
    user: {
      type: Array,
      default: () => [],
    },
  },
};
</script>