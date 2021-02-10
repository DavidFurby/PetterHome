<template>
  <nb-card
    ><nb-card-item
      ><nb-text>{{ need.type }} </nb-text>
      <nb-text> Notified: {{ need.notified }}</nb-text>
    </nb-card-item>
    <nb-button :on-press="() => goToAddScheduleScreen(petId, need.id)"
      ><nb-text>Add new schedule</nb-text></nb-button
    >
    <nb-button :on-press="() => deleteNeed(petId, need.id)"
      ><nb-text>Delete Need</nb-text></nb-button
    >
    <nb-card
      v-for="(schedule, scheduleIndex) in need.schedules"
      :key="scheduleIndex"
      ><nb-card-item
        ><nb-body
          ><nb-text
            >{{ schedule.time }} assigned to
            {{ schedule.assignedUser }}
          </nb-text>
        </nb-body>
        <nb-button
          :on-press="() => deleteSchedule(petId, need.id, schedule.id)"
          ><nb-text>Delete Schedule</nb-text></nb-button
        >
      </nb-card-item>
    </nb-card>
  </nb-card>
</template>

<script>
export default {
  props: {
    need: {
      type: Object,
    },
    petId: {
      type: String,
      default: "",
    },
    goToAddScheduleScreen: {
      type: Function,
    },
    deleteNeed: {
      type: Function,
    },
    deleteSchedule: {
      type: Function,
    },
  },
};
</script>

<style>
</style>