
    function showChart(allCountriesRate, topBestCountries, topWorstCountries) {
        let myChart = document.getElementById('myChart').getContext('2d');

        // Global Options
        Chart.defaults.global.defaultFontFamily = 'Lato';
        Chart.defaults.global.defaultFontSize = 18;
        Chart.defaults.global.defaultFontColor = '#777';
        var myData = [];
        var myLabels = [];
        var bestCountries= Object.keys(topBestCountries);
        var worstCountries = Object.keys(topWorstCountries);

        keysSorted = Object.keys(allCountriesRate).sort(function(a,b){return allCountriesRate[a]-allCountriesRate[b]});
        keysSorted.forEach(function (entry) {
            myLabels.push(entry);
            myData.push(allCountriesRate[entry]);
        });
        let countriesStatChart = new Chart(myChart, {
          type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
          data:{
            labels: myLabels,
            datasets:[{
              label:[],
              data: myData,
              backgroundColor: myLabels.map(function(country) {
                if(country.includes(bestCountries[0]) || country.includes(bestCountries[1]) || country.includes(bestCountries[2])) {
                    return 'rgb(135, 211, 234)';
                 } else if (country.includes(worstCountries[0]) || country.includes(worstCountries[1]) || country.includes(worstCountries[2])) {
                    return 'rgb(243, 125, 137)';
                 } else {
                    return "rgb(253, 201, 125)";
                }}),
              borderWidth:1,
              borderColor:'#777',
              hoverBorderWidth:3,
              hoverBorderColor:'#000'
            }]
          },
          options:{
            title:{
              display:true,
              text:'Unemployment rate in the OECD countries 2014',
              fontSize:25
            },
            legend:{
              display:false,
              position:'right',
              labels:{
                fontColor:'#000'
              }
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
