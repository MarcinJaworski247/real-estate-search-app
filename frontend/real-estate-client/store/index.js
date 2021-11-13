import Vuex from "vuex";

const createStore = () => {
  return new Vuex.Store({
    state: {
      offerForm: {
        id: null,
        title: "",
        description: "",
        category: null,
        price: null,
        size: null,
        furnished: null,
        level: null,
        town: null,
        roomsNumber: null,
        floors: null,
        offerType: null,
        flatType: null,
        plotSize: null,
        houseType: null,
        plotType: null,
        premisePurpose: null,
        avatar: null,
        files: [],
      },
    },
    mutations: {
      RESET_OFFER_FORM(state) {
        state.offerForm.id = null;
        state.offerForm.title = null;
        state.offerForm.description = null;
        state.offerForm.category = null;
        state.offerForm.price = null;
        state.offerForm.size = null;
        state.offerForm.town = null;
        state.offerForm.offerType = null;
        state.offerForm.level = null;
        state.offerForm.roomsNumber = null;
        state.offerForm.furnished = null;
        state.offerForm.flatType = null;
        state.offerForm.plotSize = null;
        state.offerForm.floors = null;
        state.offerForm.houseType = null;
        state.offerForm.plotType = null;
        state.offerForm.premisePurpose = null;
        state.offerForm.avatar = null;
        state.offerForm.files = [];
      },
      SET_OFFER_TO_EDIT(state, response) {
        state.offerForm.id = response.id;
        state.offerForm.title = response.title;
        state.offerForm.description = response.description;
        state.offerForm.category = response.category;
        state.offerForm.price = response.price;
        state.offerForm.size = response.size;
        state.offerForm.town = response.town;
        state.offerForm.offerType = response.offerType;
        state.offerForm.level = response.level;
        state.offerForm.roomsNumber = response.roomsNumber;
        state.offerForm.furnished = response.furnished;
        state.offerForm.flatType = response.flatType;
        state.offerForm.plotSize = response.plotSize;
        state.offerForm.floors = response.floors;
        state.offerForm.houseType = response.houseType;
        state.offerForm.plotType = response.plotType;
        state.offerForm.premisePurpose = response.premisePurpose;
        state.offerForm.avatar = response.avatar;
        state.offerForm.files = response.photos;
      },
      SET_OFFER_PHOTOS(state, response) {
        state.offerForm.files = response;
      },
    },
    getters: {},
    actions: {
      addOffer(vuexContext) {
        vuexContext.state.offerForm.price = +vuexContext.state.offerForm.price;
        vuexContext.state.offerForm.size = +vuexContext.state.offerForm.size;
        vuexContext.state.offerForm.level = +vuexContext.state.offerForm.level;
        vuexContext.state.offerForm.roomsNumber =
          +vuexContext.state.offerForm.roomsNumber;
        vuexContext.state.offerForm.plotSize =
          +vuexContext.state.offerForm.plotSize;
        vuexContext.state.offerForm.floors =
          +vuexContext.state.offerForm.floors;
        return this.$axios.$post(
          "http://localhost:8081/real-estate",
          vuexContext.state.offerForm
        );
      },
      getTownsToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/voivodeship");
      },
      getCategoriesToSelect(vuexContext) {
        return this.$axios.$get(
          "http://localhost:8081/enums/real-estate-category"
        );
      },
      getTypesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/offer-type");
      },
      getFlatTypesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/flat-type");
      },
      getHouseTypesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/house-type");
      },
      getPlotTypesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/plot-type");
      },
      getPremisePurposesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/premises-purpose");
      },
      getRoomTypesToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/enums/room-type");
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
      getUserOffers(vuexContext) {
        return this.$axios.$get("http://localhost:8081/real-estate");
      },
      getOffer(vuexContext, offerId) {
        return this.$axios.$get(`http://localhost:8081/real-estate/${offerId}`);
      },
      deleteOffer(vuexContext, offerId) {
        return this.$axios.$delete(
          `http://localhost:8081/real-estate/${offerId}`
        );
      },
      markAsSold(vuexContext, offerId) {
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${offerId}/set-sold`
        );
      },
      getOfferToEdit(vuexContext, offerId) {
        vuexContext.commit("RESET_OFFER_FORM");
        this.$axios
          .$get(`http://localhost:8081/real-estate/${offerId}`)
          .then((response) => {
            vuexContext.commit("SET_OFFER_TO_EDIT", response);
          });
      },
      editOffer(vuexContext) {
        vuexContext.state.offerForm.price = +vuexContext.state.offerForm.price;
        vuexContext.state.offerForm.size = +vuexContext.state.offerForm.size;
        vuexContext.state.offerForm.level = +vuexContext.state.offerForm.level;
        vuexContext.state.offerForm.roomsNumber =
          +vuexContext.state.offerForm.roomsNumber;
        vuexContext.state.offerForm.plotSize =
          +vuexContext.state.offerForm.plotSize;
        vuexContext.state.offerForm.floors =
          +vuexContext.state.offerForm.floors;
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${vuexContext.state.offerForm.id}`,
          vuexContext.state.offerForm
        );
      },
      uploadPhotos(vuexContext, params) {
        return this.$axios.$post(
          `http://localhost:8081/real-estate/${params.offerId}/files`,
          params.photos
        );
      },
    },
    namespaced: false,
  });
};

export default createStore;
