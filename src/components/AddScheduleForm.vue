<template>
  <nb-container>
    <scroll-view>
      <nb-form>
        <InputWithError
          :error="$v.needForm.type.$dirty && !$v.needForm.type.required"
          msg="Must select a type"
        >
          <nb-item stackedLabel
            ><nb-input
              placeholder="type"
              v-model="needForm.type"
              :on-blur="() => $v.needForm.type.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input placeholder="Notified" v-model="needForm.notified"
        /></nb-item>
      </nb-form>

      <nb-form>
        <InputWithError
          :error="$v.scheduleForm.time.$dirty && !$v.scheduleForm.time.required"
          msg="A time must be given for the schedule"
        >
          <nb-item stackedLabel
            ><nb-input
              placeholder="Time"
              v-model="scheduleForm.time"
              :on-blur="() => $v.scheduleForm.time.$touch()"
          /></nb-item>
        </InputWithError>

        <nb-item stackedLabel
          ><nb-input
            placeholder="Assigned to"
            v-model="scheduleForm.assignedTo"
        /></nb-item>

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
        <nb-text>Add PetNeed</nb-text>
      </nb-button>
    </scroll-view>
  </nb-container>
</template>
<script>
import React from "react";
import { Picker, Icon } from "native-base";
import DogForm from "./DogForm";
import Medication from "./Medication";
import { required } from "vuelidate/lib/validators";
import { Toast } from "native-base";

export default {
  components: { Item: Picker.Item, DogForm, Medication },
  data() {
    return {
      breedSelection: "",
      needForm: {
        type: "",
        notified: "false",
      },
      scheduleForm: {
        time: "",
        assignedTo: "",
      },
    };
  },
  props: {
    user: {
      type: Object,
    },
    pet: {
      type: Object,
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
        let petId = this.pet.id;
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
  },
};
</script>