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
        localizationId: null,
        roomsNumber: null,
        floors: null,
        offerType: null,
        flatType: null,
        plotSize: null,
        houseType: null,
        plotType: null,
        premisesPurpose: null,
        avatar: null,
        files: [],
        basicInfoId: null,
        realEstateId: null,
        rent: null,
      },
      token: null,
      currentUser: {
        id: null,
        name: null,
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
        state.offerForm.localizationId = null;
        state.offerForm.offerType = null;
        state.offerForm.level = null;
        state.offerForm.roomsNumber = null;
        state.offerForm.furnished = null;
        state.offerForm.flatType = null;
        state.offerForm.plotSize = null;
        state.offerForm.floors = null;
        state.offerForm.houseType = null;
        state.offerForm.plotType = null;
        state.offerForm.premisesPurpose = null;
        state.offerForm.avatar = null;
        state.offerForm.files = [];
        state.offerForm.basicInfoId = null;
        state.offerForm.realEstateId = null;
        state.offerForm.rent = null;
        state.offerForm.floorNumber = null;
      },
      SET_OFFER_TO_EDIT(state, response) {
        state.offerForm.id = response.id;
        state.offerForm.title = response.title;
        state.offerForm.description = response.description;
        state.offerForm.category = response.category;
        state.offerForm.price = response.price;
        state.offerForm.size = response.size;
        state.offerForm.localizationId = response.localization.id;
        state.offerForm.offerType = response.offerType;
        state.offerForm.level = response.level;
        state.offerForm.roomsNumber = response.roomsNumber;
        state.offerForm.furnished = response.furnished;
        state.offerForm.flatType = response.flatType;
        state.offerForm.plotSize = response.plotSize;
        state.offerForm.floors = response.floors;
        state.offerForm.houseType = response.houseType;
        state.offerForm.plotType = response.plotType;
        state.offerForm.premisesPurpose = response.premisesPurpose;
        state.offerForm.avatar = response.avatar;
        state.offerForm.files = response.files;
        state.offerForm.basicInfoId = response.basicInfoId;
        state.offerForm.realEstateId = response.realEstateId;
        state.offerForm.rent = response.rent;
        state.offerForm.floorNumber = response.floorNumber;
      },
      SET_OFFER_PHOTOS(state, response) {
        state.offerForm.files = response;
      },
    },
    getters: {
      isAuthenticated(state) {
        return state.token != null;
      },
    },
    actions: {
      getAllOffers(vuexContext) {
        return this.$axios.$get("http://localhost:8081/real-estate", {
          headers: { Authorization: vuexContext.state.token },
        });
      },
      searchOffers(vuexContext, searchParams) {
        return this.$axios.$get(
          "http://localhost:8081/real-estate",

          {
            params: {
              category:
                searchParams.category === "0" || !searchParams.category
                  ? null
                  : searchParams.category,
              offerType:
                searchParams.offerType === "0" || !searchParams.offerType
                  ? null
                  : searchParams.offerType,
              localizationId:
                searchParams.localizationId &&
                searchParams.localizationId !== "0"
                  ? +searchParams.localizationId
                  : null,
              priceFrom:
                searchParams.priceFrom === "0" || !searchParams.priceFrom
                  ? null
                  : +searchParams.priceFrom,
              priceTo:
                searchParams.priceTo === "0" || !searchParams.priceTo
                  ? null
                  : +searchParams.priceTo,
            },
          },
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
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
        vuexContext.state.offerForm.localizationId =
          +vuexContext.state.offerForm.localizationId;
        return this.$axios.$post(
          "http://localhost:8081/real-estate",
          vuexContext.state.offerForm,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: vuexContext.state.token,
            },
          }
        );
      },
      getTownsToSelect(vuexContext) {
        return this.$axios.$get("http://localhost:8081/localizations");
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
      async login(vuexContext, loginForm) {
        await this.$axios
          .$post("http://localhost:8081/login", loginForm)
          .then((response) => {
            vuexContext.state.token = response;
            localStorage.setItem("token", response);
            localStorage.setItem(
              "tokenExpiration",
              new Date().getTime() + 3600 * 1000
            );
            vuexContext.dispatch("getProfileData").then((res) => {
              vuexContext.state.currentUser.id = res.id;
              vuexContext.state.currentUser.name =
                res.firstName + " " + res.lastName;
            });
          })
          .catch((err) => {
            console.log(err);
          });
      },
      getProfileData(vuexContext) {
        return this.$axios.$get("http://localhost:8081/users/current", {
          headers: { Authorization: vuexContext.state.token },
        });
      },
      register(vuexContext, registerForm) {
        return this.$axios.$post("http://localhost:8081/register", {
          username: registerForm.mail,
          password: registerForm.password,
          firstName: registerForm.firstName,
          lastName: registerForm.lastName,
          phoneNumber: registerForm.phoneNumber,
        });
      },
      logOut(vuexContext) {
        vuexContext.state.token = null;
        vuexContext.state.currentUser.id = null;
        vuexContext.state.currentUser.name = null;
        localStorage.removeItem("token");
        localStorage.removeItem("tokenExpiration");
      },
      editProfile(vuexContext, profileForm) {
        return this.$axios.$patch(
          "http://localhost:8081/users/current",
          profileForm,
          { headers: { Authorization: vuexContext.state.token } }
        );
      },
      getUserOffers(vuexContext) {
        return this.$axios.$get(
          "http://localhost:8081/real-estate",
          {
            params: {
              userId: vuexContext.state.currentUser.id,
            },
          },
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      getOffer(vuexContext, params) {
        return this.$axios.$get(
          `http://localhost:8081/real-estate/${params.basicInfoId}/${params.realEstateId}`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      deleteOffer(vuexContext, offerId) {
        return this.$axios.$delete(
          `http://localhost:8081/real-estate/${offerId}`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      markAsSold(vuexContext, offerId) {
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${offerId}/set-sold`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      getOfferToEdit(vuexContext, params) {
        vuexContext.commit("RESET_OFFER_FORM");
        this.$axios
          .$get(
            `http://localhost:8081/real-estate/${params.basicInfoId}/${params.realEstateId}`,
            {
              headers: { Authorization: vuexContext.state.token },
            }
          )
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
        vuexContext.state.offerForm.localizationId =
          +vuexContext.state.offerForm.localizationId;
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${vuexContext.state.offerForm.basicInfoId}/${vuexContext.state.offerForm.realEstateId}`,
          vuexContext.state.offerForm,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      uploadPhotos(vuexContext, params) {
        return this.$axios.$post(
          `http://localhost:8081/real-estate/${params.offerId}/files`,
          params.photos,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      removePhoto(vuexContext, params) {
        return this.$axios.$delete(
          `http://localhost:8081/real-estate/${params.offerId}/files/${params.photoId}`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      async initAuth(vuexContext) {
        if (!vuexContext.state.token) {
          const token = localStorage.getItem("token");
          const expirationDate = localStorage.getItem("tokenExpiration");
          if (token && new Date().getTime() < +expirationDate) {
            vuexContext.state.token = token;
            await this.$axios
              .$get("http://localhost:8081/users/current", {
                headers: { Authorization: vuexContext.state.token },
              })
              .then((response) => {
                vuexContext.state.currentUser.id = response.id;
                vuexContext.state.currentUser.name = response.username;
              });
          } else {
            vuexContext.dispatch("logOut");
            // this.$router.push("/login");
          }
        }
      },
      showPhoneNumber(vuexContext, basicInfoId) {
        return this.$axios.$post(
          `http://localhost:8081/real-estate/${basicInfoId}/phone-views`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      saveToFavourites(vuexContext, basicInfoId) {
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${basicInfoId}/set-favourite`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      removeFromFavourites(vuexContext, basicInfoId) {
        return this.$axios.$patch(
          `http://localhost:8081/real-estate/${basicInfoId}/unset-favourite`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
      incrementViews(vuexContext, basicInfoId) {
        return this.$axios.$post(
          `http://localhost:8081/real-estate/${basicInfoId}/offer-visits`,
          {
            headers: { Authorization: vuexContext.state.token },
          }
        );
      },
    },
    namespaced: false,
  });
};

export default createStore;
