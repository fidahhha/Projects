let makePie = function() {

    //sets up the data object which is one of the properties required for the config object
    //this example uses hard-coded values
    const data = {
        labels: ["Pizza", "Burgers", "Fries"],
        datasets: [
            {
            label: "Lunch Options Survey",
            data: [300, 50, 100],
            backgroundColor: ["rgb(255, 99, 132)", "rgb(54, 162, 235)", "rgb(255, 205, 86)"],
        },
    ],
    };

    //object literal with config information required to build the chart
    const config = {
        type: "pie",
        data: data,
    };

    //create an instance of the Chart object, first parameter is the canvas element where
    //the chart will be displayed, second parameter is the config information for the chart
    var myChart = new Chart(
        document.getElementById('myChart'),
        config
    );
    }