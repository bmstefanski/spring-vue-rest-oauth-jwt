<template lang="pug">
  .container
    .row
      .col-sm-8
        SummaryBox
      .col-sm-4
        SignInBox
        SignUpBox
</template>c
<script>
  import SignInBox from "../components/SignInBox";
  import SignUpBox from "../components/SignUpBox";
  import SummaryBox from "../components/SummaryBox";

  export default {
    components: {
      SignInBox,
      SignUpBox,
      SummaryBox
    }
  }
</script>
<style>
  .card {
    margin-top: 1rem;
  }

  .container {
    margin-top: 1rem;
  }

</style>
