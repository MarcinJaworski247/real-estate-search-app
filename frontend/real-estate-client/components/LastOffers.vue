<template>
  <v-container fluid>
    <h3 class="mb-2 mt-2 text-center">Ostatnie oferty</h3>
    <v-row align="baseline">
      <v-col
        v-for="item in items"
        :key="item.id"
        class="d-flex"
        cols="12"
        sm="3"
        align-self="center"
      >
        <OfferTile class="justify-center" :offer="item" />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import OfferTile from "./OfferTile";
export default {
  name: "LastOffers",
  components: {
    OfferTile,
  },
  data() {
    return {
      items: [
        {
          id: 1,
          price: 3200,
          tags: ["Mieszkanie", "Wynajem"],
          place: "Piotrków Trybunalski",
          avatar: "~/assets/images/apartment.jpg",
        },
        {
          id: 2,
          price: 3200,
          tags: ["Mieszkanie", "Wynajem"],
          place: "Piotrków Trybunalski",
        },
        {
          id: 3,
          price: 3200,
          tags: ["Mieszkanie", "Wynajem"],
          place: "Piotrków Trybunalski",
        },
        {
          id: 4,
          price: 3200,
          tags: ["Mieszkanie", "Wynajem"],
          place: "Piotrków Trybunalski",
        },
      ],
    };
  },
};
</script>
