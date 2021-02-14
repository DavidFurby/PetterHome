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
      let petData = petForm;
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
          const petId = res.data.objectId;
          commit("deletePet", petId);
          return res.data.msg;
        })
        .catch((err) => {
          console.log(err, "error");
        });
    },
    deleteNeed({ rootState, commit }, params) {
      const petId = params.petId;
      const needId = params.needId;
      const userId = rootState.auth.user.id;

      return axiosInstance
        .delete(
          `/user/deleteNeed?userId=${userId}&petId=${petId}&needId=${needId}`
        )
        .then((res) => {
          return res.data.message;
        });
    },
    deleteSchedule({ rootState, commit }, params) {
      const petId = params.petId;
      const needId = params.needId;
      const scheduleId = params.scheduleId;
      const userId = rootState.auth.user.id;
      return axiosInstance
        .delete(
          `/user/deleteSchedule?userId=${userId}&petId=${petId}&needId=${needId}&scheduleId=${scheduleId}`
        )
        .then((res) => {
          return res.data.message;
        });
    },
    addNeedToPet({ rootState }, params) {
      let needForm = params.need;
      let userId = rootState.auth.user.id;
      let petId = params.petId;
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
    updateUserPet({ rootState, commit }, params) {
      const petId = params.petId;
      const petForm = params.petForm;
      const userId = rootState.auth.user.id;
      return axiosInstance
        .put(`/user/updateUserPet?petId=${petId}&userId=${userId}`, petForm)
        .then((res) => {
          pet = res.data.pet;
          message = res.data.msg;
          commit("updatePet", pet);
          return message;
        });
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
          console.log(error, "err");
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
    deletePet(state, petId) {
      let newPetArr = [];
      state.items.filter((filterPet) => {
        if (filterPet.id != petId) {
          newPetArr.push(filterPet);
        }
      });
      state.items = newPetArr;
    },
    updatePet(state, pet) {
      let newPetArr = [];
      state.items.map((newPet) => {
        if (newPet.id == pet.id) {
          newPet = pet;
          newPetArr.push(newPet);
        } else if (newPet.id != pet.id) {
          newPetArr.push(newPet);
        }
      });
      state.items = newPetArr;
    },
  },
};
