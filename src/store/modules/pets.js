import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    pets: [],
    pet: {},
  },
  getters: {},
  actions: {
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
      return axiosInstance.post(`/user/addPetToUser?userId=${userId}`, petData);
    },
    addNeedToPet(context, params) {
      let needForm = params.need;
      let userId = params.userId;
      let petId = params.petId;
      return axiosInstance.post(
        `/user/addNeedToPet?userId=${userId}&petId=${petId}`,
        needForm
      );
    },
    changePassword(context, params) {
      const userId = params.userId;
      const passwordRequest = params.passwordForm;
      return axiosInstance.put(
        `/user/changePassword?userId=${userId}`,
        passwordRequest
      );
    },
    sendInvite(context, params) {
      let petId = params.petId;
      let userId = params.userId;
      let username = params.username; 
      return axiosInstance.post(`/user/sendInvite?petId=${petId}&userId=${userId}`, username)
    },
    fetchPetById({ commit, state }, params) {
      const petId = params.petId;
      const userId = params.userId;
      commit("setPet", {});
      return axiosInstance
        .get(`/user/getPetById?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const pet = res.data;
          commit("setPet", pet);
          return state.pet;
        })
        .catch((error) => {
          console.log(error);
        });
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
