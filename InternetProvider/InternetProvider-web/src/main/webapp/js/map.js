$(window).load(function() {
    function initialize() {
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
            $('#popup').addClass('active');
            console.log(e.latLng); // ���������� �������
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
            success: function(data) {
                // alert(data); // show response from the php script.
                if (!data.auth) {
                    $('<iframe />', {
                        name: 'frame1',
                        id: 'frame1',
                        src: 'profile'
                    }).appendTo('body').load(function() {
                        $(this).contents().find('form').submit(function() {
                            //var url = $(this).attr('action'); // the script where you handle the form input.
                            $('#frame1').load(function() {
                                $(this).unbind('load');
                                if ($(this)[0].contentDocument.URL.search('profile') > -1) {
                                    $(this).remove();
                                }
                            });
                            return;

//                                    $.ajax({
//                                        type: "POST",
//                                        url: url,
//                                        data: $(this).serialize(), // serializes the form's elements.
//                                        //dataType: 'json',
//                                        success: function(data, x, y) {
//                                            console.log(data);
//                                            console.log(x);
//                                            console.log(y);
//                                            
//                                            //if($('#frame1').attr('src')=)
//                                            
//                                        },
//                                        statusCode: {
//                                            302: function() {
//                                                alert('302');
//                                                $('#frame1').remove;
//                                            }
//                                        }
//                                    });
//                                    return false; // avoid to execute the actual submit of the form.
                        });
                    });
                } else {
                    $('<div />', {
                        name: 'frame1',
                        id: 'frame1',
                        src: 'profile'
                    }).html('<center><h1>Order has been sent successfully</h1></center>').appendTo('body');
                }


                //alert(data); // show response from the php script.
            }
        });
        return false; // avoid to execute the actual submit of the form.
    });


});