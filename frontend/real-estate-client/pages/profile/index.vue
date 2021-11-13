<template>
  <v-container class="box" fluid>
    <div class="text-h5">Mój profil</div>
    <div style="width: 500px">
      <v-form>
        <ProfileForm />
      </v-form>
    </div>
    <v-row class="mb-4">
      <v-col cols="12" sm="6" lg="6">
        <div class="text-h5 mt-5">Moje ogłoszenia</div>
      </v-col>
      <v-col cols="12" sm="6" lg="6" class="d-flex justify-end">
        <v-icon
          large
          class="mr-2"
          :class="{ active: layout === `grid` }"
          @click="selectLayout(`grid`)"
          >apps</v-icon
        >
        <v-icon
          large
          :class="{ active: layout === `table` }"
          @click="selectLayout(`table`)"
          >view_headline</v-icon
        >
      </v-col>
    </v-row>
    <OffersList v-if="layout === `grid`" :offers="offers" />
    <OffersTable v-if="layout === `table`" :offers="offers" />
  </v-container>
</template>
<script>
import OffersList from "@/components/OffersList";
import ProfileForm from "@/components/Forms/ProfileForm.vue";
import OffersTable from "@/components/OffersTable.vue";

export default {
  name: "UserProfile",
  components: {
    OffersList,
    ProfileForm,
    OffersTable,
  },
  data() {
    return {
      offers: [],
      layout: "grid",
    };
  },
  mounted() {
    this.$store.dispatch("getUserOffers").then((response) => {
      this.offers = response._embedded.realEstateResourceList;
    });
  },
  methods: {
    selectLayout(layout) {
      this.layout = layout;
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
  max-width: 200px;
  max-height: 200px;
}
.active {
  color: green;
}
</style>
