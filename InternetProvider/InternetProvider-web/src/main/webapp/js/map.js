var map = true;
function register() {
    $('iframe').remove();
    $('#side form').submit();
}
$(window).load(function() {
    setTimeout(function() {
        $("#popup").hover(function() {
            $(this).fadeOut(300, function() {
                $(this).hide();
            });
        });
    }, 1000);
    function initialize() {
        var infowindow = new google.maps.InfoWindow({
            content: "Provider Location",
            maxWidth: 200
        });
        var infowindow2 = new google.maps.InfoWindow({
            content: "Enter your address here",
            maxWidth: 200
        });
        autocomplete
        if (window.parent.customer > 0) {
            $("#side form").append('<input type="hidden" name="customer_id" value="' + window.parent.customer + '"/>')
        }
        var marker;
        var plocation;
        var myLatlng = new google.maps.LatLng(50.447321657486334, 30.456885522842413);
        var myOptions = {
            zoom: 17,
            center: myLatlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        marker = new google.maps.Marker({
            map: map,
            draggable: true,
            animation: google.maps.Animation.DROP,
            position: map.center,
            optimized: false,
            zIndex: 999
        });
        plocation = new google.maps.Marker({
            map: map,
            draggable: false,
            animation: google.maps.Animation.DROP,
            position: map.center,
            title: 'Provider Location',
            icon: 'data:image/png;base64,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',
            visible: false,
            optimized: false,
            zIndex: 5
        });
        //plocation.setVisible(false);
        geocoder = new google.maps.Geocoder();
        // Create the search box and link it to the UI element.
        var input = /** @type {HTMLInputElement} */
                (
                        document.getElementById('pac-input'));
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        var autocomplete = new google.maps.places.Autocomplete(
                /** @type {HTMLInputElement} */
                        (input), {
                    types: ['geocode'],
                    componentRestrictions: {
                        country: 'ua'
                    }
                });
                $('#pac-input').focus();
                console.log('autocomplete');
                console.log(autocomplete);
//        infowindow2.open(map, autocomplete);
        google.maps.event.addListener(autocomplete, 'place_changed', function() {
            var place = autocomplete.getPlace();
            if (!place.geometry) {
                return;
            }

            marker.setPosition(place.geometry.location);
            console.log(marker.getPosition());
            google.maps.event.trigger(marker, 'dragend', {
                'latLng': marker.getPosition()
            });
        });
        google.maps.event.addListener(marker, 'dragend', function(e) {
            console.log(e);
            $("#side form div").remove();
            $("#side form input[type=submit]").addClass('disabled');
            $("#side form input[name=x]").attr('value', '');
            $("#side form input[name=y]").attr('value', '');
            $("#side form input[name=address]").attr('value', '');
            $.ajax({
                type: 'POST',
                url: 'Controller',
                dataType: 'json',
                data: {
                    'command': 'refresh_service',
                    'x': e.latLng.B, //50.526232,
                    'y': e.latLng.k //30.6020479
                },
                success: function(data) {
                    //                            var element = $.parseJSON(data);//JSON.parse(data);
                    if (data.status == 'ok') {
                        pLatLng = new google.maps.LatLng(data.providerLocation.x, data.providerLocation.y);
                        console.log(pLatLng);
                        //var current_bounds = map.getBounds();
                        //var marker_pos = plocation.getPosition();
                        if (!plocation.getVisible() || !pLatLng.equals(plocation.getPosition())) {
                            plocation.setPosition(pLatLng);
                            infowindow.setContent('Provider Location<br/>'+data.providerLocation.address);
                            infowindow.open(map, plocation);
                            //                                        var current_bounds = map.getBounds();
                            //                                        var marker_pos = plocation.getPosition();
                            //map.fitBounds(plocation.getPosition());
                            plocation.setVisible(true);
                            console.log(plocation);
                            plocation.setMap(null);
                            plocation.setMap(map);
                            //plocation.setAnimation(null);
                            plocation.setAnimation(google.maps.Animation.BOUNCE);
                            setTimeout(function() {
                                plocation.setAnimation(null);
                            }, 600);
                            var bounds = new google.maps.LatLngBounds();
                            if (!(map.getBounds().contains(pLatLng))) {
                                for (var i = 0; i < [marker, plocation].length; i++) {
                                    bounds.extend([marker, plocation][i].getPosition());
                                }
                                ne = bounds.getNorthEast();
                                sw = bounds.getSouthWest();
                                // the multiplier used to add space; positive for east, negative for west
                                lngPadding = 0.5
                                extendedLng = ne.lng() + (ne.lng() - sw.lng()) * lngPadding;
                                // copy original and extend with the new Lng
                                extendedBounds = bounds;
                                extendedBounds.extend(new google.maps.LatLng(ne.lat(), extendedLng));
                                map.fitBounds(extendedBounds);
                                zoomChangeBoundsListener =
                                        google.maps.event.addListenerOnce(map, 'bounds_changed', function(event) {
                                            plocation.setMap(null);
                                            plocation.setMap(map);
                                        });
                                setTimeout(function() {
                                    google.maps.event.removeListener(zoomChangeBoundsListener)
                                }, 2000);
                            }
                        }
                        var first = 'checked';
                        data.providerLocation.services.forEach(function(elem) {
                            $("#side form").prepend(
                                    '<div class="radio"><label><input type="radio" name="serviceId" value="'
                                    + elem.id + '"' + first + '>' + elem.name + ', <span>' + elem.price + '</span></label></div>');
                            first = '';
                        });
                        $("#side form input[name=x]").attr('value', e.latLng.B);
                        $("#side form input[name=y]").attr('value', e.latLng.k);
                        $("#side form input[type=submit]").removeClass('disabled');
                    }else{
                         plocation.setVisible(false);
                         infowindow.close();
                    }

                },
                error: function() {
                    alert("AJAX error");
                }
            });
            $('#popup').mouseover();
            console.log(e.latLng); // Координаты маркера
            //$('#coord').html('<br>' + e.latLng);
            geocoder.geocode({
                'latLng': e.latLng
            }, function(results, status) {
                console.log({
                    results: results,
                    status: status
                });
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[1]) {
                        $('#popup').find('span').text(results[0].formatted_address).end().fadeIn(200);
                        $("#side form input[name=address]")
                                .attr('value', results[0].formatted_address);
                    } else {
                        //alert('No results found');
                    }
                } else {
                    alert('Geocoder failed due to: ' + status);
                }
            });
        });
    }
    ;
    initialize();
    $('#order_form').submit(function(e) {
        var url = $('#order_form').attr('action'); // the script where you handle the form input.
        $("#side form input[type=submit]").addClass('disabled');
        $('#myModal').find('.modal-body').html('<span class="label label-info">sending order...</span>').end().modal('show')

        $.ajax({
            type: "POST",
            url: url,
            data: $("#order_form").serialize(), // serializes the form's elements.
            dataType: 'json',
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(['error ajax:184', jqXHR, textStatus, errorThrown]);

                if (textStatus == 'parsererror') {
                    $('#myModal').find('.modal-body').html('<div class="alert alert-danger">Error occured. Acces denied.</div>').end().modal('show')

                } else {
                    $('#myModal').find('.modal-body').html('<div class="alert alert-danger">Error occured.</div>').end().modal('show')
                }
            },
            success: function(data) {
                console.log(data);
                // alert(data); // show response from the php script.
                if (data.error) {
                    $('#myModal').find('.modal-body').html('<div class="alert alert-danger">Error occured. You must create order for user only.</div>').end().modal('show');
                    $("#side form input[type=submit]").removeClass('disabled');
                    return;
                }
                if (!data.auth) {
                    $('#myModal').find('.modal-body').html('').html($('<iframe />', {
                        name: 'frame1',
                        src: 'profile',
                        width: '100%',
                        onload: 'autoResize(this)'
                    })).end().modal('show');
                    $("#side form input[type=submit]").removeClass('disabled');
                } else {
                    $('#myModal').find('.modal-body').html('<h1>Order has been sent successfully</h1><span class="label label-success">redirecting you to order...</span>').end().modal('show')
//                    $('<div />', {
//                        name: 'frame1',
//                        id: 'frame1',
//                        src: 'profile'
//                    }).html('<center><h1>Order has been sent successfully</h1>redirecting you to order...</center>').appendTo('body');
                    setTimeout(function() {
                        window.parent.location.href = "Controller?command=" + data.command + "&order_id=" + data.order_id;
                    }, 1500)
                }
            }
        });
        return false; // avoid to execute the actual submit of the form.
    });
});
var autoResize = function(e) {
    console.log(e);
    var newheight = e.contentWindow.document.body.children.item(0).offsetHeight;
    $('#myModal iframe').height(newheight);
}