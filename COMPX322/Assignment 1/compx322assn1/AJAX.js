// Define the getDataAjax function outside of the window.onload event handler
let getDataAjax = () => {
  var request = new XMLHttpRequest();
  // Set the URL to the server-side script
  var url = "getData.php";

  // Initialise the XHR object
  request.onload = function () {
    displayData(request);
  };

  request.open("GET", url);
  request.send("");
};

// Call the getDataAjax function inside the window.onload event handler
window.onload = function () {
  getDataAjax();
};

// Function to display the event names and create click functions for each event
function displayData(request) {
  var response = request.responseText;

  //put the returned data into the <div> element
  theTitleElement = document.getElementById("event-name");
  theTitleElement.innerHTML = response;

  // Attaching event listener to all items with type event-title
  $(".event-title").on("click", function (event) {
    event.preventDefault();

    // retrieving the value of  event-id
    var name = $(this).attr("event-id");
    var url = "eventInformation.php";
    var request2 = new XMLHttpRequest();

    // updating the html event-details with the correct content
    request2.onload = function () {
      // display the additional data in the HTML element with the ID 'event-details'
      var responseJSON = JSON.parse(request2.responseText);
      document.getElementById("event-details").innerHTML =
        responseJSON.name +
        "<br>" +
        responseJSON.category +
        "<br>" +
        responseJSON.month +
        " " +
        responseJSON.day +
        "<br>" +
        "<small>" +
        responseJSON.time +
        "</small>" +
        "<br>" +
        "<small>" +
        responseJSON.location +
        "</small>" +
        "<br>" +
        "<small class='hidden'>" +
        responseJSON.cost +
        "</small>" +
        "<br>" +
        "<small class='hidden'>" +
        responseJSON.tagged +
        "</small>";

      $(".WeatherBTN").on("click", function (event) {
        const eventID = document.querySelector("#event-id").textContent; // Get the ID of the event and get the text content from
        const api = "aea17fc72e8224d338807cf0bfd1466f";
        fetch(`weatherAPI.php?id=${eventID}`)
          .then((response) => response.json())
          .then((data) => {
            const latlon = data.lat_lon;
            fetch(
              `https://api.openweathermap.org/data/2.5/weather?lat=${latlon[0]}&lon=${latlon[1]}&appid=${api}`
            )
              .then((response) => response.json())
              .then((data) => {
                const temp = Math.round(data.main.temp - 273.15); // Convert temperature from Kelvin to Celsius
                const weatherDiv = document.querySelector(".weather-section");
                weatherDiv.innerHTML = `Temperature: ${temp} &deg;C`;
              });
          });
      });

      // Hides the elements with the class "hidden"
      var hiddenElements = document.querySelectorAll(".hidden");
      for (var i = 0; i < hiddenElements.length; i++) {
        hiddenElements[i].style.display = "none";
      }
      console.log(request2.responseText);
    };

    // preparing the data to be sent using an AJAX request
    var data = "name=" + encodeURIComponent(name);
    // console.log(name);
    request2.open("POST", url);
    request2.setRequestHeader(
      "Content-type",
      "application/x-www-form-urlencoded"
    );
    // request2.send(data);
    request2.send(data);
  });
}
