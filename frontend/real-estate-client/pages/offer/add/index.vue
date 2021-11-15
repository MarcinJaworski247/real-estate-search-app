<template>
  <v-container class="box" fluid>
    <div class="text-h5">Nowe ogłoszenie</div>
    <v-form ref="form">
      <div class="text-h6 mt-2">Dane ogólne</div>
      <BaseOfferForm />
      <div class="text-h6 mt-2">Dane szczegółowe</div>
      <DetailedOfferForm />
      <v-btn color="success" @click="addOffer"> Dodaj </v-btn>
    </v-form>
  </v-container>
</template>
<script>
import BaseOfferForm from "@/components/Forms/BaseOfferForm.vue";
import DetailedOfferForm from "@/components/Forms/DetailedOfferForm.vue";

export default {
  name: "AddOffer",
  components: {
    BaseOfferForm,
    DetailedOfferForm,
  },
  beforeDestroy() {
    this.$store.commit("RESET_OFFER_FORM");
  },
  methods: {
    addOffer() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("addOffer").then(() => {
          this.$router.push("/profile");
        });
      }
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
</style>
