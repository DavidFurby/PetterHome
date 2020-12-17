<template>
  <nb-container>
    <nb-header><nb-title>Invites</nb-title></nb-header>
    <nb-text>Recieve Page</nb-text>
    <nb-card-item v-for="request in requests" :key="request.id">
      <nb-body full info>
        <nb-text
          >{{ request.user }} want to share their {{ request.pet }}
        </nb-text>
        <nb-button><nb-text>Accept</nb-text></nb-button>
      </nb-body>
    </nb-card-item>
  </nb-container>
</template>
<script>
export default {
  props: {
    requests: {
      type: Array,
      default: () => [],
    },
  },
};
</script>