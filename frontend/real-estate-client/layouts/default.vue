<template>
  <v-app>
    <nav class="background-photo">
      <v-app-bar flat color="transparent">
        <v-toolbar-title class="text-h4 app-title" @click="goHome">
          REAL ESTATE
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn class="mr-2" @click="addOffer"> Dodaj ogłoszenie </v-btn>
        <v-btn class="mr-2" @click="goToSavedOffers">
          <v-icon left> favorite_border </v-icon>
          Obserwowane
        </v-btn>
        <v-btn class="mr-2" @click="goToMyProfile">
          <v-icon left> person_outline </v-icon>
          Mój profil
        </v-btn>
        <v-btn @click="goToLogin">
          <v-icon left>account_circle</v-icon>
          Zaloguj
        </v-btn>
      </v-app-bar>
    </nav>
    <v-main class="main-container">
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
    <v-footer class="mt-5">
      <v-col class="text-center" cols="12">
        2021 - Real Estate Search App
      </v-col>
    </v-footer>
  </v-app>
</template>
<script>
export default {
  methods: {
    goToLogin() {
      this.$router.push("/login");
    },
    goHome() {
      this.$router.push("/");
    },
    addOffer() {
      this.$router.push("/offer/add");
    },
    goToSavedOffers() {
      this.$router.push("/savedOffers");
    },
    goToMyProfile() {
      this.$router.push("/profile");
    },
  },
};
</script>
<style scoped>
.background-photo {
  background-image: url("~assets/images/background.jpeg");
  height: 400px;
  opacity: 0.8;
  width: 100%;
}
.main-container {
  margin-top: -200px;
}
.app-title:hover {
  cursor: pointer;
}
</style>
