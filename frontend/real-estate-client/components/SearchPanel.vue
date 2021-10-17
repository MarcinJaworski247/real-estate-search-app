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
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select
          v-model="townId"
          label="Miasto"
          :items="towns"
          item-text="name"
          item-value="id"
          clearable
        ></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-text-field
          v-model="area"
          type="number"
          label="Powierzchnia"
          suffix="m2"
        ></v-text-field>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-btn fab large>
          <v-icon>search</v-icon>
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapActions } from "vuex";

export default {
  name: "SearchPanel",
  data() {
    return {
      categoryId: "",
      priceFrom: "",
      priceTo: "",
      townId: "",
      area: "",
      categories: [
        {
          id: 1,
          name: "Pokój",
        },
        {
          id: 2,
          name: "Mieszkanie",
        },
        {
          id: 3,
          name: "Dom",
        },
        {
          id: 4,
          name: "Garaż",
        },
        {
          id: 5,
          name: "Apartament",
        },
      ],
      towns: [
        {
          id: 1,
          name: "Warszawa",
        },
        {
          id: 2,
          name: "Wrocław",
        },
        {
          id: 3,
          name: "Kraków",
        },
        {
          id: 4,
          name: "Gdańsk",
        },
        {
          id: 5,
          name: "Łódź",
        },
      ],
    };
  },
  computed: {
    ...mapActions(["getCategoriesToSelect", "getTownsToSelect"]),
  },
  mounted() {
    // this.getCategoriesToSelect().then((response) => {
    //   this.categories = response.data;
    // });
    // this.getTownsToSelect().then((response) => {
    //   this.towns = response.data;
    // });
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
