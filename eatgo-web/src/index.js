(async () => {
    const url='http://localhost:8080/restaurants';
    const response=await fetch(url);
    const restaurants= await  response.json();
    console.log(restaurants);
    const element=document.getElementById("app");
    <!-- element.innerHTML=JSON.stringify(restaurants); -->
    <!-- step2 -->
    <!--
    element.innerHTML=`

            ${restaurants[0].id}
            ${restaurants[0].name}
            ${restaurants[0].address} `
   -->
    element.innerHTML=`
        ${restaurants.map(restaurant => `  
            <p>      
                ${restaurant.id}
                ${restaurant.name}
                ${restaurant.address}
            </p>
        `).join('')}
    `
    })();