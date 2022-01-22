<template>
  <v-container fluid class="box">
    <div class="text-h5 mb-2">Oferty</div>
    <v-row justify-center>
      <v-col cols="12" sm="2">
        <v-select
          v-model="searchParams.category"
          label="Kategoria"
          :items="categories"
          clearable
          @input="refresh"
        />
      </v-col>
      <v-col cols="12" sm="2">
        <v-select
          v-model="searchParams.localizationId"
          label="Lokalizacja"
          :items="localizations"
          item-text="city"
          item-value="id"
          clearable
          @input="refresh"
        />
      </v-col>
      <v-col cols="12" sm="2">
        <v-select
          v-model="searchParams.offerType"
          label="Rodzaj oferty"
          :items="offerTypes"
          clearable
          @input="refresh"
        />
      </v-col>
      <v-col cols="12" sm="2">
        <v-text-field
          v-model="searchParams.priceFrom"
          type="number"
          label="Cena od"
          suffix="zł"
          @input="refresh"
        ></v-text-field>
      </v-col>
      <v-col cols="12" sm="2">
        <v-text-field
          v-model="searchParams.priceTo"
          type="number"
          label="Cena do"
          suffix="zł"
          @input="refresh"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row align-center>
      <v-col cols="12" sm="6" lg="6" align-center
        >Liczba wyników: {{ count }}
      </v-col>
      <v-col cols="12" sm="6" lg="6" class="d-flex justify-end" align-center>
        <v-select
          v-model="sortingType"
          label="Sortowanie"
          :items="sortingTypes"
          width="300"
          @input="sort"
        />
      </v-col>
    </v-row>
    <div style="display: flex; justify-content: center">
      <Loader v-if="loading" />
    </div>
    <OffersList v-if="!loading" :offers="offers" />
  </v-container>
</template>
<script>
import OffersList from "@/components/OffersList";
import Loader from "@/components/Loader";

export default {
  name: "SearchResults",
  components: {
    OffersList,
    Loader,
  },
  data() {
    return {
      offers: null,
      categories: [],
      localizations: [],
      offerTypes: [],
      searchParams: {
        category: null,
        localizationId: null,
        offerType: null,
        priceFrom: null,
        priceTo: null,
      },
      sortingType: "Od najnowszych",
      sortingTypes: [
        "Od najnowszych",
        "Od najstarszych",
        "Od najtańszych",
        "Od najdroższych",
      ],
      timeoutId: null,
      loading: false,
    };
  },
  computed: {
    count() {
      if (!this.offers) return 0;
      return this.offers.length;
    },
  },
  async mounted() {
    // get filters from url
    this.searchParams.category = this.$route.params.categoryId;
    this.searchParams.localizationId = +this.$route.params.localizationId;
    this.searchParams.offerType = this.$route.params.offerType;
    this.searchParams.priceFrom =
      this.$route.params.priceFrom === "0"
        ? null
        : this.$route.params.priceFrom;
    this.searchParams.priceTo =
      this.$route.params.priceTo === "0" ? null : this.$route.params.priceTo;

    this.$store.dispatch("getCategoriesToSelect").then((response) => {
      this.categories = response;
    });
    this.$store.dispatch("getTownsToSelect").then((response) => {
      this.localizations = response;
    });
    this.$store.dispatch("getTypesToSelect").then((response) => {
      this.offerTypes = response;
    });
    this.loading = true;
    await this.$store.dispatch("getAllOffers").then((response) => {
      if (response._embedded && response._embedded.realEstateResourceList)
        this.offers = response._embedded.realEstateResourceList;
    });
    this.loading = false;
  },
  methods: {
    refresh() {
      if (this.timeoutId) {
        window.clearTimeout(this.timeoutId);
      }
      this.loading = true;
      this.timeoutId = window.setTimeout(() => {
        this.$store
          .dispatch("searchOffers", this.searchParams)
          .then((response) => {
            if (
              response._embedded &&
              response._embedded.realEstateResourceList
            ) {
              this.offers = response._embedded.realEstateResourceList;
            } else {
              this.offers = null;
            }
          });
      }, 1000);
      this.loading = false;
    },
    sort() {
      switch (this.sortingType) {
        case "Od najnowszych":
          this.offers.sort((a, b) => {
            if (a.createdAt < b.createdAt) return -1;
            if (a.createdAt > b.createdAt) return 1;
            return 0;
          });
          break;
        case "Od najstarszych":
          this.offers.sort((a, b) => {
            if (a.createdAt > b.createdAt) return -1;
            if (a.createdAt < b.createdAt) return 1;
            return 0;
          });
          break;
        case "Od najtańszych":
          this.offers.sort((a, b) => {
            if (a.price < b.price) return -1;
            if (a.price > b.price) return 1;
            return 0;
          });
          break;
        case "Od najdroższych":
          this.offers.sort((a, b) => {
            if (a.price > b.price) return -1;
            if (a.price < b.price) return 1;
            return 0;
          });
          break;
      }
    },
  },
};
</script>
<style scoped>
.box {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  width: 67%;
}
</style>
