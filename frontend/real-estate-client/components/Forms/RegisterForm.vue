<template>
  <div>
    <v-text-field v-model="firstName" required label="Imię"></v-text-field>
    <v-text-field v-model="lastName" required label="Nazwisko"></v-text-field>
    <v-text-field v-model="mail" required label="Email"></v-text-field>
    <v-text-field
      v-model="phoneNumber"
      required
      label="Numer telefonu"
    ></v-text-field>
    <v-text-field
      v-model="password"
      required
      label="Hasło"
      :type="showPass ? 'text' : 'password'"
      :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
      @click:append="showPass = !showPass"
    ></v-text-field>
    <v-btn color="success" @click="register"> Utwórz konto </v-btn>
  </div>
</template>
<script>
export default {
  name: "RegisterForm",
  data() {
    return {
      firstName: "",
      lastName: "",
      mail: "",
      phoneNumber: "",
      password: "",
      showPass: false,
    };
  },
  methods: {
    register() {
      this.$store
        .dispatch("register", {
          firstName: this.firstName,
          lastName: this.lastName,
          mail: this.mail,
          phoneNumber: this.phoneNumber,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/login");
        });
    },
  },
};
</script>
