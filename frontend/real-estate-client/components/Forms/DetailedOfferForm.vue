<template>
  <div>
    <v-row>
      <v-col
        ><v-text-field
          v-model="offerForm.floor"
          type="number"
          label="Piętro"
        ></v-text-field
      ></v-col>
      <v-col
        ><v-text-field
          v-model="offerForm.roomsCount"
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
          v-model="offerForm.flatTypeId"
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
          v-model="offerForm.floorsCount"
          type="number"
          label="Ilość pięter"
        ></v-text-field
      ></v-col>
    </v-row>

    <v-row>
      <v-col
        ><v-select
          v-model="offerForm.houseTypeId"
          label="Rodzaj"
          :items="houseTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
      <v-col
        ><v-select
          v-model="offerForm.plotTypeId"
          label="Rodzaj"
          :items="plotTypes"
          item-text="name"
          item-value="id"
          clearable
        ></v-select
      ></v-col>
      <v-col
        ><v-select
          v-model="offerForm.premisePursposeId"
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
    // this.getFlatTypesToSelect().then((response) => {
    //   this.flatTypes = response.data;
    // });
    // this.getHouseTypesToSelect().then((response) => {
    //   this.houseTypes = response.data;
    // });
    // this.getPlotTypesToSelect().then((response) => {
    //   this.plotTypes = response.data;
    // });
    // this.getPremisePurposesToSelect().then((response) => {
    //   this.premisePurposes = response.data;
    // });
    // this.getRoomTypesToSelect().then((response) => {
    //   this.roomTypes = response.data;
    // });
  },
};
</script>
