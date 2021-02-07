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
    addPetToUser({ rootState, commit }, params) {
      let petData = params.petForm;
      let userId = rootState.auth.user.id;
      return axiosInstance
        .post(`/user/addPetToUser?userId=${userId}`, petData)
        .then((res) => {
          const pet = res.data;
          commit("addPet", pet);
          return pet;
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    deletePetFromUser({ rootState }, params) {
      let petId = params.petId;
      let userId = rootState.auth.user.id;
      return axiosInstance
        .delete(`/user/deletePetFromUser?userId=${userId}&petId=${petId}`)
        .then((res) => {
          console.log(res, "success");
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    addNeedToPet({ rootState }, params) {
      let needForm = params.need;
      let userId = rootState.auth.user.id;
      let petId = params.petId;
      console.log(params);
      return axiosInstance
        .post(`/user/addNeedToPet?userId=${userId}&petId=${petId}`, needForm)
        .then((res) => {
          return res.data.message;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addScheduleToNeed({ rootState }, params) {
      let schedule = params.schedule;
      let userId = rootState.auth.user.id;
      let petId = params.petId;
      let needId = params.needId;
      return axiosInstance
        .post(
          `/user/addScheduleToNeed?userId=${userId}&petId=${petId}&needId=${needId}`,
          schedule
        )
        .then((res) => {
          return res.data.message;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changePassword(context, params) {
      const userId = params.userId;
      const passwordRequest = params.passwordForm;
      return axiosInstance.put(
        `/user/changePassword?userId=${userId}`,
        passwordRequest
      );
    },
    fetchPetById({ rootState, commit, state }, params) {
      const petId = params.petId;
      const userId = rootState.auth.user.id;
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
    addPet(state, pet) {
      state.pets.unshift(pet);
      console.log(state.pets);
    },
  },
};
