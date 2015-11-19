// implementation of AR-Experience (aka "World")
var World = {
	// true once data was fetched
	initiallyLoadedData: false,

	// different POI-Marker assets
	markerDrawable_idle: null,
	markerDrawable_selected: null,

	// list of AR.GeoObjects that are currently shown in the scene / World
	markerList: [],

	// The last selected marker
	currentMarker: null,

	// called to inject new POI data
	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData, lat, lon, alt) {
		// empty list of visible markers
		World.markerList = [];

		// start loading marker assets
		World.markerDrawable_idle = new AR.ImageResource("assets/marker_idle.png");
		World.markerDrawable_selected = new AR.ImageResource("assets/marker_selected.png");

		// loop through POI-information and create an AR.GeoObject (=Marker) per POI
		var myGeoLocation = new AR.GeoLocation(lat,lon,alt);
		for (var currentPlaceNr = 0; currentPlaceNr < poiData.length; currentPlaceNr++) {
			var geoLoc = new AR.GeoLocation(parseFloat(poiData[currentPlaceNr].latitude),parseFloat(poiData[currentPlaceNr].longitude),parseFloat(poiData[currentPlaceNr].altitude));

            var distance = myGeoLocation.distanceTo(geoLoc);
            var str1 = "odl:";
            if (distance > 5000) {
            	distance = distance / 1000;
            	str1 = str1.concat(distance.toFixed(0).toString(),"km ")
            } else {
            	str1 = str1.concat(distance.toFixed(0).toString(),"m ")
            }
			var heightdistance = alt - parseFloat(poiData[currentPlaceNr].altitude);
			heightdistance = heightdistance.toFixed(0);
			str1 = str1.concat("h:",heightdistance,"m");
			var singlePoi = {
				"id": poiData[currentPlaceNr].id,
				"latitude": parseFloat(poiData[currentPlaceNr].latitude),
				"longitude": parseFloat(poiData[currentPlaceNr].longitude),
				"altitude": parseFloat(poiData[currentPlaceNr].altitude),
				"title": poiData[currentPlaceNr].name,
				"description": str1
			};

			/*
				To be able to deselect a marker while the user taps on the empty screen, 
				the World object holds an array that contains each marker.
			*/
			World.markerList.push(new Marker(singlePoi));
		}

		World.updateStatusMessage(currentPlaceNr + ' places loaded');
	},

	// updates status message shon in small "i"-button aligned bottom center
	updateStatusMessage: function updateStatusMessageFn(message, isWarning) {

		var themeToUse = isWarning ? "e" : "c";
		var iconToUse = isWarning ? "alert" : "info";

		$("#status-message").html(message);
		$("#popupInfoButton").buttonMarkup({
			theme: themeToUse
		});
		$("#popupInfoButton").buttonMarkup({
			icon: iconToUse
		});
	},

	// location updates, fired every time you call architectView.setLocation() in native environment
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {

		if (World.markerList.length>0) {
			var myGeoLocation = new AR.GeoLocation(lat,lon,alt);
			for(var i = 0; i < World.markerList.length; i++)
			{
				var geoLoc = new AR.GeoLocation(parseFloat(World.markerList[i].poiData.latitude),parseFloat(World.markerList[i].poiData.longitude),parseFloat(World.markerList[i].poiData.altitude));

				var distance = myGeoLocation.distanceTo(geoLoc);
				var str1 = "odl: ";
                if (distance > 5000) {
                	distance = distance / 1000;
                    str1 = str1.concat(distance.toFixed(2).toString()," km")
                } else {
                    str1 = str1.concat(distance.toFixed(2).toString()," m")
                }
				World.markerList[i].poiData.description = str1.concat(distance.toString());
			}
        }
		/*
			The custom function World.onLocationChanged checks with the flag World.initiallyLoadedData if the function was already called. With the first call of World.onLocationChanged an object that contains geo information will be created which will be later used to create a marker using the World.loadPoisFromJsonData function.

		if (!World.initiallyLoadedData) {

				requestDataFromLocal with the geo information as parameters (latitude, longitude) creates different poi data to a random location in the user's vicinity.

			World.requestDataFromLocal(lat, lon);
			World.initiallyLoadedData = true;
		}
		*/
	},

	// fired when user pressed maker in cam
	onMarkerSelected: function onMarkerSelectedFn(marker) {

		// deselect previous marker
		if (World.currentMarker) {
			if (World.currentMarker.poiData.id == marker.poiData.id) {
				return;
			}
			World.currentMarker.setDeselected(World.currentMarker);
		}

		// highlight current one
		marker.setSelected(marker);
		World.currentMarker = marker;
	},

	// screen was clicked but no geo-object was hit
	onScreenClick: function onScreenClickFn() {
		if (World.currentMarker) {
			World.currentMarker.setDeselected(World.currentMarker);
		}
	},

	// request POI data
	requestDataFromLocal: function requestDataFromLocalFn(centerPointLatitude, centerPointLongitude) {
		var poisToCreate = 20;
		var poiData = [];

		for (var i = 0; i < poisToCreate; i++) {
			poiData.push({
				"id": (i + 1),
				"longitude": (centerPointLongitude + (Math.random() / 5 - 0.1)),
				"latitude": (centerPointLatitude + (Math.random() / 5 - 0.1)),
				"description": ("This is the description of POI#" + (i + 1)),
				"altitude": "100.0",
				"name": ("POI#" + (i + 1))
			});
		}
		World.loadPoisFromJsonData(poiData);
	}

};

/* 
	Set a custom function where location changes are forwarded to. There is also a possibility to set AR.context.onLocationChanged to null. In this case the function will not be called anymore and no further location updates will be received. 
*/
AR.context.onLocationChanged = World.locationChanged;

/*
	To detect clicks where no drawable was hit set a custom function on AR.context.onScreenClick where the currently selected marker is deselected.
*/
AR.context.onScreenClick = World.onScreenClick;