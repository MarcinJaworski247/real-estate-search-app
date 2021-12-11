<template>
  <v-container v-if="offer" class="box" fluid>
    <v-row class="mb-4">
      <div v-if="offer.files.length">
        <photo-carousel :photos="offer.files" />
      </div>
      <div v-else>
        <img class="photo" src="~/assets/images/image-placeholder.png" />
      </div>
    </v-row>
    <v-row>
      <v-col cols="12" lg="12" sm="12">
        <v-btn medium left icon text @click="saveOffer">
          <v-icon class="ico-fav"> favorite_border </v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row justify="center" align="center">
      <v-col cols="12" sm="6" lg="6">
        <span class="text-h5 grey-text">{{ offer.title }}</span>
      </v-col>
      <v-col cols="12" sm="6" lg="6" class="d-flex justify-end">
        <v-icon class="mr-2">local_offer</v-icon
        ><span class="text-h5">{{ offer.price.toLocaleString() }} zł</span>
      </v-col>
    </v-row>
    <v-row justify="center" align="center">
      <v-col cols="12" sm="12" lg="12">
        <v-icon large left>place</v-icon>{{ offer.localization.city }}
      </v-col>
    </v-row>
    <div class="text-h5 grey--text my-4">Opis</div>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div class="">{{ offer.description }}</div>
      </v-col>
    </v-row>
    <div class="text-h5 grey--text my-4">Informacje dodatkowe</div>
    <v-row>
      <v-col cols="12" sm="4" lg="4">Powierzchnia: {{ offer.size }} m2 </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Wynajem/sprzedaż: {{ offer.offerType }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Piętro: {{ offer.level }} / {{ offer.floors }}
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="4" lg="4">
        Ilość pokojów: {{ offer.roomsNumber }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Rodzaj zabudowy: {{ offer.flatType }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        >Umeblowane: <span v-if="offer.furnished">tak</span>
        <span v-if="!offer.furnished"></span>
      </v-col>
    </v-row>
    <div class="text-h5 grey--text my-4">Kontakt</div>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div><v-icon class="mr-2">person</v-icon>{{ offer.user.username }}</div>
        <div class="my-2">
          <v-icon class="mr-2">phone_iphone</v-icon>
          {{ offer.user.phoneNumber }}
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import PhotoCarousel from "@/components/PhotoCarousel.vue";

export default {
  name: "OfferDetails",
  components: {
    PhotoCarousel,
  },
  data() {
    return {
      offer: null,
      photos: null,
    };
  },
  mounted() {
    this.$store
      .dispatch("getOffer", {
        basicInfoId: this.$route.params.basicInfoId,
        realEstateId: this.$route.params.realEstateId,
      })
      .then((response) => {
        this.offer = response;
      });
  },
  methods: {
    saveOffer() {
      // TODO
    },
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
  width: 67%;
  padding-left: 64px;
  padding-right: 64px;
}
.photo {
  width: 300px;
  height: 200px;
}
.ico-fav:hover {
  color: red;
}
</style>
