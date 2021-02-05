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
          console.log(err);
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
  },
};
