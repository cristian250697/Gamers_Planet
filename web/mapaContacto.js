
var marcador;
let mapaContacto;

mapaContacto= L.map('mapaContacto').setView([21.128758,-101.681130],12);


mapaContacto.locate({setView: true, maxZoom: 19});
//.setView([21.128758,-101.681130],12)
L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
attribution: '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>,  GAMERS PLANET SMCSP'
}).addTo(mapaContacto);




mapaContacto.on('locationfound',onLocationFound);   
mapaContacto.on('locationerror',onLocationError);


function onLocationFound (e){
    marcador =  L.marker(e.latlng,{draggable: true}).addTo(mapaContacto);
   // marcador.bindPopup("Ubícate").openPopup();
}
function onLocationError(e){
alert("no aceptaste la ubicacion, para ofrecerte un mejor servicio, por favor, actívala.");
marcador = L.marker([21.128758,-101.681130],{draggable: true}).addTo(mapaContacto);
    marcador.bindPopup("Ubícate").openPopup();
    
}


L.Control.Watermark = L.Control.extend({
    onAdd: function(mapaContacto) {
        var img = L.DomUtil.create('img');

        img.src = 'GamersPlanet.png';
        img.style.width = '100px';
        
        return img;
    },

    onRemove: function(mapaContacto) {
        // Nothing to do here
    }
});

L.control.watermark = function(opts) {
    return new L.Control.Watermark(opts);
};

L.control.watermark({ position: 'bottomleft' }).addTo(mapaContacto);

function obtenDatos(){

    var pos = marcador.getLatLng();
   // alert(pos.lng + "," + pos.lat);
    document.getElementById("latitud").value =pos.lat;//Latitud
    document.getElementById("longitud").value =pos.lng;//Longitd
    

    
}