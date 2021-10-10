<template>
  <div class="tile">
    <img class="avatar" src="~/assets/images/apartment.jpg" />
    <div class="description">
      <div class="ml-2">
        <h3><v-icon left>local_offer</v-icon>{{ offer.price }} zł</h3>
      </div>
      <div class="ml-2 mt-1" style="display: flex">
        <h4 v-for="tag in offer.tags" :key="tag" class="mr-1">
          <v-icon left>done</v-icon>{{ tag }}
        </h4>
      </div>
      <div class="ml-2 mt-1">
        <h4><v-icon left>place</v-icon> {{ offer.place }}</h4>
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
};
</script>
<style scoped>
.tile {
  width: 360px;
  height: 220px;
  position: relative;
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
