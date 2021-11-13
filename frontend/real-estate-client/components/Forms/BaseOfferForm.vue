<template>
  <div>
    <v-text-field
      v-model="offerForm.title"
      :rules="[rules.required]"
      label="Tytuł"
    ></v-text-field>
    <v-textarea
      v-model="offerForm.description"
      :rules="[rules.required]"
      label="Opis"
    ></v-textarea>
    <v-row>
      <v-col>
        <v-text-field
          v-model="offerForm.size"
          type="number"
          label="Powierzchnia"
          suffix="m2"
          :rules="[rules.required]"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="offerForm.price"
          required
          type="number"
          suffix="zł"
          label="Cena"
          :rules="[rules.required]"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-select
          ref="category"
          v-model="offerForm.category"
          label="Kategoria"
          :items="categories"
          item-text="name"
          item-value="id"
          :rules="[rules.required]"
          clearable
        ></v-select>
      </v-col>
      <v-col>
        <v-select
          v-model="offerForm.offerType"
          label="Typ"
          :items="types"
          item-text="name"
          item-value="id"
          :rules="[rules.required]"
          clearable
        ></v-select>
      </v-col>
    </v-row>
    <v-select
      v-model="offerForm.town"
      label="Miasto"
      :items="towns"
      item-text="name"
      item-value="id"
      clearable
    ></v-select>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "BaseOfferForm",
  data() {
    return {
      towns: [],
      categories: [],
      types: [],
      rules: {
        required: (value) => !!value || "Pole wymagane",
      },
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
    this.$store.dispatch("getTownsToSelect").then((response) => {
      this.towns = response;
    });
    this.$store.dispatch("getCategoriesToSelect").then((response) => {
      this.categories = response;
    });
    this.$store.dispatch("getTypesToSelect").then((response) => {
      this.types = response;
    });
  },
};
</script>
