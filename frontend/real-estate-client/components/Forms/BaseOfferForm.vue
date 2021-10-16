<template>
  <div>
    <v-text-field
      v-model="offerForm.title"
      required
      label="Tytuł"
    ></v-text-field>
    <v-textarea
      v-model="offerForm.description"
      required
      label="Opis"
    ></v-textarea>
    <v-row>
      <v-col>
        <v-text-field
          v-model="offerForm.area"
          type="number"
          label="Powierzchnia"
          suffix="m2"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="offerForm.price"
          required
          type="number"
          suffix="zł"
          label="Cena"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-select
          ref="category"
          v-model="offerForm.categoryId"
          label="Kategoria"
          :items="categories"
          item-text="name"
          item-value="id"
          clearable
          @change="onCategoryChange()"
        ></v-select>
      </v-col>
      <v-col>
        <v-select
          v-model="offerForm.typeId"
          label="Typ"
          :items="types"
          item-text="name"
          item-value="id"
          clearable
        ></v-select>
      </v-col>
    </v-row>
    <v-select
      v-model="offerForm.townId"
      label="Miasto"
      :items="towns"
      item-text="name"
      item-value="id"
      clearable
    ></v-select>
    <v-file-input
      accept="image/*"
      prepend-icon="mdi-camera"
      label="Zdjęcia"
      multiple
      chips
      show-size
    ></v-file-input>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "BaseOfferForm",
  data() {
    return {
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
      types: [
        {
          id: 1,
          name: "Sprzedaż",
        },
        {
          id: 2,
          name: "Wynajem",
        },
      ],
    };
  },
  computed: {
    ...mapState(["offerForm"]),
    ...mapActions([
      "getTownsToSelect",
      "getCategoriesToSelect",
      "getTypesToSelect",
    ]),
  },
  mounted() {
    // this.getTownsToSelect().then((response) => {
    //   this.towns = response.data;
    // });
    // this.getCategoriesToSelect().then((response) => {
    //   this.categories = response.data;
    // });
    // this.getTypesToSelect().then((response) => {
    //   this.types = response.data;
    // });
  },
  methods: {
    onCategoryChange() {
      // TODO
    },
  },
};
</script>
