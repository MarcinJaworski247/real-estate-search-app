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
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              medium
              left
              icon
              text
              @click="saveOffer"
              v-bind="attrs"
              v-on="on"
            >
              <v-icon v-if="offer && !offer.favourite" class="ico-fav">
                favorite_border
              </v-icon>
              <v-icon v-if="offer && offer.favourite" class="ico-fav red--text">
                favorite
              </v-icon>
            </v-btn>
          </template>
          <span v-if="offer && !offer.favourite">Dodaj do obserwowanych</span>
          <span v-if="offer && offer.favourite">Usuń z obserwowanych</span>
        </v-tooltip>
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
      <v-col cols="12" sm="4" lg="4"
        ><span class="grey--text">Powierzchnia:</span> {{ offer.size }} m2
      </v-col>
      <v-col cols="12" sm="4" lg="4">
        <span class="grey--text">Wynajem/sprzedaż:</span> {{ offer.offerType }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        ><span class="grey--text">Piętro:</span> {{ offer.floorNumber }}
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="4" lg="4">
        <span class="grey--text">Ilość pokojów:</span> {{ offer.roomsNumber }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        ><span class="grey--text">Rodzaj zabudowy:</span> {{ offer.flatType }}
      </v-col>
      <v-col cols="12" sm="4" lg="4"
        ><span class="grey--text">Umeblowane:</span>
        <span v-if="offer.furnished">tak</span>
        <span v-if="!offer.furnished"></span>
      </v-col>
    </v-row>
    <div class="text-h5 grey--text my-4">Kontakt</div>
    <v-row>
      <v-col cols="12" sm="12" lg="12">
        <div>
          <v-icon class="mr-2">person</v-icon>{{ offer.user.firstName }}
          {{ offer.user.lastName.charAt(0) }}.
        </div>
        <div class="my-2">
          <v-icon class="mr-2">phone_iphone</v-icon>
          {{ phoneNumber }}
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-icon class="ml-2" @click="showNumber" v-bind="attrs" v-on="on">
                visibility
              </v-icon>
            </template>
            <span>Pokaż numer</span>
          </v-tooltip>
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
      phoneNumber: null,
    };
  },
  async mounted() {
    await this.$store
      .dispatch("getOffer", {
        basicInfoId: this.$route.params.basicInfoId,
        realEstateId: this.$route.params.realEstateId,
      })
      .then((response) => {
        this.offer = response;
        this.phoneNumber = `${this.offer.user.phoneNumber.substring(
          0,
          3
        )}******`;
      });

    if (this.offer.user.username !== this.$store.state.currentUser.name) {
      this.$store.dispatch("incrementViews", this.offer.basicInfoId);
    }
  },
  methods: {
    saveOffer() {
      if (this.offer.favourite) {
        this.$store
          .dispatch("removeFromFavourites", this.offer.basicInfoId)
          .then(() => {
            this.offer.favourite = false;
          });
      } else if (!this.offer.favourite) {
        this.$store
          .dispatch("saveToFavourites", this.offer.basicInfoId)
          .then(() => {
            this.offer.favourite = true;
          });
      }
    },
    showNumber() {
      this.$store
        .dispatch("showPhoneNumber", this.offer.basicInfoId)
        .then(() => {
          this.phoneNumber = this.offer.user.phoneNumber;
        });
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
