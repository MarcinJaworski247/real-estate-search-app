<template>
  <v-container class="box" fluid>
    <v-row class="mb-4">
      <v-col
        v-for="photo in photos"
        :key="photo"
        class="d-flex justify-center"
        cols="12"
        sm="4"
        lg="3"
      >
        <img src="~/assets/images/apartment.jpg" class="mx-4 photo" />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6" lg="6">
        <span class="text-h4 grey-text">{{ title }}</span>
      </v-col>
      <v-col cols="12" sm="6" lg="6" class="d-flex justify-end">
        <v-icon>local_offer</v-icon>{{ price }} zł
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div><v-icon>place</v-icon>{{ town }}</div>
      </v-col>
    </v-row>
    <div class="text-h4 grey-text my-4">Opis</div>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div class="grey-text">{{ description }}</div>
      </v-col>
    </v-row>
    <div class="text-h4 grey-text my-4">Informacje dodatkowe</div>
    <v-row>
      <v-col cols="12" sm="4" lg="4">Powierzchnia: {{ area }} </v-col>
      <v-col cols="12" sm="4" lg="4">Wynajem/sprzedaż: {{ type }} </v-col>
      <v-col cols="12" sm="4" lg="4">Piętro: {{ floor }} </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="4" lg="4"> Ilość pokojów: {{ roomsCount }} </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Dostęp do garażu: {{ garageAccess }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Dostęp do piwnicy: {{ basementAccess }}
      </v-col>
    </v-row>
    <div class="text-h4 grey-text my-4">Kontakt</div>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div><v-icon class="mr-2">person</v-icon>{{ userFirstName }}</div>
        <div class="my-2">
          <v-icon class="mr-2">phone_iphone</v-icon>{{ userMobilePhone }}
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "OfferDetails",
  data() {
    return {
      photos: ["test", "test", "test", "test"],
      title: "Mieszkanie 3-pokojowe, 2 piętro",
      town: "Piotrków Trybunalski",
      price: 3200,
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
      area: "60 m2",
      type: "wynajem",
      floor: 2,
      roomsCount: 3,
      garageAccess: "nie",
      basementAccess: "nie",
      userFirstName: "Paweł",
      userMobilePhone: "+48 555 666 777",
    };
  },
};
</script>
<style scoped>
.box {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.photo {
  width: 100%;
  height: 100%;
}
</style>
