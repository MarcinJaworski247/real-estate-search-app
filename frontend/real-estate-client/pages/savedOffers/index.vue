<template>
  <v-container class="box" fluid>
    <div class="text-h5 mt-5">Dodane do obserwowanych</div>
    <OffersList :offers="offers" />
  </v-container>
</template>
<script>
import OffersList from "@/components/OffersList";

export default {
  name: "SavedOffersIndex",
  components: {
    OffersList,
  },
  data() {
    return { offers: [] };
  },
  mounted() {
    this.$store.dispatch("getUserFavourites").then((response) => {
      this.offers = response;
    });
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
  max-width: 200px;
  max-height: 200px;
}
</style>
