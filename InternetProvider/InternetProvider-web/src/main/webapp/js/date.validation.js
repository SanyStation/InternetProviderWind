$(function() {
    $('form#validation').validate({
        errorPlacement: $.datepicker.errorPlacement,
        rules: {
            vdFrom: {dpDate: true},
            vdTo: {dpDate: true}
        },
        messages: {
            vdFrom: 'Please enter a valid date (yyyy-mm-dd)',
            vdTo: 'Please enter a valid date (yyyy-mm-dd)'
        }
    });
    
    $("#vdFrom").datepicker({dateFormat: 'yy-mm-dd'});
    $("#vdTo").datepicker({dateFormat: 'yy-mm-dd'});

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