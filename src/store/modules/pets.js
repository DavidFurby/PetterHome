import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    items: [],
    item: {},
  },
  getters: {},
  actions: {
    fetchPets({ commit, state }, userId) {
      return axiosInstance
        .get(`/user/getAllPets?userId=${userId}`)
        .then((res) => {
          const pets = res.data;
          commit("setItems", { items: pets, resource: "pets" }, { root: true });
          return state.items;
        });
    },
    addPetToUser({ rootState, commit }, petForm) {
      let petData = petForm
      let userId = rootState.auth.user.id;
      return axiosInstance
        .post(`/user/addPetToUser?userId=${userId}`, petData)
        .then((res) => {
          const pet = res.data.pet;
          commit("addPet", pet);
          return res.data.message;
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    deletePetFromUser({ rootState, commit }, petId) {
      const userId = rootState.auth.user.id;
      return axiosInstance
        .delete(`/user/deletePetFromUser?userId=${userId}&petId=${petId}`)
        .then((res) => {
          const pet = res.data.objectId;
          commit("deletePet", pet);
          return res.data.message;
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
          return state.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mutations: {
    setPet(state, pet) {
      Vue.set(state, "item", pet);
    },
    addPet(state, pet) {
      state.items.push(pet);
    },
    deletePet(state, pet) {
      let newPetArr = [];
      state.items.filter((filterPet) => {
        if (filterPet.id !== pet) {
          console.log(filterPet);
          newPetArr.add(filterPet);
        }
      });
      state.items === newPetArr;
      console.log(state.items);
    },
  },
};
