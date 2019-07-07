<template lang="pug">
  .col-sm-12
    form
      .card
        .card-header
          h5.my-1.font-weight-normal Sign Up
            i.fas.fa-user-plus.ml-2
        .card-body
          .form-group
            label Username
            input.form-control(type='text')
          .form-group
            label Name
            input.form-control(type='text')
          .form-group
            label Email
            input.form-control(type='email')
          .form-group
            label Password
            input.form-control(type='password')
        .card-footer.text-right
          button.btn.btn-raised.btn-secondary(type='submit') sign up
</template>
<script>
  export default {}
</script>
