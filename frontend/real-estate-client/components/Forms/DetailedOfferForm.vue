<template>
  <div>
    <v-row>
      <v-col
        ><v-text-field
          v-model="offerForm.floorNumber"
          type="number"
          label="Piętro"
        ></v-text-field
      ></v-col>
      <v-col
        v-if="
          offerForm.category === `Mieszkania` ||
          offerForm.category === `Domy` ||
          offerForm.category === `Biura i lokale`
        "
        ><v-text-field
          v-model="offerForm.roomsNumber"
          type="number"
          label="Ilość pokojów"
          :rules="[rules.required]"
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
      <v-col v-if="offerForm.category === `Mieszkania`"
        ><v-select
          v-model="offerForm.flatType"
          label="Rodzaj zabudowy"
          :items="flatTypes"
          item-text="name"
          item-value="id"
          clearable
          :rules="[rules.required]"
        ></v-select
      ></v-col>
      <v-col v-if="offerForm.category === `Domy`"
        ><v-text-field
          v-model="offerForm.plotSize"
          type="number"
          label="Powierzchnia działki"
          :rules="[rules.required]"
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
      <v-col v-if="offerForm.category === `Domy`"
        ><v-select
          v-model="offerForm.houseType"
          label="Rodzaj"
          :items="houseTypes"
          item-text="name"
          item-value="id"
          clearable
          :rules="[rules.required]"
        ></v-select
      ></v-col>
      <v-col v-if="offerForm.category === `Działki`"
        ><v-select
          v-model="offerForm.plotType"
          label="Rodzaj"
          :items="plotTypes"
          item-text="name"
          item-value="id"
          clearable
          :rules="[rules.required]"
        ></v-select
      ></v-col>
      <v-col v-if="offerForm.category === `Biura i lokale`"
        ><v-select
          v-model="offerForm.premisesPurpose"
          label="Przeznaczenie lokalu"
          :items="premisePurposes"
          item-text="name"
          item-value="id"
          clearable
          :rules="[rules.required]"
        ></v-select
      ></v-col>
    </v-row>
    <v-row>
      <v-col v-if="offerForm.category === `Mieszkania`">
        <v-text-field
          v-model="offerForm.rent"
          type="number"
          label="Czynsz"
          suffix="zł"
          :rules="[rules.required]"
        ></v-text-field>
      </v-col>
      <v-col v-if="offerForm.category === `Stancje i pokoje`">
        <v-select
          v-model="offerForm.roomType"
          label="Rodzaj pokoju"
          :items="roomTypes"
          clearable
          :rules="[rules.required]"
        />
      </v-col>
    </v-row>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "DetailedOfferForm",
  data() {
    return {
      flatTypes: [],
      houseTypes: [],
      plotTypes: [],
      premisePurposes: [],
      roomTypes: [],
      rules: {
        required: (value) => !!value || "Pole wymagane",
      },
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
