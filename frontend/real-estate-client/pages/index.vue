<template>
  <v-app>
    <SearchPanel />
    <LastOffers />
    <Categories />
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
