import Vuex from "vuex";
import Vue from "vue-native-core";
import pets from "./modules/pets";
import animals from "./modules/animals";
import invites from "./modules/invites";
import auth from "./modules/auth";
import sharedWith from "./modules/sharedWith";
import receivedPets from "./modules/receivedPets";
import notifications from "./modules/notifications";
import Vuelidate from "vuelidate";

Vue.use(Vuex);
Vue.use(Vuelidate);

export default new Vuex.Store({
  modules: {
    pets,
    animals,
    auth,
    invites,
    sharedWith,
    receivedPets,
    notifications,
  },
  state: {},
  getters: {},
  actions: {},
  mutations: {
    setItem(state, { item, resource }) {
      Vue.set(state[resource], "item", item);
    },
    setItems(state, { items, resource }) {
      Vue.set(state[resource], "items", items);
    },
  },
});
