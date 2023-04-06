// AJAX GET request to commodity databse
var request = new XMLHttpRequest();
let getDataAjax = () => {
  url = "AccessCommodities.php";
  request.onload = displayData;
  request.open("GET", url);
  request.send("");
};

let displayData = () => {
  var response = request.responseText;
  theDivElement = document.getElementById("data");
  theDivElement.innerHTML = response;
};

// Storying data into an 'Array of Object Literals'
let commodityData = function () {
  var commodity = {
    id: [1, 2, 3, 4, 5, 6, 7],
    name: ["Corn", "Cotton", "Sugar", "Copper", "Aluminium", "Wheat", "Coffee"],
    information: [
      "Global price of Corn",
      "Global price of Cotton",
      "Global price of Sugar, No. 11, World",
      "Global price of Copper",
      "Global price of Aluminium",
      "Global price of Wheat",
      "Global price of Coffee, Other Mild Arabica",
    ],
    code: ["CORN", "COTTON", "SUGAR", "COPPER", "ALUMINIUM", "WHEAT", "COFFEE"],
  };

  commodity.name = commodity.name.sort(function (a, b) {
    var nameA = a.toUpperCase();
    var nameB = b.toUpperCase();
    if (nameA < nameB) {
      return -1;
    }
    if (nameA > nameB) {
      return 1;
    }
    return 0;
  });

  // Populate commodity drop-down
  var dropDown = document.getElementById("selectCommodity");
  for (var i = 0; i < commodity.name.length; i++) {
    var option = document.createElement("option");
    option.text = commodity.name[i];
    dropDown.add(option);
  }
  return commodity;
};

window.onload = function () {
  commodityData();
};

// Creating commodities widget
function commoditiesWidget(page_element, initial_data) {
  var _data = new Array();
  // anything we append to container will be displayed on the page
  var _container = page_element;

  var deleteButton = function (label, index, click_action) {
    var _dom_element = document.createElement("input");
    _dom_element.type = "button";
    _dom_element.value = label;

    _dom_element.onclick = function () {
      click_action.call(null, index);
    };

    // Put this on the page
    this.getDomElement = function () {
      return _dom_element;
    };
  };
}
