<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <InputWithError
          :error="$v.needForm.type.$dirty && !$v.needForm.type.required"
          msg="Must select a type"
        >
          <nb-item stackedLabel>
            <nb-text>Name of the pets need</nb-text>
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
          <nb-text> Do you want to be notified about this need?</nb-text>
        </nb-list-item>
      </nb-form>

      <nb-form>
        <nb-text>Create schedule</nb-text>
        <nb-item stackedLabel>
          <nb-text>Time of day</nb-text>
          <AppTimePicker :onValueChange="(time) => setTime(time, 'time')" />
        </nb-item>
        <nb-item stackedLabel>
          <nb-text>Assign schedule to user</nb-text>
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
      needForm: {
        type: null,
        notified: false,
      },
      scheduleForm: {
        time: "00:00",
        assignedUser: this.firstAvailableUser,
      },
    };
  },
  props: {
    availableUsers: {
      type: Array,
    },
    firstAvailableUser: {
      type: String,
    },
    username: {
      type: String,
    },
    petId: {
      type: String,
    },
    navigation: {
      type: Object,
    },
    goBack: {
      type: Function,
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
  mounted() {
    console.log(this.thisFirstAvailableUser, "Form");
  },
  methods: {
    ontypeChange(typeValue) {
      this.needForm.type = typeValue;
    },
    onUserChange(userValue) {
      console.log(userValue);
      this.scheduleForm.assignedUser = userValue;
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
        console.log(scheduleForm);
        let need = {};
        let schedule = [];
        schedule.push(scheduleForm);
        need.type = needForm.type;
        need.notified = needForm.notified;
        need.schedule = schedule;
        let petId = this.petId;
        let params = { need, petId };
        this.$store
          .dispatch("pets/addNeedToPet", params)
          .then((res) => {
            if (res == "Pets need added successfully!") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "success",
                duration: 3000,
              });
              this.goBack();
            } else if (
              res == "Error: A name for the pets need must be selected"
            ) {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            } else if (res == "Error: No schedule has been made") {
              Toast.show({
                text: res,
                buttonText: "ok",
                type: "danger",
                duration: 3000,
              });
            }
          })
          .catch((err) => {
            console.log(err);
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
    setTime(time, label) {
      this.scheduleForm[label] = time;
    },
    setNotification() {
      this.needForm.notified = !this.needForm.notified;
    },
  },
  computed: {
    selectedUser() {
      console.log("test");
      return this.scheduleForm.assignedUser;
    },
  },
};
</script>