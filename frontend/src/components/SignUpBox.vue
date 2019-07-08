<template lang="pug">
  .col-sm-12
    form(@submit.prevent="handleSignup")
      .card
        .card-header
          h5.my-1.font-weight-normal Sign Up
            i.fas.fa-user-plus.ml-2
        .card-body
          .form-group
            label Username
            input.form-control(type='text', v-model="username")
          .form-group
            label Name
            input.form-control(type='text', v-model="name")
          .form-group
            label Email
            input.form-control(type='email', v-model="email")
          .form-group
            label Password
            input.form-control(type='password', v-model="password")
        .card-footer.text-right
          button.btn.btn-raised.btn-secondary(type='submit') sign up
</template>
<script>
  import {SIGNUP_ENDPOINT_URL} from "../constants";

  export default {
    data: () => ({
      username: '',
      name: '',
      email: '',
      password: ''
    }),
    methods: {
      handleSignup() {
        this.$http.post(SIGNUP_ENDPOINT_URL, {username: this.username, name: this.name, email: this.email, password: this.password}, {})
        .then(() => this.$notify.success('Successfully singed up'))
        .catch(error => {
          if (error.response.status === 409) {
            this.$notify.error('User already exists');
            return;
          }

          this.$notify.error('An error occurred while trying to signup');
        });
      }
    }
  }
</script>
