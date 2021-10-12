<template>
  <div class="tile" @click="goToDetails">
    <img class="avatar" src="~/assets/images/apartment.jpg" />
    <div class="description">
      <div class="ml-2">
        <div class="text-subtitle-1">
          <v-icon left>local_offer</v-icon>{{ offer.price }} zł
        </div>
      </div>
      <div class="ml-2 mt-1" style="display: flex">
        <div v-for="tag in offer.tags" :key="tag" class="mr-1 text-subtitle-1">
          <v-icon left>done</v-icon>{{ tag }}
        </div>
      </div>
      <div class="ml-2 mt-1">
        <div class="text-subtitle-1">
          <v-icon left>place</v-icon> {{ offer.place }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "OfferTile",
  props: {
    offer: {
      type: Object,
      required: true,
    },
  },
  methods: {
    goToDetails() {
      this.$router.push("/offer/1");
    },
  },
};
</script>
<style scoped>
.tile {
  width: 380;
  height: 220px;
  position: relative;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.tile:hover {
  cursor: pointer;
  opacity: 0.8;
}
.description {
  height: 106px;
  width: 100%;
  bottom: 0;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.8);
  margin-top: 8px;
}
.avatar {
  width: 100%;
  height: 100%;
  opacity: 0.6;
}
</style>
