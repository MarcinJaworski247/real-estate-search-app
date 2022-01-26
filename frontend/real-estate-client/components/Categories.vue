<template>
  <v-container fluid>
    <div class="mb-5 mt-2 text-center text-h5">Kategorie</div>
    <v-row align="center" class="justify-center mt-5">
      <div class="mx-3 category">
        <v-btn fab medium @click="goToResults(`Biura i lokale`)">
          <v-icon>home</v-icon>
        </v-btn>
        <div class="mt-1 grey--text">Biura i lokale</div>
      </div>
      <div class="mx-3 category">
        <v-btn fab medium @click="goToResults(`Mieszkania`)">
          <v-icon>home</v-icon>
        </v-btn>
        <div class="mt-1 grey--text">Mieszkania</div>
      </div>
      <div class="mx-3 category">
        <v-btn fab medium @click="goToResults(`Domy`)">
          <v-icon>home</v-icon>
        </v-btn>
        <div class="mt-1 grey--text">Domy</div>
      </div>
      <div class="mx-3 category">
        <v-btn fab medium @click="goToResults(`Garaże i parkingi`)">
          <v-icon>home</v-icon>
        </v-btn>
        <div class="mt-1 grey--text">Garaże</div>
      </div>
      <div class="mx-3 category">
        <v-btn fab medium @click="goToResults(`Działki`)">
          <v-icon>home</v-icon>
        </v-btn>
        <div class="mt-1 grey--text">Działki</div>
      </div>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "Categories",
  methods: {
    goToResults(category) {
      this.$router.push(`/searchResults/${category}/0/0/0/0`);
    },
  },
};
</script>
<style scoped>
.category {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 160px;
  margin-bottom: 16px;
}
</style>
