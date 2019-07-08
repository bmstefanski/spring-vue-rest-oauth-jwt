<template lang="pug">
  .col-sm-12
    form(@submit.prevent="handleSignin")
      .card
        .card-header
          h5.my-1.font-weight-normal Sign In
            i.fas.fa-sign-in-alt.ml-2
        .card-body
          .form-group
            label Username
            input.form-control(type='text', v-model="username")
          .form-group
            label Password
            input.form-control(type='password', v-model="password")
          .form-groupa
            a.btn.btn-raised.btn-lg.btn-block(:href="signInWithGithubUrl") Sign in with GitHub
              i.fab.fa-github.float-left.mt-1
        .card-footer.text-right
          button.btn.btn-raised.btn-secondary(type='submit') sign in
</template>
<script>
  import {GITHUB_OAUTH_URL, SIGNIN_ENDPOINT_URL} from "../constants";

  export default {
    data: () => ({
      username: '',
      password: '',
      signInWithGithubUrl: GITHUB_OAUTH_URL
    }),
    methods: {
      handleSignin() {
        this.$http.post(`${SIGNIN_ENDPOINT_URL}`, {username: this.username, password: this.password}, {})
        .then(response => {
          let token = response.data.accessToken;
          localStorage.setItem('accessToken', token);

          this.$parent.fetchUser();
          this.$notify.success("Successfully logged in")
        })
        .catch(error => {
          if (error.response.status === 401) {
            this.$notify.error('Bad credentials');
            return;
          }

          this.$notify.error('An error occurred while trying to signin');
        })
      }
    }
  }
</script>
