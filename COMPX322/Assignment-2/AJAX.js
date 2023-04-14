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
  // Create chart container
  const chartContainer = document.createElement("div");
  chartContainer.classList.add("container");

  // Append chart container to the page
  document.getElementById("chart-container").appendChild(chartContainer);
}

// --------------------- Creates widget for each commodity -----------------------
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

  const removeBtn = document.createElement("button");
  removeBtn.textContent = "Remove";
  removeBtn.addEventListener("click", () => {
    widgetContainer.remove();
    const index = widgets.indexOf(selectedOption);
    if (index > -1) {
      widgets.splice(index, 1);
    }
  });
  widgetContainer.appendChild(removeBtn);

  // ----------- Add Graph Button -------------------
  const graphBtn = document.createElement("button");
  graphBtn.textContent = "Graph";
  graphBtn.addEventListener("click", () => {
    console.log("Clicked commodity:", c.name);
    getApi(c.name, (response) => {
      try {
        console.log(response);
        const data = JSON.parse(response);
        console.log(data);
        const chartData = data.data;

        const chartConfig = {
          type: "line",
          data: {
            labels: chartData.map((item) => item.date),
            datasets: [
              {
                label: c.name,
                data: chartData.map((item) => item.value),
                borderColor: getRandomColor(),
                fill: false,
              },
            ],
          },
        };

        const chartContainer = document.getElementById("chart-container");
        let canvas = chartContainer.querySelector("canvas");
        if (canvas) {
          canvas.remove();
        }
        canvas = document.createElement("canvas");
        chartContainer.appendChild(canvas);
        const ctx = canvas.getContext("2d");
        const myChart = new Chart(ctx, chartConfig);
      } catch (error) {
        console.error(error);
      }
    });
  });

  widgetContainer.appendChild(graphBtn);

  // ------------------- add compartive graph button -----------------------
  const compareGraphBtn = document.createElement("button");
  compareGraphBtn.textContent = "Add to Graph";
  widgetContainer.appendChild(compareGraphBtn);

  compareGraphBtn.addEventListener("click", () => {
    console.log("Clicked Add to Graph button");

    getApi(c.name, (response) => {
      try {
        console.log(response);
        const secondData = JSON.parse(response).data;

        // Get the existing chart instance
        const chartContainer = document.getElementById("chart-container");
        const canvas = chartContainer.querySelector("canvas");
        const ctx = canvas.getContext("2d");
        const chart = Chart.instances[0];

        // Add the new dataset to the existing chart
        chart.data.datasets.push({
          label: c.name,
          data: secondData.map((item) => item.value),
          borderColor: getRandomColor(),
          fill: false,
        });

        // Update the chart
        chart.update();
      } catch (error) {
        console.error(error);
      }
    });
  });

  return widgetContainer;
};

// -------------------- fetch request to AlphaVantage API -------------------------
const getApi = (COM, callback) => {
  const url = "graph.php?Commodities=" + encodeURIComponent(COM);
  const options = {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  };

  fetch(url, options)
    .then((response) => response.text())
    .then(callback);
};

function callback(response) {
  console.log(response);
}

// Generates random colors for the graph
function getRandomColor() {
  const red = Math.floor(Math.random() * 256);
  const green = Math.floor(Math.random() * 256);
  const blue = Math.floor(Math.random() * 256);
  const rgb = `rgb(${red}, ${green}, ${blue})`;
  return rgbToHex(rgb);
}

function rgbToHex(rgb) {
  const matches = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
  const hex = matches
    ? "#" +
      matches
        .slice(1)
        .map((component) => parseInt(component).toString(16).padStart(2, "0"))
        .join("")
    : rgb;
  return hex;
}
