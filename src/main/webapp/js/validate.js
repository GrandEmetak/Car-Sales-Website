function validate() {
    let x = Boolean(true);
    if ($('#header').val() === '') {
        x = false;
    }
    if ($('#description').val() === '') {
        x = false;
    }
    if ($('#engine').val() === '') {
        x = false;
    }
    if ($('#price').val() === '') {
        x = false;
    }
    if ($('#status').val() === '') {
        x = false;
    }
    if ($('#mark').val() === '') {
        x = false;
    }
    if ($('#color').val() === '') {
        x = false;
    }
    if ($('#body').val() === '') {
        x = false;
    }
    if ($('#transm').val() === '') {
        x = false;
    }
    if ($('#drive').val() === '') {
        x = false;
    }
    if ($('#mileage').val() === '') {
        x = false;
    }
    let f = $('#body').val();
    alert('то что в форме ' + f);
    return x;
}