<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="offers"
      :items-per-page="10"
      class="elevation-1"
    >
      <template #[`item.banned`]="{ item }">
        <div v-if="item.banned" style="color: red">ZBANOWANE</div>
        <div v-if="!item.banned" style="color: green">Nie zbanowane</div>
      </template>
      <template #[`item.actions`]="{ item }">
        <div style="width: 80px">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn text icon @click="goToDetails(item)">
                <v-icon class="ico-details" v-bind="attrs" v-on="on">
                  keyboard_arrow_right
                </v-icon>
              </v-btn>
            </template>
            <span>Szczegóły</span>
          </v-tooltip>
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-if="!item.sold" text icon @click="banOffer(item)">
                <v-icon class="ico-sell red--text" v-bind="attrs" v-on="on">
                  remove_circle
                </v-icon>
              </v-btn>
            </template>
            <span>Zbanuj/odbanuj ofertę</span>
          </v-tooltip>
        </div>
      </template>
    </v-data-table>
    <v-dialog v-model="banModalVisible" max-width="500px">
      <v-card>
        <v-card-title class="text-h5"
          >Czy na pewno zmienić status oferty?</v-card-title
        >
        <v-col>
          <v-text-field v-model="comment" label="Komentarz"></v-text-field>
        </v-col>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeBanModal"
            >Anuluj</v-btn
          >
          <v-btn color="blue darken-1" text @click="banOfferConfirm"
            >Zmień</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
export default {
  name: "AdminOffersTable",
  props: {
    offers: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      headers: [
        {
          text: "Tytuł",
          value: "title",
        },
        {
          text: "Kategoria",
          value: "category",
        },
        {
          text: "Użytkownik",
          value: "user.username",
        },
        {
          text: "Dodano",
          value: "createdAt",
        },
        {
          text: "Zbanowana",
          value: "banned",
          align: "center",
        },
        {
          text: "Akcje",
          value: "actions",
          sortable: false,
          align: "center",
        },
      ],
      idToBan: null,
      ban: null,
      comment: null,
      banModalVisible: false,
      offersList: null,
    };
  },
  mounted() {
    this.offersList = this.offers;
  },
  methods: {
    banOffer(data) {
      this.idToBan = data.basicInfoId;
      this.ban = !data.banned;
      this.banModalVisible = true;
    },
    banOfferConfirm() {
      this.$store.dispatch("banOffer", {
        basicInfoId: this.idToBan,
        comment: this.comment,
        banned: this.ban,
      });
      this.banModalVisible = false;
      this.$store.dispatch("getAllOffers").then((response) => {
        if (response._embedded && response._embedded.realEstateResourceList)
          this.offersList = response._embedded.realEstateResourceList;
      });
    },
    closeBanModal() {
      this.idToBan = null;
      this.ban = null;
      this.comment = null;
      this.banModalVisible = false;
    },
    goToDetails(data) {
      this.$router.push(`/offer/${data.basicInfoId}/${data.realEstateId}`);
    },
  },
};
</script>
