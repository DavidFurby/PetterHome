import Vue from "vue-native-core";
import axios from "axios";
import { Platform } from "react-native";

const BASE_URL =
  Platform.OS === "ios" ? "http://192.168.10.206:8080" : "http://10.0.2.2:8080";

export default {
  namespaced: true,

  state: {
    items: [],
  },
  getters: {},
  actions: {
    fetchAnimals({ commit, state }) {
      commit("setAnimal", {});
      return axios
        .get(`${BASE_URL}/animals`)
        .then(async (res) => {
          const animals = await res.data;
          commit(
            "setItems",
            { items: animals, resource: "animals" },
            { root: true }
          );
          return state.items;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchAnimalById({ commit, state }, animalId) {
      commit("setAnimal", {});
      return axios.get(`${BASE_URL}/animals/${animalId}`).then((res) => {
        const animal = res.data;
        commit("setAnimal", animal);
        return state.item;
      });
    },
  },
  mutations: {
    setAnimals(state, animal) {
      Vue.set(state, "item", animal);
    },
  },
};
