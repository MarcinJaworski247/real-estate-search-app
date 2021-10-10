<template>
  <v-app>
    <nav class="background-photo">
      <v-app-bar flat color="transparent">
        <v-toolbar-title>REAL ESTATE</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn class="mr-2"> Dodaj ogłoszenie </v-btn>
        <v-btn>
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
<style scoped>
.background-photo {
  background-image: url("~assets/images/background.jpeg");
  height: 400px;
  opacity: 0.8;
}
.main-container {
  margin-top: -200px;
}
</style>
