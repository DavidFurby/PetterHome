<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <InputWithError
          :error="$v.needForm.type.$dirty && !$v.needForm.type.required"
          msg="Must select a type"
        >
          <nb-item stackedLabel>
            <nb-label>Name of the pets need</nb-label>
            <nb-input
              placeholder="type"
              v-model="needForm.type"
              :on-blur="() => $v.needForm.type.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-list-item stackedLabel>
          <nb-checkbox
            :checked="needForm.notified"
            :on-press="setNotification"
          />
          <nb-label> Do you want to be notified about this need?</nb-label>
        </nb-list-item>
      </nb-form>

      <nb-form>
        <nb-item stackedLabel>
          <nb-label>Time of day</nb-label>
          <AppTimePicker :onValueChange="(time) => setTime(time, 'time')" />
        </nb-item>

        <nb-item stackedLabel>
          <nb-label>Assign time to user</nb-label>
          <nb-picker
            note
            mode="dropdown"
            :style="{ width: 120 }"
            :selectedValue="selectedUser"
            :onValueChange="onUserChange"
          >
            <item
              v-for="(availableUser, availableUserIndex) in availableUsers"
              :key="availableUserIndex"
              :label="availableUser"
              :value="availableUser"
            />
          </nb-picker>
        </nb-item>
        <!-- <nb-list-item>
          <nb-text>Medication ?</nb-text>
          <nb-body> </nb-body>
          <nb-checkbox
            :on-press="setMedication"
            :checked="needForm.medication"
          />
        </nb-list-item>
        <Medication
          v-if="ifMedication"
          :medicationName="$v.needForm.medicationName"
          :dosage="$v.needForm.dosage"
        />
        <DogForm v-if="ifDog" />
        <nb-button block :on-press="addPet">
          <nb-text>Add Pet</nb-text> 
        </nb-button>-->
      </nb-form>
      <nb-button block :on-press="addPetNeed">
        <nb-text>Add Need </nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>
<script>
import React from "react";
import { Picker, Icon } from "native-base";
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item },
  data() {
    return {
      breedSelection: null,
      needForm: {
        type: null,
        notified: false,
      },
      scheduleForm: {
        time: "00:00",
        assignedTo: this.availableUsers[0],
      },
    };
  },
  props: {
    availableUsers: {
      type: Array,
    },
    user: {
      type: Object,
    },
    petId: {
      type: String,
    },
  },
  validations: {
    needForm: {
      type: {
        required,
      },
    },
    scheduleForm: {
      time: {
        required,
      },
    },
  },
  methods: {
    ontypeChange(typeValue) {
      this.needForm.type = typeValue;
    },
    onBreedChange(breedValue) {
      this.breedSelection = breedValue;
    },
    onUserChange(userValue) {
      this.assignedTo = userValue;
    },
    getIosIcon() {
      return <Icon name="ios-arrow-down-outline" />;
    },
    setMedication() {
      this.needForm.medication = !this.needForm.medication;
    },
    addPetNeed() {
      this.$v.needForm.$touch();
      this.$v.scheduleForm.$touch();
      if (!this.$v.needForm.$invalid || !this.$v.scheduleForm.$invalid) {
        let needForm = this.needForm;
        let scheduleForm = this.scheduleForm;
        let need = {};
        let schedule = [];
        schedule.push(scheduleForm);
        need.type = needForm.type;
        need.notified = needForm.notified;
        need.schedule = schedule;
        let userId = this.user.id;
        let petId = this.petId;
        let params = { need, userId, petId };
        this.$store
          .dispatch("pets/addNeedToPet", params)
          .then(() => this.navigateToMain())
          .catch(() => {
            Toast.show({
              text: "Something went wrong",
              buttonText: "okay",
              type: "danger",
              duration: 3000,
            });
          });
      } else {
        return alert("something went wrong");
      }
    },
    navigateToMain() {
      this.navigation.navigate("Schema", {
        message: "Succesfully added new need for pet!",
      });
    },
    setTime(time, label) {
      console.log(time);
      console.log(label);
      this.scheduleForm[label] = time;
    },
    setNotification() {
      this.needForm.notified = !this.needForm.notified;
    },
  },
  computed: {
    selectedUser() {
      return this.scheduleForm.assignedTo;
    },
  },
};
</script>