function postservl() {
    if (validate()) {
        $.ajax({
            type: 'POST',
            url: '//localhost:8080/job4j_cars/postnew.do',
            data:
                'description=' + $('#description').val()
                + '&header=' + $('#header').val()
                + '&status=' + $('#status').val()
                + '&price=' + $('#price').val()
                + '&body=' + $('#body').val()
                + '&mark=' + $('#mark').val()
                + '&engine=' + $('#engine').val()
                + '&transm=' + $('#transm').val()
                + '&color=' + $('#color').val()
                + '&drive=' + $('#drive').val()
                + '&mileage=' + $('#mileage').val(),
            // + '&user=' + f,
            dataType: 'text'
        }).done(function () {
            window.open('//localhost:8080/job4j_cars/candidate.do');
            console.log('evrething is send ok ');
        }).fail(function (err) {
            console.log('Error !')
        })
    }
}