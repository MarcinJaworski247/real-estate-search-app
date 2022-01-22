<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="offers"
      :items-per-page="5"
      class="elevation-1"
    >
      <template #[`item.price`]="{ item }">
        <div style="max-width: 40px; overflow: hidden">
          {{ item.price.toLocaleString() }}
        </div>
      </template>
      <template #[`item.sold`]="{ item }">
        <v-icon v-if="!item.sold"> check_box_outline_blank </v-icon>
        <v-icon v-if="item.sold"> check_box </v-icon>
      </template>
      <template #[`item.actions`]="{ item }">
        <div style="width: 120px">
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn text icon @click="goToEdit(item)">
                <v-icon class="ico-details" v-bind="attrs" v-on="on">
                  keyboard_arrow_right
                </v-icon>
              </v-btn>
            </template>
            <span>Edycja</span>
          </v-tooltip>
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn v-if="!item.sold" text icon @click="markAsSold(item)">
                <v-icon class="ico-sell" v-bind="attrs" v-on="on">
                  check_circle
                </v-icon>
              </v-btn>
            </template>
            <span>Oznacz jako zakończone</span>
          </v-tooltip>
          <v-tooltip top>
            <template v-slot:activator="{ on, attrs }">
              <v-btn text icon @click="deleteOffer(item)">
                <v-icon class="ico-delete" v-bind="attrs" v-on="on">
                  delete
                </v-icon>
              </v-btn>
            </template>
            <span>Usuń ofertę</span>
          </v-tooltip>
        </div>
      </template>
    </v-data-table>
    <v-dialog v-model="deleteModalVisible" max-width="500px">
      <v-card>
        <v-card-title class="text-h5"
          >Czy na pewno usunąć ogłoszenie "{{ titleToDelete }}" ?</v-card-title
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDelete">Anuluj</v-btn>
          <v-btn color="blue darken-1" text @click="deleteOfferConfirm"
            >Usuń</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="soldModalVisible" max-width="500px">
      <v-card>
        <v-card-title class="text-h5"
          >Czy na pewno oznaczyć ogłoszenie "{{ titleToSold }}" jako
          zakończone?</v-card-title
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeMarkAsSold"
            >Anuluj</v-btn
          >
          <v-btn color="blue darken-1" text @click="markAsSoldConfirm"
            >Potwierdź</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
export default {
  name: "OffersTable",
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
          text: "Cena [zł]",
          value: "price",
        },
        {
          text: "Powierzchnia [m2]",
          value: "size",
        },
        {
          text: "Zakończone",
          value: "sold",
          align: "center",
        },
        { text: "Wyświetleń", value: "visitsCounter", align: "center" },
        {
          text: "Wyświetleń numeru",
          value: "phoneViewsCounter",
          align: "center",
        },
        { text: "Akcje", value: "actions", sortable: false, align: "center" },
      ],
      offersList: this.offers,
      deleteModalVisible: false,
      idToDelete: null,
      titleToDelete: "",
      idToSold: null,
      titleToSold: "",
      soldModalVisible: false,
    };
  },
  methods: {
    deleteOffer(data) {
      this.idToDelete = data.basicInfoId;
      this.titleToDelete = data.title;
      this.deleteModalVisible = true;
    },
    deleteOfferConfirm() {
      this.$store.dispatch("deleteOffer", this.idToDelete).then(() => {
        // this.offersList
        const idx = this.offersList.findIndex(
          (item) => item.id === this.idToDelete
        );
        if (idx > -1) {
          this.offersList.splice(idx, 1);
        }
      });
      this.deleteModalVisible = false;
    },
    closeDelete() {
      this.idToDelete = null;
      this.titleToDelete = "";
      this.deleteModalVisible = false;
    },
    goToEdit(data) {
      this.$router.push(`/offer/edit/${data.basicInfoId}/${data.realEstateId}`);
    },
    markAsSold(data) {
      this.idToSold = data.basicInfoId;
      this.titleToSold = data.title;
      this.soldModalVisible = true;
    },
    closeMarkAsSold() {
      this.idToSold = null;
      this.titleToSold = "";
      this.soldModalVisible = false;
    },
    markAsSoldConfirm() {
      this.$store.dispatch("markAsSold", this.idToSold);
      this.soldModalVisible = false;
    },
  },
};
</script>
<style scoped>
.ico-details:hover {
  color: #3695e8 !important;
}
.ico-sell:hover {
  color: #5ab55e !important;
}
.ico-delete:hover {
  color: red !important;
}
</style>
