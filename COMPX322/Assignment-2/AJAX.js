// Define the commodity object
function Commodity(id, name, information, code) {
  this.id = id;
  this.name = name;
  this.information = information;
  this.code = code;
}

// Create an array to store the commodities
var commodityList = [];
var widgets = [];
var tempname;

// Fetch the commodities data and build the form
function setUpForm() {
  fetch("AccessCommodities.php")
    .then((response) => response.json())
    .then(buildForm)
    .catch((error) => console.error(error));
}

// Function to sort commodities by name
function sortByName(a, b) {
  const nameA = a.name.toUpperCase();
  const nameB = b.name.toUpperCase();
  if (nameA < nameB) {
    return -1;
  }
  if (nameA > nameB) {
    return 1;
  }
  return 0;
}

// Build the form options from the fetched commodities data
function buildForm(options) {
  const optionsSelect = document.getElementById("options-select");
  for (let i = 0; i < options.length; i++) {
    let c = new Commodity(
      options[i].id,
      options[i].name,
      options[i].information,
      options[i].code
    );
    commodityList.push(c);
  }
  commodityList.sort(sortByName);

  // Populate drop down list
  for (let j = 0; j < commodityList.length; j++) {
    const option = document.createElement("option");
    option.setAttribute("id", commodityList[j].id);
    option.text = commodityList[j].name;
    option.value = commodityList[j].id;
    optionsSelect.appendChild(option);
  }

  // Checking if widget exists or not
  optionsSelect.addEventListener("change", function () {
    const selected = optionsSelect.options[optionsSelect.selectedIndex];
    const selectedOption = selected.value;

    if (widgets.includes(selectedOption)) {
      return;
    }
    widgets.push(selectedOption);
    const widget = makeWidget(selectedOption);
    document.getElementById("widget-container").appendChild(widget);
    // find the element in the commodity array
    let c = commodityList.find(
      (commodity) => commodity.id === parseInt(selectedOption)
    );
    tempname = c.name;
  });
}

const makeWidget = (selectedOption) => {
  // find the element in the commodity array
  let c = commodityList.find(
    (commodity) => commodity.id === parseInt(selectedOption)
  );
  const widgetContainer = document.createElement("div");
  widgetContainer.classList.add("widget");

  const title = document.createElement("h2");
  title.textContent = c.name;

  widgetContainer.appendChild(title);

  const description = document.createElement("p");
  description.textContent = c.information;
  widgetContainer.appendChild(description);

  return widgetContainer;
};
