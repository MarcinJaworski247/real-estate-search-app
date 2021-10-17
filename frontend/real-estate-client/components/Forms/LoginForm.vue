<template>
  <div>
    <v-text-field v-model="mail" required label="Email"></v-text-field>
    <v-text-field
      v-model="password"
      type="password"
      required
      label="Hasło"
    ></v-text-field>

    <v-btn color="success" @click="logIn"> Zaloguj </v-btn>
  </div>
</template>
<script>
export default {
  name: "LoginForm",
  data() {
    return {
      mail: "",
      password: "",
    };
  },
  methods: {
    logIn() {
      this.$store
        .dispatch("login", {
          mail: this.mail,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/profile");
        });
    },
  },
};
</script>
