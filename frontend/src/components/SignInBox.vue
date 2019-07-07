<template lang="pug">
  .col-sm-12
    form
      .card
        .card-header
          h5.my-1.font-weight-normal Sign In
            i.fas.fa-sign-in-alt.ml-2
        .card-body
          .form-group
            label Username
            input.form-control(type='text')
          .form-group
            label Password
            input.form-control(type='password')
          .form-group
            button.btn.btn-raised.btn-lg.btn-block(type='button') Sign in with GitHub
              i.fab.fa-github.float-left.mt-1
        .card-footer.text-right
          button.btn.btn-raised.btn-secondary(type='submit') sign in
</template>
<script>
  export default {}
</script>
