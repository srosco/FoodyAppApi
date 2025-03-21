

// body raw values as JSON


/* POST */ "http://localhost:9000/api/user/create"
  // {
  //     "first_name": "John",
  //     "last_name": "Doe",
  //     "email": "john.doe@example.com",
  //     "current_maccros": 1500,
  //     "aimed_maccros": 2000,
  //     "cartId": 1,
  //     "password": "password123"
  //   }

  // {
  //   "first_name": "John",
  //   "last_name": "Doe",
  //   "email": "john.doe555591@example.com",
  // //   "current_maccros": 1500,
  // //   "aimed_maccros": 2000,
  // //   "cartId": 1,
  //   "password": "password123"
  // }
  
  /* GET all users */ "http://localhost:9000/api/user"

  /* GET specific user with id */ "http://localhost:9000/api/user/{id}"
  
  /* DELETE specific user with id */ "http://localhost:9000/api/user/{id}"

  /* POST */ "http://localhost:9000/api/products"
  
  // {
    //   "name": "Healthy Salad",
    //   "category": "Salad",
    //   "proteins": 5.2,
    //   "fibers": 4.1,
    //   "calories": 120.5,
    //   "carbohydrates": 20.3
    // }
    // {
    //   "id": 1,
    //   "name": "Chicken",
    //   "category": "Protein",
    //   "proteins": 31.0,
    //   "fibers": 0.0,
    //   "calories": 165.0,
    //   "carbohydrates": 0.0
    // },
    
    /* GET */ "http://localhost:9000/api/products"
    
    /* GET search products through ID */ "http://localhost:9000/api/products/{id}"
    
    /* DELETE */ "http://localhost:9000/api/products/{id}"

    /* 
    {
  "name": "Test Cart",
  "creation_date": "17/03/2025",
  "total_carbohydrates": 10,
  "total_calories": 100,
  "total_fibers": 5,
  "total_proteins": 20,
  "user_id": 1,
  "products": [
    {
      "id": 1,
      "name": "Product 1",
      "category": "Category 1",
      "proteins": 5,
      "fibers": 1,
      "calories": 50,
      "carbohydrates": 10
    },
    {
      "id": 2,
      "name": "Product 2",
      "category": "Category 2",
      "proteins": 10,
      "fibers": 2,
      "calories": 60,
      "carbohydrates": 15
    }
  ]
}
    */
