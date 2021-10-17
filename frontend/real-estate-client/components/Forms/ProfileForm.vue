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
        ><v-text-field v-model="mail" required label="Email"></v-text-field
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
  methods: {
    save() {
      this.$store.dispatch("editProfile", {
        firstName: this.firstName,
        lastName: this.lastName,
        mail: this.mail,
        mobilePhone: this.mobilePhone,
      });
    },
  },
};
</script>
