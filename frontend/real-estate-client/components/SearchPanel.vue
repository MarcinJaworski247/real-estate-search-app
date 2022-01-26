<template>
  <v-container class="search" fluid>
    <div class="text-h5">Szukaj</div>
    <v-row align="center">
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select
          v-model="categoryId"
          label="Kategoria"
          :items="categories"
          item-text="name"
          item-value="id"
          clearable
        ></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select
          v-model="townId"
          label="Miasto"
          :items="towns"
          item-text="city"
          item-value="id"
          clearable
        ></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select
          v-model="offerType"
          label="Rodzaj oferty"
          :items="offerTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-text-field
          v-model="priceFrom"
          type="number"
          label="Cena od"
          suffix="zł"
        ></v-text-field>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-text-field
          v-model="priceTo"
          type="number"
          label="Cena do"
          suffix="zł"
        ></v-text-field>
      </v-col>
      <v-col
        class="d-flex justify-center"
        cols="12"
        sm="2"
        @click="goToSearchResults"
      >
        <v-btn fab medium>
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">search</v-icon>
            </template>
            <span>Szukaj</span>
          </v-tooltip>
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "SearchPanel",
  data() {
    return {
      categoryId: null,
      priceFrom: null,
      priceTo: null,
      townId: null,
      offerType: null,
      categories: [],
      towns: [],
      offerTypes: [],
    };
  },
  mounted() {
    this.$store.dispatch("getCategoriesToSelect").then((response) => {
      this.categories = response;
    });
    this.$store.dispatch("getTownsToSelect").then((response) => {
      this.towns = response;
    });
    this.$store.dispatch("getTypesToSelect").then((response) => {
      this.offerTypes = response;
    });
  },
  methods: {
    goToSearchResults() {
      if (!this.priceFrom) {
        this.priceFrom = 0;
      }
      if (!this.priceTo) {
        this.priceTo = 0;
      }
      if (!this.categoryId) {
        this.categoryId = 0;
      }
      if (!this.townId) {
        this.townId = 0;
      }
      if (!this.offerType) {
        this.offerType = 0;
      }
      this.$router.push(
        `/searchResults/${this.categoryId}/${this.townId}/${this.offerType}/${this.priceFrom}/${this.priceTo}`
      );
    },
  },
};
</script>
<style scoped>
.search {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
