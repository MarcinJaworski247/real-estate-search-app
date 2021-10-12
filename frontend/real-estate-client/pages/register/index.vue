<template>
  <v-container class="box" fluid>
    <div class="text-h5">Rejestracja</div>
    <v-form v-model="valid">
      <v-text-field v-model="firstName" required label="Imię"></v-text-field>
      <v-text-field v-model="lastName" required label="Nazwisko"></v-text-field>
      <v-text-field v-model="email" required label="Email"></v-text-field>
      <v-text-field v-model="login" required label="Login"></v-text-field>
      <v-text-field
        v-model="password"
        type="password"
        required
        label="Hasło"
      ></v-text-field>
      <v-btn color="success" @click="register"> Utwórz konto </v-btn>
    </v-form>
  </v-container>
</template>
<script>
export default {
  name: "Register",
  methods: {
    register() {},
  },
};
</script>
<style scoped>
.box {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
