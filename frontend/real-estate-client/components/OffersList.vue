<template>
  <v-container fluid>
    <v-row align="baseline">
      <v-col
        v-for="item in offers"
        :key="item.id"
        class="d-flex justify-center"
      >
        <OfferTile :offer="item" />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import OfferTile from "./OfferTile.vue";
export default {
  name: "OffersList",
  components: {
    OfferTile,
  },
  props: {
    offers: {
      type: Array,
      required: false,
    },
  },
};
</script>
