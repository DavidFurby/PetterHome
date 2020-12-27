import Vuex from "vuex";
import Vue from "vue-native-core";
import pets from "./modules/pets";
import animals from "./modules/animals";
import auth from "./modules/auth"
import Vuelidate from "vuelidate";

Vue.use(Vuex);
Vue.use(Vuelidate);

export default new Vuex.Store({
  modules: {
    pets,
    animals,
    auth
  },
  state: {},
  getters: {},
  actions: {},
  mutations: {
    setItems(state, { items }, resources) {
      Vue.set(state[resources], "items", items);
    },
  },
});
