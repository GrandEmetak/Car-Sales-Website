function validate() {
    let x = Boolean(true);
    if ($('#firstName').val() === '') {
        x = false;
    }
    if ($('#position').val() === '') {
        x = false;
    }
    if ($('#city').val() === '') {
        x = false;
    }
    return x;
}
