$(function() {
    $("#tabs").tabs();

    $('form#NewValidation').validate({
        errorPlacement: $.datepicker.errorPlacement,
        rules: {
            vdNewFrom: {dpDate: true},
            vdNewTo: {dpDate: true}
        },
        messages: {
            vdNewFrom: 'Please enter a valid date (yyyy-mm-dd)',
            vdNewTo: 'Please enter a valid date (yyyy-mm-dd)'
        }
    });
    $("#vdNewFrom").datepicker({dateFormat: 'yy-mm-dd'});
    $("#vdNewTo").datepicker({dateFormat: 'yy-mm-dd'});

    $('form#DiscValidation').validate({
        errorPlacement: $.datepicker.errorPlacement,
        rules: {
            vdDiscFrom: {dpDate: true},
            vdDiscTo: {dpDate: true}
        },
        messages: {
            vdDiscFrom: 'Please enter a valid date (yyyy-mm-dd)',
            vdDiscTo: 'Please enter a valid date (yyyy-mm-dd)'
        }
    });
    $("#vdDiscFrom").datepicker({dateFormat: 'yy-mm-dd'});
    $("#vdDiscTo").datepicker({dateFormat: 'yy-mm-dd'});

    $("#vdByMonth").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'yy-mm',
        showButtonPanel: true,
        onClose: function(dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1));
        },
        beforeShowDay: function(date) {
            return [false, ""];
        }
    });
});