<template lang="pug">
  .col-sm-12
    .card
      .card-body
        .text-center
          button.btn.btn-raised.btn-secondary(@click="handleRequestSubmit") Send test request
        .mt-4
        pre#pre
</template>
<style>
  .string {color: green;}
  .number {color: darkorange;}
  .boolean {color: blue;}
  .null {color: magenta;}
  .key {color: red;}
</style>
<script>
  import {USER_DETAILS} from "../constants";

  export default {
    methods: {
      handleRequestSubmit() {
        this.$http.get(USER_DETAILS, {headers: {Authorization: `Bearer ${localStorage.getItem('accessToken')}`}})
        .then(response => this.replaceResultElementBody(response.data))
        .catch(error => this.replaceResultElementBody(error.response.data));
      },
      replaceResultElementBody(response) {
        document.getElementById('pre').innerHTML = this.syntaxHighlight(JSON.stringify(response, null, 2));
      },
      syntaxHighlight(json) {
        json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+-]?\d+)?)/g, function (match) {
          let cls = 'number';

          if (/^"/.test(match)) {
            if (/:$/.test(match)) {
              cls = 'key';
            } else {
              cls = 'string';
            }
          } else if (/true|false/.test(match)) {
            cls = 'boolean';
          } else if (/null/.test(match)) {
            cls = 'null';
          }
          return '<span class="' + cls + '">' + match + '</span>';
        });
      }
    }
  }
</script>
