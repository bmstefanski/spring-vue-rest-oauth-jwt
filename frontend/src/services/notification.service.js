import toastr from "toastr"

export default {
  error(message) {
    this.printNotification(message, "error");
  },
  success(message) {
    this.printNotification(message, "success");
  },
  warning(message) {
    this.printNotification(message, "warning");
  },
  info(message) {
    this.printNotification(message, "info");
  },
  printNotification(message, type) {
    let options = {
      "progressBar": true,
      "positionClass": "toast-bottom-right",
      "showDuration": "300",
      "hideDuration": "500",
      "timeOut": "2000",
      "extendedTimeOut": "1000"
    };

    toastr[type](message, null, options);
  }
}
