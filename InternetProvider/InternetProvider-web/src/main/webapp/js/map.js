var map = true;
function register() {
    $('iframe').remove();
    $('#side form').submit();
}
$(window).load(function() {
    setTimeout(function() {
        $("#popup").hover(function() {
            $(this).fadeOut(300, function() {
                $(this).remove();
            });
        });
    }, 1000);
    function initialize() {
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
            zIndex: 5
        });
        plocation = new google.maps.Marker({
            map: map,
            draggable: false,
            animation: google.maps.Animation.DROP,
            position: map.center,
            title: 'Provider Location',
            icon: {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 7
            },
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
            $("#side form label").remove();
            $("#side form input[type=submit]").attr('disabled');
            $("#side form input[name=x]").attr('value', '');
            $("#side form input[name=y]").attr('value', '');
            $("#side form input[name=address]").attr('value', '');
            $.ajax({
                type: 'POST',
                url: 'Controller',
                dataType: 'json',
                data: {
                    'command': 'refresh_service',
                    'x': e.latLng.A, //50.526232,
                    'y': e.latLng.k //30.6020479
                },
                success: function(data) {
                    //                            var element = $.parseJSON(data);//JSON.parse(data);
                    if (data.status = 'ok') {
                        pLatLng = new google.maps.LatLng(data.providerLocation.x, data.providerLocation.y);
                        console.log(pLatLng);
                        //var current_bounds = map.getBounds();
                        //var marker_pos = plocation.getPosition();
                        if (!pLatLng.equals(plocation.getPosition())) {
                            plocation.setPosition(pLatLng);
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
                            $("#side form").append(
                                    '<label><input type="radio" name="serviceId" value="'
                                    + elem.id + '"' + first + '>' + elem.name + ', <span>' + elem.price + '</span></label>');
                            first = '';
                        });
                        $("#side form input[name=x]").attr('value', e.latLng.A);
                        $("#side form input[name=y]").attr('value', e.latLng.k);
                        $("#side form input[type=submit]").removeAttr('disabled');
                    }

                },
                error: function() {
                    alert("AJAX error");
                }
            });
            $('#popup').mouseover();
            console.log(e.latLng); // Координаты маркера
            $('#coord').html('<br>' + e.latLng);
            geocoder.geocode({
                'latLng': e.latLng
            }, function(results, status) {
                console.log({
                    results: results,
                    status: status
                });
                if (status == google.maps.GeocoderStatus.OK) {
                    if (results[1]) {
                        $('#coord').append('<br>'
                                + results[0].formatted_address);
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
                    return;
                }
                if (!data.auth) {
                    $('#myModal').find('.modal-body').html($('<iframe />', {
                        name: 'frame1',
                        src: 'profile',
                        width: '100%',
                        onload: 'autoResize(this)'
                    })).end().modal('show')
                } else {
                    $('#myModal').find('.modal-body').html('<h1>Order has been sent successfully</h1>redirecting you to order...').end().modal('show')
//                    $('<div />', {
//                        name: 'frame1',
//                        id: 'frame1',
//                        src: 'profile'
//                    }).html('<center><h1>Order has been sent successfully</h1>redirecting you to order...</center>').appendTo('body');
                    setTimeout(function() {
                        window.parent.location.href = "Controller?command=" + data.command + "&order_id=" + data.order_id;
                    }, 1000)
                }
            }
        });
        return false; // avoid to execute the actual submit of the form.
    });
});
var autoResize = function(e) {
    console.log(e);
    var newheight = e.contentWindow.document.body.children.item().offsetHeight;
    $('#myModal iframe').height(newheight);
}