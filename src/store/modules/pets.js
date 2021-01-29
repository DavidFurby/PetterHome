import Vue from "vue-native-core";
import axios from "axios";
import { Platform } from "react-native";
import axiosInstance from "../../services/axios";
const BASE_URL =
  Platform.OS === "ios"
    ? "http://192.168.10.206:8080"
    : "http://192.168.10.206:8080";

export default {
  namespaced: true,

  state: {
    pets: [],
    pet: {}
  },
  getters: {},
  actions: {
    fetchSecret() {
      return axiosInstance
        .get(`/user/pets`)
        .then((res) => {
          const data = res.data;
          alert(JSON.stringify(data));
        })
        .catch(() => alert("Not Authorized"));
    },
    fetchPets({ commit, state }) {
      return axiosInstance
        .get(`/user/getAllPets`)
        .then((res) => {
          const pets = res.data;
          commit("setPets", pets);
          return state.pets;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addPetToUser(context, params) {
      let petData = params.petForm; 
      let userId = params.userId.user;
      return axiosInstance.post(`/user/addPetToUser?userId=${userId}`, petData).then((res) => {
      }).catch((err) => {
      });
    },
    fetchPetById({ commit, state }, params) {
      const petId = params.petId;
      const userId = params.userId; 
      commit("setPet", {});
      return axiosInstance.get(`/user/getPetById?userId=${userId}&petId=${petId}`).then((res) => {
        const pet = res.data;
        commit("setPet", pet);
        return state.pet;
      }).catch((error) => {
console.log(error)
      })
    },
  },
  mutations: {
    setPets(state, pets) {
      Vue.set(state, "pets", pets);
    },
    setPet(state, pet) {
      Vue.set(state, "pet", pet);
    },
  },
};
