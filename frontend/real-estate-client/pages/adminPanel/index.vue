<template>
  <v-container class="box" fluid>
    <div class="text-h5">Panel administracyjny</div>
    <v-row class="mb-4">
      <v-col cols="12" sm="6" lg="6">
        <div class="text-h5 mt-5">Dodane ogłoszenia</div>
      </v-col>
    </v-row>
    <AdminOffersTable :offers="offers" />
  </v-container>
</template>
<script>
import AdminOffersTable from "@/components/AdminOffersTable.vue";

export default {
  name: "AdminPanel",
  components: {
    AdminOffersTable,
  },
  data() {
    return {
      offers: [],
    };
  },
  mounted() {
    this.$store.dispatch("getAllOffers").then((response) => {
      if (response._embedded && response._embedded.realEstateResourceList)
        this.offers = response._embedded.realEstateResourceList;
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
  width: 67%;
}
</style>
