<template>
  <v-app>
    <nav class="background-photo">
      <v-app-bar flat color="transparent">
        <v-toolbar-title class="text-h4 app-title" @click="goHome">
          REAL ESTATE
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn v-if="isAuthenticated" class="mr-2" @click="addOffer">
          Dodaj ogłoszenie
        </v-btn>
        <v-btn v-if="isAuthenticated" class="mr-2" @click="goToSavedOffers">
          <v-icon left> favorite_border </v-icon>
          Obserwowane
        </v-btn>
        <v-btn v-if="isAuthenticated" class="mr-2" @click="goToMyProfile">
          <v-icon left> person_outline </v-icon>
          Mój profil
        </v-btn>
        <v-btn v-if="!isAuthenticated" @click="goToLogin">
          <v-icon left>account_circle</v-icon>
          Zaloguj
        </v-btn>
        <div v-if="isAuthenticated">
          <v-btn @click="logOut"> Wyloguj </v-btn>
        </div>
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
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    getCurrentUserName() {
      return this.$store.getters.currentUser;
    },
  },
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
    async logOut() {
      await this.$store.dispatch("logOut");
      this.$router.push("/login");
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
  margin-top: -300px;
}
.app-title:hover {
  cursor: pointer;
}
</style>
