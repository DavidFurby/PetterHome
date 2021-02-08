import Vue from "vue-native-core";
import axiosInstance from "../../services/axios";

export default {
  namespaced: true,

  state: {
    items: [],
  },
  getters: {},
  actions: {
    fetchAnimals({ commit, state }) {
      return axiosInstance
        .get(`/user/getAllAnimals`)
        .then((res) => {
          const animals = res.data;
          commit(
            "setItems",
            { items: animals, resource: "animals" },
            { root: true }
          );
          return state.animals;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchAnimalById({ commit, state }, animalId) {
      commit("setAnimals", {});
      return axiosInstance.get(`/user/${animalId}`).then((res) => {
        const animal = res.data;
        commit("setAnimals", animal);
        return state.item;
      });
    },
  },
  mutations: {
    setAnimals(state, animals) {
      Vue.set(state, "animals", animals);
    },
  },
};
