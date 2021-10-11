<template>
  <v-app>
    <SearchPanel class="mb-4" />
    <LastOffers class="mb-4" />
    <Categories class="mb-4" />
  </v-app>
</template>
<script>
import LastOffers from "@/components/LastOffers";
import SearchPanel from "@/components/SearchPanel";
import Categories from "@/components/Categories";
export default {
  components: {
    LastOffers,
    SearchPanel,
    Categories,
  },
};
</script>
