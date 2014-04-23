<%-- 
    Document   : map
    Created on : Apr 23, 2014, 12:16:37 AM
    Author     : j_mart
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Service Location select</title>
        <!--script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'>
        </script-->
        <script type='text/javascript' src='js/jquery.1.7.2.min.js'>
        </script>
        <!--link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <link rel="stylesheet" type="text/css" href="/css/result-light.css"-->
        <script type='text/javascript' src="http://maps.google.com/maps/api/js?sensor=false&libraries=places">
        </script>
        <style type='text/css'>
            #map_canvas {
                height: 100%;
                width: 100% ;
            }
            #map_canvas img {
                max-width: none;

            }
            html {
                height: 100%;
            }
            body {
                height: 100%;
                margin: 0;
                font-family: Calibri,sans-serif;
            }

            #map_canvas div {
                -webkit-transform: translate3d(0,0,0);
            }
            #popup {
                position: fixed;
                left: 50%;
                top: 50%;
                background: rgba(255, 255, 255, 0.74);
                border-radius: 7px;
                padding: 10px;
                width: 760px;
                margin-left: -390px;
                text-align: center;               
                margin-top: -90px;
                border: solid red 1px;
                transition: 0.5s;
            }
            #popup.active {
                border: solid white 1px;
                margin-top: -100px;
                top: 0%;
            }
            #side{
                position: absolute;
                right: 12px;
                top: 0;
                bottom: 0;
                height: 320px;
                margin: auto;
                width: 350px;
                background: white;
                padding: 10px 20px;
            }
            #side > form {
                font-size: 30px;
            }
            #side > form > label > span {
                font-style: italic;
            }
            #side > form > label > span:after {
                content: '$';
                font-size: 20px;
                color: #555;
            }
            .controls {
                margin-top: 16px;
                border: 1px solid transparent;
                border-radius: 2px 0 0 2px;
                box-sizing: border-box;
                -moz-box-sizing: border-box;
                height: 32px;
                outline: none;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
            }

            #pac-input {
                background-color: #fff;
                padding: 0 11px 0 13px;
                width: 400px;
                /*font-family: Roboto;*/
                font-size: 15px;
                font-weight: 300;
                text-overflow: ellipsis;
            }

            #pac-input:focus {
                border-color: #4d90fe;
                margin-left: -1px;
                padding-left: 14px;  /* Regular padding-left + 1. */
                width: 401px;
            }

            .pac-container {
                /*font-family: Roboto;*/
            }

            #type-selector {
                color: #fff;
                background-color: #4d90fe;
                padding: 5px 11px 0px 11px;
            }

            #type-selector label {
                font-family: Roboto;
                font-size: 13px;
                font-weight: 300;
            }
            #side > form > input[type="submit"] {
                font-size: 40px;
                padding: 10px 30px;
                display: block;
                box-shadow: rgb(50, 122, 220) 0px 0px 10px -1px;
                margin: 30px auto;
                position: absolute;
                bottom: 0;
                left: 0;
                right: 0;
                width: 260px;
            }
            #side > form > input[type="submit"]:hover {
                box-shadow: rgb(50, 122, 220) 0px 0px 10px 1px;
            }
            #side > form > label {
                display: block;
            }
            #side > form > input[disabled] {
                box-shadow: rgb(159, 159, 159) 0px 0px 10px -1px;
            }
            #side > form > input[disabled]:hover {
                box-shadow: rgb(159, 159, 159) 0px 0px 10px -2px;
                opacity: 0.6;
            }
        </style>
        <script type='text/javascript'>
            //<![CDATA[
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
                    var input = /** @type {HTMLInputElement} */(
                            document.getElementById('pac-input'));
                    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

                    var autocomplete = new google.maps.places.Autocomplete(
                            /** @type {HTMLInputElement} */(input), {types: ['geocode'], componentRestrictions: {country: 'ua'}});


                    google.maps.event.addListener(autocomplete, 'place_changed', function() {
                        var place = autocomplete.getPlace();
                        if (!place.geometry) {
                            return;
                        }

                        marker.setPosition(place.geometry.location);

                        console.log(marker.getPosition());
                        google.maps.event.trigger(marker, 'dragend', {'latLng': marker.getPosition()});
                    });

                    google.maps.event.addListener(marker, 'dragend', function(e) {
                        console.log(e);
                        $("#side form label").remove();
                        $("#side form input[type=submit]").attr('disabled');
                        $("#side form input[name=x]").attr('value', '');
                        $("#side form input[name=y]").attr('value', '');
                        $.ajax({
                            type: 'POST',
                            url: 'Controller',
                            dataType: 'json',
                            data: {
                                'command': 'refresh_service',
                                'x': e.latLng.A, //50.526232,
                                'y': e.latLng.k//30.6020479
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
                                    $('#coord').append('<br>' + results[0].formatted_address);
                                } else {
                                    alert('No results found');
                                }
                            } else {
                                alert('Geocoder failed due to: ' + status);
                            }
                        });
                    });
                }
                ;
                initialize();

            }); //]]>

        </script>


    </head>
    <body>
        <input id="pac-input" class="controls" type="text" placeholder="Enter your address">
        <div id="map_canvas" ></div>
        <div id="popup">Drag the marker<span id="coord"></span></div>
        <div id="side">
            <h1>Choose desired service:</h1>
            <form action="Controller" method="POST">
                <label style="font-size: 20px;">select your position first...</label>
                <input type="submit" name="send_order" value="Send order" disabled=""/>
                <input type="hidden" name="command" value="proceed_to_order"/>
                <input type="hidden" name="x" value="ProceedToOrder"/>
                <input type="hidden" name="y" value="ProceedToOrder"/>
            </form></div></div>

</body>


</html>



