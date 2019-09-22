# AmposTest

### **Logic Test** 

Package : com.ampos.test.logic

    -Easy1
    -Easy2
    -Moderate1
    -Moderate2

### **RESTful Test** 

Pagkage : com.ampos.test.restaurant

`Start Spring application : com.ampos.test.restaurant.Application`

### **End Point**

**-------------MENU-------------**

**GET:** `localhost:1150/api/menus/initial/data`
````
reponse :

[
    {
        "id": 1,
        "name": "Sheet Pan Chicken",
        "description": "One-dish dinners are a dream come true for busy cooks.",
        "image": null,
        "price": 300,
        "additionDetails": "chicken, sheet, powder"
    },
    {
        "id": 2,
        "name": "Shrimp Lo Mein",
        "description": "Lo mein—a Chinese dish that means stirred noodles—is a standard takeout order for many Americans, but making it at home is an opportunity to not only expand your culinary repertoire",
        "image": null,
        "price": 1050,
        "additionDetails": "Shrimp, oil"
    },
    {
        "id": 3,
        "name": "Lemony Lentil Soup",
        "description": "In this simple soup recipe, you’ll simmer everyday ingredients like carrots, onion, garlic, and spices with red lentils, which are the perfect soup legume since they break down into soft submission.",
        "image": null,
        "price": 50,
        "additionDetails": "lemon, water"
    },
    {
        "id": 4,
        "name": "Chicken Curry in a Hurry",
        "description": "Wait a minute, you might be thinking. How does curry make a list of quick dinner ideas? Our hurried-up version uses rotisserie chicken to save time, and the meal’s ready in just over half and hour.",
        "image": null,
        "price": 750,
        "additionDetails": "chicken, curry"
    }
]
````


**GET:** `localhost:1150/api/menus?search=additionDetails:Chicken`
````
reponse :
 [
     {
         "id": 1,
         "name": "Sheet Pan Chicken",
         "description": "One-dish dinners are a dream come true for busy cooks.",
         "image": null,
         "price": 300.00,
         "additionDetails": "chicken, sheet, powder"
     },
     {
         "id": 4,
         "name": "Chicken Curry in a Hurry",
         "description": "Wait a minute, you might be thinking. How does curry make a list of quick dinner ideas? Our hurried-up version uses rotisserie chicken to save time, and the meal’s ready in just over half and hour.",
         "image": null,
         "price": 750.00,
         "additionDetails": "chicken, curry"
     }
 ]
````

**-------------BILL-------------**

**POST:** `localhost:1150/api/bills`

SAVE
````
request :
{
    "orderMenus": [
        {
            "menuId": 1,
            "quantity": 2
        } 
        ,
         {
            "menuId": 1,
            "quantity": 1
        } 
    ],
    "orderedTime": null
}
reponse :
  [
      {
          "id": 3,
          "billNo": 2,
          "quantity": 2,
          "orderedTime": "2019-09-22T13:15:05.380+0000",
          "menuId": 1
      },
      {
          "id": 4,
          "billNo": 2,
          "quantity": 1,
          "orderedTime": "2019-09-22T13:15:05.380+0000",
          "menuId": 1
      }
  ]
````

UPDATE

**PUT:** `localhost:1150/api/bills`
````
request :
{{
 	"billNo": 2,
     "orderMenus": [
         {
             "menuId": 1,
             "quantity": 2
         } 
         ,
          {
             "menuId": 1,
             "quantity": 10
         } ,
          {
             "menuId": 4,
             "quantity": 1
         } 
     ],
     "orderedTime": null
 }
reponse :
 [
     {
         "id": 9,
         "billNo": 2,
         "quantity": 2,
         "orderedTime": "2019-09-22T13:16:52.773+0000",
         "menuId": 1
     },
     {
         "id": 10,
         "billNo": 2,
         "quantity": 10,
         "orderedTime": "2019-09-22T13:16:52.773+0000",
         "menuId": 1
     },
     {
         "id": 11,
         "billNo": 2,
         "quantity": 1,
         "orderedTime": "2019-09-22T13:16:52.773+0000",
         "menuId": 4
     }
 ]
````


DELETE

**DELETE:** `localhost:1150/api/bills/{1}`
````
reponse : 1
````


CheckBill

**GET:** `localhost:1150/api/bills/checkbill`
````
reponse : 
[
    {
        "billNo": 1,
        "totalPrice": 900.00,
        "orderMenus": [
            {
                "menuId": 1,
                "quantity": 2,
                "price": 300.00,
                "menuName": "Sheet Pan Chicken"
            },
            {
                "menuId": 1,
                "quantity": 1,
                "price": 300.00,
                "menuName": "Sheet Pan Chicken"
            }
        ]
    }
]
````
