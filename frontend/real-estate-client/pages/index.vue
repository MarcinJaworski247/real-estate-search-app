<template>
  <v-app>
    <SearchPanel class="mb-4" />
    <div class="mb-2 mt-2 text-center text-h5">Ostatnie oferty</div>
    <OffersList :offers="offers" class="mb-4" />
    <div style="display: flex; justify-content: center">
      <Loader v-if="isProposedLoading" />
    </div>
    <div v-if="isAuthenticated" class="mb-2 mt-2 text-center text-h5">
      Proponowane oferty
    </div>
    <template v-if="isAuthenticated">
      <OffersList
        v-if="proposedOffers && proposedOffers.length"
        :offers="proposedOffers"
        class="mb-4"
      />
      <div style="display: flex; justify-content: center">
        <Loader v-if="isProposedLoading" />
      </div>
      <div
        v-if="!proposedOffers || !proposedOffers.length"
        class="mb-2 mt-2 text-center"
      >
        Dodaj oferty do obserwowanych aby znaleźć tu podobne
      </div>
    </template>
    <Categories class="mb-4" />
  </v-app>
</template>
<script>
import OffersList from "@/components/OffersList";
import SearchPanel from "@/components/SearchPanel";
import Categories from "@/components/Categories";
import Loader from "@/components/Loader.vue";
export default {
  components: {
    OffersList,
    SearchPanel,
    Categories,
    Loader,
  },
  data() {
    return {
      offers: [],
      proposedOffers: [],
      isMainLoading: false,
      isProposedLoading: false,
    };
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
  },
  async mounted() {
    let allOffers = [];
    this.isMainLoading = true;
    this.isProposedLoading = true;
    await this.$store.dispatch("getAllOffers").then((response) => {
      if (response._embedded && response._embedded.realEstateResourceList)
        allOffers = response._embedded.realEstateResourceList;
    });

    if (allOffers.length) {
      allOffers = allOffers.filter((el) => !el.banned);
    }

    if (allOffers) {
      allOffers.sort((a, b) => {
        if (a.createdAt > b.createdAt) return -1;
        if (a.createdAt < b.createdAt) return 1;
        return 0;
      });
      this.offers = allOffers.slice(0, 4);
    }
    if (this.isAuthenticated) {
      this.$store.dispatch("getProposedOffers").then((response) => {
        this.proposedOffers = response;
      });
      if (this.proposedOffers.length)
        this.proposedOffers = this.proposedOffers.filter((el) => !el.banned);
    }
    this.isMainLoading = false;
    this.isProposedLoading = false;
  },
};
</script>
