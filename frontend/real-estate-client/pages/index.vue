<template>
  <v-app>
    <SearchPanel class="mb-4" />
    <div class="mb-2 mt-2 text-center text-h5">Ostatnie oferty</div>
    <OffersList class="mb-4" />
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
};
</script>
