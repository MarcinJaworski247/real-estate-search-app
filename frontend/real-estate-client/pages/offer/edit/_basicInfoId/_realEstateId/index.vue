<template>
  <v-container class="box" fluid>
    <div class="text-h5">Nowe ogłoszenie</div>
    <v-form ref="form">
      <div class="text-h6 mt-2">Dane ogólne</div>
      <BaseOfferForm />
      <v-btn class="mb-2 mb-2" color="success" @click="addPhotos">
        Dodaj zdjęcia
      </v-btn>
      <v-row v-if="offerForm.files.length" align="baseline" class="my-2">
        <PhotoCarousel
          :photos="offerForm.files"
          editable
          @removePhoto="removePhoto"
        />
      </v-row>
      <div class="text-h6 mt-2">Dane szczegółowe</div>
      <DetailedOfferForm />
      <v-btn color="success" @click="editOffer"> Zapisz </v-btn>
    </v-form>
    <v-dialog v-model="photoModalVisible" max-width="500px">
      <v-card>
        <v-card-title class="text-h5">Dodaj zdjęcia</v-card-title>
        <v-card-text>
          <v-file-input
            accept="image/*"
            prepend-icon="mdi-camera"
            label="Zdjęcia"
            multiple
            chips
            show-size
            @change="selectImage"
          ></v-file-input>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closePhotoModal"
            >Anuluj</v-btn
          >
          <v-btn
            v-if="photos"
            color="blue darken-1"
            text
            @click="photoModalConfirm"
            >Dodaj</v-btn
          >
          <v-btn v-if="!photos" disabled color="blue darken-1" text
            >Dodaj</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
import { mapState } from "vuex";

import BaseOfferForm from "@/components/Forms/BaseOfferForm.vue";
import DetailedOfferForm from "@/components/Forms/DetailedOfferForm.vue";
import PhotoCarousel from "@/components/PhotoCarousel.vue";

export default {
  name: "EditOffer",
  components: {
    BaseOfferForm,
    DetailedOfferForm,
    PhotoCarousel,
  },
  data() {
    return {
      photoModalVisible: false,
      photos: null,
    };
  },
  computed: {
    ...mapState(["offerForm"]),
  },
  mounted() {
    this.$store.dispatch("getOfferToEdit", {
      basicInfoId: this.$route.params.basicInfoId,
      realEstateId: this.$route.params.realEstateId,
    });
  },
  beforeDestroy() {
    this.$store.commit("RESET_OFFER_FORM");
  },
  methods: {
    editOffer() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("editOffer").then(() => {
          this.$router.push("/profile");
        });
      }
    },
    addPhotos() {
      this.photoModalVisible = true;
    },
    closePhotoModal() {
      this.photoModalVisible = false;
      this.photos = null;
    },
    async photoModalConfirm() {
      this.photoModalVisible = false;
      const formData = new FormData();

      for (const item of this.photos) {
        formData.append("file", item);
      }
      await this.$store
        .dispatch("uploadPhotos", {
          offerId: this.$route.params.basicInfoId,
          photos: formData,
        })
        .then(() => {
          this.$store.dispatch("getOfferToEdit", {
            basicInfoId: this.$route.params.basicInfoId,
            realEstateId: this.$route.params.realEstateId,
          });
        });
      this.photos = null;
    },
    selectImage(photos) {
      this.photos = photos;
    },
    removePhoto(id) {
      this.$store
        .dispatch("removePhoto", {
          offerId: this.$route.params.basicInfoId,
          photoId: id,
        })
        .then(() => {
          this.$store.dispatch("getOfferToEdit", {
            basicInfoId: this.$route.params.basicInfoId,
            realEstateId: this.$route.params.realEstateId,
          });
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
}
.photo {
  width: 300px;
  height: 200px;
}
</style>
