<template>
  <div>
    <v-row>
      <v-col
        ><v-text-field
          v-model="offerForm.level"
          type="number"
          label="Piętro"
        ></v-text-field
      ></v-col>
      <v-col
        ><v-text-field
          v-model="offerForm.roomsNumber"
          type="number"
          label="Ilość pokojów"
        ></v-text-field
      ></v-col>
      <v-col
        ><v-checkbox
          v-model="offerForm.furnished"
          label="Umeblowane"
        ></v-checkbox
      ></v-col>
    </v-row>

    <v-row>
      <v-col
        ><v-select
          v-model="offerForm.flatType"
          label="Rodzaj zabudowy"
          :items="flatTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
      <v-col
        ><v-text-field
          v-model="offerForm.plotSize"
          type="number"
          label="Powierzchnia działki"
        ></v-text-field
      ></v-col>
      <v-col
        ><v-text-field
          v-model="offerForm.floors"
          type="number"
          label="Ilość pięter"
        ></v-text-field
      ></v-col>
    </v-row>

    <v-row>
      <v-col
        ><v-select
          v-model="offerForm.houseType"
          label="Rodzaj"
          :items="houseTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
      <v-col
        ><v-select
          v-model="offerForm.plotType"
          label="Rodzaj"
          :items="plotTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
      <v-col
        ><v-select
          v-model="offerForm.premisePurspose"
          label="Przeznaczenie lokalu"
          :items="premisePurposes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
    </v-row>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "DetailedOfferForm",
  data() {
    return {
      flatTypes: [
        {
          id: 1,
          name: "Blok",
        },
        {
          id: 2,
          name: "Kamienica",
        },
        {
          id: 3,
          name: "Apartamentowiec",
        },
        {
          id: 4,
          name: "Loft",
        },
        {
          id: 5,
          name: "Pozostałe",
        },
      ],
      houseTypes: [
        {
          id: 1,
          name: "Wolnostojący",
        },
        {
          id: 2,
          name: "Bliźniak",
        },
        {
          id: 3,
          name: "Szzeregowiec",
        },
        {
          id: 4,
          name: "Gospodarstwo",
        },
        {
          id: 5,
          name: "Letniskowy",
        },
        {
          id: 6,
          name: "Pozostałe",
        },
      ],
      plotTypes: [
        {
          id: 1,
          name: "Działki rekreacyjne",
        },
        {
          id: 2,
          name: "Działki budowlane",
        },
        {
          id: 3,
          name: "Działki rolne",
        },
        {
          id: 4,
          name: "Działki leśne",
        },
        {
          id: 5,
          name: "Działki inwestycyjne",
        },
        {
          id: 6,
          name: "Działki rolno-budowlane",
        },
        {
          id: 7,
          name: "Działki siedliskowe",
        },
        {
          id: 8,
          name: "Ogródek działkowy",
        },
      ],
      premisePurposes: [
        {
          id: 1,
          name: "Usługowe",
        },
        {
          id: 2,
          name: "Biurowe",
        },
        {
          id: 3,
          name: "Handlowe",
        },
        {
          id: 4,
          name: "Gastronomiczne",
        },
        {
          id: 5,
          name: "Przemysłowe",
        },
        {
          id: 6,
          name: "Hotelowe",
        },
      ],
      roomTypes: [
        {
          id: 1,
          name: "Jednoosobowe",
        },
        {
          id: 2,
          name: "Dwuosobowe",
        },
        {
          id: 3,
          name: "Trzyosobowe i więcej",
        },
      ],
    };
  },
  computed: {
    ...mapState(["offerForm"]),
    ...mapActions([
      "getFlatTypesToSelect",
      "getHouseTypesToSelect",
      "getPlotTypesToSelect",
      "getPremisePurposesToSelect",
      "getRoomTypesToSelect",
    ]),
  },
  mounted() {
    this.$store.dispatch("getFlatTypesToSelect").then((response) => {
      this.flatTypes = response;
    });
    this.$store.dispatch("getHouseTypesToSelect").then((response) => {
      this.houseTypes = response;
    });
    this.$store.dispatch("getPlotTypesToSelect").then((response) => {
      this.plotTypes = response;
    });
    this.$store.dispatch("getPremisePurposesToSelect").then((response) => {
      this.premisePurposes = response;
    });
    this.$store.dispatch("getRoomTypesToSelect").then((response) => {
      this.roomTypes = response;
    });
  },
};
</script>
