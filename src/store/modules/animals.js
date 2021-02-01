import Vue from "vue-native-core";
import axios from "axios";
import { Platform } from "react-native";
import axiosInstance from "../../services/axios";


export default {
  namespaced: true,

  state: {
    animals: [],
  },
  getters: {},
  actions: {
    fetchAnimals({ commit, state }) {
      return axiosInstance
        .get(`/user/getAllAnimals`)
        .then((res) => {
          const animals = res.data;
          commit("setAnimals", animals);
          return state.animals;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchAnimalById({ commit, state }, animalId) {
      commit("setAnimals", {});
      return axiosInstance.get(`${BASE_URL}/animals/${animalId}`).then((res) => {
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
