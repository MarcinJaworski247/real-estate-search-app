<template>
  <v-app>
    <nav class="background-photo">
      <v-app-bar flat color="transparent">
        <v-toolbar-title class="text-h4 app-title" @click="goHome">
          REAL ESTATE
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn class="mr-2" @click="addOffer"> Dodaj ogłoszenie </v-btn>
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
  },
};
</script>
<style scoped>
.background-photo {
  background-image: url("~assets/images/background.jpeg");
  height: 400px;
  opacity: 0.8;
}
.main-container {
  margin-top: -200px;
}
.app-title:hover {
  cursor: pointer;
}
</style>
