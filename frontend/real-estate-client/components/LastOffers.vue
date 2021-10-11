<template>
  <v-container fluid>
    <div class="mb-2 mt-2 text-center text-h5">Ostatnie oferty</div>
    <v-row align="baseline">
      <v-col
        v-for="item in items"
        :key="item.id"
        class="d-flex justify-center"
        cols="12"
        sm="4"
        lg="3"
      >
        <OfferTile :offer="item" />
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
