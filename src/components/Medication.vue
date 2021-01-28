<template>
<view>
      <InputWithError :error="medicationName.$dirty && medicationName.required"
          msg="Name of medication must be given">
      <nb-item
        ><nb-input placeholder="Medication"></nb-item>
      </InputWithError>
       <InputWithError :error="dosage.$dirty && dosage.required"
          msg="Select amount ">
      <nb-item
        ><nb-input placeholder="Dosage amount"
      /></nb-item>
            </InputWithError>
      </view>
</template>

<script>
export default {
  props: {
    medicationName: {
      type: Object,
    },
    dosage: {
      type: Object,
    },
  },
};
</script>