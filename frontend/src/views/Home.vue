<template lang="pug">
  .container
    .row
      .col-sm-8
        .col-sm-12
          .card
            .card-body
              h1.display-4 Hello, world!
              p.lead
                | This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.
              hr.my-4
              p
                | It uses utility classes for typography and spacing to space content out within the larger container.
              p.lead
                a.btn.btn-primary.btn-lg(href='#', role='button') Learn more

        .col-sm-12
          .card
            .card-body
              h5.card-title Card title
              h6.card-subtitle.mb-2.text-muted Card subtitle
              p.card-text
                | Some quick example text to build on the card title and make up the bulk of the card's content.
              a.card-link(href='#') Card link
              a.card-link(href='#') Another link
      .col-sm-4
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
              .card-footer.text-right
                button.btn.btn-raised.btn-primary(type='submit') sign in
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
                button.btn.btn-raised.btn-primary(type='submit') sign up
</template>
<style scoped>
  .card {
    margin-top: 1rem;
  }

  .container-fluid {
    margin-top: 1rem;
  }

</style>
<script>
  export default {
    name: 'home'
  }
</script>
