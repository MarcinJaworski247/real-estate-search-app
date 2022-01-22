<template>
  <div>
    <v-text-field v-model="mail" required label="Email"></v-text-field>
    <v-text-field
      v-model="password"
      required
      label="Hasło"
      :type="showPass ? 'text' : 'password'"
      :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
      @click:append="showPass = !showPass"
    ></v-text-field>
    <div v-if="fail" class="red--text">Niepoprawny mail lub hasło!</div>
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
      showPass: false,
      fail: false,
    };
  },
  methods: {
    async logIn() {
      await this.$store
        .dispatch("login", {
          username: this.mail,
          password: this.password,
        })
        .then((res) => {
          console.log(res);
        });
      if (this.$store.getters.isAuthenticated) {
        this.$router.push("/");
      } else {
        this.fail = true;
        this.mail = "";
        this.password = "";
      }
    },
  },
};
</script>
