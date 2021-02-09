import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    receivedPets: [],
    receivedPet: {},
  },
  getters: {},
  actions: {
    fetchReceivedPets({ commit, state }, userId) {
      return axiosInstance
        .get(`/user/getReceivedPets?userId=${userId}`)
        .then((res) => {
          const receivedPets = res.data;
          commit("setReceivedPets", receivedPets);
          return state.receivedPets;
        })
        .catch((err) => {
          console.log(err, "err");
        });
    },
    fetchReceivedPetById({ rootState, commit, state }, params) {
      console.log(params);
      const petId = params.petId;
      const userId = rootState.auth.user.id;
      return axiosInstance
        .get(`/user/getReceivedPetById?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const receivedPet = res.data;
          commit("setReceivedPet", receivedPet);
          return state.receivedPet;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteReceivedPetFromUser({ rootState, commit, state }, petId) {
      const userId = rootState.auth.user.id;
      console.log(petId, "petId")
      return axiosInstance
        .delete(`/user/deleteReceivedPetFromUser?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const petId = res.data.objectId;
          commit("deleteReceivedPet", petId);
          return res.data.message;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mutations: {
    setReceivedPets(state, receivedPets) {
      Vue.set(state, "receivedPets", receivedPets);
    },
    setReceivedPet(state, receivedPet) {
      Vue.set(state, "receivedPet", receivedPet);
    },
    deleteReceivedPet(state, petId) {
      let newPetArr = [];
      state.receivedPets.filter((filterPet) => {
        if (filterPet.pet.id != petId) {
          newPetArr.push(filterPet);
        }
      });
      state.receivedPets = newPetArr;
    },
  },
};
