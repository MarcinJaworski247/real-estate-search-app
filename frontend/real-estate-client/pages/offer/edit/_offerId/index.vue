<template>
  <v-container class="box" fluid>
    <div class="text-h5">Nowe ogłoszenie</div>
    <v-form ref="form">
      <div class="text-h6 mt-2">Dane ogólne</div>
      <BaseOfferForm />
      <v-btn color="success" @click="addPhotos"> Dodaj zdjęcia </v-btn>
      <div class="text-h6 mt-2">Dane szczegółowe</div>
      <DetailedOfferForm />
      <v-btn color="success" @click="editOffer"> Edytuj </v-btn>
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
          <v-btn color="blue darken-1" text @click="photoModalConfirm"
            >Dodaj</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
import BaseOfferForm from "@/components/Forms/BaseOfferForm.vue";
import DetailedOfferForm from "@/components/Forms/DetailedOfferForm.vue";

export default {
  name: "EditOffer",
  components: {
    BaseOfferForm,
    DetailedOfferForm,
  },
  data() {
    return {
      photoModalVisible: false,
      photos: null,
    };
  },
  mounted() {
    this.$store.dispatch("getOfferToEdit", this.$route.params.offerId);
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
    photoModalConfirm() {
      this.photoModalVisible = false;
      const formData = new FormData();

      for (const item of this.photos) {
        formData.append("file", item);
      }
      this.$store.dispatch("uploadPhotos", {
        offerId: this.$route.params.offerId,
        photos: formData,
      });
      this.$store.dispatch("getOfferImages", this.$route.params.offerId);
    },
    selectImage(photos) {
      this.photos = photos;
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
}
.photo {
  width: 300px;
  height: 200px;
}
</style>
