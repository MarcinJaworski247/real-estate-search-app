import Vuex from "vuex";

const createStore = () => {
  return new Vuex.Store({
    state: {
      offerForm: {
        title: "",
        description: "",
        categoryId: null,
        price: null,
        area: null,
        townId: null,
        typeId: null,
        floor: null,
        roomsCount: null,
        furnished: null,
        flatTypeId: null,
        plotSize: null,
        floorsCount: null,
        houseTypeId: null,
        plotTypeId: null,
        premisePurposeId: null,
      },
    },
    mutations: {},
    getters: {},
    actions: {
      addOffer(vuexContext) {
        // return this.$axios.$post("/offer", vuexContext.state.offerForm);
      },
      getTownsToSelect(vuexContext) {
        // return this.$axios.$get("/getTowns");
      },
      getCategoriesToSelect(vuexContext) {
        // return this.$axios.$get("/getCategories");
      },
      getTypesToSelect(vuexContext) {
        // return this.$axios.$get("/getTypes");
      },
      getFlatTypesToSelect(vuexContext) {
        // return this.$axios.$get("/getFlatTypes");
      },
      getHouseTypesToSelect(vuexContext) {
        // return this.$axios.$get("/getHouseTypes");
      },
      getPlotTypesToSelect(vuexContext) {
        // return this.$axios.$get("/getPlotTypes");
      },
      getPremisePurposesToSelect(vuexContext) {
        // return this.$axios.$get("/getPremisePurposes");
      },
      getRoomTypesToSelect(vuexContext) {
        // return this.$axios.$get("/getRoomTypes");
      },
      login(vuexContext, loginForm) {
        // TODO
      },
      register(vuexContext, registerForm) {
        // TODO
      },
      editProfile(vuexContext, profileForm) {
        // TODO
      },
    },
    namespaced: false,
  });
};

export default createStore;
