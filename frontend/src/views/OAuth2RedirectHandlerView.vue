<script>
  import {routes} from "../main";

  export default {
    render: (createElement) => createElement('h5', "Redirecting..."),
    methods: {
      obtainUrlParameter() {
        let url = window.location.search;
        return url.includes("?token=") === false ? undefined : url.replace("?token=", "");
      }
    },
    mounted() {
      let token = this.obtainUrlParameter('token');

      if (token !== undefined) {
        localStorage.setItem('accessToken', token);
        this.$notify.success('Successfully logged in');
      } else {
        this.$notify.error('An error occurred while trying to signin')
      }

      this.$root.currentRoute = '/';
      window.history.pushState(null, routes['/'], '/');
    }
  }
</script>
