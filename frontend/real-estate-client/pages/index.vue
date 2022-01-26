<template>
  <v-app>
    <SearchPanel class="mb-4" />
    <div class="mb-2 mt-2 text-center text-h5">Ostatnie oferty</div>
    <OffersList :offers="offers" class="mb-4" />
    <div class="mb-2 mt-2 text-center text-h5">Proponowane oferty</div>
    <OffersList
      v-if="proposedOffers && proposedOffers.length"
      :offers="proposedOffers"
      class="mb-4"
    />
    <div
      v-if="!proposedOffers || !proposedOffers.length"
      class="mb-2 mt-2 text-center"
    >
      Dodaj oferty do obserwowanych aby znaleźć tu podobne
    </div>
    <Categories class="mb-4" />
  </v-app>
</template>
<script>
import OffersList from "@/components/OffersList";
import SearchPanel from "@/components/SearchPanel";
import Categories from "@/components/Categories";
export default {
  components: {
    OffersList,
    SearchPanel,
    Categories,
  },
  data() {
    return {
      offers: [],
      proposedOffers: [],
    };
  },
  async mounted() {
    let allOffers;
    await this.$store.dispatch("getAllOffers").then((response) => {
      if (response._embedded && response._embedded.realEstateResourceList)
        allOffers = response._embedded.realEstateResourceList;
    });

    allOffers = allOffers.filter((el) => !el.banned);

    if (allOffers) {
      allOffers.sort((a, b) => {
        if (a.createdAt > b.createdAt) return -1;
        if (a.createdAt < b.createdAt) return 1;
        return 0;
      });
      this.offers = allOffers.slice(0, 4);
    }

    this.$store.dispatch("getProposedOffers").then((response) => {
      this.proposedOffers = response;
    });

    this.proposedOffers = this.proposedOffers.filter((el) => !el.banned);
  },
};
</script>
