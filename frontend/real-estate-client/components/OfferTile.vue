<template>
  <div
    class="tile"
    @click="goToDetails"
    @mouseenter="actionsVisible = true"
    @mouseleave="actionsVisible = false"
  >
    <img
      v-if="offer.files.length"
      class="avatar"
      :src="`data:image/png;base64,${offer.files[0].bytes}`"
    />
    <img v-else class="avatar" src="~/assets/images/image-placeholder.png" />
    <div class="description">
      <div class="ml-2">
        <div class="text-subtitle-1">
          <v-icon small left>local_offer</v-icon
          >{{ offer.price.toLocaleString() }} zł
        </div>
      </div>
      <div class="ml-2 mt-1" style="display: flex">
        <div v-if="offer.offerType" class="mr-1 text-subtitle-1">
          <v-icon small left>done</v-icon>{{ offer.offerType }}
        </div>
        <div v-if="offer.category" class="mr-1 text-subtitle-1">
          <v-icon small left>done</v-icon>{{ offer.category }}
        </div>
      </div>
      <div class="ml-2 mt-1">
        <div class="text-subtitle-1">
          <v-icon small left>place</v-icon> {{ offer.localization.city }}
        </div>
      </div>
    </div>
    <div v-if="editable && actionsVisible">
      <v-icon @click="goToEdit">keyboard_arrow_right</v-icon>
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
    editable: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  data() {
    return {
      actionsVisible: false,
    };
  },
  methods: {
    goToDetails() {
      if (!this.editable) {
        this.$router.push(`/offer/${this.offer.id}`);
      }
    },
    goToEdit() {
      this.$router.push(`/offer/edit/${this.offer.id}`);
    },
  },
};
</script>
<style scoped>
.tile {
  width: 380px;
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
