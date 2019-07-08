<template lang="pug">
  .col-sm-12
    .card(v-if="id !== ''")
      .card-header
        h5.my-1.font-weight-normal Welcome, {{name || username}}
      .card-body
        .row
          .col-sm-3.mb-4
            img.avatar-rounded.avatar-md(v-if="avatar !== null", :src="avatar")
            img.avatar-rounded.avatar-md(v-else, src="@/assets/default_avatar.png", alt='...')
          .col-sm-9
            strong Username:
              span.font-weight-normal.ml-2 {{username}}
            br
            strong Name:
              span.font-weight-normal.ml-2 {{name}}
            br
            strong Email:
              span.font-weight-normal.ml-2 {{email}}
            br
            strong Identifier:
              span.font-weight-normal.ml-2 {{id}}
            br
            strong Provider (ID):
              span.font-weight-normal.ml-2 {{providerId}} ({{provider}})
            br
            br
            strong JWT:
              span.font-weight-normal.ml-2 {{getAccessToken()}}
    .card(v-else)
      .card-header
        h5.my-1.font-weight-normal You are unauthorized
      .card-body
        h5 You're unauthorized. Try to sign in or sign up if you don't have account yet
</template>
<style scoped>
  .avatar-md {
    width: 125px;
    height: 125px;
  }

  .avatar-rounded {
    border-radius: 8rem;
    border: 2px solid #2b2b2b;
  }
</style>
<script>
  export default {
    props: ['avatar', 'id', 'name', 'provider', 'providerId', 'username', 'email'],
    methods: {
      getAccessToken() {
        return localStorage.getItem('accessToken');
      }
    }
  }
</script>
