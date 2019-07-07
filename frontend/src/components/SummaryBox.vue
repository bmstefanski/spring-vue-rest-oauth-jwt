<template lang="pug">
  .col-sm-12
    .card
      .card-header
        h5.my-1.font-weight-normal Welcome, {username}
      .card-body
        .row
          .col-sm-3
            img.avatar-rounded.avatar-md(src="@/assets/default_avatar.png", alt='...').mb-3
          .col-sm-9
            strong Username:
              span.font-weight-normal.ml-2 zerion1
            br
            strong Name:
              span.font-weight-normal.ml-2 zerion1
            br
            strong Email:
              span.font-weight-normal.ml-2 zerion1@email.com
            br
            strong Identifier:
              span.font-weight-normal.ml-2 4189234189hDNASJDIASUHJJM!@&*(#Y
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
  export default {}
</script>
