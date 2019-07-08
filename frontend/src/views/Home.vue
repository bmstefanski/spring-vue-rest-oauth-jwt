<template lang="pug">
  .container
    .row
      .col-sm-8
        SummaryBox(:avatar="avatar", :id="id", :name="name", :provider="provider", :providerId="providerId", :username="username", :email="email")
        RequestsResultBox
      .col-sm-4
        template(v-if="!isAuthorized")
          SignInBox
          SignUpBox
        template(v-else)
          LogoutBox
</template>
<script>
  import SignInBox from "../components/SignInBox";
  import SignUpBox from "../components/SignUpBox";
  import SummaryBox from "../components/SummaryBox";
  import RequestsResultBox from "../components/RequestsResultBox";
  import LogoutBox from "../components/LogoutBox";

  import {USER_DETAILS} from "../constants";

  export default {
    data: () => ({
      avatar: '',
      id: '',
      name: '',
      provider: '',
      providerId: '',
      username: '',
      email: ''
    }),
    components: {
      SignInBox,
      SignUpBox,
      SummaryBox,
      RequestsResultBox,
      LogoutBox
    },
    computed: {
      isAuthorized() {
        return this.id !== ""
      }
    },
    methods: {
      fetchUser() {
        this.$http.get(USER_DETAILS, {headers: {Authorization: `Bearer ${localStorage.getItem('accessToken')}`}})
        .then(response => {
          ({avatar: this.avatar, id: this.id, name: this.name, provider: this.provider, providerId: this.providerId, username: this.username, email: this.email} = response.data)
        });
      }
    },
    created() {
      this.fetchUser();
    }
  }
</script>
<style>
  @import url(~toastr/toastr.scss);

  .card {
    margin-top: 1rem;
  }

  .container {
    margin-top: 1rem;
  }

</style>
