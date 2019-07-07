<template lang="pug">
  .col-sm-12
    .card
      .card-body
        a.btn.btn-raised.btn-lg.btn-block(@click="handleLogout") Wyloguj
          i.fas.fa-sign-out-alt.ml-2
</template>
<script>
  export default {
    methods: {
      handleLogout() {
        this.$parent.id = '';
        localStorage.removeItem('accessToken');
      }
    }
  }
</script>
