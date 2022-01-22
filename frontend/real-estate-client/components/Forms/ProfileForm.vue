<template>
  <div>
    <v-row>
      <v-col
        ><v-text-field v-model="firstName" required label="Imię"></v-text-field
      ></v-col>
      <v-col
        ><v-text-field
          v-model="lastName"
          required
          label="Nazwisko"
        ></v-text-field
      ></v-col>
    </v-row>
    <v-row>
      <v-col
        ><v-text-field
          v-model="mail"
          required
          label="Email"
          disabled
        ></v-text-field
      ></v-col>
      <v-col
        ><v-text-field v-model="mobilePhone" required label="Nr tel.">
        </v-text-field
      ></v-col>
    </v-row>

    <v-btn color="success" @click="save"> Zapisz </v-btn>
  </div>
</template>
<script>
export default {
  name: "ProfileForm",
  data() {
    return {
      firstName: "",
      lastName: "",
      mail: "",
      mobilePhone: "",
    };
  },
  mounted() {
    this.$store.dispatch("getProfileData").then((response) => {
      this.firstName = response.firstName;
      this.lastName = response.lastName;
      this.mail = response.username;
      this.mobilePhone = response.phoneNumber;
    });
  },
  methods: {
    save() {
      this.$store.dispatch("editProfile", {
        firstName: this.firstName,
        lastName: this.lastName,
        // mail: this.mail,
        phoneNumber: this.mobilePhone,
      });
    },
  },
};
</script>
