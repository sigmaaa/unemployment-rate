        function showLineChart(allCountriesRate) {

           $('#lineChart').remove(); // this is my <canvas> element
           $('#lineChartContainer').append('<canvas id="lineChart"><canvas>');
           countryName = document.getElementById('country-select').value;
           let chart = document.getElementById('lineChart').getContext('2d');
           // Global Options
           Chart.defaults.global.defaultFontFamily = 'Lato';
           Chart.defaults.global.defaultFontSize = 18;
           Chart.defaults.global.defaultFontColor = '#777';
           var chartData = [];
           var chartLabels = [];
           allCountriesRate.forEach(myFunction);
           function myFunction(item) {
                if (item["countryName"] == countryName) {
                    chartData.push(item["unemploymentRate"])
                    chartLabels.push(item["year"])
                }
            }

           let countriesStatChart = new Chart(chart, {
             type:'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
             data:{
               labels: chartLabels,
               datasets:[{
                 label:[],
                 data: chartData,
                 pointRadius: 6,
                 fill: false,
                 backgroundColor: 'rgb(253, 201, 125)',
                 borderWidth:1,
                 borderColor:'rgb(253, 201, 125)',
                 hoverBorderWidth:3,
                 hoverBorderColor:'#000'
               }]
             },
             options:{
               title:{
                 display:true,
                 text:'Unemployment rate (' + countryName + ')',
                 fontSize:25
               },
               legend:{
                 display:false,
                 position:'right',
                 labels:{
                   fontColor:'#000'
                 }
               },
               scales: {
                   xAxes: [{
                     scaleLabel: {
                       display: true,
                       labelString: "Years",
                     }
                   }],
                   yAxes: [{
                     scaleLabel: {
                       display: true,
                       labelString: "Unemployment rate [%]",
                     }
                   }]
                 },
               layout:{
                 padding:{
                   left:50,
                   right:0,
                   bottom:0,
                   top:0
                 }
               },
               tooltips:{
                 enabled:true
               }
             }
           });
       }