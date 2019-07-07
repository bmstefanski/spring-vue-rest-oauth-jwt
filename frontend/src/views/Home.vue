<template lang="pug">
  .container
    .row
      .col-sm-8
        SummaryBox
        RequestsResultBox
      .col-sm-4
        SignInBox
        SignUpBox
</template>
<script>
  import SignInBox from "../components/SignInBox";
  import SignUpBox from "../components/SignUpBox";
  import SummaryBox from "../components/SummaryBox";
  import RequestsResultBox from "../components/RequestsResultBox";
  import {USER_DETAILS} from "../constants";

  export default {
    components: {
      SignInBox,
      SignUpBox,
      SummaryBox,
      RequestsResultBox
    },
    created() {
      this.$http.get(USER_DETAILS)
      .then(response => {
        console.log(response);
        console.log(response.data);
      })
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
